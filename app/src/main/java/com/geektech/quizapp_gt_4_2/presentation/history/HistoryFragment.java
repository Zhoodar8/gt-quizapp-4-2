package com.geektech.quizapp_gt_4_2.presentation.history;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreFragment;
import com.geektech.quizapp_gt_4_2.model.History;
import com.geektech.quizapp_gt_4_2.presentation.history.recycler.HistoryAdapter;
import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HistoryFragment extends CoreFragment implements HistoryAdapter.Listener {

    private HistoryViewModel mViewModel;
    private RecyclerView recyclerView;
    HistoryAdapter adapter = new HistoryAdapter(this);
    private List<History> historyArrayList ;

    @Override
    protected int getLayoutID() {
        return R.layout.history_fragment;
    }

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        recyclerView = view.findViewById(R.id.history_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        // mViewModel.history.observe(Objects.requireNonNull(getActivity()), histories -> adapter.update(histories));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        mViewModel.historyLiveData.observe(getActivity(), new Observer<List<History>>() {
            @Override
            public void onChanged(List<History> histories) {
                adapter.update(histories);
                historyArrayList = histories;
            }
        });
        // TODO: Use the ViewModel
    }

    private void getPopup(int position, View view){
        PopupMenu popupMenu = new PopupMenu(getContext(),view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.delete_menu:
                        App.quizDatabase.historyDao().delete(historyArrayList.get(position).getId());
                        Toast.makeText(getActivity(),"Delete", Toast.LENGTH_LONG).show();
                        return  true;
                    case R.id.share_menu:
                        History currentHistory = historyArrayList.get(position);
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("plain/text");
                        intent.putExtra(Intent.EXTRA_TEXT,"game id: " + currentHistory.getId()
                                + "\ncategory: " + currentHistory.getCategory()
                                + "\ncorrect answers: " + currentHistory.getCorrectAnswers() + "/" + currentHistory.getAmount()
                                + "\ndifficulty: " + currentHistory.getDifficulty()
                                + "\ndate: " + currentHistory.getCreatedAt());
                        startActivity(Intent.createChooser(intent,""));
                        Toast.makeText(getActivity(), "Share", Toast.LENGTH_LONG).show();
                        return true;
                }
                return false;
            }
        });
        popupMenu.show();
    }
    @Override
    public void onHistoryClick(int position, View view) {
        getPopup(position,view);
    }
}
