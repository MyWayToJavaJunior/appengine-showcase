package com.dataart.ittalk.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@Profile("appEngine")
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(appEngineQueueHeaderChecker())
                .addPathPatterns("/*");
    }

    @Bean
    public HandlerInterceptor appEngineQueueHeaderChecker() {
        return new AppEngineQueueHeaderChecker();
    }

    public class AppEngineQueueHeaderChecker implements HandlerInterceptor {

        // This method is called before the controller
        @Override
        public boolean preHandle(HttpServletRequest request,
                                 HttpServletResponse response, Object handler) throws Exception {
            String xHeader = request.getHeader("X-Appengine-Cron");
            if (xHeader != null && xHeader.equals("true")) {
                return true;
            } else {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return false;
            }
        }

        @Override
        public void postHandle(HttpServletRequest request,
                               HttpServletResponse response, Object handler,
                               ModelAndView modelAndView) throws Exception {
            // Nothing to do
        }

        @Override
        public void afterCompletion(HttpServletRequest request,
                                    HttpServletResponse response, Object handler, Exception ex)
                throws Exception {
            // Nothing to do
        }

    }

}
