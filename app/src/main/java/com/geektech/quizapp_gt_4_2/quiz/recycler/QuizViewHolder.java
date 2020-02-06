package com.geektech.quizapp_gt_4_2.quiz.recycler;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private Listener listener;


    public QuizViewHolder(@NonNull View itemView,Listener listener) {
        super(itemView);
        this.listener = listener;
        tvHolder = itemView.findViewById(R.id.viewholder_txt_q);
        buttonTrue = itemView.findViewById(R.id.viewholder_btn_first_boolean);
        buttonFalse = itemView.findViewById(R.id.viewholder_btn_second_boolean);
        btnMultFirst = itemView.findViewById(R.id.viewholder_btn_first);
        btnMultSecond = itemView.findViewById(R.id.viewholder_btn_second);
        btnMultThird = itemView.findViewById(R.id.viewholder_btn_third);
        btnMultFourth = itemView.findViewById(R.id.viewholder_btn_fourth);
        layoutBoolean = itemView.findViewById(R.id.vh_layout_boolean);
        lyoutMultiple = itemView.findViewById(R.id.vh_layout_multiple);

      //  listener.onAnswerClick(getAdapterPosition(),0);

        initListener();

    }

    private void initListener() {
        buttonTrue.setOnClickListener(v -> {
            listener.onAnswerClick(getAdapterPosition(),0);
        });

        buttonFalse.setOnClickListener(v -> {
            listener.onAnswerClick(getAdapterPosition(),1);
        });

        btnMultFirst.setOnClickListener(v -> {
          listener.onAnswerClick(getAdapterPosition(),0);
        });
        btnMultSecond.setOnClickListener(v -> {
            listener.onAnswerClick(getAdapterPosition(),1);
        });
        btnMultThird.setOnClickListener(v -> {
             listener.onAnswerClick(getAdapterPosition(),2);
        });
        btnMultFourth.setOnClickListener(v -> {
              listener.onAnswerClick(getAdapterPosition(),3);
        });

    }


    private void setButtons(boolean enabled){
        buttonTrue.setEnabled(enabled);
        buttonFalse.setEnabled(enabled);
        btnMultFirst.setEnabled(enabled);
        btnMultSecond.setEnabled(enabled);
        btnMultThird.setEnabled(enabled);
        btnMultFourth.setEnabled(enabled);
    }
    public void onBind(Question question) {
        if (question.getSelectedAnswerPosition() !=null){
          setButtons(false);
        }else {
              setButtons(true);
        }


        tvHolder.setText( Html.fromHtml(
                question.getQuestion()));
        if (question.getType() == Etype.MULTIPLE){
            Log.e("---------", question.getAnswers()+"");
            lyoutMultiple.setVisibility(View.VISIBLE);
            layoutBoolean.setVisibility(View.GONE);
            btnMultFirst.setText(Html.fromHtml(
                    question.getAnswers().get(0)));
            btnMultSecond.setText(Html.fromHtml(
                    question.getAnswers().get(1)));
            btnMultThird.setText(Html.fromHtml(
                    question.getAnswers().get(2)));
            btnMultFourth.setText(Html.fromHtml(
                    question.getAnswers().get(3)));

        }else {
            lyoutMultiple.setVisibility(View.GONE);
            layoutBoolean.setVisibility(View.VISIBLE);
            buttonTrue.setText(Html.fromHtml(
                    question.getAnswers().get(0)));
            buttonFalse.setText(Html.fromHtml(
                    question.getAnswers().get(1)));
        }


    }



    public interface Listener{
        void onAnswerClick(int position, int selectedAnswerPosition );
    }
}
