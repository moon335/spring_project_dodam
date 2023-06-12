<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<style>
footer {
	width: 100%;
	background-color: #f9f9f9;
	margin-top: 80px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	height: 100px;
}

.chat--div{
	position: relative !important;
}

.chat--inner--div {
	position: fixed;
	bottom: 50px;
	right: 40px;
	width: 40px;
	height: 40px;
	border-radius: 50px;
	z-index: 1000000;
}

#open--chat {
	width: 100%;
	height: 100%;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 40px;
	cursor: pointer;
}

</style>

<footer>
	<div class="chat--div">
		<div class="chat--inner--div">
			<a id="open--chat" onclick="openChat('${principal}')">
				<i class="xi-message-o"></i>
			</a>
		</div>
	</div>
	<div>
		<b>(주) 도담호텔</b>
	</div>
	<div>47254, 부산 부산진구 중앙대로 749 +82-051-715-6224</div>
	<div>+82-051-715-6224</div>
</footer>
<!-- writer:이현서, web socket -->
<script type="text/javascript" src="/webjars/sockjs-client/1.5.1/sockjs.min.js"></script>
<script>
let popupX = (document.body.offsetWidth / 2) - (400 / 2);

let popupY= (window.screen.height / 2) - (600 / 2);
let popupOption = "width=400,height=600,left=" + popupX + ",top=" + popupY + ",scrollbars = yes,resizeable";
let url;
function openChat(user) {
	if(user === "") {
		alert("로그인 후 이용가능합니다.");
		location.href="/login";
	} else {
		url  = "/question/chatRoom";
		let returnChatRoom = window.open(
				url,
				"popup",
				popupOption
		);
	}
};
</script>
</body>
</html>
