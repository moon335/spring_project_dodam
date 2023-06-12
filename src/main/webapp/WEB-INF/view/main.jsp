<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=zzkxekb89f"></script>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Caveat:wght@600&family=Gowun+Dodum&family=Nanum+Gothic+Coding&family=Noto+Sans+KR:wght@300;400&family=WindSong:wght@500&display=swap')
	;

* {
	margin: 0;
	padding: 0;
	font-family: 'Noto Sans KR', sans-serif;
}

body {
	background-color: #f9f9f9;
}

.navi--bar--1 {
	color: #fff;
	font-size: 20px;
	position: relative;
	padding-left: 30px;
}

.navi--bar--1 li a, .navi--bar--2 li a, .navi--bar--3 li a,
	.navi--bar--4 li a, .navi--bar--5 li a {
	color: #fff;
	text-decoration: none;
}

.navi--bar--1 li, .navi--bar--2 li, .navi--bar--3 li, .navi--bar--4 li,
	.navi--bar--5 li {
	list-style: none;
}

.navi--bar--detail--room {
	margin-top: 20px;
	display: none;
	position: absolute;
	left: 20px;
}

#symbol {
	height: 30px;
}

.symbol--container {
	display: flex;
}

.reserve--container {
	display: flex;
	justify-content: center;
	font-size: 20px;
	background-color: #fff;
	height: 120px;
	width: 100%;
}

.form--container {
	display: flex;
	padding: 30px;
	width: 1200px;
}

.form--container>table {
	width: 1200px;
	text-align: center;
	border-bottom: 1px solid #000;
}

.check--container {
	margin-right: 10px;
	display: flex;
	align-items: center;
	font-size: 27px;
}

#input--box {
	width: 100%;
	height: 50px;
	border: none;
	text-align: center;
}

.select--box {
	height: 30px;
	border: none;
	width: 100px;
	text-align: center;
}

.toggle--box--room:hover {
	cursor: pointer;
}

.navi--bar--2 {
	color: #fff;
	font-size: 20px;
	position: relative;
	padding-left: 10px;
}

.navi--bar--detail--fac {
	margin-top: 20px;
	display: none;
	position: absolute;
	left: 2px;
}

.toggle--box--fac:hover {
	cursor: pointer;
}

.navi--bar--3 {
	color: #fff;
	font-size: 20px;
	position: relative;
}

.navi--bar--detail--reserve {
	margin-top: 20px;
	display: none;
	position: absolute;
}

.toggle--box--reserve:hover {
	cursor: pointer;
}

.navi--bar--4 {
	color: #fff;
	font-size: 20px;
	position: relative;
}

.navi--bar--detail--qna {
	margin-top: 20px;
	display: none;
	position: absolute;
	right: 55px;
}

.toggle--box--qna:hover {
	cursor: pointer;
}

.navi--bar--5 {
	color: #fff;
}

.symbol--box {
	display: flex;
}

.symbol--box li {
	padding-right: 30px;
}

.symbol--box a {
	color: #fff;
}

.image--1 {
	width: 100%;
	height: 600px;
}

.recommend--container {
	margin: 10px;
	display: flex;
	flex-direction: column;
	align-items: center;
}

.room--box {
	display: flex;
	flex-direction: column;
	margin: 0 50px;
}

.title--container {
	text-align: center;
	margin: 30px;
	font-size: 20px;
	font-weight: bold;
}

.sub--button {
	margin: 0 20px;
	width: 120px;
	background-color: #84C9FF;
	font-size: 18px;
	color: #fff;
	border: none;
	height: 50px;
}

main {
	background-image: url("/images/mainImage.jpg");
	background-repeat: no-repeat;
	background-size: cover;
	height: 800px;
}

#header--wrap {
	display: flex;
	justify-content: space-between;
}

#nav--bar--wrap {
	display: flex;
	margin-top: 30px;
	margin-right: 10px;
}

#toggle--nav--bar {
	display: flex;
	width: 500px;
	text-align: center;
	margin-right: 20px;
}

#navi--1--wrap {
	flex: 1;
}

#navi--2--wrap {
	flex: 1;
}

#navi--3--wrap {
	flex: 1;
}

#navi--4--wrap {
	flex: 1;
}

#date--input--box {
	display: flex;
	align-items: center;
}

.count--container {
	display: flex;
	align-items: center;
	font-size: 27px;
}

#main--reserve--btn {
	display: flex;
	align-items: center;
}

#reserve--first--tr:nth-child(1)>td {
	width: 500px;
}

.down--icon {
	height: 28.89px;
	display: flex;
	align-items: center;
}

.toggle--wrap--room {
	display: flex;
	cursor: pointer;
}

.toggle--wrap--fac {
	display: flex;
	cursor: pointer;
}

.toggle--wrap--reserve {
	display: flex;
	cursor: pointer;
}

.toggle--wrap--qna {
	display: flex;
	cursor: pointer;
}

#reservation--toggle--wrap {
	padding-left: 22px;
}

