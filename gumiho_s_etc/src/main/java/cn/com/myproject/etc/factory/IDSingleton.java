package cn.com.myproject.etc.factory;

import cn.com.myproject.util.SnowflakeIdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IDSingleton {

    private static final Logger logger = LoggerFactory.getLogger(IDSingleton.class);

    private static volatile SnowflakeIdWorker instance = null;

    public static SnowflakeIdWorker getInstance() {
        if (instance == null) {
            synchronized (SnowflakeIdWorker.class) {
                if (instance == null) {
                    instance = new SnowflakeIdWorker(Snowflake.workerId,Snowflake.datacenterId);
                    logger.info("IDSingleton初始化,workerId:{},datacenterId:{}", Snowflake.workerId, Snowflake.datacenterId);
                }
            }
        }
        return instance;
    }
}
