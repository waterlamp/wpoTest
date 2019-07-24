package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {
    public static boolean isEmpty(String str){
        return str==null || str.length()==0;
    }
    public static String dateToString(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

}
