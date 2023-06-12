package com.dodam.hotel.repository.model;

import java.sql.Timestamp;

import com.dodam.hotel.util.DateFormat;

import lombok.Data;

@Data
public class MembershipInfo {
	
	private Integer userId;
	private Integer membershipId;
	private User user;
	private Membership membership;
	private Timestamp updatedAt;
	
	public String dateFormat() {
		String formatDate = DateFormat.dateFormat(this.updatedAt);
		return formatDate;
	}
}