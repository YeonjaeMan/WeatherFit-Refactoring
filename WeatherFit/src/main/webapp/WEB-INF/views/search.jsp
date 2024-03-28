<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 부트스트랩, 제이쿼리, search에서 폰트어썸 쓰나? 일단 인클루드함 -->
	<%@ include file="includeHeaders.jsp"%>
	
	
	<style>
	#search{
	width: 300px;
	margin-right:10px
	}
	
	div#ajaxcontainer {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 5%;
	
	
	</style>
	
	
	
</head>
<body>
	<!-- 부트스트랩, 제이쿼리, 폰트어썸 -->
	<%@ include file="includeNavi.jsp"%>
    
    
    <main>
        <form class="d-flex justify-content-center align-items-center mt-5" role="search" action="#" method="post">
            <input type="search" name="search" id="search" placeholder="검색어를 입력하세요.">
            <input class="btn-blue ml-2" type="submit" value="검색">
            <div class="d-flex flex-column">
                <ul id="search-list"></ul>
            </div>
        </form>
        <div id="ajaxcontainer"></div>
    </main>
	
    <script src="assets/js/search.js"></script>
</body>
</html>