package cn.com.myproject.adminuser.service;

import feign.Param;
import feign.RequestLine;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 */

public interface UploadImgClient {

    @RequestLine("POST /uploadImg")
    String uploadImg(@Param("upfile") MultipartFile file, @Param("dir") String dir);
}
