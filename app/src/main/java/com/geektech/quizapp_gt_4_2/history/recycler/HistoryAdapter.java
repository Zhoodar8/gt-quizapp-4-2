package com.geektech.quizapp_gt_4_2.history.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    ArrayList<String> list = new ArrayList<>();

    public void update(ArrayList<String>list){
        this.list= list;
        list.add("Heloo");
        list.add("Heloo");
        list.add("Heloo");
        list.add("Heloo");
        list.add("Heloo");
        list.add("Heloo");
        list.add("Heloo");
        list.add("Heloo");
        list.add("Heloo");
        list.add("Heloo");
        list.add("Heloo");
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

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void onBind(String string) {
        }
    }
}
