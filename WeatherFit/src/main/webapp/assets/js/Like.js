let like = document.getElementById("like-heart");
let postIdx = $("#postIdx1").attr("id");
postIdx = postIdx.substring(7);
let sendData = { "userId": userId, "postIdx": postIdx };

$(function() {
	$.ajax({
		url: "PostLikeCheck",
		data: sendData,
		dataType: "json",
		type: "get",

		success: function(res) {
			console.log(Object.keys(res));
			let keyList = Object.keys(res);
			if(res.select != null) {
				like.classList.toggle("active");
				//$("#like-heart").hasClass("active");
			}
			$("#btn-like-cnt").text(Object.keys(res).length - 1);
			$("#btn-like-cnt").on("click", function() {
				for(let i = 0; i < Object.keys(res).length - 1; i++) {
					$("#like-user-list").append("<li>" + Object.values(res)[i].userId + "</li>");					
				}
			});
		},
		error: function() {
			alert("좋아요 상태 오류");
		}
	});
	
});

$(document).on("click", "#like-heart", function() {
	
	like.classList.toggle("active")
	
	$.ajax({
		url: "PostLikeUpdate", // 좋아요 추가를 처리하는 서블릿 또는 엔드포인트 URL
		data: sendData,
		dataType: "json",
		type: "get",

		success: function(response) {
			console.log(response);
			console.log("like 응답 완료");
			// like.classList.add("active");
			// like.setAttribute("data-id", response); // 반환된 좋아요 ID 또는 기타 정보 설정
		},
		error: function() {
			console.log("요청 실패");
		}
	});

});

