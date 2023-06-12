<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="/css/navi.css" />
<style type="text/css">
	.room--title {
		display: flex;
	}
	.change--view {
		display: flex;
		align-items: center;
	}
</style>
</head>
<body>
	<div class="body--container">
		<div class="navi--bar">
			<div class="room--title">
				<span class="title--box" onclick="location.href='/room'">객실</span>
				<c:if test="${type eq 'All' || type eq 'AllInOne'}">
					<div class="change--view">
						<span onclick="location.href='/room'" style="margin-left: 10px; cursor: pointer;">펼쳐보기</span>
						<span style="margin-left: 10px; margin-right: 10px;"> | </span>
						<span style="cursor: pointer;" onclick="location.href='/room?type=AllInOne'">모아보기</span>
					</div>
				</c:if>
			</div>
				<div>
					<c:choose>
						<c:when test="${type eq '디럭스'}">
							<span class="detail--box color--toggle" onclick="location.href='/room?type=디럭스'">디럭스</span>
							<span class="detail--box" onclick="location.href='/room?type=프리미엄'">프리미엄</span>
							<span class="detail--box" onclick="location.href='/room?type=스위트'">스위트</span>
						</c:when>
						<c:when test="${type eq '프리미엄'}">
							<span class="detail--box" onclick="location.href='/room?type=디럭스'">디럭스</span>
							<span class="detail--box color--toggle" onclick="location.href='/room?type=프리미엄'">프리미엄</span>
							<span class="detail--box" onclick="location.href='/room?type=스위트'">스위트</span>
						</c:when>
						<c:when test="${type eq '스위트'}">
							<span class="detail--box" onclick="location.href='/room?type=디럭스'">디럭스</span>
							<span class="detail--box" onclick="location.href='/room?type=프리미엄'">프리미엄</span>
							<span class="detail--box color--toggle" onclick="location.href='/room?type=스위트'">스위트</span>
						</c:when>
						<c:otherwise>
							<span class="detail--box" onclick="location.href='/room?type=디럭스'">디럭스</span>
							<span class="detail--box" onclick="location.href='/room?type=프리미엄'">프리미엄</span>
							<span class="detail--box" onclick="location.href='/room?type=스위트'">스위트</span>
						</c:otherwise>
					</c:choose>
				</div>
		</div>
		<div class="main--container">
			<div>
				<c:forEach var="roomList" items="${roomList}">
						<div class="info--container">
							<a href="/detailRoom/${roomList.id}">
								<img alt="디럭스" src="images/${roomList.image}" style="width: 1000px; height: 500px;">
							</a>
							<div class="info--outline">
								<p class="title--box">${roomList.name}</p>
								<p class="desc--box"> | ${roomList.numberOfP}인</p>
							</div>
						</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>
