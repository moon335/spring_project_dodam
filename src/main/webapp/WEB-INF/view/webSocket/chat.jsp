<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
*{
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}
body {
	background-color: #ebebeb;
}

#msg-socket {
	height: 513px;
	overflow-x: hidden;
	display: flex;
	flex-direction: column;
	margin-bottom: 10px;
}

.recieve--msg {
	padding-left: 60px;
}

.send--msg--div {
	width: 120px;
	background-color: yellow;
	border-radius: 5px;
	padding: 5px;
	margin-right: 25px;
	word-wrap: break-word;
}

.recieve--msg--div {
	width: 120px;
	background-color: yellow;
	border-radius: 5px;
	padding: 5px;
	word-wrap: break-word;
}

#message {
	width: 305px;
	height: 34px;
	border-radius: 5px;
	border: 2px solid #ccc;
}

#message:focus {
	outline: none;
}

#send--btn {
	width: 60px;
	height: 30px;
	background-color: #CCE1FF;
	border-radius: 5px;
	border: none;
}

#input--box {
	display: flex;
	justify-content: space-around;
	align-items: center;
	background-color: #fff;
	padding: 5px;
}

.exit--wrap {
	display: flex;
	justify-content: space-between;
	margin-bottom: 5px;
	background-color: #CCE1FF;
	padding: 4px 6px;
}

#exit--btn {
	border: none;
	cursor: pointer;
	background-color: #CCE1FF;
}
.notice--wrap{
	text-align: center;
	margin-bottom: 5px;
	color: #a6a7a8;
}
</style>
</head>
<body>
	<div class="exit--wrap">
		<h2>DODAM</h2>
		<button id="exit--btn" onclick="closePopUp()">❌</span></button>
	</div>
	<div id="socket">
		<div id="msg-socket">
			<div class="notice--wrap">
				<div>
					<p>호텔 도담</p>
					<p>1:1 상담원이 시작됩니다!</p>
				</div>
			</div>
			<div class="notice--wrap center">
				<div>
					<p>대화양이 많은 경우</p>
					<p>상담이 지연될 수 있으니</p>
					<p>양해 부탁드립니다</p>
				</div>
			</div>
		</div>
		<div id="input--box">
			<input type="text" id="message" onkeypress="enter(window.event)">
			<button id="send--btn" onclick="sendMsg()">전달</button>
		</div>
	</div>
	<script type="text/javascript" src="/webjars/sockjs-client/1.5.1/sockjs.min.js"></script>
	<script type="text/javascript">
		let ws = null;
		function open() {
			ws = new SockJS("/chat");
			ws.onopen = function() {
				// onmessage : message를 받았을 때의 callback
				ws.onmessage = function(e) {
					const socketDivTag = document.getElementById("msg-socket");
					const data = JSON.parse(e.data);
					const createDivTag = document.createElement("div");
					createDivTag.className = "sender--div";
					const createDivTag2 = document.createElement("div");
					createDivTag2.className = "recieve--msg";
					const createDivTag3 = document.createElement("div");
					createDivTag3.className = "recieve--msg--div";
					console.log(data)
					if (data.type === "ENTER") {
						const eh = socketDivTag.clientHeight
								+ socketDivTag.scrollTop;

						const isScroll = socketDivTag.scrollHeight <= eh;

						createDivTag.append(data.msg + "\n")
						if (!isScroll) {
							socketDivTag.scrollTop = socketDivTag.scrollHeight;
						}
					} else if(data.type === "MANAGER_CHAT"){

						createDivTag.append("👩🏻‍💻dodam:");
						createDivTag3.append(data.msg + "\n");
						createDivTag2.append(createDivTag3);
						socketDivTag.append(createDivTag);
						socketDivTag.append(createDivTag2);
						
						const eh = socketDivTag.clientHeight
								+ socketDivTag.scrollTop;
						const isScroll = socketDivTag.scrollHeight <= eh;
						if (!isScroll) {
							socketDivTag.scrollTop = socketDivTag.scrollHeight;
						}
					} else{
						const eh = socketDivTag.clientHeight
								+ socketDivTag.scrollTop;

						const isScroll = socketDivTag.scrollHeight <= eh;

						createDivTag.append(data.msg + "\n");
						if (!isScroll) {
							socketDivTag.scrollTop = socketDivTag.scrollHeight;
						}
					}
				}
			}
		}
		// 연결 종료
		function disconnect() {
			ws.close();
			const socketDivTag = document.getElementById("msg-socket");
			socketDivTag.textContent = "";
		}

		function sendMsg() {

			const socketDivTag = document.getElementById("msg-socket");
			const eh = socketDivTag.clientHeight + socketDivTag.scrollTop;

			const isScroll = socketDivTag.scrollHeight <= eh;
			let message = document.getElementById("message");
			
			const createDivTag = document.createElement("div");
			createDivTag.style.display = "flex";
			createDivTag.style.justifyContent = "flex-end";
			createDivTag.style.paddingLeft = "15px";
			createDivTag.textContent = "👤나:";
			
			const createDivTag2 = document.createElement("div");
			createDivTag2.className = "send--msg";
			createDivTag2.style.display = "flex";
			createDivTag2.style.justifyContent = "flex-end";
			
			const createDivTag3 = document.createElement("div");
			createDivTag3.className = "send--msg--div";
			createDivTag3.textContent = message.value;
			
			createDivTag2.append(createDivTag3);
			socketDivTag.append(createDivTag);
			socketDivTag.append(createDivTag2);
			
			if (!isScroll) {
				socketDivTag.scrollTop = socketDivTag.scrollHeight;
			}
			
			const messageJSON = {
				"type" : "CHAT",
				"msg" : message.value,
			}
			ws.send(JSON.stringify(messageJSON));
			message.value = null;
			message.focus();
		}
		function closePopUp() {
			disconnect();
			window.close();
		}

		function connectSocket() {
			open();
		}

		function enter(e) {
			if (e.keyCode == 13) {
				sendMsg();
			}
		}

		connectSocket();
	</script>
</body>
</html>