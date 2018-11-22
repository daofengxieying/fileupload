package com.thunisoft.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import com.thunisoft.platform.dto.ReturnMsg;
import com.thunisoft.utils.GlobalMessageEnum;

/**  
* @Title: CommonExceptionHandler  
* @Description:  异常处理配置类
* @author zsy  
* @date 2018年11月22日  
*/
@RestControllerAdvice
public class CommonExceptionHandler {

    
    /**  
     * @Title: handleMultipartException  
     * @Description:  图片大小异常
     * @return  
     */  
    @ExceptionHandler({MultipartException.class})
    public ReturnMsg handleMultipartException(HttpServletRequest request, Throwable ex){
        
        MultipartException mEx = (MultipartException)ex;
        Throwable cause = ex.getCause().getCause();
        ReturnMsg result = new ReturnMsg(false);
        float permittedSize = 0;
        result.setCode(GlobalMessageEnum.FILE_UPLOAD_SIZE.getCode());
        if (cause instanceof SizeLimitExceededException){
            SizeLimitExceededException flEx = (SizeLimitExceededException) cause;
            permittedSize = flEx.getPermittedSize() / 1024 ;
            result.setMessage(GlobalMessageEnum.FILE_UPLOAD_SIZE.getMsg()+" ("+permittedSize+"KB) ");
        } else if (cause instanceof FileSizeLimitExceededException){
            FileSizeLimitExceededException flEx = (FileSizeLimitExceededException)mEx.getCause().getCause();
            permittedSize = flEx.getPermittedSize() / 1024 ;
            result.setMessage(GlobalMessageEnum.FILE_UPLOAD_SIZE.getMsg()+" ("+permittedSize+"KB) ");
        }else {
            result.setCode(GlobalMessageEnum.SYS_CODE_500.getCode());
            result.setMessage(GlobalMessageEnum.SYS_CODE_500.getMsg());
        }     
        return result;
        
        
    }
    
    
}
