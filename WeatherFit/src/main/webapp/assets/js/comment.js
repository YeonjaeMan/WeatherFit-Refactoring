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
			$(".comment").empty();
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


		}, error: function() {
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
			console.log(data)

			$.ajax({
				url: "Images.ajax",
				data: { "postIdx": postIdx,
						"userId" : data[0].userId },
				type: "post",
				dataType: "json",
				success: function(map) {
					console.log(map);
					let imgPath = "assets/uploads/" + map.file.fileRname;
					$("#cmt-user").html(`<a href="Profile.do?userId=` + data[0].userId + `" id="userinfo-main" class="user-info d-flex align-items-center">
										    <img src="assets/user_profile/` + map.user.userProfileImg + `" alt="프로필 이미지" style="width: 40px; height: 40px; border-radius: 50%;">
										    <span id="post-user">` + data[0].userId + `</span>
										</a>
										<a href="CreateRoom.do?sendUserId=` + sessionUserId + `&receiveUserId=` + data[0].userId + `">
											<svg id="message" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
											<path d="M64 112c-8.8 0-16 7.2-16 16v22.1L220.5 291.7c20.7 17 50.4 17 71.1 0L464 150.1V128c0-8.8-7.2-16-16-16H64zM48 212.2V384c0 8.8 7.2 16 16 16H448c8.8 0 16-7.2 16-16V212.2L322 328.8c-38.4 31.5-93.7 31.5-132 0L48 212.2zM0 128C0 92.7 28.7 64 64 64H448c35.3 0 64 28.7 64 64V384c0 35.3-28.7 64-64 64H64c-35.3 0-64-28.7-64-64V128z" />
											</svg>
										</a>`);
					$('#cmt-img').attr('src', imgPath);
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



