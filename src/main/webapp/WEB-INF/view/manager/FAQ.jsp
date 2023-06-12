<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/managerHeader.jsp"%>
<style>
.faq--title--wrap {
	display: flex;
}

.faq--toggle--title {
	display: flex;
	cursor: pointer;
	background-color: #ebebeb;
	margin-bottom: 10px;
}

.faq--content {
	display: none;
}

.toggle--content {
	display: block;
}

.faq--number {
	width: 50px;
	font-size: 20px;
}

.faq--title {
	font-size: 20px;
}

.content {
	width: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
	display: flex;
}
.sub-button {
	background-color: #000;
	color: #fff;
	width: 100px;
	height: 40px;
	margin: 20px 10px;
}
.button--box {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-top: 10px;
	width: 100%;
}
</style>
<div class="content">
	<div>
		<h2>FAQ</h2>
	</div>
	<div class="main--content">
		<div id="faq--wrap">
			<c:forEach var="faq" items="${faqList}">
				<div>
					<div class="faq--toggle--title">
						<div class="faq--number">${faq.id}</div>
						<div class="faq--title">${faq.title}</div>
					</div>
					<div class="faq--content">
						<div>${faq.content}</div>
						<div class="button--box">
							<button onclick="updateFAQ(${faq.id})" class="sub-button">수정</button>
							<button onclick="deleteFAQ(${faq.id})" class="sub-button">삭제</button>
						</div>
					</div>
				</div>
			</c:forEach>
				<div class="button--box">
					<button onclick="location.href='/manager/faq/write'" class="sub-button" style="margin-top: 100px">등록</button>
				</div>
		</div>
	</div>
</div>
</main>
<script>
	function detailFAQForm(id) {
		location.href = "/manager/faq/" + id;
	}
	$(document).ready(function() {
		let faqToggle = $(".faq--toggle--title")
		faqToggle.on("click", function() {
			if ($(this).siblings().hasClass("toggle--content")) {
				$(this).siblings().removeClass("toggle--content");
			} else {
				$(".toggle--content").removeClass("toggle--content");
				$(this).siblings().addClass("toggle--content");
			}
		});
	});
	
	  function updateFAQ(id){
	        location.href = "/manager/faq/update/"+ id;
	    }
	  function deleteFAQ(id){
	        if(confirm("삭제하시겠습니까?")){
	            fetch("/manager/delete/faq?id=" + id,{
	                method: "delete",
	            }).then(async(res) => {
	                let response = await res.json();
	                console.log(response)
	                if (response.status_code == 200) {
	                        alert("삭제 완료가 되었습니다");
	                } else {
	                        alert("재 로그인 해주세요!")
	                }
	                location.href = "/manager/faq";
	            });
	        }
	    };
</script>
