package com.allen.guide.module.collect;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.allen.guide.R;
import com.allen.guide.adapter.GuideListAdapter;
import com.allen.guide.base.MVPBaseActivity;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollectActivity extends MVPBaseActivity<ICollectView, CollectPresenter> implements ICollectView {
    @BindView(R.id.back_btn)
    ImageButton mBackBtn;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.right_tv)
    TextView mRightTv;
    @BindView(R.id.guide_listView)
    ListView mGuideListView;
    private List<GuideBean> mGuideList;
    private GuideListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);
        initView();
        initNetData();
    }

    @Override
    protected CollectPresenter initPresenter() {
        return new CollectPresenter();
    }

    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }

    protected void initView() {
        mTitleTv.setText("我的收藏");
        mGuideList = new ArrayList<>();
        mAdapter = new GuideListAdapter(this, mGuideList);
        mGuideListView.setAdapter(mAdapter);
    }

    private void initNetData() {
        mPresenter.getCollectGuile();
    }

    @Override
    public void updateGuideList(List<GuideBean> guideList) {
        mGuideList.removeAll(guideList);
        mGuideList.addAll(guideList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {
        ToastUtils.showMessage(this, msg);
    }

    @Override
    public void showToast(String msg) {

    }
}
