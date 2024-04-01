$(document).ready(function() {
	// '최근' 버튼이 체크되어 있는지 확인
	if ($('#recent').is(':checked')) {

		recentPostView();
	};

	// 라디오 버튼의 상태 변경을 감지
	$('input[name="btnradio"]').change(function() {
		if ($('#recent').is(':checked')) {

			$("#ajaxcontainer").html("");

			recentPostView();

		} else {

			$("#ajaxcontainer").html("");

			recoPostView($("#weather-t1h > span").text().slice(0, -2));
		}
	});

	$(document).on("click", "#ajaxcontainer", function(event) {
		// 클릭된 요소가 btn-delete 또는 btn-edit 클래스를 가지고 있는지 확인
		if (event.target.classList.contains("btn-delete") || event.target.classList.contains("btn-edit")) {
			let card = event.target.closest(".card");
			// 클릭된 요소에서 가장 가까운 .view-btn 요소를 찾아서 data-id 값을 가져옵니다.
			let postIdx = card.getAttribute("data-id");
			if ($(event.target).hasClass("btn-delete")) {
				$.ajax({
					url: "DeletePost.do",
					type: "post",
					data: { "postIdx": postIdx },
					success: function() {
						location.href = "gomain.do";
					},
					error: function() {
						alert("게시글 삭제 오류!");
					}
				});
			}

			if ($(event.target).hasClass("btn-edit")) {
				$("#updatePostModal > div > div > div.modal-body > form").append(`<input type="hidden" name="postIdx" value="` + postIdx + `">`);
			}
		}
	});

	/*$(document).on("click", "#postcard", function() {
		let postIdx = $(this).closest('.card').data('id');
		console.log(postIdx);
		let userId = $(this).closest('.card').find('#post-user').text();
		let imgSrc = $(this).find('#post-img').attr('src');
		let content = $(this).closest('.card').find('#post-content').text();
		let hashTag = $(this).closest('.card').find('#hashtag').text();

		let userInfoHtml = '<a href="Profile.do?userId=' + userId + '" id="userinfo-main" class="user-info d-flex align-items-center">' +
			'<img src="assets/images/user_profile/base_profile.png" alt="프로필 이미지" style="width: 40px; height: 40px; border-radius: 50%;">' +
			'<span id="post-user">' + userId + '</span>' +
			'</a>' +
			'<svg id="message" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">' +
			'<path d="M64 112c-8.8 0-16 7.2-16 16v22.1L220.5 291.7c20.7 17 50.4 17 71.1 0L464 150.1V128c0-8.8-7.2-16-16-16H64zM48 212.2V384c0 8.8 7.2 16 16 16H448c8.8 0 16-7.2 16-16V212.2L322 328.8c-38.4 31.5-93.7 31.5-132 0L48 212.2zM0 128C0 92.7 28.7 64 64 64H448c35.3 0 64 28.7 64 64V384c0 35.3-28.7 64-64 64H64c-35.3 0-64-28.7-64-64V128z" />' +
			'</svg>';

		$('#like-heart').attr("data-id", postIdx);
		$('#cmt-user').html(userInfoHtml);
		$('#cmt-img').attr('src', imgSrc);
		$('#cmt-content').text(content);
		$('#cmt-hashtag').text(hashTag);

		$.ajax({
			url: "SelectLike.ajax",
			type: "post",
			dataType: "json",
			data: { "userId": sessionUserId, "postIdx": postIdx },
			success: function(res) {
				console.log(res);
				if (res == null) {
					$("#like").html(`<svg id="like-heart" data-id="` + postIdx + `" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
		                				<path d="M47.6 300.4L228.3 469.1c7.5 7 17.4 10.9 27.7 10.9s20.2-3.9 27.7-10.9L464.4 300.4c30.4-28.3 47.6-68 47.6-109.5v-5.8c0-69.9-50.5-129.5-119.4-141C347 36.5 300.6 51.4 268 84L256 96 244 84c-32.6-32.6-79-47.5-124.6-39.9C50.5 55.6 0 115.2 0 185.1v5.8c0 41.5 17.2 81.2 47.6 109.5z" />
		             				</svg>`);
				} else {
					$("#like").html(`<svg id="like-heart" class="active" data-id="` + postIdx + `" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
			                				<path d="M47.6 300.4L228.3 469.1c7.5 7 17.4 10.9 27.7 10.9s20.2-3.9 27.7-10.9L464.4 300.4c30.4-28.3 47.6-68 47.6-109.5v-5.8c0-69.9-50.5-129.5-119.4-141C347 36.5 300.6 51.4 268 84L256 96 244 84c-32.6-32.6-79-47.5-124.6-39.9C50.5 55.6 0 115.2 0 185.1v5.8c0 41.5 17.2 81.2 47.6 109.5z" />
			             				</svg>`);
				}
			},
			error: function() {
				alert("좋아요 상태 불러오기 오류");
			}

		});

	});*/

});

// 추천 게시글 보기
function recoPostView(T1H) {
	let posts = [];
	let craws = [];

	$.ajax({
		url: "Posts.ajax",
		type: "post",
		dataType: "json",
		data: { "temp": T1H },

		success: function(data) {
			// 각 요소에 type 속성 추가
			posts = data.posts.map(item => ({ ...item, type: 'post' }));
			craws = data.craws.map(item => ({ ...item, type: 'craw' }));
			let recoPosts = posts.concat(craws);
			shuffle(recoPosts);
			console.log(craws);
			for (let i = 0; i < recoPosts.length; i++) {
				// type 속성에 따라 적절한 함수 호출
				if (recoPosts[i].type === 'post') {
					viewPost(recoPosts[i]);
				} else if (recoPosts[i].type === 'craw') {
					viewCraw(recoPosts[i]);
				}
			}
		},
		error: function() {
			alert("추천 게시글 연결 실패");
		}
	});
};

