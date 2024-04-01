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


			let targetPostIdx = postIdx;


			// postIdx가 targetPostIdx와 일치하는 댓글들만 필터링
			let filteredComments = comment.filter(comment => comment.postIdx === targetPostIdx);

			// 필터링된 댓글들의 userId와 cmtContent를 출력
			filteredComments.forEach(comment => {
				$(".comment-section").append(`
        <div class="comment">
            <div class="comment-body">
              <span class="comment-author" style = "background-color:whitesmoke">`+ comment.userId + `</span>
              <br>
              <span class="comment-text" style = "background-color:yellow">`+ comment.cmtContent + `</span>
            </div>
          </div>
        `);


			});


		}, error: function(er) {
			console.log("댓글 비동기 불러오기 실패")
		}

	});
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
					$("#cmt-user").html(data[0].userId);
					$("#cmt-img").html(`<img src="` + imgPath + `">`);
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
					$(".comment-author").empty();
					$(".comment-text").empty();
					for (let i = 0; i < d.length; i++) {
					


						$(".comment-section").append(`
        <div class="comment">
            <div class="comment-body">
              <span class="comment-author" style = "background-color:whitesmoke">`+ d[i].userId + `</span>
              <br>
              <span class="comment-text" style = "background-color:yellow">`+ d[i].cmtContent + `</span>
            </div>
          </div>
        `);
					}

				}, error: function(e) {

				}
			})


		}


	})
})



