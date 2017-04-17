package com.allen.guide.module.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allen.guide.R;
import com.allen.guide.base.BaseFragment;
import com.allen.guide.model.entities.UserBean;
import com.allen.guide.module.MainActivity;
import com.allen.guide.module.collect.CollectActivity;
import com.allen.guide.module.others.RecordActivity;
import com.allen.guide.utils.UserUtil;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Allen
 * @brief
 * @date 17/3/4
 */
public class MeFragment extends BaseFragment {

    @BindView(R.id.head_iv)
    CircleImageView mHeadIv;
    @BindView(R.id.name_tv)
    TextView mNameTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    @Override
    protected void initData() {
        UserBean userbean = UserUtil.getCurrentUser(getActivity());
        if (userbean == null) {
            return;
        } else {
            Glide.with(getActivity()).load(R.mipmap.headshot_default).into(mHeadIv);
            mNameTv.setText(userbean.getUsername());
        }
    }

    @OnClick({R.id.logOutBtn, R.id.info_tv,R.id.record_tv, R.id.collect_tv, R.id.setting_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.info_tv:
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
                break;
            case R.id.record_tv:
                startActivity(new Intent(getActivity(), RecordActivity.class));
                break;
            case R.id.collect_tv:
                startActivity(new Intent(getActivity(), CollectActivity.class));
                break;
            case R.id.setting_tv:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.logOutBtn:
                UserUtil.logOut(getActivity());
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
        }
    }
}
