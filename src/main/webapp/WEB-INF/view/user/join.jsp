<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<link rel="stylesheet" href="/css/myPage.css" />
<style>
	#change--btn[type=button] {
		background-color: #ccc;
		border: none;
	}
	#change--btn[type=submit] {
		background-color: #000;
	}
	#key--check {
		width: 100%;
		padding-left: 35px;
	}
	#check--pattern {
		width: 100%;
		padding-left: 35px;
	}
	#name--check{
		width: 100%;
		padding-left: 35px;
	}
	#email--box{
		width: 280px;
	}
	#detail--address--check {
		width: 100%;
		padding-left: 35px;
	}
	#dup--check--btn {
		width: 70px;
		height: 40px;
		color: #fff;
		background-color: #000;
	}
	#tel--check{
		width: 100%;
		padding-left: 35px;
	1}
	input[type=text] {
		outline: none;
	}
	input[type=password] {
		outline: none;
	}
	input[type=email] {
		outline: none;
	}
	input[type=tel] {
		outline: none;
	}
	input[type=date] {
		outline: none;
	}
</style>
<main class="main--container">
	<div class="title--container">
		<div class="title--logo">
			<span class="material-symbols-outlined back--icon" onclick="history.back()">arrow_back</span>
			<img alt="" src="/images/dodam_wlogo.png" id="logo--image" height="40" onclick="location.href='/'">	
		</div>
		<div class="title--text">
			<p>회원가입을</p>
			<p>시작해볼까요?</p>
		</div>
	</div>
	<div class="content--container">
		<form action="/join" method="post" class="form--container">
		<div>
			<input type="email" name="email" class="input--box" placeholder="이메일을 입력해 주세요" autocomplete="off" id="email--box">
			<button type="button" id="dup--check--btn">중복확인</button>		
		</div>
		<div id="check--pattern"></div>
		<input type="password" name="password" class="input--box" placeholder="비밀번호를 입력해 주세요" autocomplete="off" id="password--box"> 
		<input type="password" name="passwordCheck" class="input--box" placeholder="비밀번호 확인" autocomplete="off" id="password--check">
		<div id="key--check"></div>
		<input type="text" name="name" class="input--box" placeholder="이름을 입력해 주세요" autocomplete="off" id="name--box">
		<div id="name--check"></div>
		<input type="date" name="birth" class="input--box" placeholder="출생년도" autocomplete="off" id="date--box">
		<div>
			<div>
				<input type="text" class="input--box" id="address" placeholder="주소" name="address" readonly="readonly" style="width:300px;" autocomplete="off">
				<input type="button" class="sub--button" id="find--address--btn" onclick="postCode()" value="검색" style="width:50px; margin: 0; background-color: #fff; border: 1px solid #000; color: #000;"><br>
			</div>
			<input type="text" class="input--box" id="detailAddress" name="detailAddress" placeholder="상세주소" autocomplete="off"><br>
			<div id="detail--address--check"></div>
		</div>
		<div>
			<label><input type="radio" name="gender" value="남성">남성</label>
			<label><input type="radio" name="gender" value="여성">여성</label>
		</div>
		<input type="tel" name="tel" id="tel--box" class="input--box" placeholder="전화번호를 입력해주세요" autocomplete="off">
		<div id="tel--check"></div>
		<button type="button" id="change--btn" class="sub--button">회원가입</button>
		</form>
	</div>
