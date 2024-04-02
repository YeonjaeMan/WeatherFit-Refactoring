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
	padding-top: 10px;
	width: 100%; /* 헤더의 너비를 화면 전체 크기로 설정 */
	z-index: 1000;
}

/* 로고 */
#title-logo {
	width: 330px;
	margin-left: 11%;
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
	font-weight: bolder;
}

.btn-blue:hover {
	background-color: #DDE6ED;
	cursor: pointer;
	color: #4A55A2;
}

.btn-user {
	margin-left: 10px;
}

/* 탈퇴 버튼 */
#btn-deleteUser {
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
	background-color: rgb(245, 87, 87);
	color: white;
	font-weight: bolder;
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
	font-weight: bolder;
}

#btn-logout:hover {
	background-color: #7895CB;
	cursor: pointer;
}

/* 게시글 작성 버튼 */
#btn-create-post {
	position: fixed; /* 고정 위치 */
	bottom: 15%;
	right: 10%;
	width: 55px;
	height: 55px;
	font-size: 50px !important;
	justify-content: center;
	align-items: center;
	display: flex;
	border-radius: 50%;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	transition: 0.3s;
	z-index: 9999;
}

#plus {
	margin-bottom: 12px;
}

/* 프로필(사용자) 아이콘 */
button.btn-user.dropdown-toggle {
    background-color: transparent;
}

.dropdown-toggle {
	border: none;
	color: #4A55A2;
}

.dropdown-toggle:hover {
	cursor: pointer;
	color: white;
}

/* 드롭다운 */
.dropdown-menu {
	text-align: center;
}

#dropdown-user.show {
	transform: translate(-97px, 45px) !important;
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

#nav-menu {
	display: flex;
	align-items: center; /* 가로 방향으로 항목들을 중앙에 정렬 */
	justify-content: center; /* 세로 방향으로 항목들을 중앙에 정렬 (필요한 경우) */
	height: 100%; /* 부모 요소의 전체 높이를 차지하도록 설정 */
}

/* 사용자버튼 밑 닉네임 */
.user-container {
	display: flex;
	flex-direction: column; /* 세로 방향으로 요소들을 정렬 */
	align-items: center;
	justify-content: center;
}

#navi-nick {
	font-weight: bolder;
	margin-top: 5px;
	font-size: 14px;
	color: #4A55A2;
	padding-left: 10px;
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
							data-bs-toggle="modal" data-bs-target="#joinModal"
							href="javascript:alert('회원가입을 해주세요.')">메시지</a></li>
						<button type="button" class="btn-blue" data-bs-toggle="modal"
							data-bs-target="#loginModal">로그인</button>


					</c:when>

					<c:when test="${member!=null}">
						<li class="nav-item"><a class="nav-link" href="gochat.do">메시지</a></li>
						<div class="user-container">
							<div class="btn-group">
								<button type="button" class="btn-user dropdown-toggle"
									data-bs-toggle="dropdown" aria-expanded>
									<img src="assets/user_profile/${member.userProfileImg}" style="width: 40px; height: 40px; border-radius: 50%;"> 
								</button>
								<ul id="dropdown-user" class="dropdown-menu">
									<li><a class="dropdown-item"
										href="Profile.do?userId=${member.userId}">프로필 확인</a></li>
									<li><a class="dropdown-item" data-bs-toggle="modal"
										data-bs-target="#profileUpdateModal">프로필 수정</a></li>
									<li><hr class="dropdown-divider"></li>
									<li><a id="btn-logout" class="dropdown-item" href="#">로그아웃</a></li>
									<li><hr class="dropdown-divider"></li>
									<li><a id="btn-deleteUser" class="dropdown-item"
										href="DeleteUserInfo.do">회원탈퇴</a></li>
								</ul>
							</div>
							<div id="navi-nick">${member.userNick}</div>
						</div>
						<button type="button" id="btn-create-post"
							class="btn btn-blue round-button fixed-button"
							data-bs-toggle="modal" data-bs-target="#createPostModal">
							<span id="plus">+</span>
						</button>
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