package com.hsshy.beam.config;
import com.hsshy.beam.common.config.DefaultFastjsonConfig;
import com.hsshy.beam.config.properties.ConsumerProperties;
import com.hsshy.beam.interceptors.AppInterceptor;
import com.hsshy.beam.interceptors.converter.WithSignMessageConverter;
import com.hsshy.beam.interceptors.security.DataSecurityAction;
import com.hsshy.beam.interceptors.security.impl.Base64SecurityAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC配置
 *
 * @author hs
 * @date 2019-03-13 15:42
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AppInterceptor appInterceptor;

    @Bean
    public DataSecurityAction dataSecurityAction() {
        return new Base64SecurityAction();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(appInterceptor).addPathPatterns("/**");
    }

    @Bean
    @ConditionalOnProperty(prefix = ConsumerProperties.CONSUMER_PREFIX, name = "sign-open", havingValue = "true", matchIfMissing = true)
    public WithSignMessageConverter withSignMessageConverter() {
        WithSignMessageConverter withSignMessageConverter = new WithSignMessageConverter();
        DefaultFastjsonConfig defaultFastjsonConfig = new DefaultFastjsonConfig();
        withSignMessageConverter.setFastJsonConfig(defaultFastjsonConfig.fastjsonConfig());
        withSignMessageConverter.setSupportedMediaTypes(defaultFastjsonConfig.getSupportedMediaType());
        return withSignMessageConverter;
    }


}