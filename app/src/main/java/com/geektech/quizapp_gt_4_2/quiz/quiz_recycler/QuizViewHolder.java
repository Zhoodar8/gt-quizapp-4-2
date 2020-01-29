package com.geektech.quizapp_gt_4_2.quiz.quiz_recycler;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.model.Etype;
import com.geektech.quizapp_gt_4_2.model.Question;

public class QuizViewHolder extends RecyclerView.ViewHolder {
    public QuizViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    void bind(Question question){
        if (question.getType()== Etype.BOOLEAN){

        }
    }
}
