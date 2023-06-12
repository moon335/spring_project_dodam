<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/managerHeader.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<style>
.content {
	width: 100%;
	display: flex;
	align-items: center;
	margin-top: 200px;
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
	align-items: center;
	border: 4px solid #9ACBF1;
	border-radius: 20px;
	width: 700px;
	height: 300px;
}

.input--box {
	border: none;
	border-bottom: 2px solid #ebebeb;
	margin: 10px;
	text-align: center;
	font-size: 20px;
}

.input--box:focus {
	outline: none;
}

.grade--box {
	border: none;
	border-bottom: 2px solid #ebebeb;
	margin: 10px;
}

.content--box {
	height: 500px;
}

.change--container {
	display: flex;
	justify-content: flex-end;
	width: 700px;
	margin-top: 10px;
}

.name--content {
	font-size: 30px;
	font-weight: bold;
}

.symbol--box {
	font-size: 40px;
	font-weight: bold;
}

.info--name {
	background-color: #9ACBF1;
	border-bottom-left-radius: 10px;
	border-top-left-radius: 10px; width : 200px;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	height: 100%;
	width: 200px;
}

.info--detail {
	display: flex;
	flex-direction: column;
	width: 470px;
	padding-left: 30px;
}

.info--box {
	margin: 5px;
	font-size: 20px;
	display: flex;
	justify-content: space-between;
}

.change--form {
	margin: 0 20px;
}

.table--container {
	width: 700px;
	text-align: center;
	margin: 20px;
}
#title--tr--container {
	font-weight: bold;
	background-color: #9ACBF1;
}
.table--tr--container {
	border-bottom: 1px solid #ebebeb;
}
.paging--box > a {
	text-decoration: none;
	color: #9ACBF1;
}
.reserve--select--box {
	display: flex;
	justify-content: center;
}
.reserve--container {
	margin-top: 40px;
}

</style>
<div class="content">
	<div class="form--container">
		<div class="info--name">
			<span class="material-symbols-outlined symbol--box">account_circle</span> <span class="name--content">${userDetail.user.name}</span> <span>${userDetail.grade.name}</span> <span>${userDetail.user.gender}</span>
			<span></span>
		</div>
		<div class="info--detail">
			<div class="info--box">
				<span>이메일</span> <span>${userDetail.user.email}</span>
			</div>
			<div class="info--box">
				<span>전화번호</span> <span>${userDetail.user.tel}</span>
			</div>
			<div class="info--box">
				<span>주소</span> <span>${userDetail.user.address}</span>
			</div>
			<div class="info--box">
				<span>생년월일</span> <span>${userDetail.user.birth}</span>
			</div>
			<div class="info--box">
				<span>가입일</span><span>${userDetail.user.dateFormat()}</span>
			</div>
			<div class="info--box">
				<span>로그인 여부</span>
				<c:choose>
					<c:when test="${userDetail.user.socialLogin == false}">
						<span>일반 회원</span>
					</c:when>
					<c:otherwise>카카오 회원</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<div class="change--container">
		<form action="/manager/updateGrade/${userDetail.user.id}" method="post" class="change--form">
			<select name="gradeId" class="grade--box">
				<option value="1">브라운</option>
				<option value="2">골드</option>
				<option value="3">다이아</option>
			</select>
			<button type="submit" class="btn btn-light">등급 수정</button>
		</form>
		<c:choose>
			<c:when test="${userDetail.user.blacklist == false}">
				<form action="/manager/updateBlack/${userDetail.user.id}" method="get">
					<button type="submit" class="btn btn-danger">블랙리스트 지정</button>
				</form>
			</c:when>
			<c:otherwise>
				<form action="/manager/updateWhite/${userDetail.user.id}" method="get">
					<button type="submit" class="btn btn-primary">블랙리스트 해제</button>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	
	<div class="reserve--container">
		<div class="reserve--select--box">
			<h4 class="reserve--select--title">예약 내역</h4>
		</div>
		<div>
			<table class="table--container">
				<c:choose>
					<c:when test="${reservations.size() != 0}">
							<tr id="title--tr--container">
								<td>Check In</td>
								<td>Check Out</td>
								<td style="width: 200px;">Room</td>
								<td>Person</td>
								<td>Total Price</td>
								<td>Date</td>
							</tr>
						<c:forEach items="${reservations}" var="list">
							<tr class="table--tr--container">
								<td>${list.startDate}</td>
								<td>${list.endDate}</td>
								<td style="width: 200px;">${list.room.roomType.name}</td>
								<td>${list.numberOfP}</td>
								<td>${list.formatPrice()}</td>
								<td>${list.dateFormat()}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr id="no--reservation">
							<td colspan="5"><h4 style="text-align: center;">예약 내역이 없습니다.</h4></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
			<div style="display: block; text-align: center;" class="paging--box">		
				<c:if test="${paging.startPage != 1}">
					<a href="/manager/userDetail/${userDetail.user.id}?nowPage=${paging.startPage - 1}&cntPerPage=${paging.cntPerPage}">&lt;</a>
				</c:if>
				<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
					<c:choose>
						<c:when test="${p == paging.nowPage}">
							<b>${p}</b>
						</c:when>
						<c:when test="${p != paging.nowPage}">
							<a href="/manager/userDetail/${userDetail.user.id}?nowPage=${p}&cntPerPage=${paging.cntPerPage}">${p}</a>
						</c:when>
					</c:choose>
				</c:forEach>
				<c:if test="${paging.endPage != paging.lastPage}">
					<a href="/managers/userDetail/${userDetail.user.id}?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}">&gt;</a>
				</c:if>
			</div>
		</div>
	</div>
</div>
</main>
<script>

/* 	const button = document.querySelector(".sub--button")
	const toggleContainer = document.querySelector(".toggle--container")
	button.addEventListener("click", ()=>{
		let item = localStorage.getItem("recordClick");
		console.log(item)
		if(item === null){
			localStorage.setItem("recordClick", true)
			toggleContainer.style.display = "block";
		}else{			
			localStorage.removeItem("recordClick")
			toggleContainer.style.display = "none";
		}

	})

	function checkRecord(){
		if(localStorage.getItem("recordClick")){		
			toggleContainer.style.display = "block";
		}
	}
	
	window.onload = () => {
		checkRecord();
	} */
	
	
	
</script>
