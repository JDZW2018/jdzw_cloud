package com.test.upload;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/9/10 15:40
 * @Description:
 */
@Controller
public class uploadController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/upload")
    public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
// 因为要使用response打印，所以设置其编码
        response.setContentType("text/html;charset=utf-8");
// 创建工厂
        DiskFileItemFactory dfif = new DiskFileItemFactory();
// 使用工厂创建解析器对象
        ServletFileUpload fileUpload = new ServletFileUpload(dfif);
        try {
// 使用解析器对象解析request，得到FileItem列表
            List<FileItem> list = fileUpload.parseRequest((RequestContext) request);
// 遍历所有表单项
            for (FileItem fileItem : list) {
// 如果当前表单项为普通表单项
                if (fileItem.isFormField()) {
// 获取当前表单项的字段名称
                    String fieldName = fileItem.getFieldName();
// 如果当前表单项的字段名为username
                    if (fieldName.equals("username")) {
// 打印当前表单项的内容，即用户在username表单项中输入的内容
                        response.getWriter().print("用户名：" + fileItem.getString() + "<br/>");
                    }
                } else {//如果当前表单项不是普通表单项，说明就是文件字段
                    String name = fileItem.getName();//获取上传文件的名称
// 如果上传的文件名称为空，即没有指定上传文件
                    if (name == null || name.isEmpty()) {
                        continue;
                    }
// 获取真实路径，对应${项目目录}/uploads，当然，这个目录必须存在
                    String savepath = this.getServletContext().getRealPath("/uploads");
// 通过uploads目录和文件名称来创建File对象
                    File file = new File(savepath, name);
// 把上传文件保存到指定位置
                    fileItem.write(file);
// 打印上传文件的名称
                    response.getWriter().print("上传文件名：" + name + "<br/>");
// 打印上传文件的大小
                    response.getWriter().print("上传文件大小：" + fileItem.getSize() + "<br/>");
// 打印上传文件的类型
                    response.getWriter().print("上传文件类型：" + fileItem.getContentType() + "<br/>");
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

}

}
