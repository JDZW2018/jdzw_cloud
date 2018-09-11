package com.test.upload;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.Iterator;
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

    /*
     *采用spring提供的上传文件的方法
     */
    @ResponseBody
    @RequestMapping("/springUpload")
    public String springUpload(HttpServletRequest request) throws IllegalStateException, IOException {
        long startTime = System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    String path = "E:/springUpload/" + file.getOriginalFilename();
                    //上传
                    file.transferTo(new File(path));
                }

            }

        }
        long endTime = System.currentTimeMillis();
        System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
        return "success";
    }


    /*
     * 采用file.Transto 来保存上传的文件
     */
    @ResponseBody
    @RequestMapping("/fileUpload2")
    public String fileUpload2(@RequestParam("file") MultipartFile file) throws IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("fileName：" + file.getOriginalFilename());
        String path = "E:/" + new Date().getTime() + file.getOriginalFilename();

        File newFile = new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        long endTime = System.currentTimeMillis();
        System.out.println("方法二的运行时间：" + String.valueOf(endTime - startTime) + "ms");
        return "/success";
    }


    /*
     * 通过流的方式上传文件
     * @RequestParam("file") 将name=file控件得到的文件封装成CommonsMultipartFile 对象
     */
    @RequestMapping("fileUpload")
    public String fileUpload(@RequestParam("file") CommonsMultipartFile file) throws IOException {


        //用来检测程序运行时间
        long startTime = System.currentTimeMillis();
        System.out.println("fileName：" + file.getOriginalFilename());

        try {
            //获取输出流
            OutputStream os = new FileOutputStream("E:/" + new Date().getTime() + file.getOriginalFilename());
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            InputStream is = file.getInputStream();
            int temp;
            //一个一个字节的读取并写入
            while ((temp = is.read()) != (-1)) {
                os.write(temp);
            }
            os.flush();
            os.close();
            is.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("方法一的运行时间：" + String.valueOf(endTime - startTime) + "ms");
        return "/success";
    }

/*    @RequestMapping("/upload")
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
    }*/

}

