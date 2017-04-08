package com.allen.guide.module.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.allen.guide.R;
import com.allen.guide.adapter.CategoryAdapter;
import com.allen.guide.base.MVPBaseFragment;
import com.allen.guide.config.Constants;
import com.allen.guide.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFragment extends MVPBaseFragment<ICategoryView, CategoryPresenter> implements ICategoryView {


    @BindView(R.id.category_listView)
    ListView mCategoryListView;
    private List<String> mCategoryList;//指南类别表
    private CategoryAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected CategoryPresenter initPresenter() {
        return new CategoryPresenter();
    }

    @Override
    protected void initData() {
        mCategoryList = new ArrayList<>();
        mAdapter = new CategoryAdapter(getActivity(), mCategoryList);
        mCategoryListView.setAdapter(mAdapter);
        mCategoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CategoryDetailActivity.class);
                intent.putExtra(Constants.PARAM_CATEGORY, mCategoryList.get(position).toString());
                getActivity().startActivity(intent);
            }
        });
        //获取指南的类别表
        mPresenter.getCategory();
    }


    @Override
    public void updataCategory(List<String> categoryList) {
        mCategoryList.removeAll(categoryList);
        mCategoryList.addAll(categoryList);
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

    }
}
