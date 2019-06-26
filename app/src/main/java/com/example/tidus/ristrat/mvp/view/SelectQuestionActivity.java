package com.example.tidus.ristrat.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.bean.LoginBean;
import com.example.tidus.ristrat.bean.QueryHMBean;
import com.example.tidus.ristrat.bean.SelectQuestionListBean;
import com.example.tidus.ristrat.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectQuestionActivity extends AppCompatActivity {
    @BindView(R.id.iv_close)
    ImageView iv_close;
    @BindView(R.id.ck_question_01)
    CheckBox ck_question_01;
    @BindView(R.id.ck_question_02)
    CheckBox ck_question_02;
    @BindView(R.id.btn_sure)
    Button btn_sure;
    private boolean question_01 = false;
    private boolean question_02 = false;
    private List<String> selectQuestionList = new ArrayList<>();
    private CaseControlBean.ServerParamsBean serverParamsBean;
    private LoginBean loginBean;
    private QueryHMBean.ServerParamsBean.LISTBean listBean;
    private SelectQuestionListBean selectQuestionListBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_select_pop);
        selectQuestionListBean = new SelectQuestionListBean();
        initView();
        initListener();
    }

    private void initListener() {
        ck_question_01.setOnCheckedChangeListener(myCheckChangelistener);

        ck_question_02.setOnCheckedChangeListener(myCheckChangelistener);

        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!question_01 && !question_02) {
                    ToastUtils.show("请至少选择一项");
                    return;
                }
                if (ck_question_01.isChecked()) {
                    selectQuestionList.add("1");
                    selectQuestionListBean.setIndexTable(selectQuestionList);
                }
                if (ck_question_02.isChecked()) {
                    selectQuestionList.add("2");
                    selectQuestionListBean.setIndexTable(selectQuestionList);
                }
                Intent intent = new Intent(SelectQuestionActivity.this, RiskAssessmentActivity.class);
                intent.putExtra("loginBean", loginBean);
                intent.putExtra("listBean", listBean);
                intent.putExtra("selectQuestionListBean", selectQuestionListBean);
                intent.putExtra("serverParamsBean", serverParamsBean);

                startActivity(intent);

                finish();
            }
        });
    }

    CompoundButton.OnCheckedChangeListener myCheckChangelistener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // TODO Auto-generated method stub
            //设置TextView的内容显示CheckBox的选择结果
            int i = buttonView.getId();
            if (i == R.id.ck_question_01) {
                question_01 = true;
            } else if (i == R.id.ck_question_02) {
                question_02 = true;
            } else {
                question_02 = false;
                question_01 = false;
            }
        }
    };

    private void initView() {
        ButterKnife.bind(this);
        listBean = (QueryHMBean.ServerParamsBean.LISTBean) getIntent().getSerializableExtra("listBean");
        loginBean = (LoginBean) getIntent().getSerializableExtra("loginBean");
        serverParamsBean = (CaseControlBean.ServerParamsBean) getIntent().getSerializableExtra("serverParamsBean");
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
