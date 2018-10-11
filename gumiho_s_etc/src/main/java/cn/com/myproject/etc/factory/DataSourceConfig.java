package cn.com.myproject.etc.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 数据库连接信息动态刷新
 * @author tianfusheng
 * @date 2018/4/3
 */
@Component
@RefreshScope
public class DataSourceConfig {
    @Value("${spring.datasource.name}")
    private String springDatasourceName;
    @Value("${spring.datasource.url}")
    private String SpringDatasourceUrl;
    @Value("${spring.datasource.username}")
    private String springDatasourceUsername;
    @Value("${spring.datasource.password}")
    private String springDatasourcePassword;
    @Value("${spring.datasource.type}")
    private String springDatasourceType;
    @Value("${spring.datasource.driver-class-name}")
    private String springDatasourceDriverClassName;

    public String getSpringDatasourceName() {
        return springDatasourceName;
    }

    public void setSpringDatasourceName(String springDatasourceName) {
        this.springDatasourceName = springDatasourceName;
    }

    public String getSpringDatasourceUrl() {
        return SpringDatasourceUrl;
    }

    public void setSpringDatasourceUrl(String springDatasourceUrl) {
        SpringDatasourceUrl = springDatasourceUrl;
    }

    public String getSpringDatasourceUsername() {
        return springDatasourceUsername;
    }

    public void setSpringDatasourceUsername(String springDatasourceUsername) {
        this.springDatasourceUsername = springDatasourceUsername;
    }

    public String getSpringDatasourcePassword() {
        return springDatasourcePassword;
    }

    public void setSpringDatasourcePassword(String springDatasourcePassword) {
        this.springDatasourcePassword = springDatasourcePassword;
    }

    public String getSpringDatasourceType() {
        return springDatasourceType;
    }

    public void setSpringDatasourceType(String springDatasourceType) {
        this.springDatasourceType = springDatasourceType;
    }

    public String getSpringDatasourceDriverClassName() {
        return springDatasourceDriverClassName;
    }

    public void setSpringDatasourceDriverClassName(String springDatasourceDriverClassName) {
        this.springDatasourceDriverClassName = springDatasourceDriverClassName;
    }

    @Override
    public String toString() {
        return "DataSourceConfig{" +
                "springDatasourceName='" + springDatasourceName + '\'' +
                ", SpringDatasourceUrl='" + SpringDatasourceUrl + '\'' +
                ", springDatasourceUsername='" + springDatasourceUsername + '\'' +
                ", springDatasourcePassword='" + springDatasourcePassword + '\'' +
                ", springDatasourceType='" + springDatasourceType + '\'' +
                ", springDatasourceDriverClassName='" + springDatasourceDriverClassName + '\'' +
                '}';
    }
}
