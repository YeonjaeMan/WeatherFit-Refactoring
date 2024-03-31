$(document).ready(function() {
	$(document).ready("click", "#like", function() {
		
		let postIdx = $("#like-heart").attr('data-id');
		console.log(postIdx);
		let sendData = {"userId" : sessionUserId, "postIdx" : postIdx};
		
		console.log($("[data-id='" + postIdx + "'] #like-heart").is('.active'));
		if($("[data-id='" + postIdx + "'] #like-heart").is('.active')) {
			$.ajax({
				url: "DeleteLike.ajax",
				data: sendData,
				dataType: "text",
				type: "delete",
				success: function(res) {
					console.log("좋아요 삭제 완료!" + res);
					$("#like").html(`<svg id="like-heart" data-id="` + postIdx + `" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
		                				<path d="M47.6 300.4L228.3 469.1c7.5 7 17.4 10.9 27.7 10.9s20.2-3.9 27.7-10.9L464.4 300.4c30.4-28.3 47.6-68 47.6-109.5v-5.8c0-69.9-50.5-129.5-119.4-141C347 36.5 300.6 51.4 268 84L256 96 244 84c-32.6-32.6-79-47.5-124.6-39.9C50.5 55.6 0 115.2 0 185.1v5.8c0 41.5 17.2 81.2 47.6 109.5z" />
		             				</svg>`);
				},
				error: function(err) {
					alert("좋아요 취소 실패!" + err);
				}
			});
		} else {
			$.ajax({
				url: "InsertLike.ajax",
				data: sendData,
				dataType: "text",
				type: "put",
				success: function(res) {
					console.log("좋아요 생성 완료!" + res);
					$("#like").html(`<svg id="like-heart" class="active" data-id="` + postIdx + `" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
		                				<path d="M47.6 300.4L228.3 469.1c7.5 7 17.4 10.9 27.7 10.9s20.2-3.9 27.7-10.9L464.4 300.4c30.4-28.3 47.6-68 47.6-109.5v-5.8c0-69.9-50.5-129.5-119.4-141C347 36.5 300.6 51.4 268 84L256 96 244 84c-32.6-32.6-79-47.5-124.6-39.9C50.5 55.6 0 115.2 0 185.1v5.8c0 41.5 17.2 81.2 47.6 109.5z" />
		             				</svg>`);
				},
				error: function(err) {
					alert("좋아요 실패!" + err);
				}
			});
		}
	});
});
