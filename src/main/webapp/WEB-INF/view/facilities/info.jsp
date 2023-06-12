<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="/css/navi.css" />
<style>
.main--container {
	display: flex;
	flex-direction: column;
}
</style>
<div class="body--container">
	<div class="navi--bar">
		<ul class="navi--bar--ul">
			<li class="selected--menu"><a href="/facilities">부대시설 소개</a></li>
			<li><a href="/pool">수영장</a></li>
			<li><a href="/spa">스파</a></li>
			<li><a href="/fitness">피트니스</a></li>
		</ul>
	</div>
	<div class="main--container">
		<div>
			<a href="/pool"> <img alt="" src="https://picsum.photos/id/169/1000/500"></a> 
			<a href="/pool">
				<p> ${pool.facilities.name}</p>
				<p> ${pool.facilities.location}</p>
			</a>
		</div>
		<div>
			<a href="/spa"> <img alt="" src="https://picsum.photos/id/169/1000/500"></a> 
			<a href="/spa">
				<p> ${spa.facilities.name}</p>
				<p> ${spa.facilities.location}</p>
			</a>
		</div>
		<div>
			<a href="/fitness"> <img alt="" src="https://picsum.photos/id/169/1000/500"></a> 
			<a href="/fitness">
				<p> ${fitness.facilities.name}</p>
				<p> ${fitness.facilities.location}</p>
			</a>
		</div>
	</div>
</div>
<%@ include file="../layout/footer.jsp"%>
