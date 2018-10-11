package cn.com.myproject.etc.sms.test;

import cn.com.myproject.etc.factory.DataSourceConfig;
import cn.com.myproject.etc.factory.Snowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tianfusheng
 * @date 2018/4/3
 */
@RestController
public class configClientTestController {

    @Autowired
    private Snowflake snowflake;
    @GetMapping(value = "snowflake")
    public String snowflake(){
        return snowflake.toString();
    }

    @Autowired
    private DataSourceConfig dataSourceConfig;
    @GetMapping(value = "dataSourceConfig")
    public String dataSourceConfig(){
        return dataSourceConfig.toString();
    }


}
