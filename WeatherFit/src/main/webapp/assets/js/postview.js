
$(document).ready(function() {
	let posts = [];

	$.ajax({
		url: "Posts.ajax",
		type: "get",
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
		error: function(err) {
			console.log("연결 실패");
		}
	})
});
