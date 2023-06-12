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
.sub-button {
	background-color: #000;
	color: #fff;
	width: 60px;
	height: 30px;
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
.title--container {
	display: flex;
}
.paging--box > a {
	text-decoration: none;
	color: #9ACBF1;
}
</style>
<div class="content">
	<h2>문의사항</h2>
	<div class="main--content">
		<table class="table--container">
			<thead>
				<tr class="tr--box">
					<th scope="col">제목</th>
					<th scope="col">아이디</th>
					<th scope="col">답변 상태</th>
					<th scope="col">답변하기/삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="questionList" items="${questionList}">
					<tr class="content--box">
						<td id="title--box">${questionList.title}</td>
						<td>${questionList.userId}</td>
						<c:choose>
							<c:when test="${questionList.status == true}">
								<td style="color: #6690FF">답변 완료</td>
							</c:when>
							<c:otherwise>
								<td style="color: #FF7D6B">답변 미완료</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${questionList.status != true}">
								<td><button onclick="questionDetail(${questionList.id})" class="sub--button">답변</button></td>
							</c:when>
							<c:otherwise>
								<td><button onclick="questionDelete(${questionList.id})" class="sub-button">삭제</button></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div style="display: block; text-align: center;" class="paging--box">
			<c:if test="${paging.startPage != 1}">
				<a href="/question/questionList?nowPage=${paging.startPage - 1}&cntPerPage=${paging.cntPerPage}">&lt;</a>
			</c:if>
			<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
				<c:choose>
					<c:when test="${p == paging.nowPage}">
						<b>${p}</b>
					</c:when>
					<c:when test="${p != paging.nowPage}">
						<a href="/question/questionList?nowPage=${p}&cntPerPage=${paging.cntPerPage}">${p}</a>
					</c:when>
				</c:choose>
			</c:forEach>
			<c:if test="${paging.endPage != paging.lastPage}">
				<a href="/question/questionList?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}">&gt;</a>
			</c:if>
		</div>
	</div>
</div>
</main>
<script>
	function questionDetail(id){
		location.href = "/question/questionDetail/"+id; 
	}
	function questionDelete(id){
		console.log(id);
		location.href = "/question/questionDelete/"+id; 
	}
</script>
