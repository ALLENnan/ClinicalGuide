package com.allen.guide.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.guide.R;
import com.allen.guide.config.Constants;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.module.guide_detail.GuideDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Allen
 * @brief 指南列表adapter
 * @date 17/3/2
 */
public class GuideListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<GuideBean> mGuideList;

    public GuideListAdapter(Context context, List<GuideBean> guideList) {
        mContext = context;
        this.mInflater = LayoutInflater.from(context);
        mGuideList = guideList;
    }

    @Override
    public int getCount() {
        return mGuideList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_home, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTitle.setText(mGuideList.get(position).getTitle());
        holder.mSource.setText(mGuideList.get(position).getSource());
        holder.mFavour.setText(mGuideList.get(position).getFavour()+"");
        
        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GuideDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.GUIDE_BEAN, mGuideList.get(position));
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }
    
    static class ViewHolder {
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.source)
        TextView mSource;
        @BindView(R.id.favour)
        TextView mFavour;
        @BindView(R.id.container)
        LinearLayout mContainer;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}