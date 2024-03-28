
$(document).ready(function() {
	$.ajax({
		url: "Posts",
		type: "post",
		dataType: "json",
		success: function(posts) {
			console.log(posts);

			for (let i = 0; i < posts.length; i++) {

				$.ajax({
					url: "Images",
					data: { "postIdx": posts[i].postIdx },
					type: "post",
					dataType: "json",
					success: function(images) {
						console.log(images);
						let imgPath = "assets/uploads/" + images.fileRname;
						console.log(imgPath);
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

							/*<svg class="bd-placeholder-img card-img-top" width="80%"
										height="400" xmlns="http://www.w3.org/2000/svg" role="img"
										aria-label="Placeholder: Thumbnail"
										preserveAspectRatio="xMidYMid slice" focusable="false">
										</svg>
										<rect width="100%" height="100%" fill="#55595c" />*/
						);
					},
					error: function() {
						console.log("이미지 가져오기 실패..");
					}
				})


			}





			$(document).on('click', '.view-btn', function() {
				let postIdx = $(this).data('id') - 1; // 게시물 ID 가져오기
				// postIdx를 이용하여 해당 게시물의 상세 정보를 가져오는 로직 구현
				// AJAX 요청, 성공 콜백에서 모달의 내용을 채우는 로직 구현



				$('#postModal .modal-body').html(posts[postIdx].postContent + "<br>"
					+ posts[postIdx].hashTag
				);
			});



		},
		error: function(err) {
			console.log("연결 실패");
		}
	})
});






