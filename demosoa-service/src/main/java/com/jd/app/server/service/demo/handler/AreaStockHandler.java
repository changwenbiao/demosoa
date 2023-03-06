package com.jd.app.server.service.demo.handler;

import com.jd.app.demo.vo.BftInfo;
import com.jd.app.server.manager.ducc.DuccAvailConfigManager;
import com.jd.app.server.manager.jsfApi.api.AreaStockStateExportApi;
import com.jd.common.util.StringUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.List;
import java.util.Map;


public class AreaStockHandler extends AbstractBenefitHandler {

    private final AreaStockStateExportApi areaStockStateExportApi;

    private Map<String, Map<String, String>> areaStockMap;


    public AreaStockHandler(String switchKey,
                            DuccAvailConfigManager duccAvailConfigManager,
                            AreaStockStateExportApi areaStockStateExportApi) {
        this.switchKey = switchKey;
        this.duccAvailConfigManager = duccAvailConfigManager;
        this.areaStockStateExportApi = areaStockStateExportApi;
    }

    @Override
    public boolean isSwitchOn() {
        boolean superSwitchOn = super.isSwitchOn();
        if (!superSwitchOn) {
            return false;
        } else {
            //����Ϊ�ļ���ַ����������ļ���رյ���
            String area = seckillBenefitRequest.getSeckillParam().getArea();
            return !StringUtils.isBlank(area)
                    && area.contains("_")
                    && area.split("_").length >= 4;
        }
    }

    @Override
    protected void onEvent0() throws Exception {
        String area = seckillBenefitRequest.getSeckillParam().getArea();

        //ʾ�����룬����rpc��ȡ�����Ϣ, skuList��area�Ǳ�Ҫ������areaStockMap keyΪsku
        this.areaStockMap = areaStockStateExportApi.getAreaStockResult(getSkuList(), area);
    }

    @Override
    public void fillResponseInfo(List<BftInfo> bftInfoList) {
        if (MapUtils.isNotEmpty(areaStockMap)) {
            for (BftInfo result : bftInfoList) {
                String skuId = result.getBaseInfo().getSkuId();
                if (areaStockMap.containsKey(skuId)) {
                    result.getCommonInfo().setAreaStock(areaStockMap.get(skuId));
                }
            }
        }
    }
}