#logo--image {
	cursor: pointer;
}

.membership--wrap {
	background-color: #f9f9f9;
	width: 100%;
	height: 300px;
	display: flex;
	justify-content: space-evenly;
}

#gift--box {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	width: 300px;
}

#discount--box {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	width: 300px;
}

#point--box {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	width: 300px;
}

.membership--container {
	margin: 10px;
	display: flex;
	flex-direction: column;
	align-items: center;
	background-color: #f9f9f9;
	height: 300px;
}

.membership--icon {
	font-size: 50px;
}

.membership--desc {
	font-size: 18px;
}

#desc--btn {
	background-color: #000;
	color: #fff;
	border: none;
	width: 284px;
	height: 70px;
	cursor: pointer;
}

/* 추가된거 */
.swiper {
	width: 1450px;
	height: 500px;
}

.swiper>h4 {
	margin-left: 70px;
}

.swiper-slide {
	text-align: center;
	font-size: 18px;
	background: none;
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

.bx-group>div {
	flex: 1;
	rbga: 0 0 0 .2;
	margin: 20px;
}

.swiper-slide img {
	display: block;
	width: 800px;
	height: 500px;
	object-fit: cover;
}

.swiper-button-next.swiper-button-disabled, .swiper-button-prev.swiper-button-disabled
	{
	opacity: 0;
	cursor: auto;
	pointer-events: none;
}

:root {
	--swiper-theme-color: #000;
}

.map--container {
	width: 100%;
	background-color: #f9f9f9;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	height: 500px;
	flex: 1;
}

#map {
	width: 100%;
	height: 400px;
}

.introduce--box {
	display: flex;
	cursor: pointer;
}

#pool {
	background-image: url("/images/pool.jpg");
	background-size: cover;
	display: flex;
	flex: 1;
}

#spa {
	background-image: url("/images/spa.jpg");
	background-size: cover;
	display: flex;
	flex: 1;
}

#fitness {
	background-image: url("/images/fitness.jpg");
	background-size: cover;
	display: flex;
	flex: 1;
	display: flex;
}

#pool>a, #spa>a, #fitness>a {
	color: #fff;
	font-size: 50px;
	text-decoration: none;
	margin-left: 15px;
}

.fac--container {
	display: flex;
	flex-direction: column;
	flex: 1;
}

.event--list {
	display: flex;
}

.event--main {
	display: flex;
    flex-direction: column;
    justify-content: center;
    width: 300px;
    margin-left: 10px;
}
.event--title {
	font-weight: bold;
	font-size: 30px;
}
</style>


<main>
	<div id="header--wrap">
		<div>
			<img alt="로고 이미지" src="/images/dodam_wlogo.png" id="logo--image"
				width="400px" onclick="location.href='/'">
		</div>
		<div id="header--nav--wrap">
			<div id="nav--bar--wrap">
				<div id="toggle--nav--bar">
					<div id="navi--1--wrap">
						<ul class="navi--bar--1">
							<li>
								<div class="toggle--wrap--room">
									<p class="toggle--box--room">객실</p>
									<p class="material-symbols-outlined down--icon">keyboard_arrow_down</p>
								</div>
								<ul class="navi--bar--detail--room">
									<li><a href="/room?type=디럭스">디럭스</a></li>
									<li><a href="/room?type=프리미엄">프리미엄</a></li>
									<li><a href="/room?type=스위트">스위트</a></li>
									<li><a href="/room">전체객실</a></li>
								</ul>
							</li>
						</ul>
					</div>
					<div id="navi--2--wrap">
						<ul class="navi--bar--2">
							<li>
								<div class="toggle--wrap--fac">
									<p class="toggle--box--fac">부대시설</p>
									<p class="material-symbols-outlined down--icon">keyboard_arrow_down</p>
								</div>
								<ul class="navi--bar--detail--fac">
									<li><a href="/dining?type=레스토랑">레스토랑</a></li>
									<li><a href="/dining?type=라운지">라운지 & 바</a></li>
									<li><a href="/fitness">피트니스</a></li>
									<li><a href="/pool">수영장</a></li>
									<li><a href="/spa">스파</a></li>
								</ul>
							</li>
						</ul>
					</div>
					<div id="navi--3--wrap">
						<ul class="navi--bar--3">
							<li>
								<div class="toggle--wrap--reserve"
									id="reservation--toggle--wrap">
									<p class="toggle--box--reserve">예약</p>
									<p class="material-symbols-outlined down--icon">keyboard_arrow_down</p>
								</div>
								<ul class="navi--bar--detail--reserve">
									<li><a href="/selectDate">객실예약</a></li>
									<li><a href="/reserveDining">다이닝예약</a></li>
								</ul>
							</li>
						</ul>
					</div>
					<div id="navi--4--wrap">
						<ul class="navi--bar--4">
							<li>
								<div class="toggle--wrap--qna">
									<p class="toggle--box--qna">문의</p>
									<p class="material-symbols-outlined down--icon">keyboard_arrow_down</p>
								</div>
								<ul class="navi--bar--detail--qna">
									<li><a href="/question/question">FAQ</a></li>
									<li style="width: 100px;"><a href="/question/qnaPage">1:1 문의</a></li>
									<li><a href="/event/eventBoard/onGoing">이벤트</a></li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
				<div class="navi--bar--5">
					<ul class="symbol--box">
						<c:choose>
							<c:when test="${principal != null}">
								<li><a href="/logout"><span
										class="material-symbols-outlined">logout</span></a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/login"><span
										class="material-symbols-outlined">login</span></a></li>
							</c:otherwise>
						</c:choose>
						<li><a href="/myPage"><span class="material-symbols-outlined">person</span></a></li>
						<li><a href="/membership"><span class="material-symbols-outlined">card_membership</span></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</main>
