<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css"/>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
<link rel="stylesheet" href="/css/navi.css" />
<style type="text/css">
	.swiper {
      width: 1450px;
      height: 500px;
    }
    
    .swiper > h4 {
    	margin-left: 70px;
    }

    .swiper-slide {
      text-align: center;
      font-size: 18px;
      background: #fff;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .swiper-slide img {
      display: block;
      width: 100%;
      height: 100%;
      object-fit: cover;
    }
	
	.bx-group {
		display: flex;
		flex-direction: row;
	}
	
	.bx-group > div {
		flex: 1;
		rbga: 0 0 0 .2;
		margin: 20px;
	}
	
	.swiper-slide img {
	    display: block;
	    width: 1000px;
	    height: 500px;
	    object-fit: cover;
	}
	
	.swiper-button-next.swiper-button-disabled, .swiper-button-prev.swiper-button-disabled {
	    opacity: 0;
	    cursor: auto;
	    pointer-events: none;
	}
	
	:root {
    	--swiper-theme-color: #000;
	}
</style>
</head>
<body>
	<div class="body--container">
		<div class="navi--bar">
			<span class="title--box"><a href="/dining">부대시설</a></span>
				<div class="detail--container">
					<c:choose>
						<c:when test="${type eq '레스토랑'}">
							<span class="detail--box color--toggle" onclick="location.href='/dining?type=레스토랑'">레스토랑</span>
							<span class="detail--box" onclick="location.href='/dining?type=라운지'">라운지 & 바</span>
							<span class="detail--box"><a href="/fitness">피트니스</a></span>
							<span class="detail--box"><a href="/pool">수영장</a></span>
							<span class="detail--box"><a href="/spa">스파</a></span>
						</c:when>
						<c:when test="${type eq '라운지'}">
							<span class="detail--box" onclick="location.href='/dining?type=레스토랑'">레스토랑</span>
							<span class="detail--box color--toggle" onclick="location.href='/dining?type=라운지'">라운지 & 바</span>
							<span class="detail--box"><a href="/fitness">피트니스</a></span>
							<span class="detail--box"><a href="/pool">수영장</a></span>
							<span class="detail--box"><a href="/spa">스파</a></span>
						</c:when>
						<c:otherwise>
							<span class="detail--box"><a href="/dining?type=레스토랑">레스토랑</a></span>
							<span class="detail--box"><a href="/dining?type=라운지">라운지 & 바</a></span>
							<span class="detail--box"><a href="/fitness">피트니스</a></span>
							<span class="detail--box"><a href="/pool">수영장</a></span>
							<span class="detail--box"><a href="/spa">스파</a></span>
						</c:otherwise>
					</c:choose>
				</div>
		</div>
		<div class="main--container">
			<c:forEach var="list" items="${diningList}">
				<div>
					<c:choose>
						<c:when test="${type.equals('레스토랑')}">
							<div class="swiper mySwiper">
								<div class="swiper-wrapper">
							      <div class="swiper-slide">
									<div class="bx-group">
										<div>
											<img alt="레스토랑" src="/images/${list.dining.image1}" width="1000px" height="500px">
										</div>
									</div>
							      </div>
							      <div class="swiper-slide">
							      	<div class="bx-group">
										<div>
											<img alt="레스토랑" src="/images/${list.dining.image2}" width="1000px" height="500px">
										</div>
									</div>
							      </div>
							      <div class="swiper-slide">
							      	<div class="bx-group">
										<div>
											<img alt="레스토랑" src="/images/${list.dining.image3}" width="1000px" height="500px">
										</div>
									</div>
							      </div>
						    	</div>
							    <div class="swiper-button-next"></div>
							    <div class="swiper-button-prev"></div>
							 </div>
						</c:when>
						<c:otherwise>
								<div class="swiper mySwiper">
									<div class="swiper-wrapper">
								      <div class="swiper-slide">
										<div class="bx-group">
											<div>
												<img alt="라운지 & 바" src="/images/bar.jpg" style="width: 1000px; height: 500px;">
											</div>
										</div>
								      </div>
							    	</div>
								    <div class="swiper-button-next"></div>
								    <div class="swiper-button-prev"></div>
								 </div>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="info--container">
					<div class="info--outline">
						<p class="title--box">${list.dining.name}</p>
						<p class="desc--box">${list.dining.location}</p>
					</div>
					<div>
						<c:if test="${type != 'All'}">
							<div class="desc--box--2">						
								<p>${list.dining.hours}</p>
							</div>
							<div class="desc--box--3">
								<div class="info--title">
									<p>추가 정보</p>								
								</div>
								<div class="info--content">
									<p>${list.contentDesc1}</p>
									<p>${list.contentDesc2}</p>
									<p>${list.contentDesc3}</p>
									<p>${list.contentDesc4}</p>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<script>
	  let swiper = new Swiper(".mySwiper", {
	      navigation: {
	      	nextEl: ".swiper-button-next",
	      	prevEl: ".swiper-button-prev",
	    },
	  });
	</script>
<%@ include file="../layout/footer.jsp"%>
