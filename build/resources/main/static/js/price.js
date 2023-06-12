/**
 * 
 */
const totalPriceTag = document.getElementById("totalPrice");
	
	const diningDivTag = document.getElementById('diningResult')
	const spaDivTag = document.getElementById('spaResult')
	const poolDivTag = document.getElementById('poolResult')
	const fitnessDivTag = document.getElementById('fitnessResult')
	
	// const couponDivTag =  document.getElementById()
	
	
	const tagetDivArray = [diningDivTag, spaDivTag, poolDivTag, fitnessDivTag]
	// 가격표
	
	// 예약 날짜
	
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
		roomPriceTag.textContent = "방 가격: " + roomPrice;
		
		const optionPriceTag = document.createElement("div");
		const optionPrice = diningPriceResult + spaPriceResult + poolPriceResult + fitnessPriceResult;
		optionPriceTag.textContent = "옵션 가격: " + optionPrice;


		let totalPrice = roomPrice + optionPrice;			

		
		const totalDivTag = document.createElement("input");
		totalDivTag.setAttribute("type", "text");
		totalDivTag.setAttribute("name", "totalPrice");
		totalDivTag.value = totalPrice;
		
		totalPriceTag.append(roomPriceTag);
		totalPriceTag.append(optionPriceTag);
		totalPriceTag.append(totalDivTag);
	}
	
	console.log(tagetDivArray)
	tagetDivArray.forEach((target)=>{
		target.addEventListener("change", totalPrice);
	})
	
	function diningCount(type) {
		const resultElement = document.getElementById('diningResult');
		// 현재 화면에 표시된 값
		let number = resultElement.innerText;
		// 더하기/빼기
		if (type === 'plus') {
			number = parseInt(number) + 1;
		} else if (type === 'minus') {
			number = parseInt(number) - 1;
		}
		resultElement.innerText = number;
	}
	
	function spaCount(type) {
		const resultElement = document.getElementById('spaResult');
		// 현재 화면에 표시된 값
		let number = resultElement.innerText;
		// 더하기/빼기
		if (type === 'plus') {
			number = parseInt(number) + 1;
		} else if (type === 'minus') {
			number = parseInt(number) - 1;
		}
		resultElement.innerText = number;
	}
	
	function poolCount(type) {
		const resultElement = document.getElementById('poolResult');
		// 현재 화면에 표시된 값
		let number = resultElement.innerText;
		// 더하기/빼기
		if (type === 'plus') {
			number = parseInt(number) + 1;
		} else if (type === 'minus') {
			number = parseInt(number) - 1;
		}
		resultElement.innerText = number;
	}
	
	function fitnessCount(type) {
		const resultElement = document.getElementById('fitnessResult');
		// 현재 화면에 표시된 값
		let number = resultElement.innerText;
		// 더하기/빼기
		if (type === 'plus') {
			number = parseInt(number) + 1;
		} else if (type === 'minus') {
			number = parseInt(number) - 1;
		}
		resultElement.innerText = number;
	}

	
	totalPrice();