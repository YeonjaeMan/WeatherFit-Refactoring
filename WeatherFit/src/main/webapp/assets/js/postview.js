$(document).ready(function() {
	// '최근' 버튼이 체크되어 있는지 확인
	if ($('#recent').is(':checked')) {
		// '최근' 버튼이 체크되어 있으면 최근 게시글 보기 함수를 호출
		recentPostView();
	};

	// 라디오 버튼의 상태 변경을 감지
	$('input[name="btnradio"]').change(function() {
		if ($('#recent').is(':checked')) {
			// ajaxcontainer 내용을 비움
			$("#ajaxcontainer").html("");
			// 최근 게시글 보기 함수 호출
			recentPostView();
		} else {
			// ajaxcontainer 내용을 비움
			$("#ajaxcontainer").html("");
			// 추천 게시글 보기 함수 호출, 현재 온도를 인자로 전달
			recoPostView($("#weather-t1h > span").text().slice(0, -2));
		}
	});

	// 게시글 삭제
	$(document).on("click", "#ajaxcontainer", function(event) {
		// 클릭된 요소가 btn-delete 또는 btn-edit 클래스를 가지고 있는지 확인
		if (event.target.classList.contains("btn-delete") || event.target.classList.contains("btn-edit")) {
			let card = event.target.closest(".card");
			// 클릭된 요소에서 가장 가까운 .view-btn 요소를 찾아서 data-id 값 가져오기
			let postIdx = card.getAttribute("data-id");
			if ($(event.target).hasClass("btn-delete")) {
				$.ajax({
					url: "DeletePost.do",
					type: "post",
					data: { "postIdx": postIdx },
					success: function() {
						// 삭제 성공 시 메인 페이지로 이동
						location.href = "gomain.do";
					},
					error: function() {
						// 삭제 실패 시 오류 메시지 출력
						alert("게시글 삭제 오류!");
					}
				});
			}
			if ($(event.target).hasClass("btn-edit")) {
				// 수정 모달에 postIdx 값을 숨겨진 input으로 추가
				$("#updatePostModal > div > div > div.modal-body > form").append(`<input type="hidden" name="postIdx" value="` + postIdx + `">`);
			}
		}
	});

});

// 추천 게시글 보기
function recoPostView(T1H) {
	let posts = [];
	let craws = [];

	$.ajax({
		url: "Posts.ajax",
		type: "post",
		dataType: "json",
		// 현재 온도를 서버에 전달
		data: { "temp": T1H },

		success: function(data) {
			// 받아온 게시글과 크롤링 데이터에 type 속성 추가
			posts = data.posts.map(item => ({ ...item, type: 'post' }));
			craws = data.craws.map(item => ({ ...item, type: 'craw' }));
			// 게시글과 크롤링 데이터 합치기
			let recoPosts = posts.concat(craws);
			// 합친 데이터를 무작위로 섞음
			shuffle(recoPosts);
			for (let i = 0; i < recoPosts.length; i++) {
				// type 속성에 따라 적절한 함수 호출
				if (recoPosts[i].type === 'post') {
					// 게시글일 경우 viewPost 함수 호출
					viewPost(recoPosts[i]);
				} else if (recoPosts[i].type === 'craw') {
					// 크롤링 데이터일 경우 viewCraw 함수 호출
					viewCraw(recoPosts[i]);
				}
			}
		},
		error: function() {
			// 요청 실패 시 오류 메시지 출력
			alert("추천 게시글 연결 실패");
		}
	});
};

// 배열을 무작위로 섞는 함수
function shuffle(array) {
	array.sort(() => Math.random() - 0.5);
}

// 최근 게시글 보기
function recentPostView() {
	// 게시글 목록을 저장할 빈 배열
	let posts = [];

	$.ajax({
		url: "RecentPosts.ajax",
		type: "post",
		dataType: "json",

		success: function(data) {
			// 서버로부터 받은 데이터를 posts 배열에 할당
			posts = data;
			// posts 배열을 순회하면서 각 게시글 정보를 화면에 표시하는 함수 호출
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
	$.ajax({
		url: "Images.ajax",
		data: {
			"postIdx": post.postIdx,
			"userId": post.userId
		},
		type: "post",
		dataType: "json",
		success: function(map) {
			// 이미지 경로 설정
//			let imgPath = "assets/uploads/" + map.file.fileRname;

			// 게시물의 온도정보가 -999일 경우
			if (post.postTemp == -999) {
				// 게시물의 온도를 null로 설정
				post.postTemp = null;
				$('#ajaxcontainer').append(`
							<div class="col-md-4 card-columns">
								<div class="card mb-2" data-id=`+ post.postIdx + `>
									<div class="card-header d-flex justify-content-between">
									</div>
									<div id="post-imgbody" class="card-body view-btn" data-bs-toggle="modal" data-bs-target="#postModal">
										<div class="img-container">
                                            <img id="post-img" src="data:image/jpeg;base64, ` + map.file + `" class="img-fluid mx-auto d-block">
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
				// 게시글의 온도 정보가 있는 경우 
			} else {
				// 온도 값에 °C 추가
				post.postTemp = post.postTemp + "°C";
				$('#ajaxcontainer').append(`
							<div class="col-md-4 card-columns">
								<div class="card mb-2" data-id=`+ post.postIdx + `>
									<div class="card-header d-flex justify-content-between">
										
									</div>
									<div id="post-imgbody" class="card-body view-btn" data-bs-toggle="modal" data-bs-target="#postModal">
										<div class="img-container">
                                            <img id="post-img" src="data:image/jpeg;base64, ` + map.file + `" class="img-fluid mx-auto d-block">
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

			// 사용자 프로필 이미지와 닉네임을 게시물에 추가
			if (sessionUserId == "") {
				// 비로그인 상태일 경우
				$(".card[data-id='" + post.postIdx + "'] .card-header").html(`<a id="userinfo-main" data-bs-toggle="modal" data-bs-target="#joinModal">
				                                            <img src="data:image/png;base64, ` + map.profile + `" alt="프로필 이미지" style="width: 40px; height: 40px; border-radius: 50%;">
				                                            <span id="post-user">` + map.user.userNick + `</span>
				                                        </a>`);
			} else {
				// 로그인 상태일 경우
				$(".card[data-id='" + post.postIdx + "'] .card-header").html(`<a href="Profile.do?userId=` + post.userId + `" id="userinfo-main" class="user-info d-flex align-items-center">
										    <img src="data:image/png;base64, ` + map.profile + `" alt="프로필 이미지" style="width: 40px; height: 40px; border-radius: 50%;">
										    <span id="post-user">` + map.user.userNick + `</span>
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
								<span id="recommend-text"> WeatherFit 추천 </span>
								</div>
								<div id="postcard" class="card-body view-btn">
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