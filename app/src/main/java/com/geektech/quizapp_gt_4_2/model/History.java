package com.geektech.quizapp_gt_4_2.model;

import java.util.Date;

public class History {

    private int id;
    private String difficulty;
    private String category;
    private int correctAnswers;
    private int amount;
    private Date createdAt;

    public History(int id, String difficulty, String category, int correctAnswers, int amount, Date createdAt) {
        this.id = id;
        this.difficulty = difficulty;
        this.category = category;
        this.correctAnswers = correctAnswers;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
