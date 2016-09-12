package com.emamp.emampapp.business.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.emamp.emampapp.EmampApplication;
import com.emamp.emampapp.business.constants.EmampConstants;
import com.emamp.emampapp.utils.ActivityUtils;
import com.emamp.emampapp.utils.SoftInputUtil;

/**
 * @ClassName: BaseActivity
 * @Description: Activity页面基类。为便于统计，所有activity页面需继承该基类。
 * @date Sep 6, 2015 12:45:28 PM
 */
public abstract class BaseActivity extends FragmentActivity implements OnClickListener {

	protected final static String TAG = EmampConstants.LogConstants.Emamp_Tag;
	protected BaseActivity CTX = BaseActivity.this;
	public static boolean isForeground = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityUtils.setScreenVertical(this);
		ActivityUtils.hideTitleBar(this);
		SoftInputUtil.hideInputMethod(this);
		EmampApplication.getInstance().addActivity(this);
		injectView();
		setListener();
		getData();

	}

	protected void setListener() {

	}

	protected void onKeyEv(View v) {

	}

	protected abstract void injectView();

	protected abstract void getData();

	@Override
	public void onClick(View v) {
		onKeyEv(v);
	}

	@Override
	protected void onResumeFragments() {
		super.onResumeFragments();
		ActivityUtils.hideSoftInput(this);
	}

	protected void showInfo(String infoMsg) {
		Toast.makeText(this, infoMsg, Toast.LENGTH_SHORT).show();
	}

	public void goByIntent(Class<?> clazz, boolean isCloseSelf) {
		Intent intent = new Intent(CTX, clazz);
		startActivity(intent);
		if (isCloseSelf) {
			CTX.finish(isCloseSelf);
		}
	}

	@SuppressWarnings("unchecked")
	protected <A extends View> A getView(int id) {
		return (A) findViewById(id);
	}

	public void finish(boolean isEnd) {
		super.finish();
		ActivityUtils.runActivityAnim(this, isEnd);
	}

	@Override
	protected void onResume() {
		super.onResume();
		isForeground = true;
		if (EmampApplication.getInstance().getCurrentRunningActivity() == this) {
			EmampApplication.getInstance().setCurrentRunningActivity(null);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		isForeground = false;
		if (EmampApplication.getInstance().getCurrentRunningActivity() == this) {
			EmampApplication.getInstance().setCurrentRunningActivity(null);
		}
	}

}
