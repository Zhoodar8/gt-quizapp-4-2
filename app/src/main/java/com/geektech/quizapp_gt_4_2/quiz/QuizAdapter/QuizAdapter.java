package com.geektech.quizapp_gt_4_2.quiz.QuizAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.R;

public class QuizAdapter extends RecyclerView.Adapter<QuizViewHolder> {
    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_quiz_view_holder, parent,false);
        QuizViewHolder quizViewHolder = new QuizViewHolder(v);



        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_quiz_view_holder_multiple_version,parent,false);
        QuizViewHolder viewHolder = new QuizViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
