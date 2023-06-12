<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>replyList Page</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<link rel="stylesheet" href="/css/myPage.css" />
</head>
<body>
	<div class="total--container">
		<c:forEach items="${questions}" var="questions" varStatus="status">
			<div class="toggle--container">
				<div class="question--container">
					<div class="titleToggle--container">
						<div>
							${questions.question.formatDate()}
						</div>
						<div class="qna--title--box">
							${questions.question.title}
						</div>
						<span class="material-symbols-outlined toggleBtn">expand_more</span>
					</div>
				</div>
				<div class="contentToggle--container">
					<div class="qna--content--box">
						<c:if test="${questions.question.uploadFile != null}">
							<img src="/images/uploads/${questions.question.uploadFile}" width="300" height="200">
						</c:if>
						${questions.question.content}
					</div>
					<div class="qna--reply--box">
						${questions.content}
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	
	<div style="display: block; text-align: center;">		
		<c:if test="${paging.startPage != 1}">
			<a href="/myReplys?nowPage=${paging.startPage - 1}&cntPerPage=${paging.cntPerPage}">&lt;</a>
		</c:if>
		<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
			<c:choose>
				<c:when test="${p == paging.nowPage}">
					<b>${p}</b>
				</c:when>
				<c:when test="${p != paging.nowPage}">
					<a href="/myReplys?nowPage=${p}&cntPerPage=${paging.cntPerPage}">${p}</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
			<a href="/myReplys?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}">&gt;</a>
		</c:if>
	</div>
	
<script>
$(document).ready(
	    function() {
	    	let faqToggle = $(".question--container")
	    	faqToggle.on("click", function() {
	    		if($(this).siblings().hasClass("toggle--content")){
		    		$(this).siblings().removeClass("toggle--content");
	    		} else {
	    			$(".toggle--content").removeClass("toggle--content");
		    		$(this).siblings().addClass("toggle--content");
	    		}
	    	});
	    });
</script>
</body>
</html>
	