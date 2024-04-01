$(document).ready(function() {

	viewUserPosts();

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
			let imgPath = "assets/uploads/" + map.file.fileRname;

			if (post.postTemp == -999) {
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

		},
		error: function() {
			alert("이미지 가져오기 실패..");
		}
	})

}
