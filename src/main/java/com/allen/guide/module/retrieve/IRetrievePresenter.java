package com.allen.guide.module.retrieve;

import java.util.Set;

public interface IRetrievePresenter {
    
    void retrieveGuiles(String field, String query);

    void getKeyWords();
    
    Set<String> getHistory();
    
    void saveHistory(Set<String> strings);
    
    void clearHistory();
}
