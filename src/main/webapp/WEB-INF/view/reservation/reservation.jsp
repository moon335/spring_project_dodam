<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="/css/navi.css" />
<link rel="stylesheet" href="/css/calender.css" />
<link rel="stylesheet" href="/css/myPage.css" />
<style type="text/css">
.form--box {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 700px;
	width: 1200px;
	border: 1px solid #ebebeb;
}
.facilities--container {
	display: flex;
	flex: 1;
	flex-direction: column;
	height: 100%;
	justify-content: center;
	padding: 10px;
	border-right: 1px solid #ccc;
	justify-content: space-around;
}
.info--container {
	display: flex;
	flex: 1;
	flex-direction: column;
	height: 100%;
}
.facilities--detail {
	text-align: center;
	margin-top: 10px;
	display: flex;
	justify-content: space-between;
}
.select--info {
	font-size: 20px;
	font-weight: bold;
	display: flex;
	flex-direction: column;
}
.input--box {
	border: none;
}
.symbol:hover {
	color: #FF9F8D;
	cursor: pointer;
}

.facilities--info--box {
	display: flex;
	justify-content: space-between;
}

.count--box {
	width: 100px;
}

#totalPrice {
	display: flex;
	flex-direction: column;
	width: 100%;
	align-items: center;
}

.check--in--box {
	display: flex;
	flex-direction: column;
	justify-content: center;
	text-align: center;
	width: 250px;
	height: 150px;
	border-right: 2px solid #ccc;
}

.check--out--box {
	display: flex;
	flex-direction: column;
	justify-content: center;
	text-align: center;
	width: 250px;
	height: 150px;
}

#date--box {
	border: 2px solid #ccc;
	border-radius: 10px;
	justify-content: space-evenly;
	position: relative;
}

#day--count{
	position: absolute;
	top: 56px;
    right: 282px;
    background-color: #64c5f3;
    color: #fff;
    border-radius: 10px;
    width: 50px;
    text-align: center;
}

.icon--box {
	display: flex;
	height: 100%;
	align-items: center;
}

.facilities--detail--option {
	display: flex;
}

#total--price--input {
	border: none;
}

input[type=number]:focus {
	outline: none;
}

input[type=text]:focus {
	outline: none;
}

select:focus {
	outline: none;
}

.room--price {
	display: flex;
	justify-content: space-between;
	width: 300px;
	font-size: 20px;
	margin-bottom: 10px;
	margin-top: 150px;
}
.option--price {
	display: flex;
	justify-content: space-between;
	width: 300px;
	font-size: 20px;
	margin-bottom: 20px;
}
.coupon--price {
	display: flex;
	flex-direction: column;
	width: 100%;
	align-items: center;
	border-top: 1px solid #ccc;
	position: relative;
}

#coupon--text{
	position: absolute;
	left: 34px;
    top: -16px;
    background-color: #fff;
    font-size: 20px;
}

#coupon--price--info {
	display: flex;
	justify-content: space-between;
	width: 300px;
	font-size: 18px;
	margin-bottom: 10px;
}

#coupon--result {
	width: 300px;
	margin-top: 50px;
}

.point--price {
	display: flex;
	flex-direction: column;
	width: 100%;
	align-items: center;
}

#point--price--info {
	display: flex;
	justify-content: space-between;
	width: 300px;
	font-size: 18px;
	margin-bottom: 20px;
}

#point--result {
	width: 300px;
}

.total--price {
	display: flex;
	justify-content: space-between;
	width: 300px;
	font-size: 20px;
	margin-top: 100px;
}

#total--price--input {
	width: 100px;
	text-align: end;
}

#diningResult {
	border: none;
    text-align: center;
}

#poolResult {
	border: none;
    text-align: center;
}

#spaResult {
	border: none;
    text-align: center;
}

#fitnessResult {
	border: none;
    text-align: center;
}

