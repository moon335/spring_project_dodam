<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<link rel="stylesheet" href="/css/myPage.css" />
<style type="text/css">
</style>
</head>
<main class="main--container">
	<div class="title--container">
		<div class="title--logo">
			<span class="material-symbols-outlined back--icon" onclick="history.back()">arrow_back</span>
			<img alt="" src="/images/dodam_wlogo.png" id="logo--image" height="40" onclick="location.href='/'">
		</div>
		<div class="title--text">
			<p>로그인</p>
			<p>하세요</p>
		</div>
	</div>
	<div class="content--container">
		<form action="/loginProc" method="post" class="form--container">
			<input type="email" name="email" placeholder="이메일을 입력해 주세요" class="input--box" autocomplete="off"> 
			<input type="password" name="password" placeholder="비밀번호를 입력해 주세요" class="input--box" autocomplete="off"> 
			<button type="submit" class="sub--button">로그인</button>
			<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=20543122589b138f374315f3bbd94eb6&redirect_uri=http://localhost:8080/auth/kakaoLogin" id="kakao--button"><img alt="" src="/images/kakao_login_medium_wide.png" width="350px;"></a>
			<p> 아직 도담 회원이 아니세요? </p>
			<p> 회원가입하고 도담의 다양한 혜택을 누려보세요 </p>
			<button type="button" class="join--button" onclick="location.href='/join'">회원가입</button>
			<button type="button" class="find--button" onclick="location.href='/findIdPw'">아이디/비밀번호 찾기</button>
		</form>
	</div>
</main>
</html>
