function loginCheck() {
	// 아이디와 비밀번호를 가져옵니다.
	let userId = document.getElementById("floatingInput").value;
	let userPw = document.getElementById("floatingPassword").value;

	// 아이디와 비밀번호가 빈 문자열인지 확인합니다.
	if (userId.trim() === "" || userPw.trim() === "") {
		alert("아이디와 비밀번호를 모두 입력하세요.");
		return false; // 폼 제출을 중단합니다.
	}
	
	let sendData = {"userId" : userId, "userPw" : userPw};
	
	$.ajax({
		url: "LoginCheck.ajax",
		data: sendData,
		dataType: "json",
		type: "post",
		success: function(res) {
			if(res != null){
				console(res);
				return true;
			}else{
				alert("로그인 실페")
				return false;
			}
		},
		error: function() {
			alert("서버 문제");
		}
	});

}


///////// 회원가입 /////////