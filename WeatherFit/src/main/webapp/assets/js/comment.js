
$(document).on('click', '.view-btn', function() { // .view-btn은 postIdx를 담기위함
												// 선택개체를 바꾼다면 이부분을 바꿔야함
												// postviews.js 에 있는것과 동일
	let postIdx = parseInt($(this).data('id'));
	console.log(postIdx);
		$.ajax({
			url: "Comments.ajax",
			type: "post",
			data: { "postIdx": Number(postIdx)},
			dataType: "json",
			success: function(comment) {
				console.log(comment)
			}, error: function(er) {
				console.log("댓글 비동기 불러오기 실패")
				console.log(Number(postIdx))
			}

		});
		
		
		

});