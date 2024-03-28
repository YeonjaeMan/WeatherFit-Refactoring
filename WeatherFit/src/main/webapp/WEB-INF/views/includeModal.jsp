<%@page import="com.smhrd.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="assets/css/address.css">
<link rel="stylesheet" href="assets/css/modal.css">

<body>
	<!-- 로그인 Modal -->

	<div class="modal fade" id="loginModal" tabindex="-1"
		aria-labelledby="loginModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="fw-bold mb-0 fs-2 text-center w-100">로그인</h4>
					<!-- 수정함  <h5 class="modal-title" id="loginModalLabel">모달 헤더 입니다.</h5> -->
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					

					<form action="Login.do" method="post">
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
						<div class="form-check text-start my-3">
							<button type="button" class="btn btn-primary"
							data-bs-toggle="modal" data-bs-target="#joinModal">회원가입</button>
						</div>
						<div class="mb-2"></div>
						<button class="btn btn-primary w-100 py-2" type="submit">로그인</button>
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



	<!-- Modal -->
	>
	<div class="modal fade" id="joinModal" tabindex="-1"
		aria-labelledby="joinModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" id = "">
					<h5 class="modal-title" id="joinModalLabel">회원가입</h5>
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
							<label for="password2Modal">이름</label> <input type="text"
								class="form-control" id="password2Modal" name="userName"
								required>
							<div class="invalid-feedback">이름을 입력해주세요.</div>
						</div>

						<div class="mb-3">
							<label for="nicknameModal">닉네임</label> <input type="text"
								class="form-control" id="nicknameModal" name="userNick" required>
							<div class="invalid-feedback">닉네임을 입력해주세요.</div>
						</div>

						<fieldset class="mb-3">
							<legend>성별</legend>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="userGender"
									id="manModal" value="남자" required> <label
									class="form-check-label" for="manModal">남자</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="userGender"
									id="womanModal" value="여자" required> <label
									class="form-check-label" for="womanModal">여자</label>
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
								                    var detailAddress = document.getElementById("detailAddress").value;
								                    var userRegion = postcode + ' ' + address + ' ' + detailAddress;

													document.getElementById('postcode').value = data.zonecode;
													document.getElementById("address").value = addr;
													document.getElementById("detailAddress").focus();

								                    // userRegion 값을 숨겨진 input 요소에 설정
								                    document.getElementById("userRegionInput").value = userRegion;
													
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
							<label for="heightModal">키</label> <input type="text"
								class="form-control" id="heightModal" placeholder="cm"
								name="userHeight" required>
							<div class="invalid-feedback">키를 입력해주세요.</div>
						</div>

						<div class="mb-3">
							<label for="weightModal">몸무게</label> <input type="text"
								class="form-control" id="weightModal" placeholder="kg"
								name="userWeight" required>
							<div class="invalid-feedback">몸무게를 입력해주세요.</div>
						</div>

						<div class="mb-3 form-check">
							<input type="checkbox" class="form-check-input"
								id="agreementModal" required> <label
								class="form-check-label" for="agreementModal">개인정보 수집 및
								이용에 동의합니다.</label>
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">닫기</button>
							<button type="submit" class="btn btn-primary">가입하기</button>
						</div>
					</form>
					
						
				</div>
			</div>
		</div>
	</div>


	<!-- 게시물 Modal -->
	<div class="modal fade" id="postModal" tabindex="-1"
		aria-labelledby="postModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="postModalLabel">게시물 상세</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<!-- 가져올 게시물 내용을 생각하자 -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">닫기</button>
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
					<h5 class="modal-title" id="exampleModalLabel">채팅상대입력</h5>
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
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">닫기</button>
						<!-- <input type="submit" class="btn btn-primary"
							value="채팅방생성"> -->
						<button class="btn btn-primary">채팅방생성</button>
					</div>
				</form>
			</div>
		</div>
	</div>







	<!-- 회원정보수정 모달 -->
	<div class="modal fade" id="profileUpdateModal" tabindex="-1"
		aria-labelledby="profileUpdateModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="profileUpdateModalLabel">프로필 업데이트</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<!-- 입력 필드 -->
					<form>
						<div class="mb-3">
							<label for="current-password" class="col-form-label">현재
								비밀번호:</label> <input type="password" class="form-control"
								id="current-password">
						</div>
						<div class="mb-3">
							<label for="new-password" class="col-form-label">새 비밀번호:</label>
							<input type="password" class="form-control" id="new-password">
						</div>
						<div class="mb-3">
							<label for="confirm-password" class="col-form-label">새
								비밀번호 확인:</label> <input type="password" class="form-control"
								id="confirm-password">
						</div>
						<!-- 추가 필드는 비슷한 방식으로 구성 -->
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-primary">저장하기</button>
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
					<h5 class="modal-title" id="exampleModalLabel">게시글 작성</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<!-- 게시글 작성 폼 -->
					<form action="CreatePost.do" method="post"
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
							<legend>현재 기온을 표기할까요?(default:기온 표기 x)</legend>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="postOption"
									id="option2" value="option2"> <label
									class="form-check-label" for="option2"> 기온 표기 o </label>
							</div>
						</fieldset>
						<div class="mb-3">
							<label for="hashtagInput" class="form-label">해시태그:</label> <input
								type="text" class="form-control" id="hashtagInput"
								name="hashTags" placeholder="#해시태그">
							<ul id="hashtagList"></ul>
						</div>
						<input type="hidden" name="userId" value="${member.userId}">
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">닫기</button>
							<button type="submit" class="btn btn-primary">작성하기</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$("#imageUpload").on("change", function(event) {
			let file = event.target.files[0];
			let reader = new FileReader();

			reader.onload = function(e) {
				$("#imagePreview").attr("src", e.target.result).show();
			}

			reader.readAsDataURL(file);
		});

		// 해시태그 배열
		let hashtags = [];

		$(document)
				.ready(
						function(event) {
							/* 			  $('#hashtagInput').on('input', function() {
							 let inputVal = $(this).val();
							 let hashtags = inputVal.split(/\s+/).filter(function(tag) {
							 return tag.startsWith('#');
							 });
							 console.log(hashtags); // 콘솔에 분리된 해시태그 배열 출력

							 }); */

							$("#hashtagInput")
									.on(
											"keyup",
											function(event) {
												let inputVal = $(this).val();
												if (event.which == 32) { // 스페이스바 키 코드는 32입니다.
													let hashtagValue = $(this)
															.val().trim();
													if (hashtagValue) { // 입력값이 비어있지 않은 경우에만 실행
														$('#hashtagList')
																.append(
																		'<li>'
																				+ hashtagValue
																				+ '<button class="remove">X</button></li>');
														$(this).val(''); // 입력 필드 초기화
														let hashtag = inputVal
																.split(/\s+/)
																.filter(
																		function(
																				tag) {
																			return tag
																					.startsWith('#');
																		});
														hashtags.push(hashtag);
														console.log(hashtags); // 콘솔에 분리된 해시태그 배열 출력
													}
												}
											});

							// 해시태그 삭제 기능
							$('#hashtagList').on('click', '.remove',
									function() {
										$(this).parent().remove(); // 해당 해시태그 삭제
										console.log(this);
										removeHashtag(this);
									});
						});

		// 해시태그 삭제 함수
		function removeHashtag(tagToRemove) {
			hashtags = hashtags.filter(function(tag) {
				return tag !== tagToRemove;
			});
			console.log(hashtags); // 업데이트된 해시태그 배열 출력
		}

		// 예시 사용법: '#exampleTag' 해시태그 삭제
		// removeHashtag('#exampleTag');
	</script>



</body>
</html>