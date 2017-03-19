package com.allen.guide.module.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.allen.guide.R;
import com.allen.guide.adapter.GuideListAdapter;
import com.allen.guide.base.MVPBaseFragment;
import com.allen.guide.config.Constants;
import com.allen.guide.config.URLs;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.model.entities.SlideBean;
import com.allen.guide.module.guide_detail.GuideDetailActivity;
import com.allen.guide.utils.ToastUtils;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

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
    SliderLayout mSlider;
    private List<GuideBean> mGuideList;
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

    private void initSlider() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.silde_view, null);
        mSlider = (SliderLayout) view.findViewById(R.id.slider);
        //获取后台的广告栏信息
        mPresenter.getSlide();
    }

    @Override
    protected void initData() {
        mGuideList = new ArrayList<>();
        mAdapter = new GuideListAdapter(getActivity(), mGuideList);
        mGuideListView.setAdapter(mAdapter);
        mPresenter.getNetGuile();

        initSlider();
    }

    @Override
    public void updateGuideList(List<GuideBean> guideList) {
        Log.d("Allen-----", "HomeFragment->updateGuideList: ");
        mGuideList.removeAll(guideList);
        mGuideList.addAll(guideList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showSlide(List<SlideBean> slideList) {
        for (final SlideBean slideBean : slideList) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView
                    .description(slideBean.getGuideBean().getTitle())
                    .image(URLs.PIC + slideBean.getPicUrl());

            textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    Intent intent = new Intent(getActivity(), GuideDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.GUIDE_BEAN, slideBean.getGuideBean());
                    intent.putExtras(bundle);
                    getActivity().startActivity(intent);
                }
            });
            mSlider.addSlider(textSliderView);
        }
        mGuideListView.addHeaderView(mSlider);
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

    @Override
    public void onStop() {
        mSlider.stopAutoCycle();
        super.onStop();
    }
}
