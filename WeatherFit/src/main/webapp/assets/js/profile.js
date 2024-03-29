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
	$(document).ready(function() {

		$.ajax({
			url: "MinePosts.ajax",
			type: "post",
			dataType: "json",
			success: function(minePosts) {
				console.log(minePosts);

				for (let i = 0; i < minePosts.length; i++) {
					$.ajax({
						url: "Images.ajax",
						type: "post",
						dataType: "json",
						data: { "postIdx": minePosts[i].postIdx },
						success: function(images) {
							console.log(images);
							let imgPath = "assets/uploads/" + images.fileRname;
							console.log(imgPath);
							$('#ajaxcontainer').append(`
							<div class="col-md-4">
								<div class="card shadow-sm">
										<img src="` + imgPath + `">
									<div class="card-body">
										<p class="card-text">`+ minePosts[i].postContent + `</p>
										<text id="hashtag" x="50%" y="50%" fill="#eceeef" dy=".3em">`+ minePosts[i].hashTag + `</text>
										<div class="d-flex justify-content-between align-items-center">
											<div class="btn-group row" id="contentmodal">
												<button type="button" class="btn btn-view btn-sm btn-outline-secondary" onclick="location.href='gopostdetail.do'">View</button>
												<button type="button" class="btn btn-view btn-sm btn-outline-secondary view-btn"
													data-id=`+ minePosts[i].postIdx + ` data-bs-toggle="modal" data-bs-target="#postModal">View</button>
											</div>
											
										</div>
									</div>
								</div>
							</div>`)
						},

						error: function() {
							alert("파일 불러오기 실패..");
						}


					})
				}
			},
			error: function() {
				console.log("요청 실패..")
			}
		});
	})
}


// 아이콘 클릭하면 배경색 남아있게 하기
$(document).ready(function(){
    $("#profilepost-icon1, #profilepost-icon2").removeClass("active");
    $("#profilepost-icon1, #profilepost-icon2").click(function(){
        // 모든 아이콘에서 'active' 클래스를 제거
        $("#profilepost-icon1, #profilepost-icon2").removeClass("active");
        // 클릭된 아이콘에만 'active' 클래스를 추가
        $(this).addClass("active");
    });
});