package com.jd.app.server.domain.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class EventProcessThreadPool extends ThreadPoolExecutor {

    public EventProcessThreadPool() {
        super(Runtime.getRuntime().availableProcessors() * 2, Runtime.getRuntime().availableProcessors() * 30, 0L, TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(1000), new CallerRunsPolicy());
    }

    public EventProcessThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), Executors.defaultThreadFactory());
    }
}
