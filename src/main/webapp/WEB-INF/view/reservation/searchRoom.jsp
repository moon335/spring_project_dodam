<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="/css/navi.css" />
<link rel="stylesheet" href="/css/calender.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css"/>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
<style>
 .room--container {
 	display: flex;
 	flex-direction: column;
 }
 .sub--button {
 	width: 200px;
 	cursor: pointer;
	background-color: #fff;
	border: 1px solid #ccc;
	margin: 5px;
 }
 #choose--box {
 	display: flex;
 	justify-content: center;
 	margin-bottom: 20px;
 }
 .date--container {
 	display: flex;
 	justify-content: center;
 	font-weight: bold;
 	font-size: 30px;
 	color: #fff;
 }
 .person--container, .button--container {
 	display: flex;
 	justify-content: center;
 	color: #fff;
 }
 .info--container {
 	width: 1000px;
 	display: flex;
 	flex-direction: column;
 	justify-content: center;
 	background-color: #84C9FF;
 	padding: 10px;
 }
 .person--box {
 	margin: 0 10px;
 }

	.swiper {
      width: 1450px;
      height: 600px;
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
	
	#back--btn {
		background-color: black;
	    color: #fff;
	    width: 120px;
	    height: 32px;
	    cursor: pointer;
	    border: none;
	}
	
	#reserve--btn {
		background-color: #FF9F8D;
		border: none;
		color: #fff;
		width: 100px;
		height: 60px;
		cursor: pointer;
	}
	
	.room--info--div {
		display: flex;
		justify-content: space-between;
		margin-top: 10px;
	}
	
</style>
<body>
	<div class="main--container">
		<div class="info--container">
			<span id="choose--box" style="font-size: 20px; color: #fff;"> <선택사항> </span>
			<div class="date--container">
				${searchDto.startDate}
				<span class="person--box">ㅡ</span>
				${searchDto.endDate}
			</div>
			<div class="person--container">
				<div id="countPerson" class="select--box">성인 ${searchDto.countPerson}인</div>
				<span class="person--box">|</span>
				<div id="countChild" class="select--box">어린이 ${searchDto.countChild}인</div>
				<span class="person--box">|</span>
				<div id="countBaby" class="select--box">유아 ${searchDto.countBaby}인</div>
			</div>
			<div class="button--container">
				<button onclick="history.back()" class="sub--button" id="back--btn">변경하기</button>
			</div>
		</div>
		<div class="room--container">
				<div class="swiper mySwiper">
					<div class="swiper-wrapper">
					<c:forEach var="room" items="${roomList}">
						<div class="swiper-slide">
							<div class="bx-group">
								<div>
									<img alt="" src="/images/${room.roomType.image}">
									<div class="room--info--div">
										<div>
											<div style="font-size: 28px; font-weight: bolder;">
												${room.roomType.name} 
											</div>
											<div style="text-align: start; font-size: 20px">
												<span>${room.roomType.formatPrice()}원 ~ </span> 
											</div>
										</div>
										<div>
											<button class="sub--button" id="reserve--btn" onclick="clickResBtn('${searchDto.startDate}', '${searchDto.endDate}','${searchDto.numberOfP}', '${room.id}', '${room.roomType.price}','${room.roomType.name}', '${searchDto.countPerson}', '${searchDto.countChild}', '${searchDto.countBaby}')">예약하기</button> <br />
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
					</div>
					<div class="swiper-button-next"></div>
					<div class="swiper-button-prev"></div>
				</div>
		</div>
	</div>
	<script type="text/javascript">
		function clickResBtn(startDate, endDate, numberOfP, id, price, name, countPerson, countChild, countBaby) {
			let selectReserveDetail = {
				startDate: startDate,
				endDate: endDate,
				numberOfP: numberOfP,
				roomId: id,
				price: price,
				name: name,
				countPerson: countPerson,
				countChild: countChild,
				countBaby: countBaby
			}
			
			let searchParams = new URLSearchParams(selectReserveDetail);
			console.log(searchParams.toString());
			location.href='/reserveRoom?' + searchParams;
			
		};
		
	  let swiper = new Swiper(".mySwiper", {
	      navigation: {
	      	nextEl: ".swiper-button-next",
	      	prevEl: ".swiper-button-prev",
	    },
	  });
	</script>

<%@ include file="../layout/footer.jsp"%>