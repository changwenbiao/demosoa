package com.jd.app.demo.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeckillBenefitRequest implements Serializable {

    /**
     * sku集合
     */
    private List<SeckillSkuInfo> seckillSkuInfos;

    /**
     * 客户端信息
     */
    private SeckillParam seckillParam;

    private String token;

    private Map<String, Object> extMap;

    public SeckillBenefitRequest(){
        this.extMap = getDefaultExtMap();
    }

    public List<SeckillSkuInfo> getSeckillSkuInfos() {
        return seckillSkuInfos;
    }

    public void setSeckillSkuInfos(List<SeckillSkuInfo> seckillSkuInfos) {
        this.seckillSkuInfos = seckillSkuInfos;
    }

    public SeckillParam getSeckillParam() {
        return seckillParam;
    }

    public void setSeckillParam(SeckillParam seckillParam) {
        this.seckillParam = seckillParam;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, Object> getExtMap() {
        return extMap;
    }

    public void setExtMap(Map<String, Object> extMap) {
        Map<String, Object> tmpMap = getDefaultExtMap();
        if(extMap != null){
            tmpMap.putAll(extMap);
        }
        this.extMap = tmpMap;
    }

    private Map<String, Object> getDefaultExtMap(){
        Map<String, Object> defaultExtMap = new HashMap<String, Object>();
        return defaultExtMap;
    }
}
