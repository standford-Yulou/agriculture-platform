package com.emamp.emampapp.business.ui.activity;

import java.net.NetworkInterface;
import java.util.Enumeration;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.alibaba.fastjson.JSONObject;
import com.emamp.emampapp.R;
import com.emamp.emampapp.business.constants.UrlConstant;
import com.emamp.emampapp.business.ui.base.BaseActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

	@ViewInject(R.id.et_userinfo)
	private EditText mEtUserInfo;
	@ViewInject(R.id.et_userpwd)
	private EditText mEtUserPwd;
	@ViewInject(R.id.ib_login)
	private ImageButton mIbLogin;
	@ViewInject(R.id.iv_tourist_login)
	private Button mIvTourist;

	@Override
	protected void setListener() {
		mIbLogin.setOnClickListener(this);
		mIvTourist.setOnClickListener(this);
	}

	@Override
	public void onKeyEv(View v) {
		switch (v.getId()) {
		case R.id.iv_return:
			finish();
			break;
		case R.id.ib_login:
			loginClick();
			break;
		case R.id.iv_tourist_login:
			// startActivity(new Intent(LoginActivity.this,
			// MainActivity.class));
			break;
		default:
			break;
		}
	}

	private void loginClick() {
		String loginName = mEtUserInfo.getText().toString();
		String loginPass = mEtUserPwd.getText().toString();
		if (TextUtils.isEmpty(loginName) || TextUtils.isEmpty(loginPass)) {
			showInfo(getResources().getString(R.string.user_pass_hint));
			return;
		}
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		params.addBodyParameter("endPoint", 1 + "");
		params.addBodyParameter("loginName", loginName);
		params.addBodyParameter("loginPass",loginPass);
		params.addBodyParameter("appId", getMac(getApplicationContext()));
		http.send(HttpMethod.POST, UrlConstant.USER_LOGIN_URL, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException ex, String message) {
						showInfo(message);
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						showInfo(responseInfo.toString());
						JSONObject obj = JSONObject
								.parseObject(responseInfo.result);
						if (obj.getBooleanValue("success")) {
//							 startActivity(new Intent(LoginActivity.this,
//							 QiyeActivity.class));
							showInfo(obj.getString("msg"));
							Log.v(TAG, "This is Verbose");
							finish();
						} else {
							showInfo(obj.getString("msg"));
						}
					}
				});

	}

	// 获取手机的mac地址
	public String getMac(Context context) {
		try {
			for (Enumeration<NetworkInterface> e = NetworkInterface
					.getNetworkInterfaces(); e.hasMoreElements();) {
				NetworkInterface item = e.nextElement();
				byte[] mac = item.getHardwareAddress();
				if (mac != null && mac.length > 0) {
					return new String(mac);
				}
			}
		} catch (Exception e) {
		}
		return "";
	}

	@Override
	protected void injectView() {
		ViewUtils.inject(this);
	}

	@Override
	protected void getData() {
	}
}
