<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.smhrd.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WeatherFit</title>

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


					<form action="Login.do" method="post"
						onsubmit="return loginCheck()">
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


					<div class="mb-2"></div>
				</div>

			</div>
		</div>
	</div>



	<!-- 회원가입 Modal -->
	<div class="modal fade" id="joinModal" tabindex="-1"
		aria-labelledby="joinModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div id="modal-join" class="modal-content">
				<div class="modal-header">
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
							
							<!-- 주소찾기 창 닫기 -->
								<img src="//t1.daumcdn.net/postcode/resource/images/close.png"
									id="btnFoldWrap" onclick="foldDaumPostcode()" alt="접기 버튼">
							</div>

							<!-- 다음 주소 api 가져오기 -->
							<script	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
							<script>
								// 우변번호 찾기 버튼
								var element_wrap = document.getElementById('addressWrap');
								
								// iframe을 넣은 element를 안보이게 한다.
								function foldDaumPostcode() {
									element_wrap.style.display = 'none';
								}

								function execDaumPostcode() {
									// 현재 scroll 위치를 저장해놓는다.
									var currentScroll = Math.max(
											document.body.scrollTop,
											document.documentElement.scrollTop);
									new daum.Postcode(
											{
												oncomplete : function(data) {
													 // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

									                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
									                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
													var addr = ''; // 주소 변수
													var extraAddr = '';
													
													//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
													if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
														addr = data.roadAddress;
													} else { // 사용자가 지번 주소를 선택했을 경우(J)
														addr = data.jibunAddress;
													}

													// 변수 설정
													var postcode = data.zonecode;
													var address = addr;
													var detailAddress = document.getElementById("detailAddress").value;
													
													// 주소를 하나의 변수로 합치기
													var userRegion = postcode + ' ' + address + ' ' + detailAddress;

													// 우편번호와 주소 정보를 해당 필드에 넣는다.
													document.getElementById('postcode').value = data.zonecode;
													document.getElementById("address").value = addr;
													
													// 커서를 상세주소 필드로 이동한다.
													document.getElementById("detailAddress").focus();

													// userRegion 값을 숨겨진 input 요소에 설정
													document.getElementById("userRegionInput").value = userRegion;

													// iframe을 넣은 element를 안보이게 한다.
													element_wrap.style.display = 'none';
													
													// 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
													document.body.scrollTop = currentScroll;
												},
												// 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. 
												// iframe을 넣은 element의 높이값을 조정한다.
												onresize : function(size) {
													element_wrap.style.height = size.height
															+ 'px';
												},
												width : '100%',
												height : '100%'
											}).embed(element_wrap);

									// iframe을 넣은 element를 보이게 한다.
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
		aria-labelledby="postModalLabel" aria-hidden="true"
		style="max-height: 90vh; overflow: auto;">
		<div class="modal-dialog modal-xl">
			<div id="modal-postdetail" class="modal-content">
				<div class="modal-body" id="cmtModal-css">
					<div class="container-fluid">
						<div class="row">
							<!-- 왼쪽 영역 -->
							<div class="col-md-6">
								<div class="row">
									<div
										class="col-12 mb-2 display-inline d-flex justify-content-between"
										id="cmt-user">
										<svg id="message" xmlns="http://www.w3.org/2000/svg"
											viewBox="0 0 512 512">
					                    	<path
												d="M64 112c-8.8 0-16 7.2-16 16v22.1L220.5 291.7c20.7 17 50.4 17 71.1 0L464 150.1V128c0-8.8-7.2-16-16-16H64zM48 212.2V384c0 8.8 7.2 16 16 16H448c8.8 0 16-7.2 16-16V212.2L322 328.8c-38.4 31.5-93.7 31.5-132 0L48 212.2zM0 128C0 92.7 28.7 64 64 64H448c35.3 0 64 28.7 64 64V384c0 35.3-28.7 64-64 64H64c-35.3 0-64-28.7-64-64V128z" />
					                    </svg>
									</div>
									<img class="col-12" id="cmt-img" src=""></img>
								</div>
							</div>
							<!-- 오른쪽 영역 -->
							<div class="col-md-6">
								<div class="flex-container">
									<div class="col-12" id="cmt-content"></div>
									<button type="button" id="btn-postclose" class="btn-close"
										data-bs-dismiss="modal" aria-label="Close"></button>
								</div>
								<div class="col-12" id="cmt-hashtag"
									style="height: 10%; border-bottom: none;"></div>
								<div id="like">
									<!-- <i class="fa-regular fa-heart" id="like-heart"></i>   -->

								</div>
								<div class="col-12">
									<span id="comment-txt">댓글</span>
									<div class="comment-section">
										<div>

											<div id="cmt-cmt" style="overflow: auto; max-height: 360px;"></div>
											<c:if test="${member!=null}">
												<div class="card-body" style="margin-top: auto;">
													<form class="form-horizontal" onsubmit='return false;'>

														<div id="cmtinput-row" class="row">
															<div id="comment-input" class="form-group col-sm-10">
																<input class="form-control input-sm" id="newReplyText"
																	type="text" name="cmtContent" placeholder="댓글을 입력하세요.">
															</div>

															<div id="cmtsubmit-wrap" class="form-group col-sm-2">
																<button type="submit"
																	class="btn btn-blue btn-sm btn-block replyAddBtn"
																	id="insert-cmt">등록</button>
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
					<h3 class="modal-title fw-bold mb-0 text-center w-100"
						id="exampleModalLabel">게시글 작성</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<!-- 게시글 작성 폼 -->
					<form id="imageUploadForm" action="CreatePost.do" method="post"
						enctype="multipart/form-data">
						<div class="mb-3">
							<label for="imageUpload" class="form-label">이미지 업로드:</label> <input
								class="form-control" type="file" id="imageUpload" name="postImg"
								accept="image/*"><br>
							<!-- 이미지 미리보기를 위한 태그 -->
							<img id="imagePreview" alt="Image Preview"
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
						<div style="text-align: right;">
							<button type="submit" class="btn btn-blue justify-content-end">작성하기</button>
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
					<h3 class="modal-title id="updateModalLabel" fw-boldmb-0text-centerw-100">게시글
						수정</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<!-- 게시글 작성 폼 -->
					<form id="update-imageUploadForm" action="UpdatePost.do"
						method="post" enctype="multipart/form-data">
						<div class="mb-3">
							<label for="imageUpload" class="form-label">이미지 업로드:</label> <input
								class="form-control" type="file" id="update-imageUpload"
								name="postImg" accept="image/*"><br>
							<!-- 이미지 미리보기를 위한 태그 -->
							<img id="update-imagePreview" alt="Image Preview"
								class="img-fluid mx-auto d-block"
								style="display: none; max-width: 200px; max-height: 200px;">
						</div>
						<div class="mb-3">
							<label for="postContent" class="form-label">내용:</label>
							<textarea class="form-control" id="update-postContent"
								name="postContent" rows="3"></textarea>
						</div>
						<fieldset class="mb-3">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" name="postTemp"
									value="" id="update-postTemp"> <label
									class="form-check-label" for="postTemp"> 현재 기온 표기 </label>
							</div>
						</fieldset>
						<div class="mb-3">
							<label for="hashtagInput" class="form-label">해시태그:</label> <input
								type="text" class="form-control" id="update-hashtagInput"
								name="hashTags" placeholder="#해시태그">
							<ul id="update-hashtagList"></ul>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary">작성하기</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script src="assets/js/validation.js"></script>

</body>
</html>