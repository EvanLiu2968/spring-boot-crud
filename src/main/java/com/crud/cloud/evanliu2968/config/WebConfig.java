package com.crud.cloud.evanliu2968.config;

import com.crud.cloud.evanliu2968.interceptor.AuthorityInterceptor;
import com.crud.cloud.evanliu2968.interceptor.SqlInjectInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * 配置各种监听器，过滤器以及拦截器。
 *
 * @author guohao.yang
 * @version v1.0.0
 * @since 2019/5/5 16:23
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Autowired
    private AuthorityInterceptor authorityInterceptor;

    @Autowired
    private SqlInjectInterceptor sqlInjectInterceptor;

    private List<String> authPatterns = Arrays.asList(
            "/swagger-ui.html/**",
            "/null/swagger-resources/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/v2/**",
            "/error/**",
            "/pom/messageAlert/**",
            "/csrf/**"
    );

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //token拦截器顺序优先
        logger.info("===================add interceptor authority===================");
        registry.addInterceptor(this.authorityInterceptor).excludePathPatterns(this.authPatterns);
        registry.addInterceptor(this.sqlInjectInterceptor).excludePathPatterns(this.authPatterns);
    }

}
