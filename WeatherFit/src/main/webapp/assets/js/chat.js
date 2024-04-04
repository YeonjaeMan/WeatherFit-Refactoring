let roomIdx;
let webSocket; // 웹소켓 생성

// 채팅방 목록 로딩 
(function() {
	$.ajax({
		// 채팅방 목록을 요청하는 AJAX
		url: "Rooms.ajax",
		// 현재 사용자의 ID를 데이터로 전송
		data: { "userId": userId },
		// 응답 받을 데이터 타입
		dataType: "json",
		// 요청 방식
		type: "post",
		// 요청 성공 시
		success: function(map) {
			// 받은 데이터의 각 방을 순회
			for (let i = 0; i < map.room.length; i++) {
				// 사용자 ID가 방의 userID와 일치하는 경우
				if (userId == map.room[i].userId) {
					// 해당 사용자의 채팅방 목록에 추가
					$("#btn-room-add").after(`<li class="nav-item"><a href="#" id="chat-idlist" class="chatroom nav-link active"
									aria-current="page" data-val=` + map.room[i].roomIdx + `> <svg class="bi pe-none me-2" width="16"
									height="16">
			                            <use xlink:href="#home" />
			                    </svg>` + map.user[i].userNick +
						`</a></li>`);
					// 일치하지 않는 경우에도 채팅방 목록에 추가
				} else {
					$("#btn-room-add").after(`<li class="nav-item"><a href="#" id="chat-idlist" class="chatroom nav-link active"
									aria-current="page" data-val=` + map.room[i].roomIdx + `> <svg class="bi pe-none me-2" width="16"
									height="16">
			                            <use xlink:href="#home" />
			                    </svg>` + map.user[i].userNick +
						`</a></li>`);
				}
			}

			// 채팅방 클릭시 배경색 추가
			// 클릭 전, 모든 채팅방의 active 클래스 제거
			$('.chatroom').removeClass('active');
			// 채팅방 클릭 시
			$('.chatroom').on('click', function() {
				// 모든 채팅방 항목의 'active' 클래스를 제거
				$('.chatroom').removeClass('active');
				// 현재 클릭된 채팅방 항목에만 'active' 클래스 추가
				$(this).addClass('active');
			});

		},
		error: function() {
			alert("연결 실패 ..");
		}
	});
}());




// 채팅방 클릭시 채팅화면 로딩 
$(document).on("click", ".chatroom", function(event) {
	// 기본 이벤트 방지
	event.preventDefault();
	// 클릭된 채팅방의 인덱스 저장
	roomIdx = $(this).attr("data-val");
	// 채팅방 html
	// 기존에 표시된 채팅방 화면 제거
	$("#chatroom-display").remove();
	$("#menu-room").after(`
					<div id="chatroom-display" class="container">
					<div id="chat-wrap">
                     <div class="chat-container" id="chat-container">
                     <!-- 채팅 메시지가 표시될 영역 -->
                     </div>
                     <div class="message-send-container">
                        <textarea type="text" id="message-input" placeholder="메시지를 입력하세요..." rows="2"></textarea>
                        <button class="btn-blue" id="btn-send">전송</button>
                     </div>
                  </div>
               </div>`);
	// 웹소켓 연결 경로
	let path = "ws://localhost:8080/WeatherFit/websocket/" + roomIdx;

	// 웹소켓 초기화
	webSocketInit(path, userId);

	// 채팅방의 채팅 내용을 로드하는 ajax 요청
	$.ajax({
		url: "Chats.ajax",
		data: { "roomIdx": roomIdx },
		dataType: "json",
		type: "post",
		success: function(chats) {
			for (let i = 0; i < chats.length; i++) {
				// 메시지를 담을 div 요소 생성
				let messageElement = document.createElement("div");

				// 메시지 소유자에 따라 클래스 추가
				if (chats[i].chatter == userId) {
					messageElement.classList.add("message", "mine");
				} else {
					messageElement.classList.add("message", "theirs");
				}

				// 메시지 텍스트를 담을 div 요소 생성 및 추가
				let textElement = document.createElement("div");
				textElement.textContent = chats[i].chat;
				messageElement.appendChild(textElement);

				// 메시지 타임스탬프를 표시할 div 요소 생성 및 추가
				let timestampElement = document.createElement("div");
				timestampElement.classList.add("timestamp");
				timestampElement.textContent = chats[i].chattedAt;
				messageElement.appendChild(timestampElement);

				// 생성된 메시지 요소를 채팅 컨테이너에 추가
				let container = document.getElementById("chat-container");
				container.appendChild(messageElement);
				// 스크롤을 가장 아래로 이동
				container.scrollTop = container.scrollHeight;
			}
		},
		error: function() {
			alert("채팅 목록 조회 실패..");
		}
	});
});

