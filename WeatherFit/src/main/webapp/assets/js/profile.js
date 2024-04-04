$(document).ready(function() {

	viewUserPosts();

});

function viewUserPosts() {
	// 게시글을 저장할 배열을 초기화
	let posts = [];
	$(document).ready(function() {
		// AJAX 요청을 통해 서버로부터 사용자 게시글 데이터를 가져오기
		$.ajax({
			url: "UserPosts.ajax",
			type: "post",
			dataType: "json",
			success: function(data) {
				// 서버로부터 받은 데이터를 posts 배열에 저장
				posts = data;
				// posts 배열의 각 요소에 대해 반복
				for (let i = 0; i < posts.length; i++) {
					// 개별 게시글을 보는 함수를 호출
					viewPost(posts[i]);
				}
			},
			error: function() {
				alert("최신 게시글 연결 실패");
			}
		});
	})
}

// 개별 게시글을 보는 함수
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
			// 이미지 경로를 구성
			let imgPath = "assets/uploads/" + map.file.fileRname;
			// 게시글의 온도 정보가 -999인 경우, 즉 온도 정보가 없는 경우
			if (post.postTemp == -999) {
				// 온도 정보를 null로 설정
				post.postTemp = null;
				// 게시글을 표시. 온도 정보가 없으므로 온도를 표시하지 않음
				$('#ajaxcontainer').append(`
							<div class="col-md-4 card-columns">
								<div class="card mb-2" data-id=`+ post.postIdx + `>
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

			// 온도 정보가 있는 경우
			} else {
				// 온도 정보 뒤에 '°C'를 붙여 표시
				post.postTemp = post.postTemp + "°C";
				// 게시글을 표시합니다. 온도 정보를 포함하여 표시
				$('#ajaxcontainer').append(`
							<div class="col-md-4 card-columns">
								<div class="card mb-2" data-id=`+ post.postIdx + `>
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
		},
		error: function() {
			alert("이미지 가져오기 실패..");
		}
	})

}
