<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="/css/navi.css" />
<link rel="stylesheet" href="/css/calender.css" />
<link rel="stylesheet" href="/css/myPage.css" />
<style type="text/css">
.main--container {
	display: flex;
	flex-direction: column;
	align-items: center;
	margin-top: 100px;
	height: 100vh;
	width: 100%;
}

.facilities--container {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

.facilities--detail {
	display: flex;
	justify-content: space-between;
	width: 300px;
}
.facilities--info--box {
	display: flex;
	justify-content: space-between;
}
#logo {
	width: 300px;
	height: 50px;
	opacity: 0.8;
}
#info--box--end {
	border-bottom: 1px solid #ebebeb;
}
.info--container {
	margin-top: 40px;
}
.fac--detail {
	display: flex;
	flex-direction: column;
	align-items: center;
}
</style>
</head>
<body>
	<div class="main--container">
		<div class="facilities--container">
			<div class="select--info">
				<div class="reserve--box">
					<img alt="" src="/images/dodam_logo.png" id="logo">
					<h5 style="text-align: center; margin-bottom: 50px;">고객님의 예약이 완료되었습니다</h5>
				</div>
				<div class="facilities--info--box">
					<div class="info--title">객실</div> 
					<div class="info--content">${successful.room.roomType.name}</div>
				</div>
				
				<div class="facilities--info--box">
						<div class="info--title">체크인</div>
						<div class="info--content">${successful.startDate} 15:00</div>
				</div>
				<div class="facilities--info--box">
						<div class="info--title">체크아웃</div>
						<div class="info--content">${successful.endDate} 11:00</div>
				</div>
				
				<div class="facilities--info--box" id="info--box--end">
					<div class="info--title">투숙인원</div>
					<div class="info--content">${successful.numberOfP}명</div>
				</div>
			</div>
		<div class="fac--detail">
			<div class="facilities--detail">
				<div class="facilities--detail--title">
					다이닝
				</div>
				<div class="facilities--detail--option">
					<c:choose>
						<c:when test="${successful.diningId != null}">
							${successful.diningId} 명
						</c:when>
						<c:otherwise>
						사용 안함
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="facilities--detail">
				<div class="facilities--detail--title">
				</div>
				</div>
			<div class="facilities--detail">
				<div class="facilities--detail--title">
					스파
				</div>
				<div class="facilities--detail--option">
					 <div class="facilities--detail--option">
					<c:choose>
						<c:when test="${successful.spaId != null}">
							${successful.spaId} 명
						</c:when>
						<c:otherwise>
						사용 안함
						</c:otherwise>
					</c:choose>
				</div>
				</div>
			</div>
		<div class="facilities--detail">
				<div class="facilities--detail--title">
				</div>
				</div>
			<div class="facilities--detail">
				<div class="facilities--detail--title">
					수영장
				</div>
				<div class="facilities--detail--option">
					<c:choose>
						<c:when test="${successful.fitnessId != null}">
							${successful.fitnessId} 명
						</c:when>
						<c:otherwise>
							사용 안함
						</c:otherwise>
					</c:choose>
				</div>
			</div>
				<div class="facilities--detail">
				<div class="facilities--detail--title">
					피트니스
				</div>
				<div class="facilities--detail--option">
					<c:choose>
						<c:when test="${successful.spaId != null}">
							${successful.spaId} 명
						</c:when>
						<c:otherwise>
						사용 안함
						</c:otherwise>
					</c:choose>
				</div>
				</div>
			
		<div class="info--container">			
			<div>
				<fieldset>
					<legend>결제 방법 : ${payType.pgType}</legend>
				</fieldset>
				<fieldset>
					<legend>결제 금액 : ${payType.formatPrice()}</legend>
				</fieldset>
			</div>
			<button type="button" class="sub--button" onclick="main()">메인 으로</button>
		</div>
		</div>
		</div>
		
	</div>

<script type="text/javascript">
	function main(){
		location.href = "/"
	}
</script>
<%@ include file="../layout/footer.jsp"%>

