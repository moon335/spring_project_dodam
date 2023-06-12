<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dodam Page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<link rel="stylesheet" href="/css/header.css" />

</head>
<body>
	<div id="header--wrap">
		<div id="img--wrap">
			<img alt="로고 이미지" src="/images/dodam_logo.png" id="logo--image" width="300" onclick="location.href='/'">
		</div>
		<div id="header--nav--wrap">
			<div id="nav--bar--wrap">
				<div id="toggle--nav--bar">
					<div id="navi--1--wrap">
						<ul class="navi--bar--1">
							<li>
								<div class="toggle--wrap--room" style="padding-right: 20px;">
									<p class="toggle--box--room"> 객실</p>
									<p class="material-symbols-outlined down--icon">keyboard_arrow_down</p>
								</div>
								<ul class="navi--bar--detail--room">
									<li><a href="/room?type=디럭스">디럭스</a></li>
									<li><a href="/room?type=프리미엄">프리미엄</a></li>
									<li><a href="/room?type=스위트">스위트</a></li>
									<li><a href="/room">전체객실</a></li>
								</ul></li>
						</ul>
					</div>
					<div id="navi--2--wrap">
						<ul class="navi--bar--2">
							<li>
							<div class="toggle--wrap--fac">
								<p class="toggle--box--fac"> 부대시설</p>
								<p class="material-symbols-outlined down--icon">keyboard_arrow_down</p>
							</div>
								<ul class="navi--bar--detail--fac">
									<li><a href="/dining?type=레스토랑">레스토랑</a></li>
									<li><a href="/dining?type=라운지">라운지 & 바</a></li>
									<li><a href="/fitness">피트니스</a></li>
									<li><a href="/pool">수영장</a></li>
									<li><a href="/spa">스파</a></li>
								</ul></li>
						</ul>
					</div>
					<div id="navi--3--wrap">
						<ul class="navi--bar--3">
							<li>
							<div class="toggle--wrap--reserve" id="reservation--toggle--wrap">
								<p class="toggle--box--reserve"> 예약</p>
								<p class="material-symbols-outlined down--icon">keyboard_arrow_down</p>
							</div>
								<ul class="navi--bar--detail--reserve">
									<li><a href="/selectDate">객실예약</a></li>
									<li><a href="/reserveDining">다이닝예약</a></li>
								</ul></li>
						</ul>
					</div>
					<div id="navi--4--wrap">
						<ul class="navi--bar--4">
							<li>
							<div class="toggle--wrap--qna">
								<p class="toggle--box--qna"> 문의</p>
								<p class="material-symbols-outlined down--icon">keyboard_arrow_down</p>
							</div>
								<ul class="navi--bar--detail--qna">
									<li><a href="/question/question">FAQ</a></li>
									<li><a href="/question/qnaPage">1:1 문의</a></li>
									<li><a href="/event/eventBoard/onGoing">이벤트</a></li>
								</ul></li>
						</ul>
					</div>
				</div>
				<div class="navi--bar--5">
					<ul class="symbol--box">
						<c:choose>
							<c:when test="${principal != null}">
								<li><a href="/logout"><span class="material-symbols-outlined">logout</span></a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/login"><span class="material-symbols-outlined">login</span></a></li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${principal != null}">
								<li><a href="/myPage"><span class="material-symbols-outlined">person</span></a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/login"><span class="material-symbols-outlined">person</span></a></li>
							</c:otherwise>							
						</c:choose>
						
						<li><a href="/membership"><span class="material-symbols-outlined">card_membership</span></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
<script src="/js/mainToggle.js"></script>
