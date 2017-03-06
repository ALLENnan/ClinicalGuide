package com.allen.guide.model.entities;

/**
 * @author Allenb
 * @brief 接受后台返回的boolean值
 * @date 17/3/6
 */
public class JResult {
    private Boolean isSucceed;

    public JResult(Boolean isSucceed) {
        this.isSucceed = isSucceed;
    }

    public Boolean getSucceed() {
        return isSucceed;
    }

    public void setSucceed(Boolean succeed) {
        isSucceed = succeed;
    }
}
