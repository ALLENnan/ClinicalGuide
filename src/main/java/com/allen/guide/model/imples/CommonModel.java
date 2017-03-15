package com.allen.guide.model.imples;

import android.content.Context;

import com.allen.guide.App;
import com.allen.guide.config.Constants;
import com.allen.guide.model.interfaces.ICommonModel;
import com.allen.guide.net.VolleyManager;
import com.allen.guide.utils.SharedPreferencesUtil;

import java.util.Set;

public class CommonModel implements ICommonModel {
    private Context mContext;
    private static volatile CommonModel sGuideModel;
    private VolleyManager.RequestBuilder mBuilder;

    private CommonModel() {
        mContext = App.getContext();
        mBuilder = new VolleyManager.RequestBuilder();

    }

    public static CommonModel getInstance() {
        if (sGuideModel == null) {
            synchronized (CommonModel.class) {
                if (sGuideModel == null) {
                    sGuideModel = new CommonModel();
                }
            }
        }
        return sGuideModel;
    }

    private void add(VolleyManager.RequestBuilder builder) {
        VolleyManager.getInstance(App.getContext()).add(mBuilder);
    }


    @Override
    public Set<String> getHistory() {
        return SharedPreferencesUtil.getDataSet(mContext, Constants.KEY_HISTORY);
    }

    @Override
    public void saveHistory(Set<String> strings) {
        SharedPreferencesUtil.putDataSet(mContext, Constants.KEY_HISTORY, strings);
    }

    @Override
    public void clearHistory() {
        SharedPreferencesUtil.clear(mContext);
    }
}
