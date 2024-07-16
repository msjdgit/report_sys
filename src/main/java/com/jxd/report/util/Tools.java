package com.jxd.report.util;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * @ClassName Tools
 * @Description TODO
 * @Author 马善军
 * @Date 2024/7/2 9:30
 * @Version 1.0
 */

public class Tools {
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    /**
     * 是否周末
     * @param date
     * @return
     */
    public static boolean isWeekend(String date) {
        LocalDate localDate = LocalDate.parse(date, DATE_FORMATTER);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    /**
     * 检查漏报天数
     * @param startOfMonth
     * @param existingDays
     * @return
     */
    public static List<String> checkDateContinuity(LocalDate startOfMonth, List<String> existingDays) {
        List<String> missingDays = new ArrayList<>();

        // 找到当前时间
        LocalDate endOfMonth = LocalDate.now();
        // 遍历这个月中的每一天
        LocalDate currentDay = startOfMonth;
        while (!currentDay.isAfter(endOfMonth)) {
            // 如果当前天不在现有天列表中，则添加到缺失天列表中
            if (!existingDays.contains(currentDay.toString())) {
                missingDays.add(currentDay.toString());
            }
            // 移动到下一天
            currentDay = currentDay.plusDays(1);
        }

        return missingDays;
    }

    /**
     * 获取时间
     * @return
     */
    public static String getTime(){
        Date currentDate = new Date(); // 获取当前时间
        Calendar calendar = Calendar.getInstance(); // 创建Calendar对象
        calendar.setTime(currentDate); // 设置Calendar对象的时间为当前时间

        int hour = calendar.get(Calendar.HOUR_OF_DAY); // 获取当前小时
        int minute = calendar.get(Calendar.MINUTE); // 获取当前分钟
        int second = calendar.get(Calendar.SECOND); // 获取当前秒钟
        String time = hour + ":" + minute + ":" + second;
        return time;
    }

    /**
     * 获取时间日期
     * @return
     */
    public static String getDateTime(){
        Date date = new Date();

        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(date);
        String dateTime = currentDate + " " + getTime();
        return dateTime;
    }

}
