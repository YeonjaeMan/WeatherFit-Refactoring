$(document).ready(function() {
	// '게시물' 버튼이 체크되어 있는지 확인
	if ($('#btnradio1').is(':checked')) {
		// 여기에 원하는 동작을 추가하세요.
		console.log('게시물 버튼이 선택되었습니다.');

		viewUserPosts();
	}

	// 라디오 버튼의 상태 변경을 감지
	$('input[name="btnradio"]').change(function() {
		if ($('#btnradio1').is(':checked')) {
			// '게시물' 버튼이 선택될 때 원하는 동작을 추가하세요.
			console.log('게시물 버튼이 선택되었습니다.');

			$("#ajaxcontainer").html("");

			viewUserPosts();

		} else {
			console.log('저장된 버튼이 선택되었습니다.');

			$("#ajaxcontainer").html("");
		}
	});
});

function viewUserPosts() {
	$(document).ready(function() {

		$.ajax({
			url: "UserPosts.ajax",
			type: "post",
			dataType: "json",
			success: function(data) {
				posts = data;
				console.log(posts);
				for (let i = 0; i < posts.length; i++) {
					viewPost(posts[i]);
				}

		},
		error: function() {
			alert("최신 게시글 연결 실패");
		}
		});
	})
}

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
									<a href="Profile.do?userId=` + post.userId + `" class="user-info d-flex align-items-center">
									    <img src="assets/images/user_profile/base_profile.png" alt="프로필 이미지" style="width: 40px; height: 40px; border-radius: 50%;">
									    <span>` + post.userId + `</span>
									</a>
								</div>
									<div class="card-body view-btn shadow-sm" data-bs-toggle="modal" data-bs-target="#postModal">
										<div class="img-container">
											<img src="` + imgPath + `" class="img-fluid mx-auto d-block">
										</div>
									</div>
										<div class="card-footer">
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
                <button type="button" class="btn-blue btn-user dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false"></button>
                <ul class="dropdown-menu">
                    <li><button type="button" class="btn btn-edit btn-sm btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#updatePostModal">Edit</button></li>
                    <li><button type="button" class="btn btn-delete btn-sm btn-outline-danger btn-post-delete">Delete</button></li>
                </ul>
            </div>`);
	} else {
		$('div.card[data-id="' + post.postIdx + '"] .card-header .user-info').after(`
			<button class="follow-button">팔로우</button>`)
	}
}


// 아이콘 클릭하면 배경색 남아있게 하기
$(document).ready(function() {
	$("#profilepost-icon1").addClass("active");
	$("#profilepost-icon1, #profilepost-icon2").click(function() {
		// 모든 아이콘에서 'active' 클래스를 제거
		$("#profilepost-icon1, #profilepost-icon2").removeClass("active");
		// 클릭된 아이콘에만 'active' 클래스를 추가
		$(this).addClass("active");
	});
});