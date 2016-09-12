package com.emamp.emampapp.business.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * @ClassName: BaseFragment
 * @Description: fragment基类。封装fragment最基本操作
 * @date Sep 6, 2015 1:41:32 PM
 */
public abstract class BaseFragment extends Fragment implements OnClickListener {
	protected final static String TAG = BaseFragment.class.getSimpleName();

	protected Context CTX;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CTX = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(loadView(), container, false);
		injectView(layout);
		setListener();
		getData();
		return layout;
	}

	protected void showInfo(String msg) {
		Toast.makeText(CTX, msg, Toast.LENGTH_SHORT).show();
	}

	protected abstract void getData();

	protected abstract int loadView();

	protected abstract void injectView(View v);

	protected void setListener() {

	}

	public void onKeyEv(View v) {

	}

	@Override
	public void onClick(View v) {
		onKeyEv(v);
	}

}
