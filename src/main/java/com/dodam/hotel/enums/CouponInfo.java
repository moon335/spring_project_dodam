package com.dodam.hotel.enums;

public enum CouponInfo {
	MEMBERSHIP(1), GOLD(2), DIA(3), DIA2(4);
	
	private int coupon;
	CouponInfo(Integer coupon) {
		this.coupon = coupon;
	}
	
	public Integer getCoupon() {
		return this.coupon;
	}
}
