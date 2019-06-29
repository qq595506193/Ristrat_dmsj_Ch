package com.example.tidus.ristrat.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.adapter.EvaluatingAdapter;
import com.example.tidus.ristrat.application.App;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EvaluatingActivity extends AppCompatActivity {
    @BindView(R.id.rv_evaluating)
    RecyclerView rv_evaluating;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_evaluating);
        ButterKnife.bind(this);
        initView(new Intent());
        initData();
    }

    private void initData() {

    }

    private void initView(Intent intent) {
        EvaluatingAdapter evaluatingAdapter = new EvaluatingAdapter(App.getContext());
        rv_evaluating.setLayoutManager(new LinearLayoutManager(App.getContext()));
        rv_evaluating.setAdapter(evaluatingAdapter);
    }
}
