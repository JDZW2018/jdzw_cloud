package cn.com.myproject.adminuser.externalinterface;

import cn.com.myproject.aliyun.oss.AliyunOssService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

/**
 *
 */
@RestController
public class UploadImageProvide {

    private static final Logger logger = LoggerFactory.getLogger(UploadImageProvide.class);

    @Autowired
    private AliyunOssService aliyunOssService;


    @Autowired
    DiscoveryClient discoveryClient;

    @PostMapping("/uploadImg")
    public String uploadImg(MultipartFile upfile, String dir)  {
        logger.debug("调用者："+discoveryClient.getServices());
        if(null == upfile || upfile.isEmpty()) {
            return "";
        }
        String filename = upfile.getOriginalFilename();
        String url = UploadImageProvide.getImgPath(dir)+filename.substring(filename.lastIndexOf("."));
        try {
            return aliyunOssService.upload(url,upfile.getInputStream());
        } catch (IOException e) {
            logger.error("上传失败",e);
            return "";
        }
    }
    private static String getImgPath(String dir) {
        LocalDate date = LocalDate.now();
        if(StringUtils.isNotBlank(dir)) {
            return dir+"/"+date.getYear()+"/"+date.getMonth().getValue()+"/"+date.getDayOfMonth()+"/"+ UUID.randomUUID();
        }
        return "img/"+date.getYear()+"/"+date.getMonth().getValue()+"/"+date.getDayOfMonth()+"/"+ UUID.randomUUID();
    }

}
