package com.test.download;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Auther: tianfusheng
 * @Date: 2018/9/11 17:55
 * @Description:
 */
@Controller
public class DownloadTestController {


    @RequestMapping("/DownloadTest")
    public void download(@RequestParam("path") String path, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //String filepath = request.getServletContext().getRealPath("/upload/"+path);
        String filepath2 = "D:\\workspace\\jdzw_cloud\\springmvcTest\\src\\main\\resources\\static\\upload\\"+path;
        File file =new File(filepath2);
        if(!file.exists()) {
            response.getWriter().print("您要下载的文件不存在！");
            return;
        }
        IOUtils.copy(new FileInputStream(file), response.getOutputStream());
    }

}
