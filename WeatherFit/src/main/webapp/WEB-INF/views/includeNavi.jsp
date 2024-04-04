<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="assets/css/weatherwidget.css">
<link rel="stylesheet" href="assets/css/includeNavi.css">

</head>
<body>
	<!-- 헤더부분(날씨위젯, 사이트명, 내비바) -->
	<header id="header">
		<div
			class="container d-flex align-items-center justify-content-between mb-3">
			<!-- 날씨위젯 -->
			<a id="weather-link" href="#"
				class="d-flex align-items-around mb-2 mb-lg-0"> <!-- 날씨 아이콘 -->
				<div id="weather-sky" class="weather-info"></div> <!-- 현재 기온 -->
				<div id="weather-t1h" class="weather-info"></div>
			</a>
			<!-- 사이트 로고 -->
			<div class="title">
				<a href="gomain.do"><img id="title-logo"
					src="assets/images/logo/logo.png" alt=""></a>
			</div>
			<!-- 내비바 메뉴 -->
			<ul id="nav-menu" class="nav mt-3">
				<li class="nav-item"><a class="nav-link" id="redirecthome"
					href="gomain.do">홈</a></li>
				<c:choose>
					<%-- 비회원은 메세지 클릭 시 회원가입 창으로 이동, 로그인버튼 --%>
					<c:when test="${member==null}">
						<li class="nav-item"><a class="nav-link"
							data-bs-toggle="modal" data-bs-target="#joinModal"
							href="javascript:alert('회원가입을 해주세요.')">메시지</a></li>
						<button type="button" class="btn-blue" data-bs-toggle="modal"
							data-bs-target="#loginModal">로그인</button>
					</c:when>
					<%-- 로그인한 회원은 메세지 클릭 시 메세지창으로 이동, 사용자 프로필사진 드롭다운 --%>
					<c:when test="${member!=null}">
						<li class="nav-item"><a class="nav-link" href="gochat.do">메시지</a></li>
						<div class="user-container">
							<!-- 프로필사진 드롬다운 버튼 -->
							<div class="btn-group">
								<button type="button" class="btn-user dropdown-toggle"
									data-bs-toggle="dropdown" aria-expanded>
									<!-- 사용자가 등록한 프로필사진 -->
									<img src="assets/user_profile/${member.userProfileImg}"
										style="width: 40px; height: 40px; border-radius: 50%;">
								</button>
								<!-- 드롭다운 -->
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
							<!-- 사용자 닉네임 -->
							<div id="navi-nick">${member.userNick}</div>
						</div>
						<!-- 게시글 작성 버튼 -->
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
		src="assets/js/weatherwidget.js?ver=<%=System.currentTimeMillis()%>"></script>
</body>
</html>