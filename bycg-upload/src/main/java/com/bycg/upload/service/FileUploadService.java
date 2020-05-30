package com.bycg.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 文件处理业务接口
 * @className: FileUploadService
 * @Author: Mirror
 * @CreateDate: 2020/5/12 19:27
 **/
public interface FileUploadService {
    String uploadImgFile(MultipartFile file);
}