<div class="reserve--container">
	<form action="/search" method="get" class="form--container">
		<table>
			<tr id="reserve--first--tr">
				<td>체크인 & 체크아웃</td>
				<td>성인</td>
				<td>어린이</td>
				<td>유아</td>
			</tr>
			<tr id="reserve--second--tr">
				<td><input class="dateSelector" id="input--box"
					placeholder="날짜를 선택하세요" name="date"></td>
				<td><select name="countPerson" class="select--box">
						<option value="1">1</option>
						<option value="2">2</option>
				</select></td>
				<td><select name="countChild" class="select--box">
						<option value="0">0</option>
						<option value="1">1</option>
						<option value="2">2</option>
				</select></td>
				<td><select name="countBaby" class="select--box">
						<option value="0">0</option>
						<option value="1">1</option>
						<option value="2">2</option>
				</select></td>
			</tr>
		</table>
		<div id="main--reserve--btn">
			<button type="submit" class="sub--button">검색</button>
		</div>
	</form>
</div>
<div class="recommend--container">
	<p class="title--container">진행중인 이벤트</p>
	<div class="swiper mySwiper">
		<div class="swiper-wrapper">
			<c:forEach var="list" items="${events}">
				<div class="swiper-slide">
					<div class="bx-group">
						<div class="event--list">
							<div class="event--img">
								<img alt="" src="/images/uploads/${list.uploadFile}">
							</div>
							<div class="event--main">
								<div class="event--title">${list.title}</div>
								<div class="event--content">${list.content}</div>
								<div class="event--date">${list.startDate}~
									${list.endDate}</div>
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

<div class="membership--container">
	<p class="title--container">도담 멤버쉽</p>
	<div class="membership--wrap">
		<div id="gift--box">
			<span class="material-symbols-outlined membership--icon">redeem</span>
			<p class="membership--desc">회원 전용 이벤트를 누려보세요</p>
		</div>
		<div id="discount--box">
			<span class="material-symbols-outlined membership--icon">calculate</span>
			<p class="membership--desc">멤버십 회원만을 위한</p>
			<p class="membership--desc">숙박 쿠폰을 제공합니다</p>
		</div>
		<div id="point--box">
			<span class="material-symbols-outlined membership--icon">monetization_on</span>
			<p class="membership--desc">포인트를 적립하고,</p>
			<p class="membership--desc">현금처럼 실용적으로 사용하세요</p>
		</div>
	</div>
	<button type="button" onclick="location.href='/membership'"
		id="desc--btn">자세히 보기</button>
</div>

<div class="introduce--box">
	<div class="fac--container">
		<div id="pool" onclick="location.href='/pool'">
			<a href="/pool">Pool</a>
		</div>
		<div id="spa" onclick="location.href='/spa'">
			<a href="/spa">Spa</a>
		</div>
		<div id="fitness" onclick="location.href='/fitness'">
			<a href="/fitness">Fitness</a>
		</div>
	</div>
	<div class="map--container">
		<span style="margin-bottom: 20px;"><b>찾아오시는 길</b></span>
		<div id="map"></div>
	</div>
</div>


<!-- 예약 달력 -->
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script>
	let dateSelector = document.querySelector('.dateSelector')
	$(".dateSelector").flatpickr({
		enableTime : false,
		dateFormat : "Y-m-d",
		mode : "range",
		minDate : "today",
	});
	
	let swiper = new Swiper(".mySwiper", {
		navigation : {
			nextEl : ".swiper-button-next",
			prevEl : ".swiper-button-prev",
		},
	});

	var mapOptions = {
		center : new naver.maps.LatLng(35.1595148, 129.0602424),
		zoom : 17
	};
	var map = new naver.maps.Map('map', mapOptions);
	var map = new naver.maps.Map(document.getElementById('map'), {
		center : new naver.maps.LatLng(35.1595148, 129.0602424),
		zoom : 17
	});
	var marker = new naver.maps.Marker({
		position : new naver.maps.LatLng(35.1595148, 129.0602424),
		map : map
	});
</script>
<script src="js/mainToggle.js"></script>
<%@ include file="layout/footer.jsp"%>