input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}
.pay {
	margin: 0 10px;
}
</style>
</head>
<body>
	<div class="main--container">
	<form name="form" action="/reserveRoom" method="post" class="form--box" id="reservation">
		<div class="facilities--container">
			<div class="select--info">

				<div class="facilities--info--box">
					<div class="info--title">객실</div> 
					<div class="info--content">${selectDetail.name}</div>
				</div>
				
				<div class="facilities--info--box" id="date--box">
					<div class="check--in--box">
						<div class="info--title">체크인</div>
						<div class="info--content">${selectDetail.startDate} 15:00</div>
					</div>
					<div class="check--out--box">
						<div class="info--title">체크아웃</div>
						<div class="info--content">${selectDetail.endDate} 11:00</div>
					</div>
					<div id="day--count"></div>
				</div>
				
				<div class="facilities--info--box">
					<div class="info--title">투숙인원</div>
					<div class="info--content">성인 ${selectDetail.countPerson}, 어린이 ${selectDetail.countChild}, 유아 ${selectDetail.countBaby}</div>
				</div>
			</div>
			<input type="hidden" value="${selectDetail.startDate}" name="startDate"> 
			<input type="hidden" value="${selectDetail.endDate}" name="endDate"> 
			<input type="hidden" value="${selectDetail.numberOfP}" name="numberOfP"> 
			<input type="hidden" value="${selectDetail.roomId}" name="roomId"> 
			<input type="hidden" name="day" id="day--result">
		<div>
		<c:choose>
		<c:when test="${diningStatus.availability == true}">
			<div class="facilities--detail">
				<div class="facilities--detail--title">
					다이닝신청 (조식)
				</div>
				<!--  <input type="button" onclick='diningCount("minus")' value="-">-->
				<div class="facilities--detail--option">
					<div class="icon--box">
						<span class="material-symbols-outlined symbol" onclick="diningMinus()">do_not_disturb_on</span>
					</div>
					<input type="number" id='diningResult' value="0" name="diningCount" min="0" class="count--box">
					<div class="icon--box">
						<span class="material-symbols-outlined symbol" onclick="diningPlus()">add_circle</span>
					</div> 
				</div>
			</div>
			</c:when>
			<c:otherwise>
			<div class="facilities--detail">
				<div class="facilities--detail--title">
					${diningStatus.statusDesc}
				</div>
				</div>
			</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${spaStatus.availability == true}">
			<div class="facilities--detail">
				<div class="facilities--detail--title">
					스파신청
				</div>
				<div class="facilities--detail--option">
					<div class="icon--box">
						<span class="material-symbols-outlined symbol" onclick="spaMinus()">do_not_disturb_on</span>
					</div>
					<input type="number" id='spaResult' value="0" name="spaCount" min="0" class="count--box">
					<div class="icon--box">
						<span class="material-symbols-outlined symbol" onclick="spaPlus()">add_circle</span>
					</div>  
				</div>
			</div>
	</c:when>
	<c:otherwise>
		<div class="facilities--detail">
				<div class="facilities--detail--title">
					${spaStatus.statusDesc}
				</div>
				</div>
	</c:otherwise>
	</c:choose>
	<c:choose>
	<c:when test="${poolStatus.availability == true}">
			<div class="facilities--detail">
				<div class="facilities--detail--title">
					수영장신청
				</div>
				<div class="facilities--detail--option">
					<div class="icon--box">
						<span class="material-symbols-outlined symbol" onclick="poolMinus()">do_not_disturb_on</span>
					</div>
					<input type="number" id='poolResult' value="0" name="poolCount" min="0" class="count--box">
					<div class="icon--box">
						<span class="material-symbols-outlined symbol" onclick="poolPlus()">add_circle</span>
					</div> 
				</div>
			</div>
			</c:when>
			<c:otherwise>
				<div class="facilities--detail">
				<div class="facilities--detail--title">
					${poolStatus.statusDesc}
				</div>
				</div>
			</c:otherwise>
	</c:choose>
	<c:choose>
	<c:when test="${fitnessStatus.availability == true}">
			<div class="facilities--detail">
				<div class="facilities--detail--title">
					피트니스신청
				</div>
				<div class="facilities--detail--option">
					<div class="icon--box">
						<span class="material-symbols-outlined symbol" onclick="fitnessMinus()">do_not_disturb_on</span>
					</div>
					<input type="number" id='fitnessResult' value="0" name="fitnessCount" min="0" class="count--box">
					<div class="icon--box">
						<span class="material-symbols-outlined symbol" onclick="fitnessPlus()">add_circle</span>
					</div> 
				</div>
			</div>
			</c:when>
			<c:otherwise>
				<div class="facilities--detail">
				<div class="facilities--detail--title">
					${fitnessStatus.statusDesc}
				</div>
				</div>
			</c:otherwise>
		 </c:choose>
		</div>
		</div>
		
		<div class="info--container">			
			<div id="totalPrice">
				<div id="select--box--wrap">
					<c:choose>
						<c:when test="${couponList != null}">
							<select id="coupon--result" name="coupon">
								<option value="0">쿠폰을 선택하세요</option>
								<c:forEach var="couponList" items="${couponList}">
									<option value="${couponList.couponInfo.id}">${couponList.couponInfo.name}</option>
								</c:forEach>
							</select>
						</c:when>
						<c:otherwise>
							<select>
								<option value="0">쿠폰을 선택하세요</option>
								<option value="0">사용 가능한 쿠폰이 없습니다.</option>
							</select>
						</c:otherwise>
					</c:choose>
					<input type="number" name="point" placeholder="사용 가능 포인트 : ${point}" id="point--result" autocomplete="off" onkeyup="checkMaxNum(${point})">
				</div>
			</div>
			<div>
				<fieldset>
					<legend style="font-size: 17px;">결제 방법 선택</legend>
					<input type="radio" name="pgType" value="nicepay" id="nicepay" class="pg-type pay" checked><label for="nicepay">신용카드결제</label>
					<input type="radio" name="pgType" value="kakaopay" id="kakaopay" class="pg-type pay"><label for="kakaopay">카카오페이결제</label>
					<input type="radio" name="pgType" value="tosspay" id="tosspay" class="pg-type pay"><label for="tosspay">토스간편결제</label>
				</fieldset>
			</div>
			<button type="button" class="sub--button" onclick="payEvent()">결제하기</button>
		</div>
	</form>
	</div>


