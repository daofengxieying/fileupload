package com.thunisoft.platform.dto;

/**  
* @Title: ResultData  
* @Description:  
* @author zsy  
* @date 2018年11月21日  
*/
public class ResultData<T> {

    private T data;
    
    private int code =200;
    
    private String msg;
    
    private Boolean success = true;
    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public int getCode() {
        
        return code;
    }
    public void setCode(int code) {
        if(200 != code){
            success = false;
        }
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
    
}
