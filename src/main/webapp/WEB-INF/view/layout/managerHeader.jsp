<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Page</title>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<style>
@import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Nanum+Gothic+Coding&family=Noto+Sans+KR:wght@300;400&display=swap');
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: 'Noto Sans KR', monospace;
}

main {
	display: flex;
	height: 100vh;
}

nav {
	width: 300px;  /* 로그 크기에 따라 수정할 예정 */	
	background-color: #64c5f3;
	height: 100vh;
	display: flex;
}

.content {
	display: flex;
	flex-direction: column;
	padding: 10px;
}

.main--content {
	width: 1200px;
	height: 600px;
	padding: 10px;
	
}

li {
	list-style: none;
	margin-bottom: 15px;
	padding-left: 20px;
	cursor: pointer;
	font-size: 25px;
	font-weight: 400;
	color: #fff;
}
li:hover {
	color: #103B93;
}

.main--headers {
	margin-bottom: 10px;
}

#search--list, #reserve--list, #room--list {
	display: none;
	font-size: 15px;
}

#search--user--wrap {
	display: flex;
	justify-content: space-between;
}
#logo--li {
	margin: 20px 20px 100px 20px;
	display: flex;
	justify-content: flex-end;
}

#chat--li {
	display: flex;
}

#li--icon--span {
	color: red;
	font-size: 30px;
	margin-left: 10px;
	display: flex;
	align-items: center;
}

</style>
<body>
	<main>
		<nav>
			<ul>
				<li id="logo--li" onclick="location.href='/manager/managerMain'"><img alt="dodam" src="/images/admin_wlogo.png" width="250" height="50"></li>
				
				<li id="room--li">
					<div>
						<span id="search--room--wrap">시설</span>
					</div>
					<ul id="room--list">
						<li id="room--li" onclick="location.href='/manager/roomStatus'">객실</li>
						<li id="dining--li" onclick="location.href='/manager/facilities'">부대시설</li>
					</ul>	
				</li>
				<li id="userList--li">
					<div>
						<span id="search--user--wrap">회원</span>
					</div>
					<ul id="search--list">
						<li onclick="location.href='/manager/userList'">전체 회원 조회</li>
						<li onclick="location.href='/manager/membershipUserList'">멤버쉽 회원 조회</li>
						<li onclick="location.href='/manager/blackList'">블랙리스트 회원 조회</li>
					</ul>	
				</li>
				<li id="reservation--li">
					<div>
						<span id="search--reserve--wrap">예약</span>
					</div>
					<ul id="reserve--list">
						<li onclick="location.href='/manager/reservation'">객실 예약</li>
						<li onclick="location.href='/manager/dining'">다이닝 예약</li>
					</ul>
				</li>
				<li id="event--li" onclick="location.href='/event/notice'">이벤트</li>
				<li id="qna--li" onclick="location.href='/question/questionList'">문의 사항</li>
				<li id="faq--li" onclick="location.href='/manager/faq'">FAQ</li>
				<li id="chat--li" onclick="location.href='/manager/chatRoomList'">실시간 채팅 문의<span id="li--icon--span"></span></li>
			</ul>
		</nav>
	<script type="text/javascript">
		$("#search--user--wrap").on("click", function() {
			$("#search--list").slideToggle()
		});
		
		$("#search--reserve--wrap").on("click", function() {
			$("#reserve--list").slideToggle()
		});
		
		$("#search--room--wrap").on("click", function() {
			$("#room--list").slideToggle()
		});
		
		
		let checkNewMessage = (5 * 1000);
		const checkMsg = function(){
		    // 통신
		    fetch("/api/checkNewMessage", ({
		    	method: "get"
		    })).then(async(response) => {
		    	let result = await response.json();
		    	if(result.status_code == 200) {
		    		console.log("메세지 왔다")
		    		let checkMsg = document.getElementById("chat--li");
		    		let iconTag = document.getElementById("li--icon--span");
		    		iconTag.className = "material-symbols-outlined";
		    		iconTag.textContent = "fiber_new";
		    		checkMsg.append(iconTag);
		    	} else {
		    		console.log("메세지 안왔다")
		    		let iconTag = document.getElementById("li--icon--span");
		    		iconTag.textContent = "";
		    	}
		    });
		}
		setInterval(checkMsg, checkNewMessage);
	</script>
	