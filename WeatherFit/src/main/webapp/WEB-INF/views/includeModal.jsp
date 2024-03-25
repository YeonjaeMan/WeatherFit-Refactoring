<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 로그인 Modal -->

	<div class="modal fade" id="loginModal" tabindex="-1"
		aria-labelledby="loginModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="loginModalLabel">모달 헤더 입니다.</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<h4 class="fw-bold mb-0 fs-2 text-center w-100">로그인</h4>

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
						<div class="form-check text-start my-3"></div>
						<div class="mb-2"></div>
						<button class="btn btn-primary w-100 py-2" type="submit">로그인</button>
					</form>
				<div class = "mb-2">
					<!-- 회원가입 Modal -->
					<!-- Modal Trigger -->
					<button type="button" class="btn btn-primary"
						data-bs-toggle="modal" data-bs-target="#joinModal">회원가입</button>
					<!-- 회원가입 Modal -->
				</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-primary">의미없는버튼</button>
				</div>
			</div>
		</div>
	</div>



	<!-- Modal -->
	<div class="modal fade" id="joinModal" tabindex="-1"
		aria-labelledby="joinModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="joinModalLabel">회원가입</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
				
				
					<form action="Join.do" method="post" class="validation-form" novalidate>
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
							<label for="password2Modal">이름</label> <input
								type="text" class="form-control" id="password2Modal" name = "userName"
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
							<label for="addressModal">주소</label> <input type="text"
								class="form-control" id="addressModal" placeholder="서울특별시 강남구" name="userRegion"
								required>
							<div class="invalid-feedback">주소를 입력해주세요.</div>
						</div>

						<div class="mb-3">
							<label for="heightModal">키</label> <input type="text"
								class="form-control" id="heightModal" placeholder="cm" name="userHeight"  required>
							<div class="invalid-feedback">키를 입력해주세요.</div>
						</div>

						<div class="mb-3">
							<label for="weightModal">몸무게</label> <input type="text"
								class="form-control" id="weightModal" placeholder="kg" name="userWeight" required>
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





</body>
</html>