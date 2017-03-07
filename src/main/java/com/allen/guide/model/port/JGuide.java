package com.allen.guide.model.port;

import com.allen.guide.model.entities.GuideBean;

import java.util.List;

public class JGuide {
    private int total;

    private List<GuideBean> rows;
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<GuideBean> getRows() {
        return rows;
    }

    public void setRows(List<GuideBean> rows) {
        this.rows = rows;
    }


    @Override
    public String toString() {
        return "JFile{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
