<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>id_inquiry Page</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<link rel="stylesheet" href="/css/myPage.css" />
<style type="text/css">
.content--container {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
}
.join--button {
	margin-top: 50px;
}
</style>
</head>
<main class="main--container">
	<div class="title--container">
		<div class="title--logo">
			<span class="material-symbols-outlined back--icon" onclick="history.back()">arrow_back</span>
			<img alt="" src="/images/dodam_wlogo.png" id="logo--image" height="40" onclick="location.href='/'">
		</div>
		<div class="title--text">
			<p>아이디</p>
			<p>찾기</p>
		</div>
	</div>
	<div class="content--container">
		<p>${responseUser.name}님의 아이디는 <b>${responseUser.email}</b> 입니다.</p>
		<button type="button" class="join--button" onclick="location.href='/login'">로그인 하러가기</button>
	</div>
</main>
<%@ include file="../layout/footer.jsp"%>
