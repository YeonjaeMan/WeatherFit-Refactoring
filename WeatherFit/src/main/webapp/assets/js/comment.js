////////////////////////////////////////////////// 댓글영역
let postIdx;
$(document).on('click', '.view-btn', function() { // .view-btn은 postIdx를 담기위함
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
			$("#cmt-cmt").empty();
			filteredComments.forEach(comment => {
				$("#cmt-cmt").append(
					`<p>${comment.userId}<br>${comment.cmtContent}</p>`

				);


			});


		}, error: function(er) {
			console.log("댓글 비동기 불러오기 실패")
		}

	});
	///////////////////////////////////////////////////////댓글영역끝///
	///////////////////////////////////////////////// 게시물 상세보기 영역
	$.ajax({
		url: "Posts.ajax",
		type: "get",
		dataType: "json",

		success: function(data) {

			for (let i = 0; i < data.length; i++) {

				$.ajax({
					url: "Images.ajax",
					data: { "postIdx": postIdx },
					type: "post",
					dataType: "json",
					success: function(images) {
						let imgPath = "assets/uploads/" + images.fileRname;
						$("#cmt-user").html(data[i].userId);
						$("#cmt-img").html(`<img src="` + imgPath + `">`);
						$("#cmt-content").html(data[i].postContent);
						$("#cmt-hashtag").html(data[i].hashTag);


					},
					error: function() {
						alert("이미지 가져오기 실패..");
					}
				})

			}

		},
		error: function(err) {
			console.log("연결 실패");
		}
	})





});
			

			$("#cmtContent").keydown(function(event){
				if(event.which == 13){
					$("insert-cmt").click();
				}
			})
			$("#cmt-cmt").empty();
			$("#insert-cmt").on("click", function() {
				let cmtContent = $("input[name='cmtContent']").val();
				console.log(cmtContent)
				$("input[name='cmtContent']").val("");
				$.ajax({
					url: "Comment.do",
					data: {
						"postIdx": postIdx,
						"cmtContent": cmtContent
					},
					type: "post",
					success: function(s) {
						$.ajax({
							url:"Comments.ajax",
							data: { "postIdx": postIdx },
							dataType: "json",
							success:function(d){
								console.log("입력성공")
								$("#cmt-cmt").empty();
								for (let i =0;i<d.length;i++){
									$("#cmt-cmt").append(
										d[i].userId+"<br>"+d[i].cmtContent+"<br>"
										);
								$("#insert-cmt").value=null;
								}
									
							},error:function(e){
								
							}
						})

						
					}


				})
			})



