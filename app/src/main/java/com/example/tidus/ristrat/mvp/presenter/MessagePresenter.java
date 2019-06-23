package com.example.tidus.ristrat.mvp.presenter;

import com.example.tidus.ristrat.callback.IRequestCallback;
import com.example.tidus.ristrat.contract.IMessageContract;
import com.example.tidus.ristrat.mvp.model.MessageModel;

import java.util.HashMap;

public class MessagePresenter extends IMessageContract.MessagePresenter {
    private MessageModel messageModel;
    private IMessageContract.IMessageView iMessageView;


    public MessagePresenter(IMessageContract.IMessageView iMessageView) {
        messageModel = new MessageModel();
        this.iMessageView = iMessageView;
    }

    @Override
    public void getMessage(HashMap<String, Object> params) {
        model.getMessage(params, new IRequestCallback() {
            @Override
            public void onSuccess(Object result) {
                if (iMessageView != null) {
                    iMessageView.onMessageSuccess(result);
                }
            }

            @Override
            public void onFailed(Object error) {
                if (iMessageView != null) {
                    iMessageView.onFailed(error);
                }
            }
        });
    }
}
