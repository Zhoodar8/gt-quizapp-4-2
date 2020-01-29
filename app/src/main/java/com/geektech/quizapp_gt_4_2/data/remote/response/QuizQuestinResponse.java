package com.geektech.quizapp_gt_4_2.data.remote.response;

import android.view.LayoutInflater;

import com.geektech.quizapp_gt_4_2.model.Question;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuizQuestinResponse {
     @SerializedName("response_code")
    private int responseCode;
    @SerializedName("results")
    private List<Question> results;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public List<Question> getResults() {
        return results;
    }

    public void setResults(List<Question> results) {
        this.results = results;
    }
}
