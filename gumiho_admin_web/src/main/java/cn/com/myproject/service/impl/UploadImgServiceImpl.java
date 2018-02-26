package cn.com.myproject.service.impl;

import cn.com.myproject.service.IUploadImgService;
import cn.com.myproject.service.UploadImgClient;
import cn.com.myproject.util.FeignSpringFormEncoder;
import feign.Feign;
import feign.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.ribbon.LoadBalancerFeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 *
 */
@Service
public class UploadImgServiceImpl implements IUploadImgService {

    @Autowired
    private LoadBalancerFeignClient loadBalancerFeignClient;

    @Override
    public String uploadImg(MultipartFile file,String dir) {
        Request.Options options =  new Request.Options(10000,60000);
        UploadImgClient client =  Feign.builder()
                .encoder(new FeignSpringFormEncoder()).options(options)
                .client(loadBalancerFeignClient)
                .target(UploadImgClient.class, "http://admin-api-provide");

        //测试
//        UploadImgClient client =  Feign.builder()
//                .encoder(new FeignSpringFormEncoder())
//                .target(UploadImgClient.class, "http://127.0.0.1:3333");
        return client.uploadImg(file,dir);
    }

    @Override
    public String uploadImg(MultipartFile file) {
        return uploadImg(file,"");
    }



}