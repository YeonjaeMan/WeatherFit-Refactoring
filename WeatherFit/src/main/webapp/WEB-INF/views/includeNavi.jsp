<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	
	body {
      padding-bottom: 3rem;
    }
	
    .fixed-button {
      position: fixed;
      bottom: 25px;
      right: 25px;
      z-index: 999;
      background-color: #0055FF;
      color: white; /* 텍스트 색상 */
      box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2); /* 그림자 효과 */
    }

    .round-button {
      width: 50px;
      height: 50px;
      border-radius: 25px; /* 동그라미 모양 */
      font-size: 30px; /* 아이콘 크기 */
      text-align: center; /* 텍스트 중앙 정렬 */
      padding: 0; /* 기본 패딩 제거 */
    }
</style>
</head>
<body>
	<!-- 헤더부분(날씨위젯, 사이트명, 내비바) -->
	<header class="p-3 bg-light">
		<div
			class="container d-flex align-items-center justify-content-between">
			<!-- 날씨위젯 -->
			<a id="weather-link" href="#"
				class="d-flex align-items-around mb-2 mb-lg-0 text-decoration-none">
				<div id="weather-sky"></div>
				<div id="weather-t1h"></div>
			</a>
			<!-- 사이트명 -->
			<div class="title">WeatherFit</div>
			<!-- 내비바 -->
			<ul class="nav nav-underline">

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

						<button type="button" class="btn btn-primary"
							data-bs-toggle="modal" data-bs-target="#loginModal">로그인</button>


					</c:when>

					<c:when test="${member!=null}">
						<li class="nav-item"><a class="nav-link" href="gosearch.do">검색</a></li>
						<!-- <li class="nav-item"><a class="nav-link" href="#">팔로우</a></li> -->
						<li class="nav-item"><a class="nav-link" href="gochat.do">메시지</a></li>
						<li class="nav-item"><a class="nav-link" href="#">알림</a></li>
						<div class="btn-group">
							<button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded>
								<i class="fa-solid fa-user"></i>
							</button>
							<ul class="dropdown-menu">
							    <li><a class="dropdown-item" href="goprofile.do">프로필 확인</a></li>
							    <li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#profileUpdateModal">프로필 수정</a></li>
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
	<script src="assets/js/notification.js?ver=<%=System.currentTimeMillis()%>"></script>
	<script src="assets/js/weatherwidget.js?ver=<%=System.currentTimeMillis()%>"></script>
</body>
</html>