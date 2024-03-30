<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.smhrd.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

/* 작성자 닉네임 영역 스타일 */
#cmt-user {
	background-color: #e9ecef; /* 배경 색상 */
	padding: 10px; /* 여백 */
	border-bottom: 1px solid #dee2e6; /* 하단 경계선 */
	font-weight: bold; /* 글꼴 굵기 */
	text-align: center; /* 텍스트 가운데 정렬 */
}

/* 사진 영역 스타일 */
#cmt-img {
	background-color: #fff; /* 배경 색상 */
	padding: 10px; /* 여백 */
	border-bottom: 1px solid #dee2e6; /* 하단 경계선 */
	text-align: center; /* 텍스트 가운데 정렬 */
}

/* 게시글 내용 영역 스타일 */
#cmt-content {
	background-color: #fff; /* 배경 색상 */
	padding: 10px; /* 여백 */
	border-bottom: 1px solid #dee2e6; /* 하단 경계선 */
	overflow-y: auto; /* 내용이 넘칠 경우 스크롤 */
}

/* 해시태그 영역 스타일 */
#cmt-hashtag {
	background-color: #fff; /* 배경 색상 */
	padding: 10px; /* 여백 */
	border-bottom: 1px solid #dee2e6; /* 하단 경계선 */
	text-align: center; /* 텍스트 가운데 정렬 */
}

/* 댓글 영역 스타일 */
#cmt-cmt {
	background-color: #fff; /* 배경 색상 */
	padding: 10px; /* 여백 */
	overflow-y: auto; /* 내용이 넘칠 경우 스크롤 */
}

/* 스크롤바 스타일 */
#cmt-content::-webkit-scrollbar, #cmt-cmt::-webkit-scrollbar {
	width: 5px; /* 스크롤바 너비 */
}

#cmt-content::-webkit-scrollbar-thumb, #cmt-cmt::-webkit-scrollbar-thumb
	{
	background: #888; /* 스크롤바 색상 */
	border-radius: 5px; /* 스크롤바 모서리 둥글게 */
}

#cmt-content::-webkit-scrollbar-thumb:hover, #cmt-cmt::-webkit-scrollbar-thumb:hover
	{
	background: #555; /* 스크롤바 색상 (호버 시) */
}
</style>


</head>
<link rel="stylesheet" href="assets/css/address.css">
<link rel="stylesheet" href="assets/css/modal.css">






