package com.allen.guide.model.interfaces;

import java.util.Set;

public interface ICommonModel {
    Set<String> getHistory();

    void saveHistory(Set<String> strings);

    void clearHistory();
}
