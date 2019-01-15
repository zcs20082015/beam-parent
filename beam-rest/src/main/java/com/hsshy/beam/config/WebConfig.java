package com.hsshy.beam.config;
import com.hsshy.beam.config.properties.BeamRestProperties;
import com.hsshy.beam.filter.AuthFilter;
import com.hsshy.beam.filter.security.DataSecurityAction;
import com.hsshy.beam.filter.security.impl.Base64SecurityAction;
import com.hsshy.beam.interceptors.AppInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置
 *
 * @author fengshuonan
 * @date 2017-08-23 15:48
 */
@Configuration
public class WebConfig {


    @Bean
    @ConditionalOnProperty(prefix = BeamRestProperties.BEAM_REST_PREFIX, name = "auth-f-open", havingValue = "true", matchIfMissing = true)
    public AuthFilter jwtAuthenticationTokenFilter() {
        return new AuthFilter();
    }

    @Bean
    public DataSecurityAction dataSecurityAction() {
        return new Base64SecurityAction();
    }






}
