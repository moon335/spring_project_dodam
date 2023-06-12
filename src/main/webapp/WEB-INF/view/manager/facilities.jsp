<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/managerHeader.jsp"%>
<style>
.content {
	width: 100%; 
	display : flex;
	height: 100%;
	justify-content: center;
	align-items: center;
}

.sub--button {
	background-color: #FF9F8D;
	border: none;
	color: #fff;
	cursor: pointer;
	width: 100px;
	height: 30px;
	margin-top: 50px;
}

.sub-button {
	background-color: #000;
	color: #fff;
	width: 40px;
	height: 30px;
	margin-left: 5px;
}
.input--box {
	border: none;
	border-bottom: 2px solid #ebebeb;
	margin: 10px;
}
.input--box:focus {
	outline: none;
}
.room--box {
	/* border: 4px solid #ebebeb; */
	border-radius: 10px;
	width: 300px;
	height: 120px;
	margin: 10px 20px;
	display: flex;
	flex-direction: column;
	padding: 15px;
}

.room--box:hover {
	background-color: #FEFAF1;
	cursor: pointer;
}

.room--total {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	width: 500px;
	height: 700px;
}
#room-name {
	font-size: 20px;
	font-weight: bold;
}
.room-title {
	display: flex;
	justify-content: space-between;
	height: 60px;
}
.room-content {
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 100%;
}
.form--container {
	display: flex;
	flex-direction: column;
	align-items: flex-end;
	width: 1000px;
}
.modal-content {
	width: 600px;
	height: 500px;
}
.modal--box {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	margin-top: 50px;
}
</style>
		<div class="content">
			<h2>부대시설</h2>
				<div class="room--total">
					<c:forEach  var="diningList" items="${diningList}">
						<div class="room--box" data-toggle="modal" data-target="#facModal" style="${diningList.availability == true ? "border: 4px solid #9ACBF1" : "border: 4px solid #FF8D6B"}" id="${diningList.id}">
							<div class="room-title">
								<span style="color: gray;">${diningList.name}</span>
								<span id="room-name">${diningList.location}</span> 
							</div>
							<div class="room-content">
								<div id="room--status">${diningList.availability == true ? "사용가능" : "사용불가"}</div>
								<div id="room--status">${diningList.statusDesc}</div>
							</div>	
						</div>
					</c:forEach>
					<div class="room--box" data-toggle="modal" data-target="#facModal" style="${pool.availability == true ? "border: 4px solid #9ACBF1" : "border: 4px solid #FF8D6B"}">
						<div class="room-title">
							<span style="color: gray;">${pool.facilities.name}</span>
							<span id="room-name">${pool.facilities.location}</span>
						</div>
						<div class="room-content">
							<div id="room--status">${pool.availability == true ? "사용가능" : "사용불가"}</div>
							<div id="room--status">${pool.statusDesc}</div>
						</div>
					</div>
					<div class="room--box" data-toggle="modal" data-target="#facModal" style="${spa.availability == true ? "border: 4px solid #9ACBF1" : "border: 4px solid #FF8D6B"}">	
						<div class="room-title">
							<span style="color: gray;">${spa.facilities.name}</span>
							<span id="room-name">${spa.facilities.location}</span>
						</div>
						<div class="room-content">
							<div id="room--status">${spa.availability == true ? "사용가능" : "사용불가"}</div>
							<div id="room--status">${spa.statusDesc}</div>
						</div>
					</div>
					<div class="room--box" data-toggle="modal" data-target="#facModal" style="${fitness.availability == true ? "border: 4px solid #9ACBF1" : "border: 4px solid #FF8D6B"}">
						<div class="room-title">
							<span style="color: gray;">${fitness.facilities.name}</span>
							<span id="room-name">${fitness.facilities.location}</span>
						</div>
						<div class="room-content">
							<div id="room--status">${fitness.availability == true ? "사용가능" : "사용불가"}</div>
							<div id="room--status">${fitness.statusDesc}</div>
						</div>
					</div>
				</div>
				
			</div>
	</main>
		<div class="modal" id="facModal">
		<div class="modal-dialog">
			<div class="modal-content">
	
				<div class="modal-header">
					<h4 class="modal-title">부대시설 상태 변경</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div>
				<form action="/manager/facilities/update" method="post" class="modal--box" id="facilties--update">
					<input type="hidden" name="target" id="target">
					<div>
						<label for="roomname">이름</label> 
						<input type="text" name="name" id="name" value="${dining.name}" class="input--box"> 
					</div>
					
					<div>
						<label for="roomnumber_of_p">위치</label> 
						<input type="text" name="number_of_p" id="number_of_p" value="${dining.location}" class="input--box">
					</div>
					
					<div>
						<label for="roomavailability">사용여부</label>
						<input type="text" name="availability" id="availability" value="${dining.availability}" class="input--box">
					</div>
					
					<div>
						<label for="description">상태</label>
						<input type="text" name="description" id="description" value="${dining.statusDesc}" class="input--box"> 
					</div>
					<button type="button" class="sub--button" onclick="updateSubmit()">부대시설 변경</button>
				</form>
				</div>
	
			</div>
		</div>
	</div>
	

