<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/managerHeader.jsp"%>
<style type="text/css">
.content {
	display: flex;
    flex-direction: column;
    padding: 0;
    align-items: center;
    justify-content: center;
    width: 100%;
}
.main--content {
	width: 400px;
	background-color: #ebebeb;
	display: flex;
	flex-direction: column;
	align-items: center;
}

#msg-socket {
	height: 532px;
	overflow-x: hidden;
	display: flex;
	flex-direction: column;
	margin-bottom: 10px;
}

.recieve--msg {
	padding-left: 10px;
}

.send--msg--div {
	width: 130px;
	background-color: yellow;
	border-radius: 5px;
	padding: 5px;
	margin-right: 25px;
	word-wrap: break-word;
}

.recieve--msg--div {
	width: 130px;
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
	background-color: #ebebeb;
	padding: 5px;
}

</style>
<div class="content">
	<div class="title--container">
		<h2>${userName}님의 상담</h2>
	</div>
	<div class="main--content">
		<div id="socket">
			<div id="msg-socket"></div>
			<div id="input--box">
				<input type="text" id="message" onkeypress="enter(window.event)">
				<button id="send--btn" onclick="sendMsg()">전달</button>
			</div>
		</div>
	</div>
</div>
</main>
<script type="text/javascript" src="/webjars/sockjs-client/1.5.1/sockjs.min.js"></script>
<script>
const ws = new SockJS("/chat");

ws.onopen = function () {
    const messageJSON = {
        "type": "ENTER",
        "msg": "매니저님께서 입장하셨습니다",
        "roomName": "${roomName}",
    }
    ws.send(JSON.stringify(messageJSON));
    // onmessage : message를 받았을 때의 callback
}

ws.onmessage = (e) => {
    const socketDivTag = document.getElementById("msg-socket");
    const data = JSON.parse(e.data)
    const createDivTag = document.createElement("div");
	createDivTag.className = "sender--div";
	const createDivTag2 = document.createElement("div");
	createDivTag2.className = "recieve--msg";
	const createDivTag3 = document.createElement("div");
	createDivTag3.className = "recieve--msg--div";
    createDivTag.append("👤${roomName}:");
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
}

function sendMsg(){
	const socketDivTag = document.getElementById("msg-socket");
	const eh = socketDivTag.clientHeight + socketDivTag.scrollTop;

	const isScroll = socketDivTag.scrollHeight <= eh;
    let message = document.getElementById("message")
    
    const createDivTag = document.createElement("div");
	createDivTag.style.display = "flex";
	createDivTag.style.justifyContent = "flex-end";
	createDivTag.style.paddingLeft = "15px";
	createDivTag.textContent = "👩🏻dodam:";
			
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
        "type": "MANAGER_CHAT",
        "roomName": "${roomName}",
        "msg": message.value,
    }
    ws.send(JSON.stringify(messageJSON));
    message.value = null;
    message.focus();
}
function enter(e) {
	if (e.keyCode == 13) {
		sendMsg();
	}
}
</script>
</body>
</html>

