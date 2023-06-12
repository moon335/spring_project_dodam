/**
 * 
 */
class MyInfoJs {
	constructor() {
		this.buttonUpdate = document.getElementById("userUpdate");
		this.buttonCoupons = document.getElementById("coupons");
		this.buttonReservations = document.getElementById("reservations");
		this.buttonQandA = document.getElementById("qandA");


		this.userInfoDiv = document.getElementById("myInfoDiv");

		this.buttonUpdate.addEventListener("click", this.createUserUpdateForm.bind(this));
		this.buttonCoupons.addEventListener("click", this.createCouponListForm.bind(this));
		this.buttonReservations.addEventListener("click", this.createReservationListForm.bind(this));
		this.buttonQandA.addEventListener("click", this.createQnAListForm.bind(this));


	}

	createUserUpdateForm() {
		while (this.userInfoDiv.firstChild) {
			this.userInfoDiv.removeChild(this.userInfoDiv.firstChild);
		};

		fetch("/api/myInfo", {
			method: "GET"
		})
			.then(async (response) => {
				let data = await response.json(); // 결과 값
				const formTag = document.createElement("form");
				formTag.className = "form--container";
				formTag.action = "/myPageProc";
				formTag.method = "POST";

				// 이메일
				const emailSpanTag = document.createElement("span");
				emailSpanTag.textContent = "이메일";

				const divEmailTag = document.createElement("div");
				const inputEmailTag = document.createElement("input");
				inputEmailTag.className = "input--box";
				inputEmailTag.setAttribute("readonly", "readonly");
				inputEmailTag.name = "email";
				inputEmailTag.type = "email";
				inputEmailTag.readOnly = true;
				divEmailTag.append(emailSpanTag);
				divEmailTag.append(inputEmailTag);
				inputEmailTag.value = data.email;

				// 비밀번호
				const passwordSpanTag = document.createElement("span");
				passwordSpanTag.textContent = "비밀번호";

				const divPasswordTag = document.createElement("div");
				const inputPasswordTag = document.createElement("input");
				const keyCheckTag = document.createElement("div");
				keyCheckTag.id = "key--check";
				inputPasswordTag.className = "input--box";
				inputPasswordTag.name = "password";
				inputPasswordTag.type = "password";
				divPasswordTag.append(passwordSpanTag);
				divPasswordTag.append(inputPasswordTag);
				divPasswordTag.append(keyCheckTag);
				inputPasswordTag.value = data.password;

				// 이름
				const nameSpanTag = document.createElement("span");
				nameSpanTag.textContent = "이름";

				const divNameTag = document.createElement("div");
				const inputNameTag = document.createElement("input");
				inputNameTag.className = "input--box";
				inputNameTag.name = "name";
				inputNameTag.type = "text";
				divNameTag.append(nameSpanTag);
				divNameTag.append(inputNameTag);
				inputNameTag.value = data.name;

				// 성별
				const genderSpanTag = document.createElement("span");
				genderSpanTag.textContent = "성별";

				const divGenderTag = document.createElement("div");
				const inputGenderTag = document.createElement("input");
				inputGenderTag.className = "input--box";
				inputGenderTag.name = "gender";
				inputGenderTag.type = "text";
				inputGenderTag.setAttribute("readonly", "readonly");
				divGenderTag.append(genderSpanTag);
				divGenderTag.append(inputGenderTag);
				inputGenderTag.value = data.gender;

				// 생년월일
				const birthSpanTag = document.createElement("span");
				birthSpanTag.textContent = "생년월일";

				const divBirthTag = document.createElement("div");
				const inputBirthTag = document.createElement("input");
				inputBirthTag.className = "input--box";
				inputBirthTag.setAttribute("readonly", "readonly");
				inputBirthTag.name = "birth";
				inputBirthTag.type = "text";
				inputBirthTag.id = "text";
				inputBirthTag.readOnly = true;
				divBirthTag.append(birthSpanTag);
				divBirthTag.append(inputBirthTag);
				inputBirthTag.value = data.birth;

				// 전화번호
				const telSpanTag = document.createElement("span");
				telSpanTag.textContent = "전화번호";

				const divTelTag = document.createElement("div");
				const inputTelTag = document.createElement("input");
				inputTelTag.className = "input--box";
				inputTelTag.name = "tel";
				inputTelTag.type = "text";
				divTelTag.append(telSpanTag);
				divTelTag.append(inputTelTag);
				inputTelTag.value = data.tel;

				// 주소
				const addressSpanTag = document.createElement("span");
				addressSpanTag.textContent = "주소";

				const divAddressTag = document.createElement("div");
				const inputAddressTag = document.createElement("input");
				inputAddressTag.className = "input--box";
				inputAddressTag.name = "address";
				inputAddressTag.type = "text";
				divAddressTag.append(addressSpanTag);
				divAddressTag.append(inputAddressTag);
				inputAddressTag.value = data.address;

				// input submit
				const updateBtnTag = document.createElement("input");
				updateBtnTag.className = "sub--button";
				updateBtnTag.id = "update--btn";
				updateBtnTag.type = "button";
				updateBtnTag.value = "정보 수정";

				inputPasswordTag.addEventListener("keyup", function() {
					let passwordBoxValue = inputPasswordTag.value;
					if (passwordBoxValue.length < 6) {
						keyCheckTag.textContent = "* 비밀번호는 6자리 이상이어야 합니다.";
						keyCheckTag.style.color = "red";
						keyCheckTag.style.fontSize = "14px";
						updateBtnTag.type = "submit";
					}
				});
				
				const withdrawBtnTag = document.createElement("input");
				withdrawBtnTag.className = "sub--button";
				withdrawBtnTag.type = "button";
				withdrawBtnTag.value = "회원 탈퇴";
				withdrawBtnTag.setAttribute("onclick", `withdrawUser('${data.email}')`);
				

				const childNodeArray = [
					divEmailTag, divPasswordTag, divNameTag, divGenderTag, divBirthTag, divAddressTag, divTelTag, updateBtnTag, withdrawBtnTag 
				];

				childNodeArray.forEach((node) => {
					formTag.append(node);
				});
				this.userInfoDiv.append(formTag);
			});
	};
	createCouponListForm() {
		while (this.userInfoDiv.firstChild) {
			this.userInfoDiv.removeChild(this.userInfoDiv.firstChild);
		};
		const couponListPage = document.createElement("iframe");
		couponListPage.src = "/couponList";
		couponListPage.scrolling = "no";
		couponListPage.width = "700px";
		couponListPage.height = "500px";
		couponListPage.frameBorder = "0";
		this.userInfoDiv.append(couponListPage);
	};

