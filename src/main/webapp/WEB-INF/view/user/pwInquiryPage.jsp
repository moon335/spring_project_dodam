<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pw_inquiry Page</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<link rel="stylesheet" href="/css/myPage.css" />
<style type="text/css">
.content--container {
	display: flex;
	flex-direction: column;
	align-items: center;
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
			<p>비밀번호</p>
			<p>찾기</p>
		</div>
	</div>
	<div class="content--container">
		<p>가입하신 이메일로 임시 비밀번호를 전송했습니다.</p>
		<p>이메일을 확인해 주세요.</p>
		<button type="button" class="join--button" onclick="location.href='/login'">로그인 하러가기</button>
	</div>
</main>
