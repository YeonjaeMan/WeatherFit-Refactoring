<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<link rel="stylesheet" href="assets/css/weatherwidget.css">


<style>
/* 헤더 색 */
#header {
	background-color: #C5DFF8;
	top: 0;
	padding: 5px;
}

/* 로고 */
#title-logo {
	width: 330px;
}

.title {
	margin: 0; /* Remove any margins */
	padding: 0; /* Remove any padding */
}

/* 메뉴이름 */

/* 버튼 */
.btn-blue {
	position: relative;
	border: none;
	display: inline-block;
	padding: 5px 10px;
	margin-top: 3px;
	border-radius: 8px;
	/* font-family: "paybooc-Light", sans-serif; */
	text-decoration: none;
	font-weight: 600;
	transition: 0.25s;
	background-color: #7895CB;
	color: white;
}

.btn-blue:hover {
	background-color: #DDE6ED;
	cursor: pointer;
	color: #4A55A2;
}

/* 로그아웃 버튼 */
#btn-logout {
	position: relative;
	border: none;
	display: inline-block;
	width: 80%;
	margin-top: 3px;
	border-radius: 8px;
	/* font-family: "paybooc-Light", sans-serif; */
	text-decoration: none;
	font-weight: 600;
	transition: 0.25s;
	background-color: #A0BFE0;
	color: white;
}

#btn-logout:hover {
	background-color: #7895CB;
	cursor: pointer;
}

/* 프로필(사용자) 아이콘 */
.dropdown-toggle {
	background-color: #7895CB;
	border: none;
}

.dropdown-toggle:hover {
	background-color: #DDE6ED;
	cursor: pointer;
	color: #4A55A2;
}

/* 드롭다운 */
.dropdown-menu {
	text-align: center;
}

/* 메뉴 */
.nav-link {
	color: #4A55A2 !important;
	text-decoration: none !important;
	text-decoration-line: none !important;
	font-weight: bolder !important;
	font-size: 18px;
}

.nav-link:hover {
	color: white !important;
	transform: scale(1.1);
	cursor: pointer;
}


</style>







</head>
<body>
	<!-- 헤더부분(날씨위젯, 사이트명, 내비바) -->
	<header id="header">
		<div
			class="container d-flex align-items-center justify-content-between mb-3">
			<!-- 날씨위젯 -->
			<a id="weather-link" href="#"
				class="d-flex align-items-around mb-2 mb-lg-0">
				<div id="weather-sky" class="weather-info"></div>
				<div id="weather-t1h" class="weather-info"></div>
			</a>
			<!-- 사이트 로고(수정함) -->
			<div class="title">
				<a href="gomain.do"><img id="title-logo"
					src="assets/images/logo/logo.png" alt=""></a>
			</div>
			<!-- 내비바 (수정함) -->
			<ul id="nav-menu" class="nav mt-3">

				<li class="nav-item"><a class="nav-link" id="redirecthome"
					href="gomain.do">홈</a></li>
				<c:choose>
					<c:when test="${member==null}">
						<li class="nav-item"><a class="nav-link"
							href="javascript:alert('회원가입ㄱ')">검색</a></li>
						<!--  <li class="nav-item"><a class="nav-link"
							href="javascript:alert('회원가입ㄱ')">팔로우</a></li> -->
						<li class="nav-item"><a class="nav-link"
							href="javascript:alert('회원가입ㄱ')">메시지</a></li>
						<li class="nav-item"><a class="nav-link"
							href="javascript:alert('회원가입ㄱ')">알림</a></li>

						<button type="button" class="btn-blue" data-bs-toggle="modal"
							data-bs-target="#loginModal">로그인</button>


					</c:when>

					<c:when test="${member!=null}">
						<li class="nav-item"><a class="nav-link" href="gosearch.do">검색</a></li>
						<!-- <li class="nav-item"><a class="nav-link" href="#">팔로우</a></li> -->
						<li class="nav-item"><a class="nav-link" href="gochat.do">메시지</a></li>
						<li class="nav-item"><a class="nav-link" href="#">알림</a></li>
						<div class="btn-group">
							<button type="button" class="btn-blue dropdown-toggle"
								data-bs-toggle="dropdown" aria-expanded>
								<i class="fa-solid fa-user"></i>
							</button>
							<ul class="dropdown-menu">

								<li><a class="dropdown-item" href="goprofile.do">프로필 확인</a></li>
								<li><a class="dropdown-item" data-bs-toggle="modal"
									data-bs-target="#profileUpdateModal">프로필 수정</a></li>













								<li><hr class="dropdown-divider"></li>
								<li><a id="btn-logout" class="dropdown-item" href="#">로그아웃</a></li>
							</ul>

						</div>
						<button type="button" id="btn-create-post" class="btn btn-primary round-button fixed-button" data-bs-toggle="modal" data-bs-target="#createPostModal">+</button>
					</c:when>

				</c:choose>
			</ul>

		</div>
	</header>
	
	
	<%@ include file="includeModal.jsp"%>
	<script src="assets/js/login.js?ver=<%=System.currentTimeMillis()%>"></script>
	<script
		src="assets/js/notification.js?ver=<%=System.currentTimeMillis()%>"></script>
	<script
		src="assets/js/weatherwidget.js?ver=<%=System.currentTimeMillis()%>"></script>
</body>
</html>