package cn.com.myproject.ue.controller;

import cn.com.myproject.service.IUploadImgService;
import com.baidu.ueditor.ActionEnter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/7/31.
 */
@Controller
@RequestMapping("/UEditor")
public class UEditorController {

    private static final Logger logger = LoggerFactory.getLogger(UEditorController.class);

    @Autowired
    private IUploadImgService uploadImgService;

    @Value("${ueditor.url}")
    private String path;

    @RequestMapping("/config")
    public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding( "utf-8" );
        response.setHeader("Content-Type" , "text/html");

       // ActionEnter action = new ActionEnter(request,  ClassUtils.getDefaultClassLoader().getResource("").getPath()) ;
        ActionEnter action = new ActionEnter(request,  path) ;
        String res = action.exec() ;

        response.getWriter().print(res);
    }

    @RequestMapping(value="/uploadImg")
    @ResponseBody
    public  Map<String, Object> uploadImg(@RequestParam("upfile") MultipartFile file, HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> params = new HashMap<String, Object>();
        String url = uploadImgService.uploadImg(file,"ue");
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        if(org.apache.commons.lang3.StringUtils.isBlank(url)) {
            logger.error("上传文件失败");
            params.put("state","ERROR");
            return params;
        }
        params.put("state","SUCCESS");
        params.put("url", url);
        params.put("title", file.getName());
        params.put("original", file.getName());
        return params;
    }
}
