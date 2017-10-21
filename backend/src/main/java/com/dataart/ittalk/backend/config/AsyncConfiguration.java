package com.dataart.ittalk.backend.config;

import com.google.appengine.api.ThreadManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        return new SimpleAsyncTaskExecutor(ThreadManager.backgroundThreadFactory());
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new CustomAsyncExceptionHandler();
    }

    public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

        private final Log logger = LogFactory.getLog(CustomAsyncExceptionHandler.class);

        @Override
        public void handleUncaughtException(
                Throwable throwable, Method method, Object... obj) {

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("Exception message - ");
            stringBuilder.append(throwable.getMessage());
            stringBuilder.append(" | ");
            stringBuilder.append("Method name - ");
            stringBuilder.append(method.getName());
            stringBuilder.append(" | ");
            for (Object param : obj) {
                stringBuilder.append("Parameter value - ");
                stringBuilder.append(param);
                stringBuilder.append(" | ");
            }

            logger.error(stringBuilder.toString());
        }

    }

}
