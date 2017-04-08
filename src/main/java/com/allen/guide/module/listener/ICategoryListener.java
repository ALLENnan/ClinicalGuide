package com.allen.guide.module.listener;

import java.util.List;

public interface ICategoryListener extends IBaseListener {

    void onSuccess(List<String> categoryList);
}
