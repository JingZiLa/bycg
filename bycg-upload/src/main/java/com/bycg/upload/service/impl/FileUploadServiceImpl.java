package com.bycg.upload.service.impl;

import com.bycg.upload.service.FileUploadService;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 文件处理业务实现类
 * @className: FileUploadServiceImpl
 * @Author: Mirror
 * @CreateDate: 2020/5/12 19:28
 **/
@Service
public class FileUploadServiceImpl implements FileUploadService {


    @Autowired
    private FastFileStorageClient storageClient;

    //图片文件白名单
    private static final List<String> IMGAGEFILE_TYPES = Arrays.asList("image/png", "image/jpeg", "image/jpg", "image/gif");

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    /**
     * 图片文件上传
     *
     * @param file
     * @return
     */
    @Override
    public String uploadImgFile(MultipartFile file) {

        //获取文件名
        String filename = file.getOriginalFilename();
        try {
            //获取文件类型
            String fileContentType = file.getContentType();
            //校验文件类型
            if (!IMGAGEFILE_TYPES.contains(fileContentType)) {
                LOGGER.info("文件类型不合法: {}", filename);
                return null;
            }
            //读取图片内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            //校验图片内容
            if (image == null) {
                LOGGER.info("文件内容不合法: {}", filename);
                return null;
            }

            //获取图片后缀
            String ext = StringUtils.substringAfterLast(filename, ".");

            //把文件上传到服务器
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);

            //返回图片路径
            return "http://image.bycg.com/"+storePath.getFullPath();
        } catch (IOException e) {
            LOGGER.info("服务器内部错误: {}",filename);
            e.printStackTrace();
        }
        return null;
    }
}
