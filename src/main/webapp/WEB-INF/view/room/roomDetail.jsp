<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="/css/navi.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css"/>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
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
<body>
	<div class="body--container">
		<div class="navi--bar">
			<span class="title--box"><a href="/room">객실</a></span>
				<div>
					<span class="detail--box"><a href="/room?type=디럭스">디럭스</a></span>
					<span class="detail--box"><a href="/room?type=프리미엄">프리미엄</a></span>
					<span class="detail--box"><a href="/room?type=스위트">스위트</a></span>
				</div>
		</div>
		<div class="main--container">
			<div class="swiper mySwiper">
				<div class="swiper-wrapper">
			      <div class="swiper-slide">
					<div class="bx-group">
						<div>
							<img alt="객실 사진" src="/images/${room.image}" width="1000px" height="500px">
						</div>
					</div>
			      </div>
			      <div class="swiper-slide">
			      	<div class="bx-group">
						<div>
							<img alt="객실 사진" src="/images/${room.image2}" width="1000px" height="500px">
						</div>
					</div>
			      </div>
			      <div class="swiper-slide">
			      	<div class="bx-group">
						<div>
							<img alt="객실 사진" src="/images/${room.image3}" width="1000px" height="500px">
						</div>
					</div>
			      </div>
		    	</div>
			    <div class="swiper-button-next"></div>
			    <div class="swiper-button-prev"></div>
			 </div>
			
			<div class="info--container">
				<div class="title--box">
				<p>${room.name}</p>
				</div>
				<div class="desc--box--2">
					<p>${room.numberOfP} 인</p>
					<p>가격(1박 기준) : ${room.formatPrice()}원</p>
				</div>
				<div class="desc--box3">
					<p>${room.description}</p>
				</div>
			</div>
			<div>		
			</div>
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
