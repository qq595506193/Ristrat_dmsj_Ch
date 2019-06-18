package com.example.tidus.ristrat.mvp.view.iview;

import com.example.tidus.ristrat.base.IView;
import com.example.tidus.ristrat.bean.Commit;
import com.example.tidus.ristrat.bean.ReportBean;

public interface IReportView extends IView {
    void success(ReportBean reportBean);
    void succ(Commit commit);

    void faild(Throwable t);

}
