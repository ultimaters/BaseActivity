package com.learn.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.learn.R;
import com.ultimate.BaseActivity;
import com.ultimate.widget.LabelView;

public class MainActivity extends BaseActivity {

	LinearLayout chatView;
	LinearLayout contactView;
	LinearLayout findView;
	LinearLayout meView;
	LinearLayout header;
	LabelView mLabelView;
	
	ImageButton right1;
	Button right2;
	
	private final int UPDATE_CONTENT_1 = 1;
	private final int UPDATE_CONTENT_2 = 2;
	private final int UPDATE_CONTENT_3 = 3;
	private final int UPDATE_CONTENT_4 = 4;
	
	private Handler mHandler = new Handler(){
		
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE_CONTENT_1:
				setHeaderCenterViewText("微信");
				setContent(chatView);
				setHeaderRightView(right1);
				break;
			case UPDATE_CONTENT_2:
				setHeaderCenterViewText("通讯录");
				setContent(contactView);
				setHeaderRightView(right2);
				break;
			case UPDATE_CONTENT_3:
				setHeaderCenterViewText("发现");
				setHeaderRightView(null);
				setContent(findView);
				break;
			case UPDATE_CONTENT_4:
				setHeaderCenterViewText("我");
				setHeaderRightView(null);
				setContent(meView);
				break;

			default:
				break;
			}
		};
	};

	@SuppressLint("NewApi")
	@Override
	public void initView() {
		setHeaderBackgroudResource(R.drawable.header_backgroud);
		
		setHeaderCenterViewText("微信");
		
		right1 = new ImageButton(this);
		right1.setImageResource(R.drawable.header_right_btn);
		right1.setBackground(getResources().getDrawable(R.drawable.header_btn_normal));
		setHeaderRightView(right1);
		
		right2 = new Button(this);
		right2.setText("添加");
		right2.setTextColor(Color.WHITE);
		right2.setTextSize(15);
		right2.setBackground(getResources().getDrawable(R.drawable.header_btn_normal));
		
		setFooterVisible(true);
		setFooterButtonBackgroud(0, android.R.drawable.ic_menu_call);
		setFooterButtonBackgroud(1, android.R.drawable.ic_menu_gallery);
		setFooterButtonBackgroud(2, android.R.drawable.ic_menu_camera);
		setFooterButtonBackgroud(3, android.R.drawable.ic_menu_compass);

//		chatView = new LinearLayout(this);
//		chatView.setLayoutParams(new RelativeLayout.LayoutParams(
//				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
//		chatView.setBackgroundColor(Color.WHITE);
//		TextView textView = new TextView(this);
//		textView.setText("xixixixixi");
//		textView.setTextSize(20);
//		chatView.addView(textView);
		
		chatView = (LinearLayout) LinearLayout.inflate(this, R.layout.layout_weixin, null);
		setContent(chatView);

//		contactView = new RelativeLayout(this);
//		contactView.setLayoutParams(new RelativeLayout.LayoutParams(
//				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
//		contactView.setBackgroundColor(Color.WHITE);
//		ListView listView = new ListView(this);
//		listView.setAdapter(new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_1, new String[] { "1", "2",
//						"3", "4", "5", "6", "7", "8", "9", "10"}));
//		contactView.addView(listView);
		contactView = (LinearLayout) LinearLayout.inflate(this, R.layout.layout_contact, null);

//		findView = new RelativeLayout(this);
//		findView.setLayoutParams(new RelativeLayout.LayoutParams(
//				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
//		findView.setBackgroundColor(Color.WHITE);
//		TextView textView2 = new TextView(this);
//		textView2.setText("Yeah!");
//		textView2.setTextSize(25);
//		findView.addView(textView2);
		findView = (LinearLayout) LinearLayout.inflate(this, R.layout.layout_find, null);

//		meView = new RelativeLayout(this);
//		meView.setLayoutParams(new RelativeLayout.LayoutParams(
//				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
//		meView.setBackgroundColor(Color.WHITE);
//		TextView textView3 = new TextView(this);
//		textView3.setText("Settings");
//		textView3.setTextSize(25);
//		meView.addView(textView3);
		meView = (LinearLayout) LinearLayout.inflate(this, R.layout.layout_me, null);
	}

	@Override
	public void initData() {
		right1.setPadding(45, 50, 45, 50);
		getFooterButton(0).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						new Thread(new Runnable() {

							@Override
							public void run() {
								mHandler.sendEmptyMessage(UPDATE_CONTENT_1);
							}
						}).start();
					}
				});

		getFooterButton(1).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						new Thread(new Runnable() {

							@Override
							public void run() {
								mHandler.sendEmptyMessage(UPDATE_CONTENT_2);
							}
						}).start();
					}
				});

		getFooterButton(2).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						new Thread(new Runnable() {

							@Override
							public void run() {
								mHandler.sendEmptyMessage(UPDATE_CONTENT_3);
							}
						}).start();
					}
				});

		getFooterButton(3).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						new Thread(new Runnable() {

							@Override
							public void run() {
								mHandler.sendEmptyMessage(UPDATE_CONTENT_4);
							}
						}).start();
					}
				});
		
		right1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, GroupChatActivity.class));
				
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

}