function shuffle(array) {
	array.sort(() => Math.random() - 0.5);
}

// 최근 게시글 보기
function recentPostView() {
	let posts = [];

	$.ajax({
		url: "RecentPosts.ajax",
		type: "post",
		dataType: "json",

		success: function(data) {
			posts = data;
			for (let i = 0; i < posts.length; i++) {
				viewPost(posts[i]);
			}

		},
		error: function() {
			alert("최신 게시글 연결 실패");
		}
	})
};

// 공통인 이미지를 가져오는 ajax와 더불어 브라우저에 html을 작성해주기
function viewPost(post) {
	console.log(typeof sessionUserId);
	$.ajax({
		url: "Images.ajax",
		data: { "postIdx": post.postIdx,
				"userId" : post.userId },
		type: "post",
		dataType: "json",
		success: function(map) {
			let imgPath = "assets/uploads/" + map.file.fileRname;
			
			if(post.postTemp == -999) {
				post.postTemp = null;
				$('#ajaxcontainer').append(`
							<div class="col-md-4 card-columns">
								<div class="card mb-2" data-id=`+ post.postIdx + `>
									<div class="card-header d-flex justify-content-between">
										
									</div>
									<div id="post-imgbody" class="card-body view-btn" data-bs-toggle="modal" data-bs-target="#postModal">
										<div class="img-container">
											<img id="post-img" src="` + imgPath + `" class="img-fluid mx-auto d-block">
										</div>
									</div>
									<div class="card-footer d-flex justify-content-between">
										<p id="post-content" style="display:none;"> ` + post.postContent + ` </p>
										<p id="post-temp" style="display:none;"> ` + post.postTemp + `</p>
									    <text id="hashtag" x="50%" y="50%" fill="#eceeef" dy=".3em">`+ post.hashTag + `</text>
									</div>
								</div>
							</div>`
				);
				
			} else {
				post.postTemp = post.postTemp + "°C";
				$('#ajaxcontainer').append(`
							<div class="col-md-4 card-columns">
								<div class="card mb-2" data-id=`+ post.postIdx + `>
									<div class="card-header d-flex justify-content-between">
										
									</div>
									<div id="post-imgbody" class="card-body view-btn" data-bs-toggle="modal" data-bs-target="#postModal">
										<div class="img-container">
											<img id="post-img" src="` + imgPath + `" class="img-fluid mx-auto d-block">
										</div>
									</div>
									<div class="card-footer d-flex justify-content-between">
										<p id="post-content" style="display:none;"> ` + post.postContent + ` </p>
									    <text id="hashtag" x="50%" y="50%" fill="#eceeef" dy=".3em">`+ post.hashTag + `</text>
										<p id="post-temp"> ` + post.postTemp + `</p>
									</div>
								</div>
							</div>`
				);
				
			}
			
			if(sessionUserId == "") {
				        $(".card[data-id='" + post.postIdx + "'] .card-header").html(`<a id="userinfo-main" data-bs-toggle="modal" data-bs-target="#joinModal">
				                                            <img src="assets/user_profile/` + map.user.userProfileImg + `" alt="프로필 이미지" style="width: 40px; height: 40px; border-radius: 50%;">
				                                            <span id="post-user">` + post.userId + `</span>
				                                        </a>`);
				    } else {
						$(".card[data-id='" + post.postIdx + "'] .card-header").html(`<a href="Profile.do?userId=` + post.userId + `" id="userinfo-main" class="user-info d-flex align-items-center">
										    <img src="assets/user_profile/` + map.user.userProfileImg + `" alt="프로필 이미지" style="width: 40px; height: 40px; border-radius: 50%;">
										    <span id="post-user">` + post.userId + `</span>
											</a>`);
					}

			userPostCheck(post);

		},
		error: function() {
			alert("이미지 가져오기 실패..");
		}
	})

}

// 로그인한 유저의 세션 아이디와 포스트에 담겨 있는 아이디 확인해주기
function userPostCheck(post) {
	if (sessionUserId == post.userId) {
		$('div.card[data-id="' + post.postIdx + '"] .card-header .user-info').after(`
            <div class="btn-group">
                <button type="button" id="btn-editdel" class="btn-blue btn-user dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false"></button>
                <ul id="dropdown-editdel" class="dropdown-menu">
                    <li><button type="button" class="btn btn-edit btn-sm btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#updatePostModal">게시글 수정</button></li>
                    <li><button type="button" class="btn btn-delete btn-sm btn-outline-danger btn-post-delete">게시글 삭제</button></li>
                </ul>
            </div>`);
	}
}

function viewCraw(craw) {
	$('#ajaxcontainer').append(`
					<div class="col-md-4 card-columns">
						<div class="card" data-id=`+ craw.crawlingIdx + `>
							<div class="card-header d-flex justify-content-between">
								<span> WeatherFit 추천 </span>
								</div>
								<div id="postcard" class="card-body view-btn shadow-sm">
									<div class="img-container">
										<img id="post-img" src="` + craw.src + `" class="img-fluid mx-auto d-block">
									</div>
								</div>
								<div class="card-footer">
								    <text id="hashtag" x="50%" y="50%" fill="#eceeef" dy=".3em">`+ craw.tag + `</text>
								</div>
							</div>
						</div>`
	);

}