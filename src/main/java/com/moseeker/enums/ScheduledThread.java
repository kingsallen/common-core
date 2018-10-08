package com.moseeker.enums;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zztaiwll on 18/9/17.
 */
public enum ScheduledThread {
    Instance;
    ScheduledExecutorService executorService;

    private ScheduledThread(){
        init();
    }
    private void init() {
        executorService=Executors.newScheduledThreadPool(20);
    }
    public void close() {
        synchronized (executorService) {
            if(executorService != null) {
                this.executorService.shutdown();
            }
        }
    }
    public <T> Future<T> startTast(Runnable task, int time) {
        if(executorService == null) {
            synchronized (this) {
                init();
            }
        }
        return (Future<T>) this.executorService.schedule(task,time,TimeUnit.MILLISECONDS);
    }
}
