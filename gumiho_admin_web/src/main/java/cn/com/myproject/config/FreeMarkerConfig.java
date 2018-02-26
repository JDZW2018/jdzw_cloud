package cn.com.myproject.config;

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

    @Value("${server.context-path}")
    private String contextPath;

    @PostConstruct
    public void  init() throws TemplateModelException {
        if("/".equals(contextPath)) {
            contextPath = "";
        }
        configuration.setSharedVariable("ctx",contextPath);
    }

}
