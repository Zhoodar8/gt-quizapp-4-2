package com.geektech.quizapp_gt_4_2.utils;

import com.geektech.quizapp_gt_4_2.model.Question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class QuestionConverter {
    @androidx.room.TypeConverter
    public static List<Question> fromRow(String row) {
        if (row == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Question>>() {
        }.getType();
        return gson.fromJson(row, type);
    }

    @androidx.room.TypeConverter
    public static String questionsToRow(List<Question> questions) {
        if (questions == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Question>>() {
        }.getType();
        return gson.toJson(questions, type);
    }
}
