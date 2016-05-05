package com.han.chat.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.han.chat.adapter.MsgAdapter;
import com.han.chat.module.Msg;

public class MainActivity extends Activity implements OnClickListener {

	private List<Msg> data = new ArrayList<Msg>();;
	private ListView msgListView;
	private EditText inputText;
	private Button btnSent;
	private MsgAdapter msgAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initData();
		initView();
	}

	private void initView() {
		msgListView = (ListView) findViewById(R.id.msg_list_view);
		inputText = (EditText) findViewById(R.id.input_text);
		btnSent = (Button) findViewById(R.id.btn_sent);
		msgAdapter = new MsgAdapter(MainActivity.this, R.layout.msg_item, data);
		msgListView.setAdapter(msgAdapter);
		btnSent.setOnClickListener(this);
	}

	private void initData() {
		
		Msg msg = new Msg("Hello Mike!", Msg.TYPE_RECEIVED);
		data.add(msg);
		msg = new Msg("Hello Han!",Msg.TYPE_SENT);
		data.add(msg);
		msg = new Msg("Let's go!",Msg.TYPE_RECEIVED);
		data.add(msg);
	}

	@Override
	public void onClick(View v) {
		String content = inputText.getText().toString();
		if ("".equals(content)) {
			Toast.makeText(MainActivity.this, "不能发送空消息", Toast.LENGTH_SHORT).show();
			return;
		}
		Msg msg = new Msg(content, Msg.TYPE_SENT);
		data.add(msg);
		msgAdapter.notifyDataSetChanged();//当有消息的时候，刷新listview的显示
		msgListView.setSelection(data.size());//将listview定位到最后一行
		inputText.setText("");
	}

}
