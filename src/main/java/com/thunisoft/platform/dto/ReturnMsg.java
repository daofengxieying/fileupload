package com.thunisoft.platform.dto;

/**  
* @Title: ReturnMsg  
* @Description:  
* @author zsy  
* @date 2018年11月21日  
*/
public class ReturnMsg {

    private boolean success;  //操作成功失败标志
    private Integer code; //返回code， 200代表ok， 404， 500等说明
    private String message;  //code对应的返回消息
    private Object data; //分页对应的返回具体业务数据
    
    public ReturnMsg() {
        super();
    }
    
    public ReturnMsg(boolean success) {
        super();
        this.success = success;
    }

    public ReturnMsg(boolean success, Integer code, String message) {
        super();
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public ReturnMsg(boolean success, Integer code, String message, Object data) {
        super();
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    
}
