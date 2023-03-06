package com.jd.app.server.service.demo.impl;

import com.google.common.collect.Lists;
import com.jd.app.demo.jsf.BenefitAggregationService;
import com.jd.app.demo.vo.BftInfo;
import com.jd.app.demo.vo.BftResult;
import com.jd.app.demo.vo.SeckillBenefitRequest;
import com.jd.app.demo.vo.SeckillSkuInfo;
import com.jd.app.demo.vo.benefit.BaseInfo;
import com.jd.app.server.content.tools.common.executor.EventProcessThreadPool;
import com.jd.app.server.service.demo.handler.AbstractBenefitHandler;
import com.jd.app.server.service.core.MiaoShaEvent;
import com.jd.sirector.Sirector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class BenefitAggregationServiceImpl implements BenefitAggregationService, ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Resource
    private EventProcessThreadPool bigSeckillEventProcessThreadPool;

    @Override
    public BftResult getBenefitInfoList(SeckillBenefitRequest request) {

        BftResult bftResult = null;
        try {

            List<String> handlerNames = Lists.newArrayList("areaStockHandler", "partitionProductsHandler");
            List<AbstractBenefitHandler> handlerList = handlerNames.stream()
                    .map(handlerName -> applicationContext.getBean(handlerName, AbstractBenefitHandler.class).setBenefitRequestAndBizName(request, "demoAppName"))
                    .filter(AbstractBenefitHandler::isSwitchOn).collect(Collectors.toList());
            if(handlerList.size() > 0){
                Sirector<MiaoShaEvent> sirector = new Sirector<>(bigSeckillEventProcessThreadPool);
                AbstractBenefitHandler[] eventHandlersArr = new AbstractBenefitHandler[handlerList.size()];
                handlerList.toArray(eventHandlersArr);
                sirector.begin(eventHandlersArr);
                sirector.ready();
                sirector.publish(new MiaoShaEvent(), 500);
                bftResult = createResponseInfos(request, handlerList);
            }
        } catch (Exception e){
            //TODO
        }

        return bftResult;
    }

    public BftResult createResponseInfos(SeckillBenefitRequest seckillBenefitRequest, List<AbstractBenefitHandler> handlerList) {
        BftResult bftResult = new BftResult("-1", "");
        try {
            List<BftInfo> bftInfoList = new ArrayList<>();
            for (SeckillSkuInfo request : seckillBenefitRequest.getSeckillSkuInfos()) {
                BftInfo bftInfo = new BftInfo();
                // 基本信息
                BaseInfo baseInfo = bftInfo.getBaseInfo();
                baseInfo.setSkuId(request.getSkuId());
                bftInfoList.add(bftInfo);
            }
            handlerList.forEach(h -> h.fillResponseInfo(bftInfoList));

            for (BftInfo result : bftInfoList) {
                String skuId = result.getBaseInfo().getSkuId();
                bftResult.putValue(skuId, result);
            }
            bftResult.setCode("0");
            bftResult.setMsg("success");
            return bftResult;
        } catch (Exception e) {
            log.error("createResponseInfos error: ", e);
        }
        return bftResult;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}