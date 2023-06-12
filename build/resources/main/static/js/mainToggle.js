/**
 * 
 */
		let timer;
		$(document).ready(function() {
			$(".toggle--wrap--room").click(function() {
				if($(".navi--bar--detail--fac").show() || 
					$(".navi--bar--detail--reserve").show() ||
					$(".navi--bar--detail--qna").show()) {
						$(".navi--bar--detail--fac").hide();
						$(".navi--bar--detail--reserve").hide();
						$(".navi--bar--detail--qna").hide();
					}
				if(timer){
					clearTimeout(timer)
				}
				timer = setTimeout(function(){
					$(".navi--bar--detail--room").slideToggle();
				}, 200)
			});
		});

		$(document).ready(function() {
			$(".toggle--wrap--fac").click(function() {
				if($(".navi--bar--detail--room").show() || 
					$(".navi--bar--detail--reserve").show() ||
					$(".navi--bar--detail--qna").show()) {
						$(".navi--bar--detail--room").hide();
						$(".navi--bar--detail--reserve").hide();
						$(".navi--bar--detail--qna").hide();
					}
				// javascript 쓰로틀링
				if(timer){
					clearTimeout(timer)
				}
				timer = setTimeout(function(){
					$(".navi--bar--detail--fac").slideToggle();
				}, 200)
				
			});
		});
		$(document).ready(function() {
			$(".toggle--wrap--reserve").click(function() {
				if($(".navi--bar--detail--room").show() || 
					$(".navi--bar--detail--fac").show() ||
					$(".navi--bar--detail--qna").show()) {
						$(".navi--bar--detail--room").hide();
						$(".navi--bar--detail--fac").hide();
						$(".navi--bar--detail--qna").hide();
					}
				if(timer){
					clearTimeout(timer)
				}
				timer = setTimeout(function(){
					$(".navi--bar--detail--reserve").slideToggle();
				}, 200)
			});
		});
		$(document).ready(function() {
			$(".toggle--wrap--qna").click(function() {
				if($(".navi--bar--detail--room").show() || 
					$(".navi--bar--detail--fac").show() ||
					$(".navi--bar--detail--reserve").show()) {
						$(".navi--bar--detail--room").hide();
						$(".navi--bar--detail--fac").hide();
						$(".navi--bar--detail--reserve").hide();
					}
				if(timer){
					clearTimeout(timer)
				}
				timer = setTimeout(function(){
					$(".navi--bar--detail--qna").slideToggle();
				}, 200)
			});
		});