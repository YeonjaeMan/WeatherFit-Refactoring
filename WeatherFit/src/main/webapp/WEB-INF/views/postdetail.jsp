<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail</title>

<%@ include file="includeHeaders.jsp"%>

<link rel="stylesheet" href="assets/css/postdetail.css">
</head>
<body>
	<main>
		<%@ include file="includeNavi.jsp"%>
		<!-- 게시글 확인 페이지 -->
		<!-- Page content-->
		<div class="container mt-5" id="container-post">
			<div class="row">
				<div class="col-lg-4">
					<!-- Post content-->
					<article>
						<!-- Post header-->
						<header id="header-id" class="mb-4">
							<!-- Post title-->
							<div class="flex-shrink-0 d-flex mb-1 align-items-center">
								<img id="img-profile" class="rounded-circle"
									src="https://mblogthumb-phinf.pstatic.net/MjAyMjA3MTVfMTUz/MDAxNjU3ODkzNDE1MzU4.JO8gT9XAe5JIb-OKM5Y-_Iz5VdDYfhuHrQ6JtKKTSBQg.QVI_F5CCDt3XTa9CwHCkmtPD6J33bWn_u_iXzWkacysg.JPEG.ksej02/IMG_6411.JPG?type=w800"
									alt="..." />&nbsp;&nbsp;
								<h4 class="fw-bolder mb-1 mr-3">woooodong</h4>
								<div id="message-wrap">
									<svg id="message" xmlns="http://www.w3.org/2000/svg"
										viewBox="0 0 512 512">
										<!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
                    <path
											d="M64 112c-8.8 0-16 7.2-16 16v22.1L220.5 291.7c20.7 17 50.4 17 71.1 0L464 150.1V128c0-8.8-7.2-16-16-16H64zM48 212.2V384c0 8.8 7.2 16 16 16H448c8.8 0 16-7.2 16-16V212.2L322 328.8c-38.4 31.5-93.7 31.5-132 0L48 212.2zM0 128C0 92.7 28.7 64 64 64H448c35.3 0 64 28.7 64 64V384c0 35.3-28.7 64-64 64H64c-35.3 0-64-28.7-64-64V128z" />
                  </svg>
								</div>
							</div>
							<!-- Post categories-->
							<a class="badge bg-secondary text-decoration-none link-light"
								href="#!">이달의 패션왕</a> <a
								class="badge bg-secondary text-decoration-none link-light"
								href="#!">인기짱</a>
						</header>
						<!-- Preview image figure-->
						<figure class="mb-4 justify-content-center">
							<img id="img-post" class="img-fluid rounded"
								src="https://dummyimage.com/900x400/ced4da/6c757d.jpg" alt="..." />
						</figure>
					</article>
				</div>
				<div class="col-lg-4 justify-content-center">
					<section class="col-md-10 mt-5">
						<div id="post-txt">
							난 ㄱㅏ끔 ⓡⓔⓓ를 입는ㄷr..<br>
							<br> レΓ는 심㉨よ0l 없♥ㅓ · · <br> ュ㈃ŇΛ┤ · · ⌽r픈 girl 느낄己i 無o┤
							· ·. <br> ■■■■■■■■■□ 패션충전 90%
						</div>
						<div id="post-hashtag">#오늘날씨 #미쳤다 #이게나 #OOTD #선팔맞팔</div>
						<div id="like">
							<!-- <i class="fa-regular fa-heart" id="like-heart"></i>   -->
							<svg id="like-heart" xmlns="http://www.w3.org/2000/svg"
								viewBox="0 0 512 512">
								<!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
                <path
									d="M47.6 300.4L228.3 469.1c7.5 7 17.4 10.9 27.7 10.9s20.2-3.9 27.7-10.9L464.4 300.4c30.4-28.3 47.6-68 47.6-109.5v-5.8c0-69.9-50.5-129.5-119.4-141C347 36.5 300.6 51.4 268 84L256 96 244 84c-32.6-32.6-79-47.5-124.6-39.9C50.5 55.6 0 115.2 0 185.1v5.8c0 41.5 17.2 81.2 47.6 109.5z" />
              </svg>
							dongzu, yeon님이 좋아합니다.
						</div>
					</section>


					<!-- 댓글섹션 -->
					<section class="col-md-10 mt-4">
						<div class="card bg-light">
							<div class="card-body">
								<!-- 댓글 글씨들..  -->
								<!-- Comment with nested comments-->
								<div class="d-flex mb-4">
									<!-- Parent comment-->
									<div class="flex-shrink-0">
										<img id="img-profile" class="rounded-circle"
											src="https://dummyimage.com/50x50/ced4da/6c757d.jpg"
											alt="..." />
									</div>
									<div class="ms-3">
										<div class="fw-bold">dongzu</div>
										패션 어렵다...
										<!-- Child comment 1-->
										<div class="d-flex mt-2">
											<div class="flex-shrink-0">
												<img id="img-profile" class="rounded-circle"
													src="https://dummyimage.com/50x50/ced4da/6c757d.jpg"
													alt="..." />
											</div>
											<div class="ms-3">
												<div class="fw-bold">yeon</div>
												never give up!
											</div>
										</div>
										<!-- Child comment 2-->
										<div class="d-flex mt-2">
											<div class="flex-shrink-0">
												<img id="img-profile" class="rounded-circle"
													src="https://dummyimage.com/50x50/ced4da/6c757d.jpg"
													alt="..." />
											</div>
											<div class="ms-3">
												<div class="fw-bold">heessoso</div>
												화이팅팅!!
											</div>
										</div>
									</div>
								</div>
								<!-- 댓글 작성창-->
								<form action="#" method="post" class="mb-4" id="comment-form">
									<textarea class="form-control" rows="2"
										placeholder="댓글을 남겨주세요.">
                  </textarea>
									<div class="row justify-content-center">
										<button type="submit" class="btn btn-primary mt-3"
											id="btn-submit">댓글 등록</button>
									</div>
								</form>
							</div>
						</div>
					</section>
				</div>
			</div>
		</div>
		<!-- </div> -->
	</main>



	<script src="assets/js/postdetail.js"></script>
</body>
</html>