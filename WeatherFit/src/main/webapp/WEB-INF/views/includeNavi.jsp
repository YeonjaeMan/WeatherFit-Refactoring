<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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

				<li class="nav-item"><a class="nav-link" id="redirecthome" href = "gomain.do">홈</a></li>
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
						
						<button type="button" class="btn btn-primary" data-bs-toggle="modal"
						data-bs-target="#loginModal">로그인</button>
						
						
					</c:when>

					<c:when test="${member!=null}">
						<li class="nav-item"><a class="nav-link" href="gosearch.do">검색</a></li>
						<!-- <li class="nav-item"><a class="nav-link" href="#">팔로우</a></li> -->
						<li class="nav-item"><a class="nav-link" href="gochat.do">메시지</a></li>
						<li class="nav-item"><a class="nav-link" href="#">알림</a></li>
						<button type="button" class="btn btn-primary" id="btn-logout">로그아웃</button>
					</c:when>

				</c:choose>
			</ul>

		</div>
	</header>
	<%@ include file="includeModal.jsp"%>
	<script src="././assets/js/login.js?ver=<%=System.currentTimeMillis()%>"></script>
	<script src="././assets/js/notification.js?ver=<%=System.currentTimeMillis()%>"></script>
	<script src="././assets/js/weatherwidget.js?ver=<%=System.currentTimeMillis()%>"></script>
</body>
</html>