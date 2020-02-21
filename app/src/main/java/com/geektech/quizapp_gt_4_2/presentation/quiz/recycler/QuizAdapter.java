package com.geektech.quizapp_gt_4_2.presentation.quiz.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class QuizAdapter extends RecyclerView.Adapter<QuizViewHolder> {
   private List<Question> list = new ArrayList<>();
    private QuizViewHolder.Listener listener;

    public QuizAdapter(QuizViewHolder.Listener listener) {
        this.listener = listener;
    }

    public void update(List<Question>list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

  public List<Question> getmQuestions() {
        return list;
    }
    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_quiz_view_holder,parent,false);
        QuizViewHolder viewHolder = new QuizViewHolder(view, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        if (holder instanceof QuizViewHolder){
            ((QuizViewHolder) holder).onBind(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
