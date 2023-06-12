<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/managerHeader.jsp"%>
<style>
.content {
	width: 100%; 
	display : flex;
	justify-content: center;
	align-items: center;
}
.main--content {
	margin-top: 50px;
	display: flex;
	flex-direction: column;
	align-items: center;
}
.form--container {
	display: flex;
	align-items: center;
	border: 4px solid #9ACBF1;
	border-radius: 20px;
	width: 700px;
	height: 300px;
}
.search--container {
	display: flex;
	justify-content: center;
	margin-right: 200px;
}

#title--box {
	width: 600px;
}

.sub--button {
	background-color: #FF9F8D;
	border: none;
	color: #fff;
	cursor: pointer;
	width: 100px;
	height: 30px;
}
.button--box {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-top: 10px;
	width: 100%;
}
.sub-button {
	background-color: #000;
	color: #fff;
	width: 100px;
	height: 40px;
}

.input--box {
	border: none;
	border-bottom: 2px solid #ebebeb;
	margin: 10px;
}
.search--buttons {
	width: 900px;
	display: flex;
	justify-content: space-between;
}
.all--reserve {
	display: flex;
	justify-content: center;
	align-items: center;
}
.paging--box > a {
	text-decoration: none;
	color: #9ACBF1;
}
.table--container {
	width: 1000px;
	text-align: center;
	
}
.tr--box {
	background-color: #ebebeb;
	font-weight: bold;
	height: 30px;
}
.content--box {
	height: 50px;
	border-bottom: 1px solid #ebebeb;
}
.content--box:hover {
	cursor: pointer;
}
.title--container {
	display: flex;
}
.form--button--box {
	display: flex;
	justify-content: flex-end;
	width: 1000px;
	margin-bottom: 10px;
}
</style>
	<div class="content">
		<div class="title--container">
			<h2>다이닝 예약 리스트</h2>
		</div>
		
	<div class="main--content">
			<div class="search--buttons">
				<div class="all--reserve">
					<button onclick="location.href='/manager/dining'" style="margin-right: 8px;" class="sub--button">오늘 예약 보기</button>
					<button onclick="location.href='/manager/allDining'" style="margin-right: 8px;" class="sub--button">전체 예약 보기</button>
				</div>
				<form class="form--box">
					<input type="date" name="date" class="input--box"> <input type="submit" value="검색" class="sub-button">
				</form>
			</div>
			<table class="table--container">
				<thead>
					<tr class="tr--box">
						<th>회원 ID</th>
						<th>이름</th>
						<th>전화번호</th>
						<th>예약인원</th>
						<th>날짜</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dining" items="${diningList}">
						<tr class="content--box">
							<td>${dining.user.id}</td>
							<td>${dining.user.name}</td>
							<td>${dining.user.tel}</td>
							<td>${dining.numberOfP}</td>
							<td>${dining.startDate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</main>
