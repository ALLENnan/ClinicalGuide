package com.allen.guide.module.listener;

import java.util.List;

public interface IWordListener extends IBaseListener {

    void onSuccess(List<String> wordList);
}
