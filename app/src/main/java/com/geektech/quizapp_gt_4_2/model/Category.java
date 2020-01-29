package com.geektech.quizapp_gt_4_2.model;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
