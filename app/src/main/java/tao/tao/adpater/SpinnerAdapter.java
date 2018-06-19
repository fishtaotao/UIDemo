package tao.tao.adpater;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tao.tao.com.R;
import tao.tao.model.DataBean;

/**
 * Author：wjt on 2018/4/23 0023 15:23
 */


public class SpinnerAdapter extends BaseAdapter {

    public List<DataBean> mList = null;
    public Activity mContext;

    public SpinnerAdapter(List<DataBean> mList, Activity mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            View view = mContext.getLayoutInflater().inflate(R.layout.adapter_item, null);
            TextView textView = (TextView) view.findViewById(R.id.adapter_item_text);
            textView.setText(mList.get(position).getAnimName());

            return textView;
        } else {
            return convertView;
        }

    }
}