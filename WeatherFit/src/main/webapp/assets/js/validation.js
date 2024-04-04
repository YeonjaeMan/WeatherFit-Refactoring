// 로그인 유효성 검사
function loginCheck() {
	// 아이디와 비밀번호를 가져옴
	let userId = document.getElementById("floatingInput").value;
	let userPw = document.getElementById("floatingPassword").value;

	// 아이디와 비밀번호가 빈 문자열인지 확인
	if (userId.trim() === "" || userPw.trim() === "") {
		// 둘 중 하나라도 빈 문자열이면 경고창을 띄움
		alert("아이디와 비밀번호를 모두 입력하세요.");
		// 폼 제출을 중단
		return false; 
	}
	
	// 서버로 보낼 데이터 객체 생성
	let sendData = {"userId" : userId, "userPw" : userPw};
	
	$.ajax({
		url: "LoginCheck.ajax",
		data: sendData,
		dataType: "json",
		type: "post",
		success: function(res) {
			if(res != null){
				return true;
			}else{
				alert("로그인 실패")
				return false;
			}
		},
		error: function() {
			alert("서버 문제");
		}
	});
}

