
function postView(T1H) {
	let posts = [];

	console.log(T1H);

	$.ajax({
		url: "Posts.ajax",
		type: "post",
		dataType: "json",
		data: {"temp" : T1H},

		success: function(data) {
			posts = data;
			for (let i = 0; i < posts.length; i++) {
				$.ajax({
					url: "Images",
					data: { "postIdx": posts[i].postIdx },
					type: "post",
					dataType: "json",
					success: function(images) {
						let imgPath = "assets/uploads/" + images.fileRname;
						$('#ajaxcontainer').append(`
									<div class="col-md-4">
										<div class="card shadow-sm">
												<img src="` + imgPath + `">
											<div class="card-body">
												<p class="card-text">`+ posts[i].postContent + `</p>
												<text id="hashtag" x="50%" y="50%" fill="#eceeef" dy=".3em">`+ posts[i].hashTag + `</text>
												<div class="d-flex justify-content-between align-items-center">
													<div class="btn-group row" id="contentmodal">
														<button type="button" class="btn btn-view btn-sm btn-outline-secondary" onclick="location.href='gopostdetail.do'">View</button>
														<button type="button" class="btn btn-view btn-sm btn-outline-secondary view-btn"
															data-id=`+ posts[i].postIdx + ` data-bs-toggle="modal" data-bs-target="#postModal">View</button>
													</div>
												</div>
											</div>
										</div>
									</div>`
						);
					},
					error: function() {
						alert("이미지 가져오기 실패..");
					}
				})


			}

			$(document).on('click', '.view-btn', function() {

				let postIdx = parseInt($(this).data('id'), 10); // 게시물 ID를 숫자로 변환합니다.
				console.log(postIdx);
				// 데이터 속성 이름이 'id'인지, 'postIdx'인지 확인하여 일치하는 이름을 사용
				let post = posts.find(post => post.postIdx === postIdx); // 예를 들어, 여기서는 'postIdx'를 사용

				if (post) { // post가 정상적으로 찾아졌다면
					$('#postModal .modal-body').html(post.postContent + "<br>" + post.hashTag);
				} else {
					// 게시물을 찾지 못했을 때의 처리 로직
					$('#postModal .modal-body').html('해당 게시물을 찾을 수 없습니다.');
				}


				$.ajax({
					url: "Comments.ajax",
					data: { "postIdx": postIdx },
					dataType: "json",
					success: function(comment) {
						console.log(comment)
					}, error: function() {
						console.log("댓글 비동기 불러오기 실패")
						console.log(postIdx)
					}

				});




			});



		},
		error: function() {
			console.log("연결 실패");
		}
	})
};
