<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>WeatherFit</title>

<!-- 부트스트랩, 제이쿼리, 폰트어썸 -->
<%@ include file="includeHeaders.jsp"%>

<link rel="stylesheet" href="assets/css/main.css">
<link rel="stylesheet" href="assets/css/imageupload.css">
<link rel="stylesheet" href="assets/css/postcard.css">

</head>
<body>
	<!-- 날씨 위젯과 상단 네비 분리 -->

	<%@ include file="includeNavi.jsp"%>

	<%
	UserVO uvo = (UserVO) session.getAttribute("member");
	%>

	<div id="container-notification"></div>

	<!-- 메인부분(토글버튼, 게시글 카드) -->
	<main>
		<div class="album bg-tertiary"></div>
		<div class="container">
			<!-- 토글 버튼 -->
			<div id="maintoggle" class="d-flex justify-content-center">
				<div class="btn-group" role="group"
					aria-label="Basic radio toggle button group">
					<input type="radio" class="btn-check btn-darkblue" id="recent"
						name="btnradio" autocomplete="off" checked> <label
						class="btn btn-outline-primary" for="recent">최근</label> <input
						type="radio" class="btn-check btn-darkblue" id="recommand"
						name="btnradio" autocomplete="off"> <label
						class="btn btn-outline-primary" for="recommand">추천</label>
				</div>
			</div>

			<!-- 게시글 카드 -->
			<div> 
				<div id="ajaxcontainer"
					class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4"></div>
				<!-- ajax게시판바로불러오기 -->
			</div>

		</div>
	</main>
	
	<script>
		let sessionUserId = "${member.userId}";
	</script>

	<script src="assets/js/postview.js?ver=<%=System.currentTimeMillis()%>"></script>
	<script src="assets/js/comment.js?ver=<%=System.currentTimeMillis()%>"></script>
	<script src="assets/js/imageupload.js?ver=<%=System.currentTimeMillis()%>"></script>

	<!-- <script src="assets/js/crawlingView.js"></script> -->

</body>
</html>