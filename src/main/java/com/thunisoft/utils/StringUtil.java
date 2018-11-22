package com.thunisoft.utils;

/**  
* @Title: StringUtil  
* @Description:  
* @author zsy  
* @date 2018年11月21日  
*/
public class StringUtil {

    
    public static String[] split(String stringToSplit, String delimiter) {
        if (stringToSplit == null) {
            return null;
        } else if (delimiter == null) {
            throw new IllegalArgumentException("delimiter must be not null");
        } else {
            return stringToSplit.indexOf(delimiter) < 0 ? new String[]{stringToSplit} : stringToSplit.split(delimiter);
        }
    }
    
}
