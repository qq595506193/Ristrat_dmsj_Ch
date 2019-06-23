package com.example.tidus.ristrat.mvp.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tidus.ristrat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CancelQuestionActivity extends AppCompatActivity {
    @BindView(R.id.iv_close)
    ImageView iv_close;
    @BindView(R.id.help_feedback)
    EditText help_feedback;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_assess);
        initView();
        initListener();
    }

    private void initListener() {


    }


    private void initView() {
        ButterKnife.bind(this);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        help_feedback.setHintTextColor(Color.GRAY);
    }


}
