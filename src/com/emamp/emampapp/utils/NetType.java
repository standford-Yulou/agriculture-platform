package com.emamp.emampapp.utils;

/**
 * @ClassName: NetType
 * @Description: 网络类型
 * @date Sep 6, 2015 12:23:08 PM
 */
public enum NetType {
	/**
	 * 无网络
	 */
	NET_NONE(1),
	/**
	 * 手机
	 */
	NET_MOBILE(2),
	/**
	 * wifi
	 */
	NET_WIFI(4),
	/**
	 * 其他
	 */
	NET_OTHER(8);

	NetType(int value) {
		this.value = value;
	}

	public int value;
}
