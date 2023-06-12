<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/managerHeader.jsp"%>
<style>
.content {
	width: 100%; 
	display : flex;
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
.search--container {
	display: flex;
	justify-content: center;
	margin-right: 200px;
}

#title--box {
	width: 600px;
}

.sub--button {
	background-color: #FF9F8D;
	border: none;
	color: #fff;
	cursor: pointer;
	width: 100px;
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
	width: 100px;
	height: 40px;
}

.input--box {
	border: none;
	border-bottom: 2px solid #ebebeb;
	margin: 10px;
}
.search--buttons {
	width: 900px;
	display: flex;
	justify-content: space-between;
}
.all--reserve {
	display: flex;
	justify-content: center;
	align-items: center;
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
.content--box:hover {
	cursor: pointer;
}
.title--container {
	display: flex;
}
</style>
	<div class="content">
		<div class="title--container">
			<h2>객실 예약 리스트</h2>
		</div>
		<div class="main--content">
				<div class="search--buttons">
					<div class="all--reserve">
						<button onclick="location.href='/manager/reservation'" style="margin-right: 8px;" class="sub--button">오늘 예약 보기</button>
						<button onclick="location.href='/manager/allReservation'" style="margin-right: 8px;" class="sub--button">전체 예약 보기</button>
					</div>
					<form class="form--box">
						<input type="text" name="name" placeholder="예약자 이름을 입력하세요" class="input--box"/> 
						<input type="submit" value="검색" class="sub-button"/>
					</form>
				</div>
				<table class="table--container">
					<thead>
						<tr class="tr--box">
							<th scope="col">이름</th>
							<th scope="col">전화번호</th>
							<th scope="col">체크인</th>
							<th scope="col">체크아웃</th>
							<th scope="col">예약인원</th>
							<th scope="col">룸 타입</th>
							<th scope="col">식당</th>
							<th scope="col">피트니스</th>
							<th scope="col">수영장</th>
							<th scope="col">스파</th>
							<th scope="col">취소</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${reservationList.size() != 0}">
								<c:forEach var="reservation" items="${reservationList}">
									<tr onclick="detailReservation(${reservation.id})" class="content--box">
										<td>${reservation.user.name}</td>
										<td>${reservation.user.tel}</td>
										<td>${reservation.startDate}</td>
										<td>${reservation.endDate}</td>
										<td>${reservation.numberOfP}</td>
										<td>${reservation.room.id != null ? reservation.room.id : ""}${reservation.room != null ? "-" : ""}${reservation.room.roomType.name != null ?  reservation.room.roomType.name : "❌"}</td>
										<td>${reservation.dining.name != null ? reservation.dining.name : "❌"}</td>
										<td>${reservation.fitness.id != null ? "⚪": "❌"}</td>
										<td>${reservation.pool.id != null ? "⚪" : "❌"}</td>
										<td>${reservation.spa.id != null ? "⚪" : "❌"}</td>
										<td><button onclick="deleteReservation(${reservation.id})" class="sub--button">취소</button></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="11" class="content--box">예약리스트가 없습니다.</td>
								</tr>
					        </c:otherwise>
						</c:choose>
					</tbody>
				</table>
					<div style="display: block; text-align: center;" class="paging--box">
					<c:if test="${paging.startPage != 1}">
						<a href="/manager/allReservation?nowPage=${paging.startPage - 1}&cntPerPage=${paging.cntPerPage}">&lt;</a>
					</c:if>
					<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
						<c:choose>
							<c:when test="${p == paging.nowPage}">
								<b>${p}</b>
							</c:when>
							<c:when test="${p != paging.nowPage}">
								<a href="/manager/allReservation?nowPage=${p}&cntPerPage=${paging.cntPerPage}">${p}</a>
							</c:when>
						</c:choose>
					</c:forEach>
					<c:if test="${paging.endPage != paging.lastPage}">
						<a href="/manager/allReservation?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}">&gt;</a>
					</c:if>
				</div>
				<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
	
				<div class="modal-header">
					<h4 class="modal-title">이벤트 등록</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
	
				<div>
					<form action="/event/event-insert" method="post" class="form-container">
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
						<button type="submit" class="sub--button">등록</button>
					</form>
				</div>
			</div>
		</div>
	</div>
			</div>
		</div>
</main>
	<script>
	    function detailReservation(id){
	        let e = window.event;
	        e.preventDefault();
	        location.href = "/manager/reservation/" + id;
	    }
	    function deleteReservation(id){
	        let e = window.event;
	        e.cancelBubble = true;
	        console.log(id)
	        if(confirm("예약 삭제하시겠습니까? 복구 불가")){
	            fetch("/manager/reservation/delete?id=" + id, ({
	                method: "delete",
	            }))
	                .then(async (response) => {
	                    let result = await response.json()
	                    if(result.status_code == 200){
	                        alert(result.msg)
	                        location.href = result.redirect_uri;
	                    }else{
	                        alert(result.msg)
	                    }
	                })
	        }
	    }
	</script>
	