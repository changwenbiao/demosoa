package com.jd.app.demo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class BftResult implements Serializable {

    private String code;

    private String msg;

    private Map<String, BftInfo> resultMap = new HashMap<String, BftInfo>();

    public BftResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BftResult(String code, String msg, Map<String, BftInfo> resultMap) {
        this.code = code;
        this.msg = msg;
        this.resultMap = resultMap;
    }

    public static BftResult error(String msg) {
        return new BftResult("-1", msg);
    }

    public static BftResult success(Map<String, BftInfo> resultMap) {
        return new BftResult("0", "success", resultMap);
    }

    public BftInfo getValue(String k) {
        if (resultMap == null) {
            resultMap = new HashMap<String, BftInfo>();
        }
        return this.resultMap.get(k);
    }

    public BftInfo putValue(String k, BftInfo v) {
        return this.resultMap.put(k, v);
    }
}
