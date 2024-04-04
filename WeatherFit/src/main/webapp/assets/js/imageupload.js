// 게시글 작성 시 id가 imageUpload인 input태그에 변화가 있으면 이미지 미리보기를 보여줌
$(document).on("change", "#imageUpload", function(event) {
	// 사용자가 선택한 파일을 변수에 저장
	let file = event.target.files[0];
	// 파일을 읽기 위한 FileReader 객체 생성
	let reader = new FileReader();
	// 파일이 성공적으로 읽혔을 때 실행될 함수
	reader.onload = function(e) {
		// "imagePreview"라는 ID를 가진 요소의 src 속성을 읽은 파일의 내용으로 설정하고 보여줌
		$("#imagePreview").attr("src", e.target.result).show();
	}
	// FileReader를 사용하여 사용자가 선택한 파일을 Data URL 형식으로 읽음
	reader.readAsDataURL(file);
});

// 게시글 이미지 수정 시 수정된 이미지를 미리보기로 보여줌
$(document).on("change", "#update-imageUpload", function(event) {
	// 사용자가 선택한 파일을 변수에 저장
	let file = event.target.files[0];
	// 파일을 읽기 위한 FileReader 객체 생성
	let reader = new FileReader();
	// 파일이 성공적으로 읽혔을 때 실행될 함수
	reader.onload = function(e) {
		// "update-imagePreview"라는 ID를 가진 요소의 src 속성을 읽은 파일의 내용으로 설정하고 보여줌
		$("#update-imagePreview").attr("src", e.target.result).show();
	}
	// FileReader를 사용하여 사용자가 선택한 파일을 Data URL 형식으로 읽음
	reader.readAsDataURL(file);
});