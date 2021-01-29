package com.atxiaoming.utils;

import java.math.BigDecimal;

public class CommonUtils {
    public static String Arr2Str(String[] arr){
        String str = "";
        for(int i = 0;i<arr.length;i++){
            if(i==arr.length - 1){
                str += arr[i];
            }else{
                str += arr[i] + ",";
            }
        }
        return str;
    }

    public static String[] Str2Arr(String str){
        String[] arr = str.split(",");
        return arr;
    }

    public static String getFileName(String url){
        String fileName = url.substring(48,url.length());
        return fileName;
    }

    public static BigDecimal getMin(BigDecimal[] arrs){
        if(arrs.length == 0){
            return BigDecimal.valueOf(0);
        }
        BigDecimal i = arrs[0];
        for (BigDecimal a : arrs){
            if(i.compareTo(a) == 1){
                i = a;
            }
        }
        return i;
    }
}