<body>
	<!-- 로그인 Modal -->

	<div class="modal fade" id="loginModal" tabindex="-1"
		aria-labelledby="loginModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div id="modal-login" class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title fw-bold mb-0 text-center w-100">회원 로그인</h3>
					<!-- 수정함  <h5 class="modal-title" id="loginModalLabel">모달 헤더 입니다.</h5> -->
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">


					<form action="Login.do" method="post" onsubmit="return loginCheck()">
						<div></div>
						<div class="form-floating">
							<input type="text" class="form-control" id="floatingInput"
								name="email_id" placeholder="id" required> <label
								for="floatingInput">아이디</label>
							<div class="invalid-feedback">아이디를 입력해주세요.</div>
						</div>
						<div class="form-floating">
							<input type="password" class="form-control" id="floatingPassword"
								name="email_pw" placeholder="Password" required> <label
								for="floatingPassword">비밀번호</label>
							<div class="invalid-feedback">비밀번호를 입력해주세요.</div>
						</div>
						<div class="form-check text-start mt-1 d-flex justify-content-end">
							<button type="button" id="btn-join" class="btn"
								data-bs-toggle="modal" data-bs-target="#joinModal">회원가입</button>
						</div>
						<div class="mb-2"></div>
						<button class="btn-blue w-100 py-2" type="submit">로그인</button>
					
					</form>
					
					
					<div class="mb-2">
						<!-- 회원가입 Modal -->
						<!-- Modal Trigger -->

						<!-- 회원가입 Modal -->
					</div>
				</div>

			</div>
		</div>
	</div>



	<!-- 회원가입 Modal -->
	<div class="modal fade" id="joinModal" tabindex="-1"
		aria-labelledby="joinModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" id="">
					<h3 class="modal-title fw-bold mb-0 text-center w-100">회원가입</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">


					<form action="Join.do" method="post" class="validation-form"
						novalidate>
						<div class="mb-3">
							<label for="idModal">아이디</label> <input type="text"
								class="form-control" id="idModal" name="userId" required>
							<div class="invalid-feedback">아이디를 입력해주세요.</div>
						</div>

						<div class="mb-3">
							<label for="passwordModal">비밀번호</label> <input type="password"
								class="form-control" id="passwordModal" name="userPw" required>
							<div class="invalid-feedback">비밀번호를 입력해주세요.</div>
						</div>

						<div class="mb-3">
							<div class="row">
								<div class="col">
									<label for="password2Modal">이름</label> <input type="text"
										class="form-control" id="password2Modal" name="userName"
										required>
									<div class="invalid-feedback">이름을 입력해주세요.</div>
								</div>
								<div class="col">
									<label for="nicknameModal">닉네임</label> <input type="text"
										class="form-control" id="nicknameModal" name="userNick"
										required>
									<div class="invalid-feedback">닉네임을 입력해주세요.</div>
								</div>
							</div>
						</div>

						<fieldset class="mb-3">
							<label>성별</label>
							<div class="row">
								<div class="col">
									<div class="form-check">
										<input class="form-check-input" type="radio" name="userGender"
											id="manModal" value="남자" required> <label
											class="form-check-label" for="manModal">남자</label>
									</div>
								</div>
								<div class="col">
									<div class="form-check">
										<input class="form-check-input" type="radio" name="userGender"
											id="womanModal" value="여자" required> <label
											class="form-check-label" for="womanModal">여자</label>
									</div>
								</div>
							</div>
						</fieldset>

						<div class="mb-3">
							<label for="addressModal">주소</label><br>
							<div id="addressWrap">
								<img src="//t1.daumcdn.net/postcode/resource/images/close.png"
									id="btnFoldWrap" onclick="foldDaumPostcode()" alt="접기 버튼">
							</div>

							<script
								src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
							<script>
								var element_wrap = document
										.getElementById('addressWrap');

								function foldDaumPostcode() {
									element_wrap.style.display = 'none';
								}

								function execDaumPostcode() {
									var currentScroll = Math.max(
											document.body.scrollTop,
											document.documentElement.scrollTop);
									new daum.Postcode(
											{
												oncomplete : function(data) {
													var addr = '';
													var extraAddr = '';

													if (data.userSelectedType === 'R') {
														addr = data.roadAddress;
													} else {
														addr = data.jibunAddress;
													}

													// 주소를 하나의 변수로 합치기
													var postcode = data.zonecode;
													var address = addr;
													var detailAddress = document
															.getElementById("detailAddress").value;
													var userRegion = postcode
															+ ' ' + address
															+ ' '
															+ detailAddress;

													document
															.getElementById('postcode').value = data.zonecode;
													document
															.getElementById("address").value = addr;
													document.getElementById(
															"detailAddress")
															.focus();

													// userRegion 값을 숨겨진 input 요소에 설정
													document
															.getElementById("userRegionInput").value = userRegion;

													element_wrap.style.display = 'none';

													document.body.scrollTop = currentScroll;
												},
												onresize : function(size) {
													element_wrap.style.height = size.height
															+ 'px';
												},
												width : '100%',
												height : '100%'
											}).embed(element_wrap);

									element_wrap.style.display = 'block';
								}
							</script>
							<input type="text" id="postcode" placeholder="우편번호" readonly
								required> <input type="button"
								onclick="execDaumPostcode()" value="우편번호 찾기"><br> <input
								type="text" id="address" placeholder="주소" readonly required>
							<input type="text" id="detailAddress" placeholder="상세주소" required>
							<div class="invalid-feedback">주소를 입력해주세요.</div>

							<!-- 사용자 지역을 전달할 숨겨진 input 요소 -->
							<input type="hidden" id="userRegionInput" name="userRegion">
						</div>

						<div class="mb-3">
							<div class="row">
								<div class="col">
									<label for="heightModal">키</label> <input type="text"
										class="form-control" id="heightModal" placeholder="cm"
										name="userHeight" required>
									<div class="invalid-feedback">키를 입력해주세요.</div>
								</div>
								<div class="col">
									<label for="weightModal">몸무게</label> <input type="text"
										class="form-control" id="weightModal" placeholder="kg"
										name="userWeight" required>
									<div class="invalid-feedback">몸무게를 입력해주세요.</div>
								</div>
							</div>
						</div>


						<div class="mb-3 form-check justify-content-end">
							<input type="checkbox" class="form-check-input"
								id="agreementModal" required> <label
								class="form-check-label" for="agreementModal">개인정보 수집 및
								이용에 동의합니다.</label>
						</div>

						<div>
							<button type="submit" class="btn btn-blue w-100">가입하기</button>
						</div>
					</form>


				</div>
			</div>
		</div>
	</div>

	<!-- 게시물 상세보기 모달 -->
	<div class="modal fade" id="postModal" tabindex="-1"
		aria-labelledby="postModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">
				<div class="modal-body" id="cmtModal-css">
					<div class="container-fluid">
						<div class="row">
							<!-- 왼쪽 영역 -->
							<div class="col-md-6">
								<div class="row">
									<div class="col-12 mb-2" id="cmt-user" style="height: 20%;">작성자
										닉네임 영역</div>
									<div class="col-12" id="cmt-img" style="height: 820px;">사진
										영역</div>
								</div>
							</div>
							<!-- 오른쪽 영역 -->
							<div class="col-md-6">
								<div class="row">
									<div class="col-12 mb-2" id="cmt-content" style="height: 20%;">게시글
										내용 영역</div>
									<div class="col-12 mb-2" id="cmt-hashtag" style="height: 20%;">해시태그
										영역</div>
									<div class="col-12" id="cmt-cmt" style="height: 60%;"></div>
										<div class="comment-section"></div>
									<c:if test="${member!=null}">
										<div class="card-body">
											<form class="form-horizontal" onsubmit='return false;'>
												<div class="row">
													<div class="form-group col-sm-8">
														<input class="form-control input-sm" id="newReplyText"
															type="text" name="cmtContent" placeholder="댓글 입력...">
													</div>
													<div class="form-group col-sm-2">
														<input class="form-control input-sm" id="newReplyWriter"
															type="text" value="${member.userId}">
													</div>
													<div class="form-group col-sm-2">
														<button type="submit" class="btn btn-blue btn-sm btn-block replyAddBtn" id="insert-cmt">
															<i class="fa fa-save"></i> 저장
														</button>
													</div>
												</div>
											</form>
										</div>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 채팅방 생성 모달 -->
	<div class="modal fade" id="createRoomModal" tabindex="-1"
		role="dialog" aria-labelledby="createRoomModalLabel"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title fw-bold mb-0 text-center w-100"
						id="exampleModalLabel">채팅상대입력</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form action="CreateRoom.do" method="post">
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon1">@UserId</span>
						</div>
						<input type="text" name="receiveUserId" class="form-control"
							placeholder="UserId" aria-label="Username"
							aria-describedby="basic-addon1"> <input type="hidden"
							name="sendUserId" value="${member.userId}">
					</div>
					<div>
						<button class="btn btn-blue">채팅방생성</button>
					</div>
				</form>
			</div>
		</div>
	</div>







	<!-- 회원정보수정 모달 -->
	<div class="modal fade" id="profileUpdateModal" tabindex="-1"
		aria-labelledby="profileUpdateModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div id="modal-update" class="modal-content">
				<div class="modal-header">
					<h3 id="profileUpdateModalLabel"
						class="modal-title fw-bold mb-0 text-center w-100">프로필 업데이트</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<!-- 입력 필드 -->
					<form action="UpdateUserInfo.do" method="post">
						<div class="mb-3">
							<label for="current-password" class="col-form-label">현재
								비밀번호:</label> <input type="password" class="form-control"
								id="current-password">
						</div>
						<div class="mb-1">
							<label for="new-password" class="col-form-label">새 비밀번호:</label>
							<input type="password" class="form-control" id="new-password"
								name="new-password">
						</div>
						<div class="mb-3">
							<label for="confirm-password" class="col-form-label">새
								비밀번호 확인:</label> <input type="password" class="form-control"
								id="confirm-password">
						</div>
						<div class="mb-3">
							<label for="new-nickname" class="col-form-label">새 닉네임:</label> <input
								type="text" class="form-control" id="new-nickname"
								name="new-nickname">
						</div>
						<div class="mb-3">
							<label for="new-region" class="col-form-label">새 주소:</label> <input
								type="text" class="form-control" id="new-region"
								name="new-region">
						</div>
						<div class="mb-3">
							<label for="new-heigth" class="col-form-label">새 키:</label> <input
								type="text" class="form-control" id="new-height"
								name="new-height">
						</div>
						<div class="mb-3">
							<label for="new-weight" class="col-form-label">새 몸무게:</label> <input
								type="text" class="form-control" id="new-weight"
								name="new-weight">
						</div>
						<!-- 추가 필드는 비슷한 방식으로 구성 -->
						<div>
							<button type="submit" class="btn btn-blue w-100">저장하기</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- 게시글 작성 모달 -->
	<div class="modal fade" id="createPostModal" tabindex="-1"
		aria-labelledby="createPostModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title id="exampleModalLabel" fw-bold mb-0 text-center w-100">게시글 작성</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<!-- 게시글 작성 폼 -->
					<form id="imageUploadForm" action="CreatePost.do" method="post"
						enctype="multipart/form-data">
						<div class="mb-3">
							<label for="imageUpload" class="form-label">이미지 업로드:</label> <input
								class="form-control" type="file" id="imageUpload1" name="postImg"
								accept="image/*"><br>
							<!-- 이미지 미리보기를 위한 태그 -->
							<img id="imagePreview1" alt="Image Preview"
								class="img-fluid mx-auto d-block"
								style="display: none; max-width: 200px; max-height: 200px;">
						</div>
						<div class="mb-3">
							<label for="postContent" class="form-label">내용:</label>
							<textarea class="form-control" id="postContent"
								name="postContent" rows="3"></textarea>
						</div>
						<fieldset class="mb-3">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" name="postTemp"
									value="" id="postTemp"> <label class="form-check-label"
									for="postTemp"> 현재 기온 표기 </label>
							</div>
						</fieldset>
						<div class="mb-3">
							<label for="hashtagInput" class="form-label">해시태그:</label> <input
								type="text" class="form-control" id="hashtagInput"
								name="hashTags" placeholder="#해시태그">
							<ul id="hashtagList"></ul>
						</div>
						<div>
							<button type="submit" class="btn btn-blue justify-content-end">작성하기</button>
							<!-- loading 이미지 -->
							<div id="loadingAnimation" class="hidden">
								<img src="loading.gif" alt="Loading...">
								<p>Loading...</p>
							</div>
							<div id="imageContainer" class="hidden">
								<img id="uploadedImage" src="" alt="Uploaded Image">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- 게시글 수정 모달 -->
	<div class="modal fade" id="updatePostModal" tabindex="-1"
		aria-labelledby="updatePostModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title id="updateModalLabel" fw-bold mb-0 text-center w-100">게시글 수정</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<!-- 게시글 작성 폼 -->
					<form id="imageUploadForm" action="UpdatePost.do" method="post"
						enctype="multipart/form-data">
						<div class="mb-3">
							<label for="imageUpload" class="form-label">이미지 업로드:</label> <input
								class="form-control" type="file" id="imageUpload2" name="postImg"
								accept="image/*"><br>
							<!-- 이미지 미리보기를 위한 태그 -->
							<img id="imagePreview2" alt="Image Preview"
								class="img-fluid mx-auto d-block"
								style="display: none; max-width: 200px; max-height: 200px;">
						</div>
						<div class="mb-3">
							<label for="postContent" class="form-label">내용:</label>
							<textarea class="form-control" id="postContent"
								name="postContent" rows="3"></textarea>
						</div>
						<fieldset class="mb-3">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" name="postTemp"
									value="" id="postTemp"> <label class="form-check-label"
									for="postTemp"> 현재 기온 표기 </label>
							</div>
						</fieldset>
						<div class="mb-3">
							<label for="hashtagInput" class="form-label">해시태그:</label> <input
								type="text" class="form-control" id="hashtagInput"
								name="hashTags" placeholder="#해시태그">
							<ul id="hashtagList"></ul>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary">작성하기</button>
							<!-- loading 이미지 -->
							<div id="loadingAnimation" class="hidden">
								<img src="loading.gif" alt="Loading...">
								<p>Loading...</p>
							</div>
							<div id="imageContainer" class="hidden">
								<img id="uploadedImage" src="" alt="Uploaded Image">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<script src="assets/js/validation.js"></script>
	
</body>
</html>