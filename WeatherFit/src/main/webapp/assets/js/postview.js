


(function() {
	$.ajax({
		url: "Posts",
		type: "get",
		dataType: "json",
		success: function(posts) {
			console.log(posts);
		

			$.each(posts, function(i) {
				
			});
			

			let ajaxcontent =`
				<div class="col-md-4">
					<div class="card shadow-sm">
						<svg class="bd-placeholder-img card-img-top" width="100%"
							height="225" xmlns="http://www.w3.org/2000/svg" role="img"
							aria-label="Placeholder: Thumbnail"
							preserveAspectRatio="xMidYMid slice" focusable="false">
							<title>Placeholder</title>
							<rect width="100%" height="100%" fill="#55595c" />
							<text x="50%" y="50%" fill="#eceeef" dy=".3em">`+posts[0].hashTag+`</text>
						</svg>
						<div class="card-body">
							<p class="card-text">`+ posts[0].postContent +`</p>
							<div class="d-flex justify-content-between align-items-center">
								<div class="btn-group">
									<button type="button" class="btn btn-sm btn-outline-secondary">View</button>
									<button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
									<a class="btn btn-default" data-target="#contentmodal" data-toggle="modal">상세보기</a>
								</div>
								<small class="text-body-secondary">9 mins</small>
							</div>
						</div>
					</div>
				</div>`;

		for (let i = 0; i < 8; i++) {
        $('#ajaxcontainer').append(`
				<div class="col-md-4">
					<div class="card shadow-sm">
						<svg class="bd-placeholder-img card-img-top" width="100%"
							height="225" xmlns="http://www.w3.org/2000/svg" role="img"
							aria-label="Placeholder: Thumbnail"
							preserveAspectRatio="xMidYMid slice" focusable="false">
							<title>Placeholder</title>
							<rect width="100%" height="100%" fill="#55595c" />
							<text x="50%" y="50%" fill="#eceeef" dy=".3em">`+posts[i].hashTag+`</text>
						</svg>
						<div class="card-body">
							<p class="card-text">`+ posts[i].postContent +`</p>
							<div class="d-flex justify-content-between align-items-center">
								<div class="btn-group" id="contentmodal">
									<button type="button" class="btn btn-sm btn-outline-secondary" data-target="#postModal" data-toggle="modal">View</button>
									<button type="button" class="btn btn-sm btn-outline-secondary" onclick="location.href='gopostdetail.do'">View</button>
								</div>
								<small class="text-body-secondary">`+ posts[i].createdAt +`</small>
							</div>
						</div>
					</div>
				</div>`
			
		);
    }



		},
		error: function(err) {
			console.log("연결 실패");
		}
	})
}());
