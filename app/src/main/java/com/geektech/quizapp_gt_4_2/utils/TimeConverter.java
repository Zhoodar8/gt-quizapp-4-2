package com.geektech.quizapp_gt_4_2.utils;

import androidx.room.TypeConverter;

import java.util.Date;

public class TimeConverter {
    @TypeConverter
    public static Date fromTimestamp(Long time){
        return time == null ? null : new Date(time);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date){
        return date == null ? 0L : date.getTime();
    }
}
