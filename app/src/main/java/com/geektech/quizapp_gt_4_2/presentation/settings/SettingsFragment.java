package com.geektech.quizapp_gt_4_2.presentation.settings;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.View;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreFragment;
import com.geektech.quizapp_gt_4_2.presentation.main.MainViewModel;

public class SettingsFragment extends CoreFragment {
    private SettingsViewModel mViewModel;
    private MainViewModel mMainViewModel;

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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }


}
