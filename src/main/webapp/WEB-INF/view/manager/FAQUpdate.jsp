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
.form--container {
	display: flex;
	flex-direction: column;
}
.input--box {
	border: none;
	border-bottom: 2px solid #ebebeb;
	margin: 10px;
}
.input--title--box {
	border: none;
	background-color: #ebebeb;
	margin: 10px;
	height: 40px;
}
.content--box {
	height: 500px;
}
textarea {
	border: 3px solid #ebebeb;
}
.input--title--box:focus ,textarea:focus{
	outline: none;
}
</style>
<div class="content">
	<h2>FAQ</h2>
	<div class="main--content">
		<form method="post" action="/manager/faq/update-proc" class="form--container">
		    <input type="hidden" name="id" value="${faq.id}">
		    <input type="text" name="title" value="${faq.title}" class="input--title--box">
		    <textarea rows="20" cols="90" value="${faq.content}" name="content" ></textarea>
		    <div class="button--box">
			    <button class="sub-button">수정</button>		    
		    </div>
		</form>
		</div>
	</div>
