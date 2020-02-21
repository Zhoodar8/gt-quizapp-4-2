package com.geektech.quizapp_gt_4_2.presentation.settings;

import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreFragment;
import com.geektech.quizapp_gt_4_2.presentation.main.MainViewModel;

public class SettingsFragment extends CoreFragment {
    private SettingsViewModel mViewModel;
    private MainViewModel mMainViewModel;
    private TextView tvDelete;
    private AlertDialog.Builder adBuilder;

    @Override
    protected int getLayoutID() {
        return R.layout.settings_fragment;
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvDelete = view.findViewById(R.id.settings_txt_fourth);
        init();
    }

    private void init() {
        tvDelete.setOnClickListener(v -> {
            adBuilder = new AlertDialog.Builder(getContext());
            adBuilder.setTitle("Notification !");
            adBuilder.setMessage("Do you want clear your History ?");
            adBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    App.historyStorage.deleteAll();
                    Toast.makeText(getContext(), "History deleted", Toast.LENGTH_LONG).show();
                }
            });

            adBuilder.setNegativeButton("Never Mind", (dialog, which) -> {
                 dialog.cancel();
            });

            AlertDialog alertDialog =adBuilder.create();
            alertDialog.show();

        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }


}
