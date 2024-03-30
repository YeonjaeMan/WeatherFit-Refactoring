$(document).ready(function() {
	// '게시물' 버튼이 체크되어 있는지 확인
	if ($('#btnradio1').is(':checked')) {

		viewUserPosts();
	}

	// 라디오 버튼의 상태 변경을 감지
	$('input[name="btnradio"]').change(function() {
		if ($('#btnradio1').is(':checked')) {

			$("#ajaxcontainer").html("");

			viewUserPosts();

		} else {

			$("#ajaxcontainer").html("");
		}
	});
});

function viewUserPosts() {
	let posts = [];
	$(document).ready(function() {

		$.ajax({
			url: "UserPosts.ajax",
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

		},
		error: function() {
			alert("이미지 가져오기 실패..");
		}
	})

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