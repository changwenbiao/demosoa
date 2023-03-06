package com.jd.app.demo.vo.benefit;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class CommonInfo implements Serializable {

    /**
     * 区域库存
     */
    private Map<String, String> areaStock;

    /**
     * 区域不支持配送
     */
    private Map<String, String> partitionResponse;
}
