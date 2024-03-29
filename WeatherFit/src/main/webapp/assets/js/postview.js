$(document).ready(function() {
	// '최근' 버튼이 체크되어 있는지 확인
	if ($('#recent').is(':checked')) {
		// 여기에 원하는 동작을 추가하세요.
		console.log('최근 버튼이 선택되었습니다.');

		recentPostView();
	};

	// 라디오 버튼의 상태 변경을 감지
	$('input[name="btnradio"]').change(function() {
		if ($('#recent').is(':checked')) {
			// '게시물' 버튼이 선택될 때 원하는 동작을 추가하세요.
			console.log('최근 버튼이 선택되었습니다.');

			$("#ajaxcontainer").html("");

			recentPostView();

		} else {
			console.log('추천 버튼이 선택되었습니다.');

			$("#ajaxcontainer").html("");

			recoPostView($("#weather-t1h > span").text().slice(0, 2));
		}
	});

	document.addEventListener("click", function(event) {
		let postIdx = event.target.closest(".view-btn").getAttribute("data-id");
		if (event.target.classList.contains("btn-delete")) {
			$.ajax({
				url: "DeletePost.ajax",
				type: "post",
				data: {"postIdx" : postIdx}
			});
		}
		
		if(event.target.classList.contains("btn-edit")) {
			$("#updatePostModal > div > div > div.modal-body > form").append(`<input type="hidden" name="postIdx" value="` + postIdx + `">`);
		}
	});
});

function recoPostView(T1H) {
	let posts = [];

	$.ajax({
		url: "Posts.ajax",
		type: "post",
		dataType: "json",
		data: { "temp": T1H },

		success: function(data) {
			posts = data;
			for (let i = 0; i < posts.length; i++) {
				$.ajax({
					url: "Images.ajax",
					data: { "postIdx": posts[i].postIdx },
					type: "post",
					dataType: "json",
					success: function(images) {
						let imgPath = "assets/uploads/" + images.fileRname;
						$('#ajaxcontainer').append(`
							<div class="col-md-4">
								<div class="card shadow-sm view-btn" 
							data-id=`+ posts[i].postIdx + ` data-bs-toggle="modal" data-bs-target="#postModal">
										<img src="` + imgPath + `">
									<div class="card-body">
										<p class="card-text">`+ posts[i].postContent + `</p>
										<text id="hashtag" x="50%" y="50%" fill="#eceeef" dy=".3em">`+ posts[i].hashTag + `</text>
										<div class="d-flex justify-content-between align-items-center">
											<div class="btn-group row" id="contentmodal">
												<button type="button" class="btn btn-view btn-sm btn-outline-secondary" onclick="location.href='gopostdetail.do'">View</button>
												<button type="button" class="btn btn-edit btn-sm btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#updatePostModal">Edit</button>
    											<button type="button" class="btn btn-delete btn-sm btn-outline-danger btn-post-delete">Delete</button>
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

		},
		error: function() {
			console.log("연결 실패");
		}
	})
};

function recentPostView() {
	let posts = [];

	$.ajax({
		url: "RecentPosts.ajax",
		type: "post",
		dataType: "json",

		success: function(data) {
			posts = data;
			for (let i = 0; i < posts.length; i++) {
				$.ajax({
					url: "Images.ajax",
					data: { "postIdx": posts[i].postIdx },
					type: "post",
					dataType: "json",
					success: function(images) {
						let imgPath = "assets/uploads/" + images.fileRname;
						$('#ajaxcontainer').append(`
							<div class="postcard col-md-4 fixed-height">
								<div class="card shadow-sm view-btn" 
							data-id=`+ posts[i].postIdx + ` data-bs-toggle="modal" data-bs-target="#postModal">
										<img src="` + imgPath + `">
									<div class="card-body">
										<p class="card-text">`+ posts[i].postContent + `</p>
										<text id="hashtag" x="50%" y="50%" fill="#eceeef" dy=".3em">`+ posts[i].hashTag + `</text>
										<div class="d-flex justify-content-between align-items-center">
											<div class="btn-group row" id="contentmodal">
												<button type="button" class="btn btn-view btn-sm btn-outline-secondary" onclick="location.href='gopostdetail.do'">View</button>
												<button type="button" class="btn btn-view btn-sm btn-outline-secondary >View</button>
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

		},
		error: function() {
			console.log("연결 실패");
		}
	})
};
