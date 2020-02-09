package com.geektech.quizapp_gt_4_2.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.geektech.quizapp_gt_4_2.utils.QuestionConverter;
import com.geektech.quizapp_gt_4_2.utils.TimeConverter;

import java.util.Date;
import java.util.List;

@Entity(tableName = "quiz_result")
public class QuizResult {
    @PrimaryKey(autoGenerate = true)
    public int id;

   @ColumnInfo(name = "questions")
   @TypeConverters({QuestionConverter.class})
    private List<Question> questions;
   @ColumnInfo(name = "correct_answer")
   @TypeConverters({QuestionConverter.class})
    private int correctAnswer;

   @ColumnInfo(name = "user_time")
   @TypeConverters({TimeConverter.class})
    private Date createdAt;
    private String category;
    private String difficulty;

    public QuizResult(int id, List<Question> questions, int correctAnswer, Date createdAt, String category, String difficulty) {
        this.id = id;
        this.questions = questions;
        this.correctAnswer = correctAnswer;
        this.createdAt = createdAt;
        this.category = category;
        this.difficulty = difficulty;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
