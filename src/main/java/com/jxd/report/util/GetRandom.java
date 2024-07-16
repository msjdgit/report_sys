package com.jxd.report.util;

import java.util.Random;

/**
 * @ClassName GetRandom
 * @Description TODO
 * @Author 马善军
 * @Date 2024/6/10 12:06
 * @Version 1.0
 */

public class GetRandom {
    public static int getRandom(int count){
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for(int i=0;i<count;i++){
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num)+""), "");
        }
        sb.toString();
        return Integer.parseInt(sb.toString());

    }
}
