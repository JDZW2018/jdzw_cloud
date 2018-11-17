package cn.com.myproject.adminuser.service.impl;


import cn.com.myproject.adminuser.service.IUploadImgService;
import cn.com.myproject.adminuser.service.UploadImgClient;
import cn.com.myproject.adminuser.util.FeignSpringFormEncoder;
import feign.Feign;
import feign.Request;
import feign.ribbon.RibbonClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 */
@Service
public class UploadImgServiceImpl implements IUploadImgService {


    @Override
    public String uploadImg(MultipartFile file,String dir) {
        Request.Options options =  new Request.Options(10000,60000);
        UploadImgClient client =  Feign.builder()
                .encoder(new FeignSpringFormEncoder()).options(options)
                .client(RibbonClient.create())
                .target(UploadImgClient.class, "http://gumiho-s-admin-user");

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