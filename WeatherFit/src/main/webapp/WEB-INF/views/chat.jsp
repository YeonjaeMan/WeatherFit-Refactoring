<%@page import="com.smhrd.model.UserVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>chat</title>

	<%@ include file="includeHeaders.jsp"%>
<link rel="stylesheet" href="assets/css/chat.css">
</head>
<body>
	<%@ include file="includeNavi.jsp"%>

	<main>
		<div id="menu-room" class="container-fluid d-flex p-3 bg-light">
			<ul class="nav mb-auto">
				<li id="btn-room-add">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-primary"
						data-bs-toggle="modal" data-bs-target="#createRoomModal">채팅상대추가</button>
				</li>
			</ul>
		</div>
	</main>

	<script>
		let userId = "${member.userId}";
	</script>
	<script src="assets/js/chat.js"></script>
</body>
</html>