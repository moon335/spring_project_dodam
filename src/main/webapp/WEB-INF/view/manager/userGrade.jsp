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
.form--container {
	display: flex;
	align-items: center;
	border: 4px solid #9ACBF1;
	border-radius: 20px;
	width: 700px;
	height: 300px;
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
	width: 60px;
	height: 30px;
}
.input--box {
	border: none;
	border-bottom: 2px solid #ebebeb;
	margin: 10px;
}
.search--container {
	display: flex;
	justify-content: flex-end;
	width: 100%;
	margin-right: 200px;
}
.title--button {
	margin-top: 6px;
	margin-left: 20px;
}
.modal-content {
	width: 670px;
	height: 600px;
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
.title--container {
	display: flex;
}
</style>
		<div class="content">
			<div class="title--container">
				<h2>회원 리스트</h2>
			</div>
			<div class="main--content">
				<div class="search--container">
					<div>
						<form action="/manager/userNameList" method="get">
							<input type="text" name="name" class="input--box" placeholder="이름을 입력해주세요">
							<button type="submit" class="sub-button">검색</button>
						</form>
					</div>
				<div>
					<form action="/manager/userGradeList" method="get">
						<select name="gradeId" class="input--box">
							<option value="1">브라운</option>
							<option value="2">골드</option>
							<option value="3">다이아</option>
						</select>
						<button type="submit" class="sub-button">조회</button>
					</form>
				</div>
				</div>
				<table class="table--container">
					<thead>
						<tr class="tr--box">
							<th scope="col">이름</th>
							<th scope="col" style="width: 410px;">이메일</th>
							<th scope="col">전화번호</th>
							<th scope="col">생년월일</th>
							<th scope="col">정보</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${viewAll}">
							<tr class="content--box">
								<td>${user.user.name}</td>
								<td>${user.user.email}</td>
								<td>${user.user.tel}</td>
								<td>${user.user.birth}</td>
								<td><button onclick="userDetail(${user.user.id})" class="sub--button">상세</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div style="display: block; text-align: center;">
					<c:if test="${paging.startPage != 1}">
						<a href="/manager/userGradeList?nowPage=${paging.startPage - 1}&cntPerPage=${paging.cntPerPage}">&lt;</a>
					</c:if>
					<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
						<c:choose>
							<c:when test="${p == paging.nowPage}">
								<b>${p}</b>
							</c:when>
							<c:when test="${p != paging.nowPage}">
								<a href="/manager/userGradeList?nowPage=${p}&cntPerPage=${paging.cntPerPage}">${p}</a>
							</c:when>
						</c:choose>
					</c:forEach>
					<c:if test="${paging.endPage != paging.lastPage}">
						<a href="/manager/userGradeList?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}">&gt;</a>
					</c:if>
				</div>
			</div>
		</div>
	</main>
<script>
function userDetail(id){
	// 유저 정보 확인과 등급 수정 할수 있는 페이지로
	location.href = "/manager/userDetail/"+id; 
}
</script>
