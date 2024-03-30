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
	
	$(document).on("click", "#postcard", function() {
		let postIdx = $(this).closest('.card').data('id');
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
					       
		$('#cmt-user').html(userInfoHtml);
		$('#cmt-img').attr('src', imgSrc);
		$('#cmt-content').text(content);
		$('#cmt-hashtag').text(hashTag);
		
		$.ajax({
			url: "SelectLike.ajax",
			type: "post",
			dataType: "json",
			data: {"userId" : sessionUserId, "postIdx" : postIdx},
			success: function(res) {
				console.log(res);
			},
			error: function() {
				alert("좋아요 상태 불러오기 실패")
			}
			
		});
	});
	
});

// 추천 게시글 보기
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
				viewPost(posts[i]);
			}

		},
		error: function() {
			alert("추천 게시글 연결 실패");
		}
	})
};

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
	$.ajax({
		url: "Images.ajax",
		data: { "postIdx": post.postIdx },
		type: "post",
		dataType: "json",
		success: function(images) {
			let imgPath = "assets/uploads/" + images.fileRname;
			$('#ajaxcontainer').append(`
						<div class="col-md-4 card-columns">
							<div class="card" data-id=`+ post.postIdx + `>
								<div class="card-header d-flex justify-content-between">
									<a href="Profile.do?userId=` + post.userId + `" id="userinfo-main" class="user-info d-flex align-items-center">
									    <img src="assets/images/user_profile/base_profile.png" alt="프로필 이미지" style="width: 40px; height: 40px; border-radius: 50%;">
									    <span id="post-user">` + post.userId + `</span>
										</a>
								</div>
								<div id="postcard" class="card-body view-btn shadow-sm" data-bs-toggle="modal" data-bs-target="#postModal">
									<div class="img-container">
										<img id="post-img" src="` + imgPath + `" class="img-fluid mx-auto d-block">
									</div>
								</div>
								<div class="card-footer">
									<p id="post-content" style="display:none;"> ` + post.postContent + ` </p>
									<p id="post-temp" style="display:none;"> ` + post.postTemp + `</p>
								    <text id="hashtag" x="50%" y="50%" fill="#eceeef" dy=".3em">`+ post.hashTag + `</text>
								</div>
							</div>
						</div>`
			);

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
                <ul class="dropdown-menu">
                    <li><button type="button" class="btn btn-edit btn-sm btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#updatePostModal">Edit</button></li>
                    <li><button type="button" class="btn btn-delete btn-sm btn-outline-danger btn-post-delete">Delete</button></li>
                </ul>
            </div>`);
	}
}
