package com.example.tidus.ristrat.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class SelectQuestionListBean implements Serializable {
    private Set<String> indexTable;

    public Set<String> getIndexTable() {
        return indexTable;
    }

    public void setIndexTable(Set<String> indexTable) {
        this.indexTable = indexTable;
    }

    @Override
    public String toString() {
        return "SelectQuestionListBean{" +
                "indexTable=" + indexTable +
                '}';
    }
}
