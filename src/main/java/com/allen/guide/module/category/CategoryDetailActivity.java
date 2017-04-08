package com.allen.guide.module.category;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.allen.guide.R;
import com.allen.guide.adapter.GuideListAdapter;
import com.allen.guide.base.BaseActivity;
import com.allen.guide.config.Constants;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.model.imples.GuideModel;
import com.allen.guide.model.interfaces.IGuideModel;
import com.allen.guide.module.listener.IGuideListener;
import com.allen.guide.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryDetailActivity extends BaseActivity {

    @BindView(R.id.guide_listView)
    ListView mGuideListView;
    private GuideListAdapter mGuideListAdapter;
    private List<GuideBean> mGuideList;
    private IGuideModel mGuideModel;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        ButterKnife.bind(this);
        getIntentData();
        initView();
        initData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        category = intent.getStringExtra(Constants.PARAM_CATEGORY);
    }

    private void initView() {
        mGuideList = new ArrayList<>();
        mGuideListAdapter = new GuideListAdapter(this, mGuideList);
        mGuideListView.setAdapter(mGuideListAdapter);
    }

    private void initData() {
        mGuideModel = GuideModel.getInstance();

        //访问后台获取某一类别下的指南
        mGuideModel.getGuideList(category, new IGuideListener() {
            @Override
            public void onSuccess(List<GuideBean> guideList) {
                mGuideList.removeAll(guideList);
                mGuideList.addAll(guideList);
                mGuideListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onSuccess(String msg) {
            }

            @Override
            public void onFail(String msg) {
                ToastUtils.showMessage(getApplicationContext(), msg);
            }

            @Override
            public void onError(String msg) {
                ToastUtils.showMessage(getApplicationContext(), msg);
            }
        });
    }
}