<!-- <script src="/js/price.js"></script> -->
<script type="text/javascript">
	const dayResultInput = document.getElementById("day--result");
	const dayCountDiv = document.getElementById("day--count");

	let searchParamsDate = (new URL(document.location)).searchParams;
	let startDatejs = searchParamsDate.get("startDate");
	let endDatejs = searchParamsDate.get("endDate");

	let startDatejsArray = startDatejs.split("-");
	let endDatejsArray = endDatejs.split("-");

	let startDateSeconds = new Date(startDatejsArray[0], startDatejsArray[1]-1, startDatejsArray[2]);
	let endDateSeconds = new Date(endDatejsArray[0], endDatejsArray[1]-1, endDatejsArray[2]);

	let day = (endDateSeconds-startDateSeconds) /1000 / 60 / 60 / 24

	dayResultInput.value = day;
	dayCountDiv.textContent = day + "박";

	const diningPrice = ${diningPrice};
	const spaPrice = ${spaPrice};
	const poolPrice = ${poolPrice};
	const fitnessPrice = ${fitnessPrice};


	const room = ${selectDetail.price} * day;
	let roomPrice = Number(room).toLocaleString("ko-KR");
	const totalPriceTag = document.getElementById("totalPrice");

	const diningDivTag = document.getElementById('diningResult')
	const spaDivTag = document.getElementById('spaResult')
	const poolDivTag = document.getElementById('poolResult')
	const fitnessDivTag = document.getElementById('fitnessResult')

	const couponSelectTag =  document.getElementById('coupon--result')

	const pointSelectTag =  document.getElementById('point--result')
	const tagetDivArray = [diningDivTag, spaDivTag, poolDivTag, fitnessDivTag, couponSelectTag, pointSelectTag]

	let coupon = 0;
	let point = 0;

	// 예약 날짜
	const startDate = ${selectDetail.startDate};
	const endDate = ${selectDetail.endDate};
	let nights = endDate - startDate;

	// 총 계산
	function totalPrice(){
		while(totalPriceTag.firstChild)  {
			totalPriceTag.firstChild.remove()
		}
		let diningPriceResult = parseInt(diningDivTag.value) * diningPrice;
		let spaPriceResult = parseInt(spaDivTag.value) * spaPrice;
		let poolPriceResult = parseInt(poolDivTag.value) * poolPrice;
		let fitnessPriceResult = parseInt(fitnessDivTag.value) * fitnessPrice;



		const roomPriceTag = document.createElement("div");
		const roomPriceNameTag = document.createElement("div");
		const roomPriceNumTag = document.createElement("div");
		roomPriceTag.className = "room--price";
		roomPriceNameTag.textContent = "객실 금액:";
		roomPriceNumTag.textContent = roomPrice;
		roomPriceTag.append(roomPriceNameTag);
		roomPriceTag.append(roomPriceNumTag);


		const optionPriceTag = document.createElement("div");
		const optionPriceNameTag = document.createElement("div");
		const optionPriceNumTag = document.createElement("div");
		optionPriceTag.className = "option--price";
		const option = diningPriceResult + spaPriceResult + poolPriceResult + fitnessPriceResult;
		let optionPrice = Number(option).toLocaleString("ko-KR");
		optionPriceNameTag.textContent = "옵션 금액:";
		optionPriceNumTag.textContent = optionPrice;
		optionPriceTag.append(optionPriceNameTag);
		optionPriceTag.append(optionPriceNumTag);

		const couponPriceTag = document.createElement("div");
		const couponPriceNameTag = document.createElement("div");
		const couponPriceNumTag = document.createElement("div");
		const couponPriceInfoTag = document.createElement("div");
		const couponTextTag = document.createElement("div");
		couponTextTag.textContent = "할인";
		couponTextTag.id = "coupon--text";
		couponPriceTag.className = "coupon--price";
		couponPriceInfoTag.id = "coupon--price--info";
		couponPriceNameTag.textContent = "쿠폰 할인 금액:";
		let couponPrice = Number(coupon).toLocaleString("ko-KR");
		couponPriceNumTag.textContent = couponPrice;
		couponPriceTag.append(couponSelectTag);
		couponPriceInfoTag.append(couponPriceNameTag);
		couponPriceInfoTag.append(couponPriceNumTag);
		couponPriceTag.append(couponPriceInfoTag);
		couponPriceTag.append(couponTextTag);

		const pointPriceTag = document.createElement("div");
		const pointPriceNameTag = document.createElement("div");
		const pointPriceNumTag = document.createElement("div");
		const pointPriceInfoTag = document.createElement("div");
		pointPriceTag.className = "point--price";
		pointPriceInfoTag.id = "point--price--info";
		pointPriceNameTag.textContent = "포인트 사용 금액:";
		let pointPrice = Number(point).toLocaleString("ko-KR");
		pointPriceNumTag.textContent = pointPrice;
		pointPriceInfoTag.append(pointPriceNameTag);
		pointPriceInfoTag.append(pointPriceNumTag);
		pointPriceTag.append(pointSelectTag);
		pointPriceTag.append(pointPriceInfoTag);

		let totalPrice = room + option + coupon - point;

		const totalPriceDivTag = document.createElement("div");
		totalPriceDivTag.className = "total--price";
		const totalPriceNameTag = document.createElement("div");
		totalPriceNameTag.textContent = "총 결제 금액: ";
		const priceDivBox = document.createElement("div");
		const totalDivTag = document.createElement("input");
		const priceSpanTag = document.createElement("span");
		priceSpanTag.textContent = "원";
		totalDivTag.setAttribute("type", "text");
		totalDivTag.id = "total--price--input";
		totalDivTag.setAttribute("name", "totalPrice");
		totalDivTag.setAttribute("readonly", "readonly");
		totalDivTag.value = totalPrice;
		priceDivBox.append(totalDivTag);
		priceDivBox.append(priceSpanTag);
		totalPriceDivTag.append(totalPriceNameTag);
		totalPriceDivTag.append(priceDivBox);

		totalPriceTag.append(roomPriceTag);
		totalPriceTag.append(optionPriceTag);
		totalPriceTag.append(couponPriceTag);
		totalPriceTag.append(pointPriceTag);
		totalPriceTag.append(totalPriceDivTag);
		totalPriceValue = document.getElementById("total--price--input").value;
	}
	console.log(couponSelectTag)

	couponSelectTag.addEventListener("change", ()=>{
		const startDate = ${selectDetail.startDate};
		const endDate = ${selectDetail.endDate};
		let nights = endDate - startDate;
		console.log(couponSelectTag.value)
		if(couponSelectTag.value == 1) {
			// '1박 무료 숙박 쿠폰'
			coupon = -${selectDetail.price}
		}
		else if(couponSelectTag.value == 2){
			// "객실 10% 할인 쿠폰"
			coupon = (${selectDetail.price} * nights) * 0.1;
			console.log(coupon)
		}
		else if(couponSelectTag.value == 3){
			// "객실 30% 할인 쿠폰"
			coupon = (${selectDetail.price} * nights) * 0.3;
		}
		else if(couponSelectTag.value == 4){
			//"다이닝 식사권 2매 쿠폰"
			coupon = -60000;
		} else {
			coupon = 0;
		}

	})

	pointSelectTag.addEventListener("change", ()=> {
		point = pointSelectTag.value
	});


	tagetDivArray.forEach((target)=>{
		target.addEventListener("change", totalPrice);
	})

	function diningMinus() {
		const resultElement = document.getElementById("diningResult");
		let number = resultElement.value;
		if(number > 0){
			number = parseInt(number) - 1;
			resultElement.value = number;
			totalPrice();
		}
	}

	function diningPlus() {
		const resultElement = document.getElementById("diningResult");
		let maxNumber = ${selectDetail.countPerson} + ${selectDetail.countChild} + ${selectDetail.countBaby};
		let number = resultElement.value;
		if(number < maxNumber) {
			let number = resultElement.value;
			number = parseInt(number) + 1;
			resultElement.value = number;
		}
		totalPrice();
	}

	function spaMinus() {
		const resultElement = document.getElementById("spaResult");
		let number = resultElement.value;
		if(number > 0){
			number = parseInt(number) - 1;
			resultElement.value = number;
			totalPrice();
		}
	}

	function spaPlus() {
		const resultElement = document.getElementById("spaResult");
		let maxNumber = ${selectDetail.countPerson} + ${selectDetail.countChild} + ${selectDetail.countBaby};
		let number = resultElement.value;
		if(number < maxNumber) {
			let number = resultElement.value;
			number = parseInt(number) + 1;
			resultElement.value = number;
		}
		totalPrice();
	}

	function poolMinus() {
		const resultElement = document.getElementById("poolResult");
		let number = resultElement.value;
		if(number > 0){
			number = parseInt(number) - 1;
			resultElement.value = number;
			totalPrice();
		}
	}


	function poolPlus() {
		const resultElement = document.getElementById("poolResult");
		let maxNumber = ${selectDetail.countPerson} + ${selectDetail.countChild} + ${selectDetail.countBaby};
		let number = resultElement.value;
		if(number < maxNumber) {
			let number = resultElement.value;
			number = parseInt(number) + 1;
			resultElement.value = number;
		}
		totalPrice();
	}

	function fitnessMinus() {
		const resultElement = document.getElementById("fitnessResult");
		let number = resultElement.value;
		if(number > 0){
			number = parseInt(number) - 1;
			resultElement.value = number;
			totalPrice();
		}
	}

	function fitnessPlus() {
		const resultElement = document.getElementById("fitnessResult");
		let maxNumber = ${selectDetail.countPerson} + ${selectDetail.countChild} + ${selectDetail.countBaby};
		let number = resultElement.value;
		if(number < maxNumber) {
			let number = resultElement.value;
			number = parseInt(number) + 1;
			resultElement.value = number;
		}
		totalPrice();
	}

	function checkMaxNum(point) {
		let numberBox = document.getElementById("point--result");
		let setMaxNum = parseInt(numberBox.value);

		if(setMaxNum < 0) {
			alert("0보다 작은 수는 입력할 수 없습니다.");
			numberBox.value = 0;
		} else if(setMaxNum > point) {
			alert("보유한 포인트보다 많은 포인트를 입력할 수 없습니다.")
			numberBox.value = 0;
		}
	}

	totalPrice();
