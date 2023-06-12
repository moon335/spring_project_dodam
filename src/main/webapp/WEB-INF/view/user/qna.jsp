<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<link rel="stylesheet" href="/css/navi.css" />
<style>
.main--container {
	display: flex;
	justify-content: center;
	align-items: center;
}
.form--container {
	display: flex;
	flex-direction: column;
	justify-content: center;
	padding: 30px;
	width: 1200px;
}

.sub--button {
	background-color: #FF9F8D; 
	border : none; 
	color : #fff; 
	width : 350px;
	height : 40px; 
	margin : 10px;
	cursor: pointer;
}

.select--box {
	height: 40px;
	border: none;
	border-bottom: 2px solid black;
	margin: 0 10px 30px 0;
}

.form--title {
	display: flex;
}
.qna--title {
	height: 40px;
	border: none;
	border-bottom: 2px solid black;
	width: 300px;
}
.qna--title:focus {
	outline: none;
}
.form-bottom-container {
	margin-top: 20px;
}
</style>
</head>
<body>
	<div class="main--container">
		<form action="/question/qnaProc" method="post"
			enctype="Multipart/form-data" class="form--container">
			<div class="form--title">
				<select name="category" class="select--box">
					<option value="호텔건의">호텔건의</option>
					<option value="예약문의">예약문의</option>
					<option value="부대시설문의">부대시설문의</option>
					<option value="회원문의">회원문의</option>
				</select> 
				<input type="text" name="title" placeholder="제목을 입력해주세요" class="qna--title">

			</div>

			<textarea class="form-control summernote" rows="5" id="content" name="content" id="summernote--box"></textarea>
			<div class="form-bottom-container">
				<input type="file" name="file" class="input--box"> 
				<input type="submit" value="질문등록" class="sub--button">			
			</div>
		</form>
	</div>
	<script>
		$('.summernote').summernote(
				{
					tabsize : 2,
					width : 1100,
					height : 500,
					toolbar : [ [ 'style', [ 'style' ] ],
							[ 'color', [ 'color' ] ],
							[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
							[ 'table', [ 'table' ] ], [ 'insert', [] ], ],
				});
		
		$(document).ready(function() {
			$('#summernote').summernote();
		});
	</script>
	<script src="js/mainToggle.js"></script>
<%@ include file="../layout/footer.jsp"%>
