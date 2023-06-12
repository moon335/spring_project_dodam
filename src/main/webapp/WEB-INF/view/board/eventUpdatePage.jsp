<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/managerHeader.jsp"%>
<style>
.content {
width: 100%; 
display : flex;
justify-content: center;
align-items: center;
}
.sub--button {
	background-color: #FF9F8D;
	border: none;
	color: #fff;
	cursor: pointer;
	width: 60px;
	height: 30px;
}
.sub-button {
	background-color: #000;
	color: #fff;
	width: 200px;
	height: 40px;
	margin-top: 20px;
}
.form--container {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}
.input--box {
	border: none;
	border-bottom: 2px solid #ebebeb;
	margin: 10px;
}
</style>
<div class="content">
<h2>일정</h2>
	<div class="main">
		<div class="main--content">
			<div>
				<form action="/event/updateEvent/${event.id}" method="post" class="form--container">
					<div>
						<label>제목</label> 
						<input type="text" class="input--box" name="title" value="${event.title}">
					</div>
					<div>
						<label>내용</label> 
						<input type="text" class="input--box" name="content" value="${event.content}">
					</div>

					<div>
						<label>시작일</label> 
						<input type="text" class="input--box" name="startDate" value="${event.startDate}">
					</div>
					<div>
						<label>종료일</label> 
						<input type="text" class="input--box" name="endDate" value="${event.endDate}">
					</div>
					<button type="submit" class="sub-button">행사 일정 수정</button>
				</form>
			</div>
		</div>
	</div>
</div>
</div>
</main>
<%@ include file="../layout/footer.jsp"%>

