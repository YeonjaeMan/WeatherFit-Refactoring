$(document).ready(function() {

	$.ajax({
		url: "CrawlingPost.ajax",
		type: "post",
		dataType: "json",
		success: function(crawling) {


			for (let i = 0; i < 0; i++) {
				$('#ajaxcontainer').append(`
				<div class="col-md-4">
					<div class="card shadow-sm">
						<img src="` + crawling[i].src + `">
							<title>Placeholder</title>
							<rect width="100%" height="100%" fill="#55595c" />
						</svg>
						<div class="card-body">
							<p class="card-text">`+ crawling[i].tag + `</p>
							<text id="hashtag" x="50%" y="50%" fill="#eceeef" dy=".3em">`+ crawling[i].season + `</text>
							<div class="d-flex justify-content-between align-items-center">
								<div class="btn-group row" id="contentmodal">
									<button type="button" class="btn btn-view btn-sm btn-outline-secondary" onclick="location.href='gopostdetail.do'">View</button>
									<button type="button" class="btn btn-view btn-sm btn-outline-secondary view-btn"
										data-id=`+ crawling[i].crawlingIdx + ` data-bs-toggle="modal" data-bs-target="#postModal">View</button>
								</div>
								
							</div>
						</div>
					</div>
				</div>`

				);
			}




		},
		error: function() {
			console.log("연결 실패");
		}
	})
});
