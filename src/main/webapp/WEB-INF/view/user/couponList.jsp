<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>couponList Page</title>
<link rel="stylesheet" href="/css/myPage.css" />
</head>
<body>
	<c:choose>
		<c:when test="${coupons == null}">
			<div class="coupon--info--container">
				<h2>보유한 쿠폰이 없습니다.</h2>
			</div>
		</c:when>
		<c:otherwise>
			<div class="coupon--container">
				<c:forEach items="${coupons}" var="coupons">
					<div class="color--container">
						<img alt="" src="/images/dodam_wlogo.png" width="150" height="50">
					</div>
					<div class="coupon--info--container">
						<div class="coupon--name--container">
							${coupons.couponInfo.name}
						</div>
						<div class="coupon--date--container">
							${coupons.startDate} &nbsp; ~ &nbsp;
							${coupons.endDate}
						</div>
					</div>
				</c:forEach>
			</div>
			
			<div style="display: block; text-align: center;">		
				<c:if test="${paging.startPage != 1}">
					<a href="/couponList?nowPage=${paging.startPage - 1}&cntPerPage=${paging.cntPerPage}">&lt;</a>
				</c:if>
				<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
					<c:choose>
						<c:when test="${p == paging.nowPage}">
							<b>${p}</b>
						</c:when>
						<c:when test="${p != paging.nowPage}">
							<a href="/couponList?nowPage=${p}&cntPerPage=${paging.cntPerPage}">${p}</a>
						</c:when>
					</c:choose>
				</c:forEach>
				<c:if test="${paging.endPage != paging.lastPage}">
					<a href="/couponList?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}">&gt;</a>
				</c:if>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>
	