</main>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	/* 우편번호 api 코드 - 현우 */
    function postCode() {
        new daum.Postcode({
            oncomplete: function(data) {
                let addr = ''; // 주소 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { 
                	// 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById("address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailAddress").focus();
            }
        }).open();
    }
    
    /* 유효성 검사 코드 - 현우 */
    let passwordCheck = document.getElementById("password--check");
    let passwordBox = document.getElementById("password--box");
   	let checkKey = document.getElementById("key--check");
	let submitBtn = document.getElementById("change--btn");
	let emailBox = document.getElementById("email--box");
	let dateBox = document.getElementById("date--box");
	let addressBox = document.getElementById("address");
	let detailAddressBox = document.getElementById("detailAddress");
	let checkPatternDiv = document.getElementById("check--pattern");
	let detailAddressDiv = document.getElementById("detail--address--check");
	let radioBox = document.getElementsByName("gender");
	let nameBox = document.getElementById("name--box");
	let telBox = document.getElementById("tel--box");
	let telCheckDiv = document.getElementById("tel--check");
	let inputPattern =  /[@]/;
	let reg = /\s/g;
	let checkEmail = false;
	let checkPassword = false;
	let checkGender = false;
	let checkName = false;
	let checkBirth = false;
	let checkAddress = false;
	let checkDetailAddress = false;
	let checkTel = false;
	
	// 전부 null이 아닌 경우
	function checkSubmit(checkEmail, checkPassword, checkGender, checkName, 
			checkBirth, checkAddress, checkDetailAddress, checkTel) {
		if(checkEmail && checkPassword && checkGender && checkName && checkBirth 
				&& checkAddress && checkDetailAddress && checkTel) {
			submitBtn.type = "submit";
		} else {
			submitBtn.type = "button";
		}
	}
	
	/* 중복검사 함수 - 현우 */
    let idCheckBtn = document.getElementById("dup--check--btn");
    idCheckBtn.addEventListener("click", function() {
    	let email = document.getElementById("email--box").value;
	    if(inputPattern.test(email) == false) {
			alert("이메일은 @를 포함해야 합니다.");	
	    } else if(email.includes('.com') == false) {
	    	alert("이메일은 .com형식으로 끝나야합니다.")
	    } else {
		   	fetch("/api/duplicationUser?email=" + email, ({
		   		method: "get"
		   	})).then(async (response) => {
		   		let result = await response.json();
		   		if(result.status_code == 200) {
		   			alert(result.msg);
		   			checkEmail = true;
		   			checkSubmit(checkEmail, checkPassword, checkGender, checkName, 
		   					checkBirth, checkAddress, checkDetailAddress, checkTel)
		   		} else {
		   			alert(result.msg);
		   		}
		   	});
	    }
    });
    
	/* 이메일 폼 검사 */
	emailBox.addEventListener("keyup", function () {
		let emailBoxValue = emailBox.value;
		if(emailBoxValue.match(reg)) {
			console.log("asdfasdf");
			checkPatternDiv.textContent = "* 공백이 포함되어 있습니다.";
			checkPatternDiv.style.color = "red";
			checkPatternDiv.style.fontSize = "14px";
			checkEmail = false;
    	} else {
			if(inputPattern.test(emailBoxValue) == false) {
				checkPatternDiv.textContent = "* 이메일은 @를 포함해야 합니다.";
				checkPatternDiv.style.color = "red";
				checkPatternDiv.style.fontSize = "14px";
		   		checkEmail = false;
			} else if(emailBoxValue.includes('.com') == false) {
				checkPatternDiv.textContent = "이메일은 .com형식으로 끝나야합니다.";
				checkPatternDiv.style.color = "red";
				checkPatternDiv.style.fontSize = "14px";
		   		checkEmail = false;
		    } else {
				checkPatternDiv.replaceChildren();
			}
    	}
	});
	
	/* 비밀번호 확인란 검사 */
    passwordCheck.addEventListener("keyup", function() {
    	let passwordBoxValue = passwordBox.value;
    	let passwordCheckBoxValue = passwordCheck.value;
    	if(passwordBoxValue.length < 6) {
			checkKey.textContent = "* 비밀번호는 6자리 이상이어야 합니다.";
    		checkKey.style.color = "red";
    		checkKey.style.fontSize = "14px";
    		checkPassword = false;
    	} else {
	    	if(passwordBoxValue != passwordCheckBoxValue){
	    		checkKey.textContent = "* 비밀번호가 일치하지 않습니다.";
	    		checkKey.style.color = "red";
	    		checkKey.style.fontSize = "14px";
	    		checkPassword = false;
	    	} else {
	    		checkKey.textContent = "* 비밀번호가 일치합니다!";
	    		checkKey.style.color = "blue";
	    		checkKey.style.fontSize = "14px";
	    		checkPassword = true;
	    		checkSubmit(checkEmail, checkPassword, checkGender, checkName, 
    					checkBirth, checkAddress, checkDetailAddress, checkTel)
	    	}
    	}
    });
    
	/* 비밀번호 확인칸 클릭했을 때 검사 */
    passwordCheck.addEventListener("click", function() {
    	let passwordBoxValue = passwordBox.value;
    	if(passwordBoxValue.length < 6) {
			checkKey.textContent = "* 비밀번호는 6자리 이상이어야 합니다.";
    		checkKey.style.color = "red";
    		checkKey.style.fontSize = "14px";
    		submitBtn.type = "button";
    	}
    });
    
	/* 이름칸 검사 */
    nameBox.addEventListener("keyup", function() {
    	let nameBoxValue = nameBox.value;
    	let nameCheckDiv = document.getElementById("name--check");
    	if(nameBoxValue.match(reg)) {
    		nameCheckDiv.textContent = "* 공백이 포함되어 있습니다.";
    		nameCheckDiv.style.color = "red";
    		nameCheckDiv.style.fontSize = "14px";
    		checkName = false;
    	} else {
    		nameCheckDiv.replaceChildren();
    		checkName = true;
    		checkSubmit(checkEmail, checkPassword, checkGender, checkName, 
					checkBirth, checkAddress, checkDetailAddress, checkTel)
    	}
    });
    
	/* 날짜칸 검사 */
    dateBox.addEventListener("change", function() {
    	checkBirth = true;
    	checkSubmit(checkEmail, checkPassword, checkGender, checkName, 
				checkBirth, checkAddress, checkDetailAddress, checkTel)
    });
    
	/* 주소 입력했는지 검사 */
    detailAddressBox.addEventListener("click", function() {
    	let addressBoxValue = addressBox.value;
    	if(addressBoxValue.length == 0) {
    		alert("주소 먼저 검색해주세요.");
    		let addressBtn = document.getElementById("find--address--btn");
    		addressBtn.focus();
    	}
    });
    
	/* 상세주소 검사 */
    detailAddressBox.addEventListener("keyup", function() {
    	let detailAddressBoxValue = detailAddressBox.value;
    	let addressBoxValue = addressBox.value;
    	if(detailAddressBoxValue.length == 0) {
    		detailAddressDiv.textContent = "* 상세주소를 입력해주세요.";
    		detailAddressDiv.style.color = "red";
    		detailAddressDiv.style.fontSize = "14px";
    		checkDetailAddress = false;
    	} else {
    		detailAddressDiv.replaceChildren();
    		if(addressBoxValue.length != 0) {
    			checkAddress = true;
    		}
    		checkDetailAddress = true;
    		checkSubmit(checkEmail, checkPassword, checkGender, checkName, 
					checkBirth, checkAddress, checkDetailAddress, checkTel)
    	}
    });
    
	/* 라디오박스 체크 여부 검사 */
    for(let radio of radioBox) {
    	radio.addEventListener("click", function() {
    		if(this.checked) {
    			checkGender = true
    			checkSubmit(checkEmail, checkPassword, checkGender, checkName, 
    					checkBirth, checkAddress, checkDetailAddress, checkTel)
    		}
    	});
    }
    
	/* 전화번호 입력란 검사 */
    telBox.addEventListener("keyup", function() {
    	let telPattern = /\d{3}-\d{4}-\d{4}/;
    	let telBoxValue = telBox.value;
    	if(telPattern.test(telBoxValue) == false) {
    		telCheckDiv.textContent = "* 전화번호는 010-0000-0000 형식이어야 합니다.";
    		telCheckDiv.style.color = "red";
    		telCheckDiv.style.fontSize = "14px";
    		checkTel = false;
    	} else {
    		telCheckDiv.replaceChildren();
    		checkTel = true;
    		checkSubmit(checkEmail, checkPassword, checkGender, checkName, 
					checkBirth, checkAddress, checkDetailAddress, checkTel)
    	}
    });
</script>	