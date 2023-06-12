<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
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
	
	#faq--wrap {
		width: 1200px;
	}
	
	main {
		height: 800px;
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	
</style>
	<main>
		<div>
			<h2>FaQ</h2>
		</div>
		<div id="faq--wrap">
			<c:forEach var="faq" items="${faqList}">
				<div>
					<div class="faq--toggle--title">
						<div class="faq--number">${faq.id}</div>
						<div class="faq--title">${faq.title}</div>
					</div>
					<div class="faq--content">
						<div>${faq.content}</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</main>
	
	<script>
	    $(document).ready(
		    function() {
		    	let faqToggle = $(".faq--toggle--title")
		    	faqToggle.on("click", function() {
		    		if($(this).siblings().hasClass("toggle--content")){
			    		$(this).siblings().removeClass("toggle--content");
		    		} else {
		    			$(".toggle--content").removeClass("toggle--content");
			    		$(this).siblings().addClass("toggle--content");
		    		}
		    	});
		    });
	</script>
<%@ include file="../layout/footer.jsp"%>
