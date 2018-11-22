package com.thunisoft.platform.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.thunisoft.platform.dto.ResultData;
import com.thunisoft.platform.dto.ReturnMsg;
import com.thunisoft.utils.GlobalMessageEnum;
import com.thunisoft.utils.ImageUtils;

/**  
* @Title: PlatformController  
* @Description:  
* @author zsy  
* @date 2018年11月21日  
*/
@Controller
public class PlatformController {
    
    
    @Value("${imgUploadConfig.product-image-file}")
    private String productImageFilePath;
    
    @RequestMapping("/list")
    public String list(){
       
       return "list";
    }
    
    @RequestMapping(value="/onfile",method=RequestMethod.POST)
    public ReturnMsg photoUpload(MultipartFile file){
        ReturnMsg returnMsg = new ReturnMsg();
        //判断文件是否为空
        ReturnMsg result = ImageUtils.checkUpLoadImg(file);
        if (!result.isSuccess()) {
            return result;
        }
        String fileName =null;
        try {
            
            fileName = ImageUtils.fileCopy(file, productImageFilePath);
            if (StringUtils.isEmpty(fileName)) { //这里直接返回null或者文件名称
                result.setSuccess(false);
                result.setMessage(GlobalMessageEnum.FILE_UPLOAD_FAILURE.getMsg());
                result.setCode(GlobalMessageEnum.FILE_UPLOAD_FAILURE.getCode());
                return result;
            }
            
            
            
            /*affectRows = productDao.insertSelective(product);
            
            if(affectRows != 1){
                logger.error("Insert Into DateBase Failure !!!");
                ImageUtils.deleteImageByPath(ImageUtils.getUploadFileAbsolutePath(fileName, productImageFilePath));
                result.setSuccess(false);
                result.setMessage(GlobalMessageEnum.DATABASE_INTERACTIVE_FAILED.getMsg());
                result.setCode(GlobalMessageEnum.DATABASE_INTERACTIVE_FAILED.getCode());
                ImageUtils.deleteImageByPath(productImageFilePath + File.separator + fileName);

            }*/
        } catch (Exception e) {
           
            result.setSuccess(false);
            result.setMessage(GlobalMessageEnum.SYS_CODE_500.getMsg());
            result.setCode(GlobalMessageEnum.SYS_CODE_500.getCode());
            ImageUtils.deleteImageByPath(productImageFilePath + File.separator + fileName);
        }
        
        
        
        return returnMsg;
    }
    
    /*** 
     * 保存文件 
     * @param file 
     * @return 
     */  
    private boolean saveFile(MultipartFile file, String path) {  
        // 判断文件是否为空  
        if (!file.isEmpty()) {  
            try {  
                File filepath = new File(path);
                if (!filepath.exists()) 
                    filepath.mkdirs();
                // 文件保存路径  
                String savePath = path + file.getOriginalFilename();  
                // 转存文件  
                file.transferTo(new File(savePath));  
                return true;  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return false;  
    }  
    

}