<script>
    
	
	let allDivTag = document.querySelectorAll(".room--box");
	const divTagNum = allDivTag.length;
	for(let i = 0; i < divTagNum; i++){
		if(i === 0 || i === 1){
			allDivTag[i].addEventListener('click', ()=>{
				document.getElementById("target").value = "식당"
				fetch("/api/findDiningInfo/"+allDivTag[i].id)
						.then(async(response)=>{
							let data = await response.json();
							let modalFormTag = document.getElementById("modal--form");
							// modalFormTag.action = "/manager/facilitiesStatus/" + data.id;
							console.log(data)
							document.getElementById("name").value = data.name;
							document.getElementById("number_of_p").value = data.location;
							if(data.availability == true) {
								document.getElementById("availability").value = '사용가능';
							} else {
								document.getElementById("availability").value = '사용불가';
							}
							document.getElementById("description").value = data.statusDesc;
						})
			})
		} else if(i === 2){
			allDivTag[i].addEventListener('click', ()=>{
				document.getElementById("target").value = "수영장"
				document.getElementById("name").value = "${pool.facilities.name}";
				document.getElementById("number_of_p").value = '${pool.facilities.location}';
				if(${pool.availability}) {
					document.getElementById("availability").value = '사용가능';
				} else {
					document.getElementById("availability").value = '사용불가';
				}
				document.getElementById("description").value = '${pool.statusDesc}';
			})
		} else if(i === 3){
			allDivTag[i].addEventListener('click', ()=>{
				document.getElementById("target").value = "스파"
				document.getElementById("name").value = '${spa.facilities.name}';
				document.getElementById("number_of_p").value = '${spa.facilities.location}';
				if(${spa.availability}) {
					document.getElementById("availability").value = '사용가능';
				} else {
					document.getElementById("availability").value = '사용불가';
				}
				document.getElementById("description").value = '${spa.statusDesc}';
			})
		} else{
			allDivTag[i].addEventListener('click', ()=>{
			document.getElementById("target").value = "피트니스"
				document.getElementById("name").value = '${fitness.facilities.name}';
				document.getElementById("number_of_p").value = '${fitness.facilities.location}'
				if(${fitness.availability}) {
					document.getElementById("availability").value = '사용가능';
				} else {
					document.getElementById("availability").value = '사용불가';
				}
				document.getElementById("description").value = '${fitness.statusDesc}';
			})
		}

	}

	function statusValueChange(status){
		if(status === '사용가능') {
			return true;
		} else if(status === '사용불가') {
			return false;
		}
		return status;
	}

	let roomStatus = document.getElementById("availability");
	roomStatus.addEventListener("keyup", function() {
		if(roomStatus.value == '사용가능') {
			roomStatus.value = true;
		} else if(roomStatus.value == '사용불가') {
			roomStatus.value = false;
		}
	});

	function updateSubmit(){
		let form = document.getElementById('facilties--update');


		form.submit();
	}
</script>
