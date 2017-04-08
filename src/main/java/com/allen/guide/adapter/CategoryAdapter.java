package com.allen.guide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.guide.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mList;

    public CategoryAdapter(Context context, List<String> list) {
        mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
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
            convertView = mInflater.inflate(R.layout.list_item_category, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTitle.setText(mList.get(position));
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.container)
        LinearLayout mContainer;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}