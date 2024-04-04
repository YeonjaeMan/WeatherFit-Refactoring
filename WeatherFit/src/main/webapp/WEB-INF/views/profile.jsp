<%@page import="com.smhrd.database.DAO"%>
<%@page import="com.smhrd.model.FollowingVO"%>
<%@page import="java.util.List"%>
<%@page import="com.smhrd.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>WeatherFit</title>

<%@ include file="includeHeaders.jsp"%>
<link rel="stylesheet" href="assets/css/profile.css">
<link rel="stylesheet" href="assets/css/postcard.css">

</head>
<body>
	<%@ include file="includeNavi.jsp"%>

	<%
	UserVO mvo = (UserVO) session.getAttribute("member");
	UserVO uvo = (UserVO) session.getAttribute("userProfileInfo");
	FollowingVO flvo = (FollowingVO) session.getAttribute("followingCheck");
	%>

	<main>
		<div id="profilepost-wrap">
			<div class="container">
				<div class="row">
					<div
						class="d-flex justify-content-center align-items-center mt-4 mb-4">
						<!-- 프로필 사진 -->
						<img class='img-profile rounded-circle'
							src='assets/user_profile/<%=uvo.getUserProfileImg()%>'>
						<title>프로필사진</title>
						<rect width="250px" height="250px"
							fill="var(--bs-secondary-color)" />
						</svg>

						<div id="nick-follow" class="d-flex flex-column ml-5">
							<div class="d-flex align-items-center">
								<!-- 닉네임 -->
								<h3 class="fw-bold" id="nickname">
									<%
									if (uvo.getUserNick() == null) {
										out.print(uvo.getUserId());
									} else {
										out.print(uvo.getUserNick());
									}
									%>
								</h3>
								<p>
									<c:choose>
										<c:when test="${member.userId==userProfileInfo.userId}">
											<a class='btn-blue' id='btn-edit' data-bs-toggle="modal"
												data-bs-target="#profileModal">편집</a>
										</c:when>

										<c:when test="${member.userId!=userProfileInfo.userId}">
											<c:choose>
												<c:when test="${followingCheck.followee==null}">
													<a class='btn-blue' id='btn-follow'
														href='InsertFollowing.do?follower=${member.userId}&followee=${userProfileInfo.userId}'>팔로우</a>
												</c:when>
												<c:when test="${followingCheck.followee!=null}">
													<a class='btn-blue' id='btn-follow'
														href='DeleteFollowing.do?follower=${member.userId}&followee=${userProfileInfo.userId}'>팔로우
														취소</a>
												</c:when>
											</c:choose>
										</c:when>

									</c:choose>

								</p>
							</div>
							<p id="profile-id">
								<%
								out.print(uvo.getUserId());
								%>
							</p>
							<!-- 프로필소개 -->
							<p id="profiletext">
								<%=uvo.getUserProfileInfo()%>
							</p>
						</div>
					</div>
				</div>
			</div>

			<hr class="line">
			<div class="d-flex justify-content-center">
				<div class="btn-photo btn-group align-items-center" role="group"
					aria-label="Basic radio toggle button group">
					<input type="radio" class="btn-check" name="btnradio"
						id="btnradio1" autocomplete="off" checked> <label
						id="profilepost-icon1" class="btn-photo" for="btnradio1">📸</label>
				</div>
			</div>
			<hr class="line" id="line2">
			<div>
				<div id="ajaxcontainer"
					class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-1"></div>
				<!-- ajax게시판바로불러오기 -->
			</div>
		</div>
	</main>

	<!-- 프로필 수정 모달 -->
	<div class="modal fade" id="profileModal" tabindex="-1"
		aria-labelledby="profileModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="profileModalLabel">프로필 수정</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<!-- 프로필 수정 폼 -->
					<form action="UpdateProfile.do" method="post"
						enctype="multipart/form-data">
						<div class="mb-3">
							<label for="profileImage" class="form-label">프로필 이미지</label> <input
								type="file" class="form-control" name="profileImg"
								id="profileImage">
						</div>
						<div class="mb-3">
							<label for="nickname" class="form-label">닉네임</label> <input
								type="text" class="form-control" name="userNick" id="nickname">
						</div>
						<div class="mb-3">
							<label for="profileIntro" class="form-label">프로필 소개</label>
							<textarea class="form-control" name="userProfileInfo"
								id="profileIntro" rows="3"></textarea>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary">저장</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script src="assets/js/profile.js"></script>
	<script src="assets/js/comment.js"></script>

</body>
</html>