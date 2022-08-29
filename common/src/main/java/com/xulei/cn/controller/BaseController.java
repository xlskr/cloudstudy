package com.xulei.cn.controller;

import com.xulei.cn.utils.QiniuUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

public class BaseController {


    /**
     * 图片上传
     * @param file
     * @return
     */
    @RequestMapping(value="/imgs", method = RequestMethod.POST)
    public String uploadImg(@RequestParam("file") MultipartFile  file) {
        String filename = file.getOriginalFilename();
        FileInputStream inputStream = null;
        try {
            inputStream = (FileInputStream) file.getInputStream();
            filename = UUID.randomUUID()+ filename;
            String link = QiniuUtil.uploadImgToQiNiu(inputStream, filename);
            return link;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //为文件重命名：uuid+filename
        return "";
    }


}
