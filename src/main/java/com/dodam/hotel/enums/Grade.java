package com.dodam.hotel.enums;

public enum Grade {
	BROWN(1), GOLD(2), DIA(3);
	
	private int grade;
	Grade(Integer grade) {
		this.grade = grade;
	}
	
	public Integer getGrade() {
		return this.grade;
	}
}
