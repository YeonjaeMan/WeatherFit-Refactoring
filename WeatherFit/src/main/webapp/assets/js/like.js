$(document).ready(function() {
	$('#like-heart').click(function() {
		
		let postIdx = $("#like-heart").data("id");
		let sendData = {"userId" : sessionUserId, "postIdx" : postIdx};
		let that = this;
		
		if($(this).hasClass('active')) {
			$.ajax({
				url: "DeleteLike.ajax",
				data: sendData,
				dataType: "json",
				type: "post",
				success: function() {
					$(that).toggleClass('active');
				},
				error: function() {
					alert("좋아요 취소 실패!");
				}
			});
		} else {
			$.ajax({
				url: "InsertLike.ajax",
				data: sendData,
				dataType: "json",
				type: "post",
				success: function() {
					$(that).toggleClass('active');
				},
				error: function() {
					alert("좋아요 실패!");
				}
			});
		}
	});
});
