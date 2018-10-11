package cn.com.myproject.etc.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class Snowflake {

    public static long workerId;

    public static long datacenterId;

    @Value("${snowflake.workerId}")
    public void setWorkerId(long workerId) {
        Snowflake.workerId = workerId;
    }

    @Value("${snowflake.datacenterId}")
    public void setDatacenterId(long datacenterId) {
        Snowflake.datacenterId = datacenterId;
    }

    public static long getWorkerId() {
        return workerId;
    }

    public static long getDatacenterId() {
        return datacenterId;
    }
    @Override
    public String toString(){
        return "value:[workerId:"+workerId+"],[datacenterId:"+datacenterId+"],";
    }


}
