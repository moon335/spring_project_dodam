<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/managerHeader.jsp"%>
<style>
.content {
	width: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
	display: flex;
}
.sub-button {
	background-color: #000;
	color: #fff;
	width: 400px;
	height: 40px;
	margin: 20px 10px;
}
.button--box {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-top: 10px;
	width: 100%;
}
.input--box {
	border: none;
	border-bottom: 2px solid #ebebeb;
	margin: 10px;
}

.main--content {
	margin-top: 50px;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}
.reply--form {
	display: flex;
	flex-direction: column;
	align-items: center;
}
.reply--box {
	width: 1000px;
	height: 200px;
	width: 100%;
}
.reply--box:focus {
	outline: none;
	border: 1px solid #ebebeb;
}

.q--title--box {
	display: flex;
	justify-content: space-between;
	width: 800px;
	background-color: #ebebeb;
	height: 30px;
}

#category, #userId {
	display: flex;
	align-items: center;
}
.title--and--content {
	display: flex;
	flex-direction: column;
	height: 300px;
	padding-top: 20px;
}

</style>

<div class="content">
		<h2>문의사항</h2>
		<div class="main--content">
			<div class="q--container">
				<div class="q--box">
					<div class="q--title--box">
						<div id="category">[${question.category}]</div>
						<div id="userId">회원 ID : ${question.userId}</div>
					</div>
				<div class="title--and--content">
					<label style="font-weight: bold; font-size: 20px;">${question.title}</label>
					<label>${question.content}</label>
				</div>
				</div>
				<form action="/question/reply/${question.id}/${principal.id}" method="post" class="reply--form">
				<input type="text" name="content" class="reply--box" placeholder="답변을 작성해주세요" autocomplete="off">
				<button type="submit" class="sub-button">답변 작성</button>
				</form>
				</div>
		</div>
	</div>
</body>
</html>

