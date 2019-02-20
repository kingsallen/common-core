package com.moseeker.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConfigShutDownGracefully implements DisposableBean, ExitCodeGenerator {
    @Override
    public void destroy() throws Exception {

        log.debug("<<<<<<<<<<<即将销毁, 先等待60s使现有请求完成响应......................>>>>>>>>>>>>>>>");
        Thread.sleep(60*1000);
        log.debug("<<<<<<<<<<<已销毁了......................>>>>>>>>>>>>>>>");
    }

    @Override
    public int getExitCode() {
        return 0;
    }
}
