package cn.com.myproject.adminuser.config;

import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by liyang-macbook on 2017/6/28.
 */
@Configuration
public class FreeMarkerConfig {
    @Autowired
    private freemarker.template.Configuration configuration;
    @Value("${ctx.value}")
    private  String ctx;
    @PostConstruct
    public void  init() throws TemplateModelException {

        configuration.setSharedVariable("ctx",ctx);
    }

}
