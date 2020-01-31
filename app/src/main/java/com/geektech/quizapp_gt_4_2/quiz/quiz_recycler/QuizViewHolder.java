package com.geektech.quizapp_gt_4_2.quiz.quiz_recycler;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.model.Etype;
import com.geektech.quizapp_gt_4_2.model.Question;

public class QuizViewHolder extends RecyclerView.ViewHolder {
    private TextView tvHolder;
    private Button buttonTrue;
    private Button buttonFalse;
    private Button btnMultFirst;
    private Button btnMultSecond;
    private Button btnMultThird;
    private Button btnMultFourth;
    private LinearLayout layoutBoolean;
    private LinearLayout lyoutMultiple;


    public QuizViewHolder(@NonNull View itemView) {
        super(itemView);
        tvHolder = itemView.findViewById(R.id.viewholder_txt_q);
        buttonTrue = itemView.findViewById(R.id.viewholder_btn_first_boolean);
        buttonFalse = itemView.findViewById(R.id.viewholder_btn_second_boolean);
        btnMultFirst = itemView.findViewById(R.id.viewholder_btn_first);
        btnMultSecond = itemView.findViewById(R.id.viewholder_btn_second);
        btnMultThird = itemView.findViewById(R.id.viewholder_btn_third);
        btnMultFourth = itemView.findViewById(R.id.viewholder_btn_fourth);
        layoutBoolean = itemView.findViewById(R.id.vh_layout_boolean);
        lyoutMultiple = itemView.findViewById(R.id.vh_layout_multiple);

    }

    void bind(Question question){
//        if (question.getType()== Etype.BOOLEAN){
//
//        }
    }

    public void onBind(Question question) {
        tvHolder.setText(question.getQuestion());

    }
}
