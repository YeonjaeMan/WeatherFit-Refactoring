$(document).ready(function() {
	// '게시물' 버튼이 체크되어 있는지 확인
	if ($('#btnradio1').is(':checked')) {
		// 여기에 원하는 동작을 추가하세요.
		console.log('게시물 버튼이 선택되었습니다.');

		viewMinePosts();
	}

	// 라디오 버튼의 상태 변경을 감지
	$('input[name="btnradio"]').change(function() {
		if ($('#btnradio1').is(':checked')) {
			// '게시물' 버튼이 선택될 때 원하는 동작을 추가하세요.
			console.log('게시물 버튼이 선택되었습니다.');

			$("#ajaxcontainer").html("");
			
			viewMinePosts();
			
		} else {
			console.log('저장된 버튼이 선택되었습니다.');
			
			$("#ajaxcontainer").html("");
		}
	});
});

function viewMinePosts() {
	$.ajax({
		url: "MinePosts",
		data: { "userId": userId },
		type: "get",
		dataType: "json",
		success: function(minePosts) {
			console.log(minePosts);

			for (let i = 0; i < minePosts.length; i++) {
				$('#ajaxcontainer').append(`
		 				<div class="col-md-4">
		 					<div class="card shadow-sm">
		 						<svg class="bd-placeholder-img card-img-top" width="100%"
		 							height="225" xmlns="http://www.w3.org/2000/svg" role="img"
		 							aria-label="Placeholder: Thumbnail"
		 							preserveAspectRatio="xMidYMid slice" focusable="false">
		 							<title>Placeholder</title>
		 							<rect width="100%" height="100%" fill="#55595c" />
		 							<text x="50%" y="50%" fill="#eceeef" dy=".3em">`+ minePosts[i].hashTag + `</text>
		 						</svg>
		 						<div class="card-body">
		 							<p class="card-text">`+ minePosts[i].postContent + `</p>
		 							<div class="d-flex justify-content-between align-items-center">
		 								<div class="btn-group" id="contentmodal">
		 									<button type="button" class="btn btn-sm btn-outline-secondary" onclick="location.href='gopostdetail.do'">View</button>
		 									<button type="button" class="btn btn-sm btn-outline-secondary view-btn"
		 										data-id=`+ minePosts[i].postIdx + ` data-bs-toggle="modal" data-bs-target="#postModal">View</button>
		 								</div>
		 								<small class="text-body-secondary">`+ minePosts[i].createdAt + `</small>
		 							</div>
		 						</div>
		 					</div>
		 				</div>`

				);
			}
		},
		error: function() {
			console.log("요청 실패..")
		}
	});
}