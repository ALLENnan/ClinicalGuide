package com.allen.guide.module.retrieve;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.allen.guide.R;
import com.allen.guide.adapter.GuideListAdapter;
import com.allen.guide.base.MVPBaseFragment;
import com.allen.guide.config.Constants;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.allen.guide.R.id.guide_listView;

/**
 * @author Allen
 * @brief 首页
 * @date 17/2/28
 */
public class RetrieveFragment extends MVPBaseFragment<IRetrieveView, RetrievePresenter> implements IRetrieveView {

    @BindView(guide_listView)
    ListView mGuideListView;
    @BindView(R.id.query_spinner)
    Spinner mQuerySpinner;
    @BindView(R.id.query_auto_text)
    AutoCompleteTextView mQueryAutoText;
    @BindView(R.id.search_history_listView)
    ListView mSearchHistoryListView;
    @BindView(R.id.search_history_layout)
    LinearLayout mSearchHistoryLayout;

    private List<GuideBean> mGuideList;
    private List<String> mWordList;
    private GuideListAdapter mAdapter;
    private ArrayAdapter<String> mArrayAdapter;
    private ArrayAdapter mHistoryAdapter;
    private String mField;
    private List<String> mHistoryList;

    @Override
    public void onStart() {
        super.onStart();
    }

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
    public void setKeyWords(List<String> wordList) {
        mWordList = wordList;
        mArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mWordList);
        mQueryAutoText.setAdapter(mArrayAdapter);
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
        mWordList = new ArrayList<>();
        mAdapter = new GuideListAdapter(getActivity(), mGuideList);
        mGuideListView.setAdapter(mAdapter);

        mQuerySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] field = Constants.RETRIEVE_SRTS;
                mField = field[pos];
                doQuery();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mQuerySpinner.setSelection(0, true);
        mArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mWordList);
        mQueryAutoText.setAdapter(mArrayAdapter);

        mPresenter.getKeyWords();

        initAutoText();
        initHistory();
    }

    private void initAutoText() {
        mQueryAutoText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    if (mHistoryList.size() > 0) {
                        mSearchHistoryLayout.setVisibility(View.VISIBLE);
                        mGuideListView.setVisibility(View.GONE);
                    } else {
                        mSearchHistoryLayout.setVisibility(View.GONE);
                        mGuideListView.setVisibility(View.VISIBLE);
                    }
                } 
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 初始化历史记录
     */
    private void initHistory() {
        mSearchHistoryLayout.setVisibility(View.VISIBLE);
        mHistoryList = new ArrayList<>(mPresenter.getHistory());
        Collections.reverse(mHistoryList);
        mHistoryAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_item_history, mHistoryList);
        mSearchHistoryListView.setAdapter(mHistoryAdapter);
        mSearchHistoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mQueryAutoText.setText(mHistoryList.get(i) + "");
                mSearchHistoryLayout.setVisibility(View.GONE);
            }
        });
    }

    @OnClick({R.id.query_iv, R.id.container, R.id.clear_history_btn})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.query_iv:
                doQuery();
                break;
            case R.id.container:
                break;
            case R.id.clear_history_btn:
                mPresenter.clearHistory();
                mHistoryList.clear();
                mHistoryAdapter.notifyDataSetChanged();
                break;
        }
    }

    private void doQuery() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mQueryAutoText.getWindowToken(), 0);

        String str = mQueryAutoText.getText().toString();
        if(!TextUtils.isEmpty(str)) {
            mPresenter.retrieveGuiles(mField, str);
        }
        if (!mHistoryList.contains(str)) {
            mHistoryList.add(str);
        }

        mPresenter.saveHistory(new LinkedHashSet<String>(mHistoryList));
        mHistoryAdapter.notifyDataSetChanged();

        mSearchHistoryLayout.setVisibility(View.GONE);
        mGuideListView.setVisibility(View.VISIBLE);
    }
}
