package com.jd.app.server.manager.jsfApi.api;

import com.jd.app.server.manager.ducc.DuccAvailConfigManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PartitionRpcServiceApi {

    @Resource
    private DuccAvailConfigManager duccAvailConfigManager;

    public Map<String, Map<String, String>> findProductsMix(List<String> skuList, String area) {
        //ʾ������, ����Ϊrpc�������
        return null;
    }
}