</script>
<!-- writer: 이현서 -->
<!-- 나이스페이 결제 모듈 스크립트 -->
<script src="https://pay.nicepay.co.kr/v1/js/"></script>
<!-- 토스페이 결제 모듈 스크립트 -->
<script src="https://js.tosspayments.com/v1/payment-widget"></script>

<script>
	let orderNameValue = '${orderName}';
	let totalPriceValue = document.getElementById("total--price--input").value;
	let form = document.getElementById("reservation");

	
	let e = window.event;
	let pgArray = [...document.getElementsByClassName("pg-type")];
	function payEvent() {
		let payType;
		pgArray.some((pgInput) => {
			if(pgInput.checked){
				payType = pgInput.value
				return
			}
		})

		let popupOption = "width=800,height=800";
		let url;
		if(payType === "nicepay"){
			url = "/pay/payReady?paySelect=nicepay&total_amount="+ totalPriceValue +"&orderName="+orderNameValue;
			let returnPay = window.open(
					url,
					"popup",
					popupOption
			);
			returnPay.focus();
		}else if(payType === "kakaopay"){
			url = "/pay/kakaopay?item_name="+ orderNameValue +"&total_amount=" + totalPriceValue
			let returnPay = window.open(
					url,
					"popup",
					popupOption
			);
			returnPay.focus();
		}else if(payType === "tosspay"){
			url = "/pay/payReady?paySelect=toss&total_amount="+ totalPriceValue +"&orderName="+orderNameValue;
			let returnPay = window.open(
					url,
					"popup",
					popupOption
			);
			returnPay.focus();
		}
	}

	function getReturnValue(returnValue){
		let returnJSON = JSON.parse(returnValue);
		console.log(returnJSON)
		if(returnJSON.status == "OK"){
			let tidInputTag = document.createElement("input");
			tidInputTag.type = "hidden";
			tidInputTag.value = returnJSON.tid;
			tidInputTag.name = "tid"
			form.append(tidInputTag)
			form.submit();
		}
	}

</script>
<%@ include file="../layout/footer.jsp"%>
