package com.geektech.quizapp_gt_4_2.presentation.history.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    ArrayList<QuizResult> list = new ArrayList<>();

    public void update(ArrayList<QuizResult> history) {
        this.list.clear();
        this.list.addAll(history);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_view_holder, parent, false);
        HistoryViewHolder viewHolder = new HistoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        private TextView
                tvCategory,
                tvCorrectAnswer,
                tvDifficulty, tvDate;

        private ImageView imgDeleteHistory;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.card_category);
            tvCorrectAnswer = itemView.findViewById(R.id.card_correct_answer);
            tvDifficulty = itemView.findViewById(R.id.card_difficulty);
            tvDate = itemView.findViewById(R.id.card_date);
            imgDeleteHistory = itemView.findViewById(R.id.card_img);
            imgDeleteHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     //later
                  //  App.historyStorage.deleteAll();
                }
            });

        }

        public void onBind(QuizResult result) {
            tvCorrectAnswer.setText(result.getCorrectAnswer());
            tvCategory.setText(result.getCategory());
            tvDifficulty.setText(result.getDifficulty());

        }
    }
}
