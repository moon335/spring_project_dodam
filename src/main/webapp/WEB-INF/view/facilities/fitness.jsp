<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
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
				<div>
					<span class="detail--box"><a href="/dining?type=레스토랑">레스토랑</a></span>
					<span class="detail--box"><a href="/dining?type=라운지">라운지 & 바</a></span>
					<span class="detail--box selected--menu"><a href="/fitness">피트니스</a></span>
					<span class="detail--box"><a href="/pool">수영장</a></span>
					<span class="detail--box"><a href="/spa">스파</a></span>
				</div>
		</div>
	<div class="main--container">
		<div>
			<div class="swiper mySwiper">
					<div class="swiper-wrapper">
						<div class="swiper-slide">
							<div class="bx-group">
								<div>
									<img alt="피트니스 사진" src="/images/${fitness.facilities.image1}" width="1000px" height="500px">
								</div>
							</div>
						</div>
						<div class="swiper-slide">
							<div class="bx-group">
								<div>
									<img alt="피트니스 사진" src="/images/${fitness.facilities.image2}" width="1000px" height="500px">
								</div>
							</div>
						</div>
					</div>
					<div class="swiper-button-next"></div>
					<div class="swiper-button-prev"></div>
				</div>
			 </div>
		<div class="info--container">
		 	<div class="info--outline">
				<p class="title--box"> ${fitness.facilities.name}</p>
				<p class="desc--box"> ${fitness.facilities.location}</p>
		 	</div>
		 	<div class="desc--box--2">
				<p> ${fitness.hours}</p>
		 	</div>
				<span class="info--title--desc"> ${fitness.facilitiesDesc.titleDesc}</span>
				<div class="desc--box--3">
					<div class="info--title">
					<p> ${fitness.facilitiesDesc.contentDesc1}</p>
					</div>
				<div class="info--content">
					<p> ${fitness.facilitiesDesc.contentDesc2}</p>
					<p> ${fitness.facilitiesDesc.contentDesc3}</p>
					<p> ${fitness.facilitiesDesc.contentDesc4}</p>
					<p> ${fitness.facilitiesDesc.contentDesc5}</p>
				</div>
			</div>
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
