<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<style>
	main {
		width: 1200px;
		margin: 0 auto;
    	display: flex;
    	flex-direction: column;
    	align-items: center;
    	height: 740px;
	}
	
	.all--event--wrap {
		width: 100%;
		display: flex;
		flex-wrap: wrap;
		margin: 0 auto;
		margin-top: 100px;
		justify-content: center;
	}
	
	.event--list {
		width: 500px;
		height: 250px;
		margin: 10px 20px;
		display: flex;
		justify-content: center;
	}
	
	.event--icon {
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    width: 80px;
	    background-color: #ebebeb;
	}
	
	.event--main {
	    display: flex;
	    flex-direction: column;
	    justify-content: space-evenly;
	    align-items: center;
	    width: 420px;
	    background-color: #acacac;
	    font-size: 18px;
	}
	
	.active--btn {
		border: none;
		width: 300px;
		font-size: 20px;
	}
	
	#closed--btn {
		background-color: #fff;
		border-top: 3px solid #84C9FF;
	}
	
</style>
	<main>
		<div>
			<button class="active--btn" type="button" onclick="location.href='/event/eventBoard/onGoing'">진행중인 이벤트</button>
			<button class="active--btn" id="closed--btn" type="button" onclick="location.href='/event/eventBoard/closed'">종료된 이벤트</button>
		</div>
		<div class="all--event--wrap">
			<c:forEach var="list" items="${viewAll}">
				<div class="event--list">
					<div class="event--icon">
						<span class="material-symbols-outlined" style="font-size: 40px">celebration</span>
					</div>
					<div class="event--main">
						<div class="event--title">
							${list.title}
						</div>
						<div class="event--date">
							${list.startDate} ~ ${list.endDate}
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div style="display: block; text-align: center;">		
			<c:if test="${paging.startPage != 1}">
				<a href="/event/eventBoard/closed?nowPage=${paging.startPage - 1}&cntPerPage=${paging.cntPerPage}">&lt;</a>
			</c:if>
			<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
				<c:choose>
					<c:when test="${p == paging.nowPage}">
						<b>${p}</b>
					</c:when>
					<c:when test="${p != paging.nowPage}">
						<a href="/event/eventBoard/closed?nowPage=${p}&cntPerPage=${paging.cntPerPage}">${p}</a>
					</c:when>
				</c:choose>
			</c:forEach>
			<c:if test="${paging.endPage != paging.lastPage}">
				<a href="/event/eventBoard/closed?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}">&gt;</a>
			</c:if>
		</div>
	</main>
<%@ include file="../layout/footer.jsp"%>
