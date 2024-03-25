<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
</head>
<body>
	<!-- 헤더부분(날씨위젯, 사이트명, 내비바) -->
	<header class="p-3 bg-light">
		<div
			class="container d-flex align-items-center justify-content-between">
			<!-- 날씨위젯 -->
			<div class="logo">WeatherWidget</div>
			<!-- 사이트명 -->
			<div class="title">WeatherFit</div>
			<!-- 내비바 -->
			<ul class="nav nav-underline">
				<li class="nav-item"><a class="nav-link" href="main.jsp">홈</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="search.jsp">검색</a></li>
				<li class="nav-item"><a class="nav-link" href="#">팔로우</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="message.jsp">메시지</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">알림</a></li>
				<li>
					<!-- 사용자 닉네임이 자신이면 자신의 프로필을 보여주고, 아니라면 다른사람의 프로필을 보여줌 -->
					<button type="button" class="btn btn-primary">로그인</button>
				</li>
			</ul>
		</div>
	</header>
	<main>
		<div class="container">
			<div class="row">
				<div class="d-flex justify-content-evenly">
					<!-- 프로필 사진 -->
					<svg class="bd-placeholder-img rounded-circle" width="140"
						height="140" xmlns="http://www.w3.org/2000/svg" role="img"
						aria-label="Placeholder" preserveAspectRatio="xMidYMid slice"
						focusable="false">
                        <title>프로필사진</title>
                        <rect width="100%" height="100%"
							fill="var(--bs-secondary-color)" />
                    </svg>
					<div class="d-flex flex-column justify-content-evenly">
						<div class="d-flex justify-content-evenly">
							<!-- 닉네임 -->
							<h2 class="fw-normal">닉네임</h2>
							<!-- 팔로우버튼 -->
							<p>
								<a class="btn btn-secondary" href="#">팔로우</a>
							</p>
						</div>
						<!-- 프로필소개 -->
						<p>프로필 소개 나는야 퉁퉁이~ 골목대장이라네~ 나는야 퉁퉁이~ 으라차차차차</p>
					</div>
				</div>
			</div>
		</div>
		<div class="d-flex justify-content-center">
			<div class="btn-group" role="group"
				aria-label="Basic radio toggle button group">
				<input type="radio" class="btn-check" name="btnradio" id="btnradio1"
					autocomplete="off" checked> <label
					class="btn btn-outline-primary" for="btnradio1">게시물</label> <input
					type="radio" class="btn-check" name="btnradio" id="btnradio3"
					autocomplete="off"> <label class="btn btn-outline-primary"
					for="btnradio3">저장됨</label>
			</div>
		</div>
		<hr>
		<div class="album bg-body-tertiary">
			<div class="container">
				<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
					<div class="col">
						<div class="card shadow-sm">
							<svg class="bd-placeholder-img card-img-top" width="100%"
								height="225" xmlns="http://www.w3.org/2000/svg" role="img"
								aria-label="Placeholder: Thumbnail"
								preserveAspectRatio="xMidYMid slice" focusable="false">
                                <title>Placeholder</title>
                                <rect width="100%" height="100%"
									fill="#55595c" />
								<text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text>
                            </svg>
							<div class="card-body">
								<p class="card-text">This is a wider card with supporting</p>
								<div class="d-flex justify-content-between align-items-center">
									<div class="btn-group">
										<button type="button" class="btn btn-sm btn-outline-secondary">View</button>
										<button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
									</div>
									<small class="text-body-secondary">9 mins</small>
								</div>
							</div>
						</div>
					</div>
					<div class="col">
						<div class="card shadow-sm">
							<svg class="bd-placeholder-img card-img-top" width="100%"
								height="225" xmlns="http://www.w3.org/2000/svg" role="img"
								aria-label="Placeholder: Thumbnail"
								preserveAspectRatio="xMidYMid slice" focusable="false">
                                <title>Placeholder</title>
                                <rect width="100%" height="100%"
									fill="#55595c" />
								<text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text>
                            </svg>
							<div class="card-body">
								<p class="card-text">This is a wider card with supporting
									text below as a natural lead-in to additional content. This
									content is a little bit longer.</p>
								<div class="d-flex justify-content-between align-items-center">
									<div class="btn-group">
										<button type="button" class="btn btn-sm btn-outline-secondary">View</button>
										<button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
									</div>
									<small class="text-body-secondary">9 mins</small>
								</div>
							</div>
						</div>
					</div>
					<div class="col">
						<div class="card shadow-sm">
							<svg class="bd-placeholder-img card-img-top" width="100%"
								height="225" xmlns="http://www.w3.org/2000/svg" role="img"
								aria-label="Placeholder: Thumbnail"
								preserveAspectRatio="xMidYMid slice" focusable="false">
                                <title>Placeholder</title>
                                <rect width="100%" height="100%"
									fill="#55595c" />
								<text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text>
                            </svg>
							<div class="card-body">
								<p class="card-text">This is a wider card with supporting
									text below as a natural lead-in to additional content. This
									content is a little bit longer.</p>
								<div class="d-flex justify-content-between align-items-center">
									<div class="btn-group">
										<button type="button" class="btn btn-sm btn-outline-secondary">View</button>
										<button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
									</div>
									<small class="text-body-secondary">9 mins</small>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
</body>
</html>