package com.geektech.quizapp_gt_4_2.core;

public interface BaseCallback<T> {

    void onSuccess(T result);

    void onFailure(Exception e);

}
