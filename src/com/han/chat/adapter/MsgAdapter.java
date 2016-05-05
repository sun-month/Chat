package com.han.chat.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.han.chat.activity.R;
import com.han.chat.module.Msg;

public class MsgAdapter extends ArrayAdapter<Msg> {

	private int resource;

	public MsgAdapter(Context context, int resource, List<Msg> objs) {
		super(context, resource, objs);
		this.resource = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Msg msg = getItem(position);
		ViewHolder holder;
		if (convertView==null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(getContext()).inflate(resource, null);
			holder.left_layout = (LinearLayout) convertView.findViewById(R.id.left_layout);
			holder.right_layout = (LinearLayout) convertView.findViewById(R.id.right_layout);
			holder.left_msg = (TextView) convertView.findViewById(R.id.left_msg);
			holder.right_msg = (TextView) convertView.findViewById(R.id.right_msg);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		if (msg.getType() == Msg.TYPE_RECEIVED) {
			holder.left_layout.setVisibility(View.VISIBLE);
			holder.right_layout.setVisibility(View.GONE);
			holder.left_msg.setText(msg.getContent());
		} else if(msg.getType() == Msg.TYPE_SENT){
			holder.right_layout.setVisibility(View.VISIBLE);
			holder.left_layout.setVisibility(View.GONE);
			holder.right_msg.setText(msg.getContent());
		}
		
		return convertView;
	}

	static class ViewHolder {
		LinearLayout left_layout;
		LinearLayout right_layout;
		TextView left_msg;
		TextView right_msg;
	}
}
