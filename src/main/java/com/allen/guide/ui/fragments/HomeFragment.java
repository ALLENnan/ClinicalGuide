package com.allen.guide.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.allen.guide.R;
import com.allen.guide.adapter.GuideListAdapter;
import com.allen.guide.base.MVPBaseFragment;
import com.allen.guide.model.entities.JFile;
import com.allen.guide.presenter.imples.HomePresenter;
import com.allen.guide.ui.interfaces.IHomeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Allen
 * @brief 首页
 * @date 17/2/28
 */
public class HomeFragment extends MVPBaseFragment<IHomeView, HomePresenter> implements IHomeView {

    @BindView(R.id.guide_listView)
    ListView mGuideListView;
    private List<JFile.File> mGuideList;
    private GuideListAdapter mAdapter;

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Allen-----", "HomeFragment->onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        mGuideList = new ArrayList<>();
        mAdapter = new GuideListAdapter(getActivity(), mGuideList);
        mGuideListView.setAdapter(mAdapter);
        mPresenter.getNetGuile();
    }

    @Override
    public void updateGuideList(List<JFile.File> guideList) {
        Log.d("Allen-----", "HomeFragment->updateGuideList: ");
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
    public void showErrorMsg(String msg) {

    }

    @Override
    public void showToast(String msg) {

    }
}
