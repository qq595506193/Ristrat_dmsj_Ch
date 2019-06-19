package com.example.tidus.ristrat.callback;

public interface IRequestCallback {
    void onSuccess(Object result);

    void onFailed(Object error);
}
