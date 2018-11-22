package com.thunisoft.utils;

import java.util.Random;
import java.util.UUID;

/**  
* @Title: UuidUtils  
* @Description:  
* @author zsy  
* @date 2018年11月21日  
*/
public class UuidUtils {

private static Random random = new Random();
    
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public static String getUuidByLength(int len) {
        String uuid = getUuid();
        int uuidLength = uuid.length();
        if (len < 1) {
            return null;
        }
        if (len >= uuidLength) {
            return uuid;
        }
        //随机一个开始截取的初始位，截取一定len长度的字符串
        int startPoint = random.nextInt(uuidLength-len+1);
        return uuid.substring(startPoint, startPoint+len);
    }
    
}
