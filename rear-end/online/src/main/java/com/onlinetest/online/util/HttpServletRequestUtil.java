package com.onlinetest.online.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kevinhuang
 * @date 2019-11-19 21:28
 */
public class HttpServletRequestUtil {
    public static int getInt(HttpServletRequest request, String key){
        try {
            //将key转化成整型
            return Integer.decode(request.getParameter(key));
        }catch (Exception e){
            return -1;
        }
    }

    public static long getLong(HttpServletRequest request, String key){
        try {
            //将key转化成长整型
            return Long.valueOf(request.getParameter(key));
        }catch (Exception e){
            return -1;
        }
    }

    public static Double getDouble(HttpServletRequest request, String key){
        try {
            //将key转化成双精度整型
            return Double.valueOf(request.getParameter(key));
        }catch (Exception e){
            return -1d;
        }
    }

    public static boolean getBoolean(HttpServletRequest request, String key){
        try {
            //将key转化成boolean型
            return Boolean.valueOf(request.getParameter(key));
        }catch (Exception e){
            return false;
        }
    }

    public static String getString(HttpServletRequest request, String key){
        try {
            //将key转化成String
            String result = request.getParameter(key);
            if(result!=null){
                result.trim();
            }
            if("".equals(result)){
                result=null;
            }
            return result;
        }catch (Exception e){
            return null;
        }
    }
}
