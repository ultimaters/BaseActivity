package com.learn.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ultimate.BaseActivity;
import com.ultimate.app.AppManager;

import com.learn.R;

public class GroupChatActivity extends BaseActivity {

	Button leftButton;
	LinearLayout content;
	
	@SuppressLint("NewApi")
	@Override
	public void initView() {
		setHeaderBackgroudResource(R.drawable.header_backgroud);
		setHeaderCenterViewText("ÈºÁÄ");
		
		leftButton = new Button(this);
		leftButton.setText("È¡Ïû");
		leftButton.setTextColor(Color.WHITE);
		leftButton.setTextSize(15);
		leftButton.setBackground(getResources().getDrawable(R.drawable.header_btn_normal));
		setHeaderLeftView(leftButton);
		
		content = (LinearLayout)LinearLayout.inflate(this, R.layout.layout_groupchat, null);
		setContent(content);

	}

	@Override
	public void initData() {
		leftButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AppManager.getAppManager().finishCurrentActivity();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			AppManager.getAppManager().finishCurrentActivity();
		}
		return true;
	}

}
