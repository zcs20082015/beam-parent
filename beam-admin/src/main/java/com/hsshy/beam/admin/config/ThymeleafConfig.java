package com.hsshy.beam.admin.config;
import com.hsshy.beam.admin.core.util.KaptchaUtil;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class ThymeleafConfig {

    @Resource
    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        if(viewResolver != null) {
            Map<String, Object> vars = new HashMap<>();
            vars.put("ctx", "/app/");
            vars.put("var1", "var1");
            vars.put("var2", "var2");
            vars.put("kaptcha",new KaptchaUtil());
            viewResolver.setStaticVariables(vars);
        }
    }
}
