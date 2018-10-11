package cn.com.myproject.adminuser.service;

import org.springframework.web.multipart.MultipartFile;


/**
 *
 */
public interface IUploadImgService {

    /**
     * 上传图片
     * @param file 文件
     * @param dir 目录，可以为空
     * */
    String uploadImg(MultipartFile file, String dir);

    String uploadImg(MultipartFile file);


}