	createReservationListForm() {
		while (this.userInfoDiv.firstChild) {
			this.userInfoDiv.removeChild(this.userInfoDiv.firstChild);
		};

		const reservationListPage = document.createElement("iframe");
		reservationListPage.src = "/myReservations";
		reservationListPage.scrolling = "no";
		reservationListPage.width = "1200px";
		reservationListPage.height = "500px";
		reservationListPage.frameBorder = "0";
		this.userInfoDiv.append(reservationListPage);

	};

	createQnAListForm() {
		while (this.userInfoDiv.firstChild) {
			this.userInfoDiv.removeChild(this.userInfoDiv.firstChild);
		};

		const replyListPage = document.createElement("iframe");
		replyListPage.src = "/myReplys";
		replyListPage.scrolling = "no";
		replyListPage.width = "700px";
		replyListPage.height = "500px";
		replyListPage.frameBorder = "0";
		this.userInfoDiv.append(replyListPage);
	};
}

class UserInfoUpdateDto {
	constructor(email, password, name, gender, birth, tel) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.tel = tel;
	};

	ToJson() {
		let object = {
			email: this.email,
			password: this.password,
			name: this.name,
			gender: this.gender,
			birth: this.birth,
			tel: this.tel,
		};
		return JSON.stringify(object);
	}
}

class CouponDto {
	constructor(id, name, content, startDate, endDate) {
		this.id = id;
		this.name = name;
		this.content = content;
		this.startDate = startDate;
		this.endDate = endDate;
	};

	ToJson() {
		let object = {
			id: this.id,
			name: this.name,
			content: this.content,
			startDate: this.startDate,
			endDate: this.endDate,
		};
		return JSON.stringify(object);
	}
}

class ReservationDto {
	constructor(id, startDate, endDate, numberOfP, totalPrice, room, createdAt) {
		this.id = id;
		this.numberOfP = numberOfP;
		this.totalPrice = totalPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.room = room;
		this.createdAt = createdAt;
	};

	ToJson() {
		let object = {
			id: this.id,
			numberOfP: this.numberOfP,
			totalPrice: this.totalPrice,
			startDate: this.startDate,
			endDate: this.endDate,
			room: this.room,
			createdAt: this.createdAt
		};
		return JSON.stringify(object);
	}
}

class ReplyDto {
	constructor(id, content, question, manager) {
		this.id = id;
		this.content = content;
		this.question = question;
		this.manager = manager;
	};

	ToJson() {
		let object = {
			id: this.id,
			content: this.content,
			question: this.question,
			manager: this.manager
		};
		return JSON.stringify(object);
	}
}

let passwordBox = document.getElementById("password--box");
let checkKey = document.getElementById("key--check");
let updateBtn = document.getElementById("update--btn");
passwordBox.addEventListener("keyup", function() {
	let passwordBoxValue = passwordBox.value;
	if (passwordBoxValue.length < 6) {
		checkKey.textContent = "* 비밀번호는 6자리 이상이어야 합니다.";
		checkKey.style.color = "red";
		checkKey.style.fontSize = "14px";
	} else {
		checkKey.textContent = "* OK!!";
		checkKey.style.color = "blue";
		checkKey.style.fontSize = "14px";
		updateBtn.type = "submit";
	}
});

new MyInfoJs();