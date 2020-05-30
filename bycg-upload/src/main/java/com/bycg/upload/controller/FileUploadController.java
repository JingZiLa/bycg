package com.bycg.upload.controller;

import com.bycg.upload.service.FileUploadService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 文件处理控制层
 * @className: FileUploadController
 * @Author: Mirror
 * @CreateDate: 2020/5/12 19:27
 **/
@Controller
@RequestMapping("upload")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 图片文件上传
     * @param file
     * @return
     */
    @PostMapping("image")
    public ResponseEntity<String> uploadImgFile(@RequestParam("file") MultipartFile file) {

        String url = this.fileUploadService.uploadImgFile(file);

        if (StringUtils.isBlank(url)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }


}