// 전송 버튼시 실행 
$(document).on("click", "#btn-send", function() {
	sendMessage(userId, roomIdx);
})

// 전송 버튼을 눌렀을 때 실행하는 함수 모음 
function sendMessage(userId, roomIdx) {
	let message = $("#message-input").val();
	socketMsgSend(userId, roomIdx, message);
	resetInput();
}

// 사용자가 메세지를 전송하면 input을 초기화 기능  
function resetInput() {
	var input = document.getElementById("message-input");
	input.value = ""; // 입력 필드 초기화
}

// 웹소켓 초기화 및 이벤트 핸들러 등록
function webSocketInit(path, userId) {
	webSocket = new WebSocket(
		path);
	// 연결 성공 시
	webSocket.onopen = function(event) {
		socketOpen(event);
	};
	// 연결 종료 시
	webSocket.onclose = function(event) {
		socketClose(event);
	};
	// 메시지 수신 시
	webSocket.onmessage = function(event) {
		socketMessage(event, userId);
	};
	// 에러 발생 시
	webSocket.onerror = function(event) {
		socketError(event);
	};
}

// 웹소켓 연결
function socketOpen() {
	console.log("연결 완료");
}

// 웹소켓 닫힘
function socketClose() {
	console.log("웹소켓이 닫혔습니다.");
	// 웹소켓이 닫혀있으면 재연결을 시도
	webSocketInit();
}

// 메시지 송신
function socketMsgSend(userId, roomIdx, sendMessage) {
	let msg = {
		roomIdx: roomIdx,
		message: sendMessage,
		userId: userId
	};
	// 세션리스트에 메시지를 송신
	webSocket.send(JSON.stringify(msg));
}

// 웹소켓 메시지 수신
function socketMessage(event, userId) {
	// 수신 data
	let receiveData = event.data;
	// 수신받은 데이터를 JSON 형태로 변환
	let receiveMessage = JSON.parse(receiveData);

	// 새로운 메시지를 담을 div 요소를 생성
	let messageElement = document.createElement("div");

	// 메시지가 현재 사용자의 것인지 다른 사람의 것인지에 따라 클래스를 다르게 추가
	if (receiveMessage.userId == userId) {
		messageElement.classList.add("message", "mine");
	} else {
		messageElement.classList.add("message", "theirs");
	}

	// 메시지 내용을 담을 요소 생성, 메세지 내용 설정
	let textElement = document.createElement("div");
	textElement.textContent = receiveMessage.message;
	messageElement.appendChild(textElement);

	// 시각을 담을 요소 생성 
	// 현재 시각 가져오기
	let timestamp = new Date().toLocaleTimeString();
	let timestampElement = document.createElement("div");
	timestampElement.classList.add("timestamp");
	// 시각 표시
	timestampElement.textContent = timestamp;
	messageElement.appendChild(timestampElement);

	// 생성된 메시지 요소를 채팅 컨테이너에 추가
	let container = document.getElementById("chat-container");
	container.appendChild(messageElement);
	// 스크롤을 가장 아래로 이동
	container.scrollTop = container.scrollHeight;

}

// 웹소켓 에러
function socketError(event) {
	alert("에러가 발생하였습니다.");
}

// 웹소켓 종료
function disconnect() {
	webSocket.close();
}