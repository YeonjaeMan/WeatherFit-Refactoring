<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

</head>
<body>
	<!-- 날씨 위젯과 상단 네비 분리 -->
	
	<%@ include file="includeNavi.jsp"%>



	<div id="container-notification"></div>

	<!-- 메인부분(토글버튼, 게시글 카드) -->
	<main>
		<div class="album bg-tertiary"></div>
		<div class="container">
			<!-- 토글 버튼 -->
			<p class="d-flex justify-content-center">
				<button type="button" class="btn btn-primary"
					data-bs-toggle="button">최신</button>
			</p>
			<!-- 게시글 카드 -->

			<div>
				<div id="ajaxcontainer"
					class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3"></div>
				<!-- ajax게시판바로불러오기 -->
			</div>

		</div>
	</main>

	
	
	
	
	
	

	<script src="assets/js/postview.js?ver=<%=System.currentTimeMillis()%>"></script>

</body>
</html>