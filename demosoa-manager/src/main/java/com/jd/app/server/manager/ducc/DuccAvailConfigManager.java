package com.jd.app.server.manager.ducc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DuccAvailConfigManager {

    public String get(String configKey) {
        //示例代码
        return null;
    }

    public Boolean getBoolean(String configKey, Boolean defaultVal) {
        //示例代码
        return null;
    }

    public Integer getInteger(String configKey, Integer defaultVal) {
        //示例代码
        return null;
    }

    public String getString(String configKey, String defaultVal) {
        //示例代码
        return null;
    }

    public <T> List<T> getList(String configKey, List<T> defaultVal, Class<T> tClass) {
        //示例代码
        return null;
    }
}