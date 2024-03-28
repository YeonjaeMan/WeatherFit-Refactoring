$(document).ready(function() {
	$.ajax({
		url: "Posts.ajax",
		type: "get",
		dataType: "json",
		success: function(posts) {


			$.each(posts, function(i) {

			});


			for (let i = 0; i < 4; i++) {
				$('#ajaxcontainer').append(`
				<div class="col-md-4">
					<div class="card shadow-sm">
						<svg class="bd-placeholder-img card-img-top" width="100%"
							height="225" xmlns="http://www.w3.org/2000/svg" role="img"
							aria-label="Placeholder: Thumbnail"
							preserveAspectRatio="xMidYMid slice" focusable="false">
							<title>Placeholder</title>
							<rect width="100%" height="100%" fill="#55595c" />
							<text x="50%" y="50%" fill="#eceeef" dy=".3em">`+ posts[i].hashTag + `</text>
						</svg>
						<div class="card-body">
							<p class="card-text">`+ posts[i].postContent + `</p>
							<div class="d-flex justify-content-between align-items-center">
								<div class="btn-group" id="contentmodal">
									<button type="button" class="btn btn-sm btn-outline-secondary" onclick="location.href='gopostdetail.do'">View</button>
									<button type="button" class="btn btn-sm btn-outline-secondary view-btn"
										data-id=`+ posts[i].postIdx  + ` data-bs-toggle="modal" data-bs-target="#postModal">View</button>
								</div>
								<small class="text-body-secondary">`+ posts[i].createdAt + `</small>
							</div>
						</div>
					</div>
				</div>`

				);
			}

			$(document).on('click', '.view-btn', function() {
				let postIdx = $(this).data('id')-1; // 게시물 ID 가져오기
				// postIdx를 이용하여 해당 게시물의 상세 정보를 가져오는 로직 구현
				// AJAX 요청, 성공 콜백에서 모달의 내용을 채우는 로직 구현
					
				
				
				$('#postModal .modal-body').html(posts[postIdx].postContent+"<br>"
				+posts[postIdx].hashTag
				);
			});



		},
		error: function(err) {
			console.log("연결 실패");
		}
	})
});
$("#ajaxcontroller").on("click",()=>{
	$.ajax({
		url:"Posts.ajax",
		dataType:"json",
		success: function(e){
			console.log("ajax컨트롤러를 경유")
			console.log(e)
		},error: function(r){
			console.log("ajax컨트롤러 경유실패")
		}
})
	
})





