<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>change_pw Page</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<link rel="stylesheet" href="/css/myPage.css" />
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Nanum+Gothic+Coding&family=Noto+Sans+KR:wght@300;400&display=swap');

</style>
</head>
<main class="main--container">
		<div class="title--container">
		<div class="title--logo">
			<span class="material-symbols-outlined back--icon" onclick="history.back()">arrow_back</span>
			<img alt="" src="/images/dodam_wlogo.png" id="logo--image" height="40" onclick="location.href='/'">
		</div>
		<div class="title--text">
			<p>비밀번호를</p>
			<p>다시</p>
			<p>설정해주세요</p>
		</div>
	</div>
	<div class="content--container">
		<form action="/changePwProc" method="post" class="form--container" id="changeForm">
			<input type="password" name="currentPwd" placeholder="현재 비밀번호를 입력해주세요" class="input--box">
			<input type="password" name="changePwd" placeholder="변경할 비밀번호를 입력해주세요" class="input--box">
			<input type="password" name="checkChangePwd" placeholder="비밀번호를 한번 더 입력해주세요" class="input--box">
			<button type="button" class="sub--button" onclick="formCheck()">비밀번호 변경</button>
		</form>
	</div>
</main>

<script>
	let changeForm = document.getElementById("changeForm");
	let currentPwd = changeForm.currentPwd;
	let changePwd = changeForm.changePwd;
	let checkChangePwd = changeForm.checkChangePwd;
	function formCheck(){
		let success = false;
		if(currentPwd.value === ""){
			alert("현재 비밀번호를 입력하세요.")
		}
		else if(changePwd.value === ""){
			alert("변경할 비밀번호를 입력하세요,")
		}
		else if(checkChangePwd.value === ""){
			alert("변경할 재입력비밀번호를 입력하세요.")
		}
		else if(changePwd.value !== checkChangePwd.value){
			alert("비밀번호가 일치 하지 않습니다")
		}else{
			success = true;
		}


		if(success){
			changeForm.submit();
		}
	}

</script>
