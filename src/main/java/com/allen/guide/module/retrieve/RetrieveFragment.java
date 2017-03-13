package com.allen.guide.module.retrieve;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;

import com.allen.guide.R;
import com.allen.guide.adapter.GuideListAdapter;
import com.allen.guide.base.MVPBaseFragment;
import com.allen.guide.config.Constants;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.utils.BaseUtil;
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
    
    @BindView(R.id.guide_listView)
    ListView mGuideListView;
    @BindView(R.id.query_spinner)
    Spinner mQuerySpinner;
    @BindView(R.id.query_auto_text)
    AutoCompleteTextView mQueryAutoText;

    private List<GuideBean> mGuideList;
    private GuideListAdapter mAdapter;
    private String mField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_retrieve, container, false);
        ButterKnife.bind(this, view);
        return view;
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

        mQuerySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] field = Constants.RETRIEVE_SRTS;
                mField = field[pos];
                Log.d("Allen-----", "RetrieveFragment->onItemSelected: " + mField);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mQuerySpinner.setSelection(0, true);

        String [] arr={"指南","干扰素"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arr);
        mQueryAutoText.setAdapter(adapter);
    }

    @OnClick({R.id.query_iv, R.id.container})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.query_iv:
                BaseUtil.hideSoftInput(getActivity());
                mPresenter.retrieveGuiles(mField, mQueryAutoText.getText().toString());
                break;
            case R.id.container:
                BaseUtil.hideSoftInput(getActivity());
                break;
        }
    }
}
