<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/managerHeader.jsp"%>
	<div class="content">
		<div>
			<form action="/manager/roomStatus/${room.id}" method="post">
				<label for="roomname">방 이름</label> 
				<input type="text" name="name" id="name" value="${room.roomType.name}"> 
				<br>
				<label for="roomprice">방 가격</label> 
				<input type="text" name="price" id="price" value="${room.roomType.price}">
				<br>
				<label for="roomavailability">방 사용 여부 가능</label>
				<input type="text" name="availability" id="availability" value="${room.availability}">
				<br>
				<label for="description">방 내용</label>
				<input type="text" name="description" id="description" value="${room.roomType.description}"> 
				<br>
				<label for="roomnumber_of_p">방 수용 인원</label> 
				<input type="text" name="number_of_p" id="number_of_p" value="${room.roomType.numberOfP}">
				<br>
				<button type="submit">수정</button>
			</form>
			</div>
		</div>
	</main>

