<%@page import="com.smhrd.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>


	<!-- 부트스트랩, 제이쿼리, 폰트어썸 -->
	<%@ include file="includeHeaders.jsp"%>
	<link rel="stylesheet" href="assets/css/profile.css">
	
</head>
<body>
	<!-- 날씨 위젯과 상단 네비 분리 -->

	<%@ include file="includeNavi.jsp"%>

	<%
	UserVO uvo = (UserVO) session.getAttribute("member");
	%>

	<main>
		<div class="container">
			<div class="row">
				<div class="d-flex justify-content-evenly mt-5 mb-5">
					<!-- 프로필 사진 -->
					<svg class="bd-placeholder-img rounded-circle" width="140"
						height="140" xmlns="http://www.w3.org/2000/svg" role="img"
						aria-label="Placeholder" preserveAspectRatio="xMidYMid slice"
						focusable="false">
                        <title>프로필사진</title>
                        <rect width="100%" height="100%"
							fill="var(--bs-secondary-color)" />
                    </svg>

					<div id="nick-follow" class="d-flex flex-column justify-content-evenly">
						<div  class="d-flex">
							<!-- 닉네임 -->
							<h2 class="fw-normal" id="nickname"><%= uvo.getUserNick() %></h2>

							<!-- 팔로우버튼 -->
							<p>
								<a class="btn-blue" href="#">팔로우</a>
							</p>
						</div>
						<!-- 프로필소개 -->
						<p><%=uvo.getUserProfileInfo()%></p>
					</div>
				</div>
			</div>
		</div>
		<hr>
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
		<div>
			<div id="ajaxcontainer"
				class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3"></div>
			<!-- ajax게시판바로불러오기 -->
		</div>
	</main>

	<script type="text/javascript">
		let userId = "<%=uvo.getUserId()%>
		";
	</script>
	<script src="assets/js/profile.js"></script>

</body>
</html>