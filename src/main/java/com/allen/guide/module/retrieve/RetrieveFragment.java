package com.allen.guide.module.retrieve;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.allen.guide.R;
import com.allen.guide.adapter.GuideListAdapter;
import com.allen.guide.base.MVPBaseFragment;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Allen
 * @brief 首页
 * @date 17/2/28
 */
public class RetrieveFragment extends MVPBaseFragment<IRetrieveView, RetrievePresenter> implements IRetrieveView {

    @BindView(R.id.query_et)
    EditText mQueryEt;
    @BindView(R.id.guide_listView)
    ListView mGuideListView;

    private List<GuideBean> mGuideList;
    private GuideListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_retrieve, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.query_iv)
    public void onClick() {
        mPresenter.retrieveGuiles("title", mQueryEt.getText().toString());
    }

    @Override
    public void updateGuideList(List<GuideBean> guideList) {
        mGuideList.clear();
        if (guideList.size() == 0) {
            showToast("暂无结果");
        } else {
            mGuideList.addAll(guideList);
        }
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
        ToastUtils.showMessage(getActivity(), msg);
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showMessage(getActivity(), msg);
    }

    @Override
    protected RetrievePresenter initPresenter() {
        return new RetrievePresenter();
    }

    @Override
    protected void initData() {
        mGuideList = new ArrayList<>();
        mAdapter = new GuideListAdapter(getActivity(), mGuideList);
        mGuideListView.setAdapter(mAdapter);
    }
}
