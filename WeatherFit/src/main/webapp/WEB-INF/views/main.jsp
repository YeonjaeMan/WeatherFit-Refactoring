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



<style>


.btn-view {
	position: relative;
	display: inline;
	padding: 5px 10px;
	margin-top: 3px;
	border-radius: 8px;
	/* font-family: "paybooc-Light", sans-serif; */
	text-decoration: none;
	font-weight: 600;
	transition: 0.25s;
	color: #4A55A2;
	border-color: #7895CB;
}

.btn-view:hover {
	cursor: pointer;
	background-color: #7895CB;
	color: white;
}

/* 메인페이지 게시글 토글버튼 */
#btn-mainpost {
    margin-top: 30px;
}


/* 게시글 크기 고정.. 하고싶다 */
.fixed-height {
    height: 600px;
    overflow: auto;
  }


.postcard {
	border: none;
	border-radius: 0px;
}

.postcard:hover {
	cursor: pointer; /* 마우스 커서를 포인터로 변경 */
}


#hashtag {
	color: rgb(93, 93, 214);
	display: -webkit-box;
	-webkit-box-orient: vertical;
	overflow: hidden;
	text-overflow: ellipsis;
	-webkit-line-clamp: 1; /* 원하는 줄 수를 지정합니다. */
	line-clamp: 1; /* 일반적인 브라우저에 대한 지원 */
}

/* 게시글 내용 */
.card-text {
	display: -webkit-box;
	-webkit-box-orient: vertical;
	overflow: hidden;
	text-overflow: ellipsis;
	-webkit-line-clamp: 1; /* 원하는 줄 수를 지정합니다. */
	line-clamp: 1; /* 일반적인 브라우저에 대한 지원 */
}
</style>

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
				<button type="button" id="btn-mainpost" class="btn-blue"
					data-bs-toggle="button">최신</button>
			</p>
			<!-- 게시글 카드 -->

			<div>
				<div id="ajaxcontainer"
					class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-1"></div>
				<!-- ajax게시판바로불러오기 -->
			</div>

		</div>
	</main>

	<script src="assets/js/postview.js?ver=<%=System.currentTimeMillis()%>"></script>
	<script src="assets/js/comment.js?ver=<%=System.currentTimeMillis()%>"></script>


	<!-- <script src="assets/js/crawlingView.js"></script> -->

</body>
</html>