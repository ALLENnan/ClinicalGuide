package com.allen.guide.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.guide.R;
import com.allen.guide.ui.fragments.HomeFragment;
import com.allen.guide.ui.fragments.MeFragment;
import com.allen.guide.ui.fragments.RetrieveFragment;
import com.allen.guide.utils.UserUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.btn_home)
    TextView mBtnHome;
    @BindView(R.id.btn_retrieve)
    TextView mBtnRetrieve;
    @BindView(R.id.btn_me)
    TextView mBtnMe;
    private TextView[] mTabs;
    private Fragment[] mFragmentArray;
    private HomeFragment mHomeFragment;
    private RetrieveFragment mRetrieveFragment;
    private MeFragment mMeFragment;
    private int mCurrentTabIndex;
    private boolean is2CallBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initTabs();
        initFragments();
    }

    private void initTabs() {
        mTabs = new TextView[3];
        mTabs[0] = mBtnHome;
        mTabs[1] = mBtnRetrieve;
        mTabs[2] = mBtnMe;
        mTabs[0].setSelected(true);
    }

    private void initFragments() {
        Log.d("Allen-----", "MainActivity->initFragments: ");
        mHomeFragment = new HomeFragment();
        mRetrieveFragment = new RetrieveFragment();
        mMeFragment = new MeFragment();
        mFragmentArray = new Fragment[]{mHomeFragment, mRetrieveFragment, mMeFragment};
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mHomeFragment)
                .show(mHomeFragment).commit();
        mCurrentTabIndex = 0;
    }

    private void setTabSelection(int index) {
        if (mCurrentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(mFragmentArray[mCurrentTabIndex]);
            if (!mFragmentArray[index].isAdded()) {
                trx.add(R.id.fragment_container, mFragmentArray[index]);
            }
            trx.show(mFragmentArray[index]).commit();
        }
        mTabs[mCurrentTabIndex].setSelected(false);
        //把当前tab设为选中状态
        mTabs[index].setSelected(true);
        mCurrentTabIndex = index;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!is2CallBack) {
                is2CallBack = true;
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        is2CallBack = false;
                    }
                }, 1500);
            } else {
                finish();
            }
        }
        return true;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        int position = savedInstanceState.getInt("pos");
        setTabSelection(position);

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("pos", mCurrentTabIndex);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setTabSelection(0);
    }

    @OnClick({R.id.btn_home, R.id.btn_retrieve, R.id.btn_me})
    public void onClick(View view) {
        int index = 0;
        switch (view.getId()) {
            case R.id.btn_home:
                index = 0;
                break;
            case R.id.btn_retrieve:
                index = 1;
                break;
            case R.id.btn_me:
                if (UserUtil.getCurrentUser(this) == null) {
                    startActivity(new Intent(this, LoginActivity.class));
                    return;
                } 
                index = 2;
                break;
        }
        setTabSelection(index);
    }
}
