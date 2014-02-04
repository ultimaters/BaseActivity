package com.ultimate;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.ultimate.app.AppManager;
import com.ultimate.attr.Content;
import com.ultimate.attr.Footer;
import com.ultimate.attr.Header;
import com.ultimate.util.UIHelper;

/**
 * 3-Segment Activity(Header+Content+Footer)
 * 
 * @author Max Xu
 * 
 */
public abstract class BaseActivity extends Activity implements Header, Footer,
		Content {

	/* Global layout */
	private RelativeLayout mGlobalLayout;
	private RelativeLayout.LayoutParams mHeaderLayoutParams;
	private RelativeLayout.LayoutParams mFooterLayoutParams;
	private RelativeLayout.LayoutParams mContentLayoutParams;

	/* Header */
	private RelativeLayout mHeaderLayout;
	private RelativeLayout.LayoutParams mHeaderLeftLayoutParams;
	private RelativeLayout.LayoutParams mHeaderCenterLayoutParams;
	private RelativeLayout.LayoutParams mHeaderRightLayoutParams;

	private RelativeLayout.LayoutParams mHeaderLeftViewLayoutParams;
	private RelativeLayout.LayoutParams mHeaderCenterViewLayoutParams;
	private RelativeLayout.LayoutParams mHeaderRightViewLayoutParams;

	private RelativeLayout mHeaderLeftLayout;
	private RelativeLayout mHeaderRightLayout;
	private RelativeLayout mHeaderCenterLayout;
	private View mHeaderLeftView;
	private TextView mHeaderCenterView;
	private View mHeaderRightView;

	/** The layout_width of header. Default is 95. */
	private int HEADER_LAYOUT_HEIGHT = 95;
	/** The backgroud resource(Drawable object) of header. Set 0 to remove it. */
	private int mHeaderBackgroudResource = android.R.drawable.dark_header;
	/** The backgroud color of header center view. Default is #03b9fc. */
	private int mHeaderBackgroudColor = Color.parseColor("#03b9fc");
	/** The text of header center view. Default is "Title". */
	private String mHeaderCenterViewText = "Title";
	/** The text size of header center view. Default is 20. */
	private float mHeaderCenterViewTextSize = 20;
	/** The text color of header center view. Default is WHITE. */
	private int mHeaderCenterViewTextColor = Color.WHITE;

	/* Footer */
	private LinearLayout mFooterLayout;
	private LinearLayout.LayoutParams mFooterButtonLayoutParams;

	private ArrayList<RadioButton> mFooterButtons = new ArrayList<RadioButton>();

	/**
	 * Default is false. If {@link mIsFooterVisible} is true, the footer view
	 * will be visible.
	 */
	private boolean mIsFooterVisible = false;
	/** The layout_width of footer. Default is WRAP_CONTENT. */
	private int FOOTER_LAYOUT_HEIGHT = LayoutParams.WRAP_CONTENT;
	/** The backgroud resource(Drawable object) of footer. Set 0 to remove it. */
	private int mFooterBackgroudResource = android.R.drawable.bottom_bar;
	/** The backgroud of footer. */
	private int mFooterBackgroudColor = Color.BLACK;
	/** The backgroud of footer button. */
	private ArrayList<Integer> mFooterButtonBackgroud = new ArrayList<Integer>();
	/** The max number of footer button. */
	private int FOOTER_BUTTONS_MAX_NUM = 10;
	/** The current number of footer button. used for subclass. */
	private int FOOTER_BUTTONS_NUM = 4;

	/* Content */
	private RelativeLayout mContentLayout;
	private View mContent;
	private RelativeLayout.LayoutParams mContentViewParams;

	/**
	 * true means Activity is initing, false means Activity has init. Default is
	 * true.
	 */
	private boolean mIsInit = true;

	/* ID */
	private int HEADER_LAYOUT_ID = 0x00000001;
	private int CONTENT_LAYOUT_ID = 0x00000002;
	private int FOOTER_LAYOUT_ID = 0x00000003;
	private int HEADER_LEFT_ID = 0x00000010;
	private int HEADER_CENTER_ID = 0x00000011;
	private int HEADER_RIGHT_ID = 0x00000012;
	private int HEADER_LEFT_VIEW_ID = 0x00000100;
	private int HEADER_CENTER_VIEW_ID = 0x00000101;
	private int HEADER_RIGHT_VIEW_ID = 0x00000102;

	/** init the component(allocate memory) */
	private void initAttributes() {
		// header
		mHeaderLayout = new RelativeLayout(this);

		// header left
		mHeaderLeftLayout = new RelativeLayout(this);
		mHeaderLeftLayoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		mHeaderLeftView = new ImageView(this);
		mHeaderLeftViewLayoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		// header center
		mHeaderCenterLayout = new RelativeLayout(this);
		mHeaderCenterLayoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		mHeaderCenterView = new TextView(this);
		mHeaderCenterViewLayoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		// header right
		mHeaderRightLayout = new RelativeLayout(this);
		mHeaderRightLayoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		mHeaderRightView = new ImageView(this);
		mHeaderRightViewLayoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		// footer
		mFooterLayout = new LinearLayout(this);
		mFooterButtons = new ArrayList<RadioButton>(FOOTER_BUTTONS_MAX_NUM);
		mFooterButtonBackgroud = new ArrayList<Integer>(FOOTER_BUTTONS_MAX_NUM);
		mFooterButtonLayoutParams = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, FOOTER_LAYOUT_HEIGHT);
		for (int i = 0; i < FOOTER_BUTTONS_MAX_NUM; i++) {
			mFooterButtons.add(new RadioButton(this));
			mFooterButtonBackgroud.add(i, android.R.drawable.ic_menu_view);
		}

		// content
		mContentLayout = new RelativeLayout(this);
		mContent = new View(this);
		mContentViewParams = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		// global
		mGlobalLayout = new RelativeLayout(this);
		mHeaderLayoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, HEADER_LAYOUT_HEIGHT);
		mFooterLayoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		mContentLayoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

	}

	/** init the Header View */
	private void initHeader() {
		mHeaderLayout.setId(HEADER_LAYOUT_ID);
		mHeaderLayout.setLayoutParams(mHeaderLayoutParams);
		mHeaderLayout.setBackgroundColor(mHeaderBackgroudColor);
		mHeaderLayout.setBackgroundResource(mHeaderBackgroudResource);
		mHeaderLayout.setPadding(10, 10, 10, 10);

		/* Left View */
		mHeaderLeftLayout.setId(HEADER_LEFT_ID);
		mHeaderLeftLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		mHeaderLeftLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
		mHeaderLeftLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

		mHeaderLeftView.setId(HEADER_LEFT_VIEW_ID);

		mHeaderLeftLayout.addView(mHeaderLeftView, mHeaderLeftViewLayoutParams);
		mHeaderLayout.addView(mHeaderLeftLayout, mHeaderLeftLayoutParams);

		/* Center View */
		mHeaderCenterLayout.setId(HEADER_CENTER_ID);
		mHeaderCenterLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		mHeaderCenterLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
		mHeaderCenterLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

		mHeaderCenterView.setId(HEADER_CENTER_VIEW_ID);
		mHeaderCenterView.setText(mHeaderCenterViewText);
		mHeaderCenterView.setTextSize(mHeaderCenterViewTextSize);
		mHeaderCenterView.setTextColor(mHeaderCenterViewTextColor);

		mHeaderCenterLayout.addView(mHeaderCenterView,
				mHeaderCenterViewLayoutParams);
		mHeaderLayout.addView(mHeaderCenterLayout, mHeaderCenterLayoutParams);

		/* Right View */
		mHeaderRightLayout.setId(HEADER_RIGHT_ID);
		mHeaderRightLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		mHeaderRightLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
		mHeaderRightLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

		mHeaderRightView.setId(HEADER_RIGHT_VIEW_ID);

		mHeaderRightLayout.addView(mHeaderRightView,
				mHeaderRightViewLayoutParams);
		mHeaderLayout.addView(mHeaderRightLayout, mHeaderRightLayoutParams);
	}

	/** init the Footer View */
	private void initFooter() {
		mFooterLayout.setId(FOOTER_LAYOUT_ID);
		mFooterLayout.setLayoutParams(mFooterLayoutParams);
		mFooterLayout.setOrientation(LinearLayout.HORIZONTAL);
		mFooterLayout.setBackgroundColor(mFooterBackgroudColor);
		mFooterLayout.setBackgroundResource(mFooterBackgroudResource);

		/* add footer buttons */
		for (int i = 0; i < FOOTER_BUTTONS_NUM; i++) {
			mFooterButtons.get(i).setButtonDrawable(
					mFooterButtonBackgroud.get(i));
			mFooterButtons.get(i).setClickable(true);

			// the layout outside button
			LinearLayout linearLayout = new LinearLayout(this);
			linearLayout.setOrientation(LinearLayout.VERTICAL);
			linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			layoutParams.weight = 1;
			linearLayout.setLayoutParams(layoutParams);

			linearLayout.addView(mFooterButtons.get(i),
					mFooterButtonLayoutParams);
			mFooterLayout.addView(linearLayout);
		}
	}

	/** init the Content View */
	private void initContent() {
		mContentLayout.setId(CONTENT_LAYOUT_ID);
		mContentLayout.addView(mContent, mContentViewParams);
	}

	/** init the Global View */
	private void initActivity() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		mGlobalLayout.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		/* add Header Layout */
		mHeaderLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		mGlobalLayout.addView(mHeaderLayout, mHeaderLayoutParams);

		/* add Footer Layout */
		mFooterLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		if (mIsFooterVisible) {
			mGlobalLayout.addView(mFooterLayout, mFooterLayoutParams);
		}

		/* add Content Layout */
		mContentLayoutParams.addRule(RelativeLayout.BELOW, HEADER_LAYOUT_ID);
		mContentLayoutParams.addRule(RelativeLayout.ABOVE, FOOTER_LAYOUT_ID);
		mGlobalLayout.addView(mContentLayout, mContentLayoutParams);

		mIsInit = false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initAttributes();

		// for subclass
		initView();

		initHeader();
		initFooter();
		initContent();
		initActivity();
		setContentView(mGlobalLayout);

		// for subclass
		initData();

		// add Activity to Activity Stack
		AppManager.getAppManager().addActivity(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// finish this Activity
		AppManager.getAppManager().finishActivity(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "ÍË  ³ö");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			AppManager.getAppManager().exitApplication(BaseActivity.this);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			UIHelper.showNoticeDialog(BaseActivity.this);
		}

		return super.onKeyDown(keyCode, event);
	}

	/**
	 * For subclass, the HeaderView¡¢FooterView¡¢ContentView and other settings
	 * related to view display should be here. <h1>Note</h1> {@link #initView()}
	 * must be called before {@link super.onCreate()} in {@link #onCreate()}
	 * method. But you needn't do that.
	 * */
	public abstract void initView();

	/**
	 * For subclass, the view method setting(like set listener) should be here.
	 * */
	public abstract void initData();

	@Override
	public void setHeaderBackgroudResource(int resId) {
		if (!mIsInit && mHeaderBackgroudResource != resId) {
			mHeaderLayout.setBackgroundResource(resId);
		}
		mHeaderBackgroudResource = resId;
	}

	@Override
	@Deprecated
	public void setHeaderBackgroudColor(int color) {
		if (!mIsInit && mHeaderBackgroudColor != color) {
			mHeaderLayout.setBackgroundColor(color);
		}
		mHeaderBackgroudColor = color;
	}

	@Override
	public void setHeaderLeftView(View leftView) {
		if (leftView != null) {
			if (!mIsInit && !mHeaderLeftView.equals(leftView)) {
				mHeaderLeftLayout.removeView(mHeaderLeftView);
				mHeaderLeftLayout
						.addView(leftView, mHeaderLeftViewLayoutParams);
			}
			mHeaderLeftView = leftView;
		} else {
			if (!mIsInit) {
				mHeaderLeftLayout.removeView(mHeaderLeftView);
			}
		}

	}

	@Override
	public void setHeaderRightView(View rightView) {
		if (rightView != null) {
			if (!mIsInit && !mHeaderRightView.equals(rightView)) {
				mHeaderRightLayout.removeView(mHeaderRightView);
				mHeaderRightLayout.addView(rightView,
						mHeaderRightViewLayoutParams);
			}
			mHeaderRightView = rightView;
		} else {
			if (!mIsInit) {
				mHeaderRightLayout.removeView(mHeaderRightView);
			}
		}
	}

	@Override
	public void setHeaderCenterViewText(String centerText) {
		setHeaderCenterViewText(centerText, mHeaderCenterViewTextColor);
	}

	@Override
	public void setHeaderCenterViewText(String centerText, int color) {
		setHeaderCenterViewText(centerText, color, mHeaderCenterViewTextSize);
	}

	@Override
	public void setHeaderCenterViewText(String centerText, int color, float size) {
		if (!mIsInit && !mHeaderCenterViewText.equals(centerText)) {
			mHeaderCenterView.setText(centerText);
		}
		if (!mIsInit && mHeaderCenterViewTextColor != color) {
			mHeaderCenterView.setTextColor(color);
		}
		if (!mIsInit && mHeaderCenterViewTextSize != size) {
			mHeaderCenterView.setTextSize(size);
		}
		mHeaderCenterViewText = centerText;
		mHeaderCenterViewTextColor = color;
		mHeaderCenterViewTextSize = size;
	}

	@Override
	public void setFooterVisible(boolean vis) {
		mIsFooterVisible = vis;
	}

	@Override
	public void setFooterBackgroudResource(int resId) {
		mFooterBackgroudResource = resId;
	}

	@Override
	public void setFooterBackgroudColor(int color) {
		mFooterBackgroudColor = color;
	}

	@Override
	public void setFooterButtonsNum(int num) {
		if (num < 0 || num > FOOTER_BUTTONS_MAX_NUM) {
			System.err.println("Footer button number error.");
		} else {
			FOOTER_BUTTONS_NUM = num;
		}
	}

	@Override
	public RadioButton getFooterButton(int index) {
		if (index < 0 || index > FOOTER_BUTTONS_NUM - 1) {
			System.err.println("Footer button index is out of bound.");
		} else {
			return mFooterButtons.get(index);
		}
		return null;
	}

	@Override
	public void setFooterButtonBackgroud(int btnId, int bgResId) {
		if (btnId < 0 || btnId > FOOTER_BUTTONS_NUM - 1) {
			System.err.println("Footer button index is out of bound.");
		} else {
			if (mFooterButtonBackgroud.get(btnId) != bgResId) {
				mFooterButtons.get(btnId).setButtonDrawable(bgResId);
			}
			mFooterButtonBackgroud.set(btnId, bgResId);
		}
	}

	@Override
	public void setContent(View contentView) {
		// When change the content view after application has init.
		// Set the content view manually.
		if (!mIsInit && mContentLayout != null && !contentView.equals(mContent)) {
			mContentLayout.removeView(mContent);
			mContentLayout.addView(contentView, mContentViewParams);
		}
		mContent = contentView;

	}

}
