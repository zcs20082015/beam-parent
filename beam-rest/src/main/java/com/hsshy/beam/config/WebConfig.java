package com.hsshy.beam.config;
import com.hsshy.beam.config.properties.BeamRestProperties;
import com.hsshy.beam.modular.auth.AuthFilter;
import com.hsshy.beam.modular.auth.security.DataSecurityAction;
import com.hsshy.beam.modular.auth.security.impl.Base64SecurityAction;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * web配置
 *
 * @author fengshuonan
 * @date 2017-08-23 15:48
 */
@Configuration
public class WebConfig {

    @Bean
    @ConditionalOnProperty(prefix = BeamRestProperties.BEAM_REST_PREFIX, name = "auth-open", havingValue = "true", matchIfMissing = true)
    public AuthFilter jwtAuthenticationTokenFilter() {
        return new AuthFilter();
    }

    @Bean
    public DataSecurityAction dataSecurityAction() {
        return new Base64SecurityAction();
    }

}
