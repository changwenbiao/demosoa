package com.jd.app.server.service.demo.handler;

import com.jd.app.demo.vo.BftInfo;
import com.jd.app.demo.vo.SeckillBenefitRequest;
import com.jd.app.demo.vo.SeckillSkuInfo;
import com.jd.app.server.manager.ducc.DuccAvailConfigManager;
import com.jd.app.server.service.core.MiaoShaEvent;
import com.jd.sirector.EventHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractBenefitHandler implements EventHandler<MiaoShaEvent> {
    protected String bizName;
    protected SeckillBenefitRequest seckillBenefitRequest;

    protected String switchKey;
    protected DuccAvailConfigManager duccAvailConfigManager;

    public AbstractBenefitHandler setBenefitRequestAndBizName(SeckillBenefitRequest bigSeckillRequestNew, String bizName) {
        this.bizName = bizName;
        this.seckillBenefitRequest = bigSeckillRequestNew;
        return this;
    }

    @Override
    public void onEvent(MiaoShaEvent miaoShaEvent) {
        //监控代码：开始
        try {
            List<String> skuList = getSkuList();
            if (skuList.isEmpty()) {
                return;
            }
            onEvent0();
        } catch (Exception e) {
            log.error("aggregation_soa.execute.benefit_handler." + this.getClass().getSimpleName() + "." + bizName, e);
            //监控代码：异常
        } finally {
            //监控代码：结束
        }
    }

    protected abstract void onEvent0() throws Exception;

    public abstract void fillResponseInfo(List<BftInfo> bftInfoList);

    public boolean isSwitchOn() {
        return duccAvailConfigManager.getBoolean(switchKey, true);
    }

    protected void log(String format, Object... arg) {
    }

    protected Set<Long> getSkuSet() {
        return seckillBenefitRequest.getSeckillSkuInfos()
                .stream().filter(Objects::nonNull)
                .map(SeckillSkuInfo::getSkuId)
                .filter(Objects::nonNull)
                .map(String::trim)
                .map(Long::parseLong)
                .collect(Collectors.toSet());
    }

    protected List<String> getSkuList() {
        return seckillBenefitRequest.getSeckillSkuInfos()
                .stream().filter(Objects::nonNull)
                .map(SeckillSkuInfo::getSkuId)
                .filter(Objects::nonNull)
                .map(String::trim)
                .collect(Collectors.toList());
    }
}