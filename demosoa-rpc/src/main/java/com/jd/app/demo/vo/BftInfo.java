package com.jd.app.demo.vo;

import com.jd.app.demo.vo.benefit.BaseInfo;
import com.jd.app.demo.vo.benefit.CommonInfo;
import lombok.Data;

import java.io.Serializable;

@Data
public class BftInfo implements Serializable {

    /**
     * 基本信息
     */
    private BaseInfo baseInfo = new BaseInfo();

    /**
     * 商品公共属性
     */
    private CommonInfo commonInfo = new CommonInfo();
}
