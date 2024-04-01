////////////////////////////////////////////////// 댓글영역
let postIdx;
$(document).on('click', '.card', function() { // .view-btn은 postIdx를 담기위함 -> .card로 변경
	// 선택개체를 바꾼다면 이부분을 바꿔야함
	// postviews.js 에 있는것과 동일
	postIdx = parseInt($(this).data('id'));
	console.log(postIdx);
	$.ajax({
		url: "Comments.ajax",
		type: "post",
		data: { "postIdx": postIdx },
		dataType: "json",
		success: function(comment) {
			console.log(comment)
			for (let i = 0; i < comment.length; i++) {

				$("#cmt-cmt").append(`
					<p>`+ comment[i].userId + `</p>
					<p>`+ comment[i].cmtContent + `</p>
				`);


			}



		}, error: function(er) {
			console.log("댓글 비동기 불러오기 실패")
		}

	});
	$("#cmt-cmt").empty();
	///////////////////////////////////////////////////////댓글영역끝///
	///////////////////////////////////////////////// 게시물 상세보기 영역
	$.ajax({
		url: "Postdetail.ajax",
		type: "post",
		data: { "postIdx": postIdx },
		dataType: "json",

		success: function(data) {


			$.ajax({
				url: "Images.ajax",
				data: { "postIdx": postIdx },
				type: "post",
				dataType: "json",
				success: function(images) {
					let imgPath = "assets/uploads/" + images.fileRname;
					console.log(imgPath)
					$("#cmt-user").html(data[0].userId);
					$("#cmt-img").attr("src", imgPath);
					$("#cmt-content").html(data[0].postContent);
					$("#cmt-hashtag").html(data[0].hashTag);


				},
				error: function() {
					alert("이미지 가져오기 실패..");
				}
			})


		},
		error: function(err) {
			console.log("연결 실패");
		}
	})





});



document.querySelector("#newReplyText").addEventListener("keydown", (e) => {
	if (e.key === 13) {
		$("#insert-cmt").click();
	}
})



$("#insert-cmt").on("click", function() {
	let cmtContent = $("input[name='cmtContent']").val();
	console.log(cmtContent)
	$.ajax({
		url: "Comment.do",
		data: {
			"postIdx": postIdx,
			"cmtContent": cmtContent
		},
		type: "post",
		success: function(s) {
			$("input[name='cmtContent']").val("");

			$.ajax({
				url: "Comments.ajax",
				data: { "postIdx": postIdx },
				dataType: "json",
				success: function(d) {
					console.log("입력성공")
					
					for (let i = 0; i < d.length; i++) {
						$("#cmt-cmt").append(`
					<p>`+ comment[i].userId + `</p>
					<p>`+ comment[i].cmtContent + `</p>
				`);
					}

				}, error: function(e) {

				}
			})
			

		}


	})
})



