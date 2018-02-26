package cn.com.myproject.aliyun.oss;

import cn.com.myproject.aliyun.oss.prop.AliyunOssProp;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;


public class AliyunOssService {

    private static Logger logger = LoggerFactory.getLogger(AliyunOssService.class);

    private AliyunOssProp aliyunOssProp;


    public AliyunOssService() {

    }

    public AliyunOssService(AliyunOssProp aliyunOssProp) {
        this.aliyunOssProp = aliyunOssProp;
    }


    public OSSClient create() {
        return new OSSClient(aliyunOssProp.getEndpoint(),aliyunOssProp.getAccessKeyId(),aliyunOssProp.getAccessKeySecret());
    }


    public void shutDown(OSSClient client) {
        client.shutdown();
    }


    public String upload(String key,InputStream inputStream) {
        OSSClient ossClient = this.create();
        try {
            PutObjectResult result = ossClient.putObject(aliyunOssProp.getBucketName(),key,inputStream);
            this.shutDown(ossClient);
            return aliyunOssProp.getReturnUrlPrefix()+key;
        } catch (OSSException oe) {
            logger.error("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            logger.error("Error Message: " + oe.getErrorCode());
            logger.error("Error Code:       " + oe.getErrorCode());
            logger.error("Request ID:      " + oe.getRequestId());
            logger.error("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            logger.error("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            logger.error("Error Message: " + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return "";

    }
}
