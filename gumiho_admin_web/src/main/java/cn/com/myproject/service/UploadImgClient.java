package cn.com.myproject.service;

import feign.Param;
import feign.RequestLine;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 *
 */

public interface UploadImgClient {

    @RequestLine("POST /uploadImg")
    String uploadImg(@Param("upfile") MultipartFile file, @Param("dir") String dir);
}
