<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/managerHeader.jsp"%>
<style>
.content {
	width: 100%; display : flex;
	justify-content: center;
	align-items: center;
}
.main--content {
	margin-top: 50px;
	display: flex;
	flex-direction: column;
	align-items: center;
}
#title--box {
	width: 600px;
}

.sub--button {
	background-color: #FF9F8D;
	border: none;
	color: #fff;
	cursor: pointer;
	width: 60px;
	height: 30px;
}

.button--box {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-top: 10px;
	width: 100%;
}
.sub-button {
	background-color: #000;
	color: #fff;
	width: 200px;
	height: 40px;
}
.form-container {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	margin: 20px;
}
.input--box {
	border: none;
	border-bottom: 2px solid #ebebeb;
	margin: 10px;
}
.input--box:focus {
	outline: none;
}
.modal-content {
	width: 700px;
	height: 500px;
}
.paging--box > a {
	text-decoration: none;
	color: #9ACBF1;
}
.table--container {
	width: 1000px;
	text-align: center;
	
}
.tr--box {
	background-color: #ebebeb;
	font-weight: bold;
	height: 30px;
}
.content--box {
	height: 50px;
	border-bottom: 1px solid #ebebeb;
}
</style>
	<div class="content">
	<h2>공지사항 및 이벤트</h2>
			<div class="main--content">
				<table class="table--container">
					<thead>
						<tr class="tr--box">
							<th scope="col">일정</th>
							<th scope="col">시작일</th>
							<th scope="col">종료일</th>
							<th scope="col"></th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${viewAll}" var="list">
							<tr class="content--box">
								<td id="title--box">${list.title}</td>
								<td>${list.startDate}</td>
								<td>${list.endDate}</td>
								<td><button onclick="eventDetail(${list.id})" class="sub--button">수정</button></td>
								<td><button onclick="eventDelete(${list.id})" class="sub--button">삭제</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<div style="display: block; text-align: center;" class="paging">
					<c:if test="${paging.startPage != 1}">
						<a href="/event/notice?nowPage=${paging.startPage - 1}&cntPerPage=${paging.cntPerPage}">&lt;</a>
					</c:if>
					<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
						<c:choose>
							<c:when test="${p == paging.nowPage}">
								<b>${p}</b>
							</c:when>
							<c:when test="${p != paging.nowPage}">
								<a href="/event/notice?nowPage=${p}&cntPerPage=${paging.cntPerPage}">${p}</a>
							</c:when>
						</c:choose>
					</c:forEach>
					<c:if test="${paging.endPage != paging.lastPage}">
						<a href="/event/notice?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}">&gt;</a>
					</c:if>
				</div>
				<div class="button--box">
					<button type="button" class="sub-button" data-toggle="modal" data-target="#myModal">이벤트 작성</button>
				</div>
			</div>
		</div>
	
	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
	
				<div class="modal-header">
					<h4 class="modal-title">이벤트 등록</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
	
				<div>
					<form action="/event/event-insert" method="post" class="form-container" enctype="Multipart/form-data">
						<div>
							<label>시작일</label> 
							<input type="date" name="startDate" value="2015-10-13" class="input--box"> 
							<label>종료일</label> 
							<input type="date" name="endDate" value="2015-10-13" class="input--box">
						</div>
						<div>
							<label>제목</label>
							<input type="text" name="title" value="asdsadasd" class="input--box" style="width: 320px;"> 
						</div>
						<div>
							<label>내용</label> 
							<input type="text" name="content" value="asdasdasdasd" class="input--box" style="width: 320px; height: 200px;">
						</div>
						<input type="file" name="file" class="input--box">
						<button type="submit" class="sub--button">등록</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	</main>
<script>
	function eventDetail(id){
		location.href = "/event/updateEventPage/"+id; 
	}
	function eventDelete(id){
		location.href = "/event/deleteEvent/"+id; 
	}
</script>


