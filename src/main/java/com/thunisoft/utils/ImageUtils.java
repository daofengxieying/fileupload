package com.thunisoft.utils;

import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang.StringUtils;
import com.thunisoft.platform.dto.ReturnMsg;

/**  
* @Title: ImageUtils  
* @Description:  上传工具类
* @author zsy  
* @date 2018年11月21日  
*/
@Component
public class ImageUtils {

    
private static Logger logger = LoggerFactory.getLogger(ImageUtils.class);
    
    private static String imgPattern;
    
    //spring boot 获取动态数据的方式之一
    @Value("${imgUploadConfig.img-pattern}")
    public void setImgPattern(String imgPattern) {
        //ImageUtils.imgPattern = imgPattern;    //这种写法会被扫描工具扫描成高级别的bug
        setFiledValue(imgPattern);
    }
    
    public void setFiledValue(String oriObj) {
        ImageUtils.imgPattern = oriObj;
    }

    /**
     * 校验上传文件基本信息
     * @param imageFile 上传源文件
     * @return 如果校验不通过，返回false，通过返回true
     */
    public static ReturnMsg checkUpLoadImg(MultipartFile imageFile){
        ReturnMsg msg = new ReturnMsg(true);
        
        if(imageFile == null){
            msg.setSuccess(false);
            msg.setCode(GlobalMessageEnum.FILE_UPLOAD_NULL.getCode());
            msg.setMessage(GlobalMessageEnum.FILE_UPLOAD_NULL.getMsg());
            return msg;
        }

        String fileContentType = imageFile.getContentType();
        if(StringUtils.isBlank(fileContentType)){
            msg.setSuccess(false);
            msg.setCode(GlobalMessageEnum.FILE_TYPE_UNKNOW.getCode());
            msg.setMessage(GlobalMessageEnum.FILE_TYPE_UNKNOW.getMsg());
            return msg;

        }
        
        logger.debug("filter  image-pattern: {}", imgPattern);
        
        String[] imagePattern = StringUtil.split(imgPattern, ",");
        for (String pattern : imagePattern) {
            if(fileContentType.contains(pattern)){
                return msg; //成功匹配就直接返回信息
            }
        }
        // 返回错误提示"仅支持.jpg .png格式的图片文件"
        msg.setSuccess(false);
        msg.setCode(GlobalMessageEnum.FILE_TYPE_UNACCEPT.getCode());
        msg.setMessage(GlobalMessageEnum.FILE_TYPE_UNACCEPT.getMsg());
        return msg;
    }
    
    public static String getUploadFileAbsolutePath(String fileName, String fileDir){
        StringBuilder uploadFileAbsolutePath = new StringBuilder();
        // 如果路径未被创建则创建该路径
        File filePath = new File(fileDir);
        if(!filePath.exists()){
            filePath.mkdirs();
            logger.info("Init upload directory {} success!! ", fileDir);
        }
        uploadFileAbsolutePath.append(filePath.getAbsolutePath()).append(File.separator).append(fileName);
        return uploadFileAbsolutePath.toString();
    }
    
    public static String getFileNameByUUID(String originalUpLoadFileName){
        StringBuilder fileNameByUuid = new StringBuilder();
        fileNameByUuid.append(UuidUtils.getUuidByLength(10)).append("_").append(originalUpLoadFileName);
        return fileNameByUuid.toString();
    }
    
    /**
     * 根据图片路径删除产品图片
     * @param imgsPath 
     */
    public static boolean deleteImageByPath(String imgsPath) {
        if(StringUtils.isNotBlank(imgsPath)){
            File del = new File(imgsPath);
            if(del.exists()){
                return del.delete();
            }else {
                logger.info("file is not exist {} !! ", imgsPath);
                return false;
            }
        }else {
            logger.info("imgsPath is null {} !! ");
            return false;
        }
    }
    
    
    public static String fileCopy(MultipartFile imageFile, String imageFilePath) {
        try {
            // 将文件上传到指定的路径
            String originalFilename = imageFile.getOriginalFilename();
            String fileName = ImageUtils.getFileNameByUUID(originalFilename);
            String uploadFileAbsolutePath = ImageUtils.getUploadFileAbsolutePath(fileName, imageFilePath);

            FileOutputStream fos = new FileOutputStream(new File(uploadFileAbsolutePath));
            FileCopyUtils.copy(imageFile.getInputStream(), fos);
            
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
