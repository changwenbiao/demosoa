package com.jd.app.demo.jsf;

import com.jd.app.demo.vo.BftResult;
import com.jd.app.demo.vo.SeckillBenefitRequest;

public interface BenefitAggregationService {
    BftResult getBenefitInfoList(SeckillBenefitRequest bigSeckillRequestNew);
}
