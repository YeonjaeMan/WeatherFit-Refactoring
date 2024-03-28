let roomIdx;
let webSocket; // 웹소켓 생성

// 채팅방 목록 로딩 
(function() {
	$.ajax({
		url: "Rooms.ajax",
		data: { "userId": userId },
		dataType: "json",
		type: "post",
		success: function(rooms) {
			console.log(rooms);
			for (let i = 0; i < rooms.length; i++) {
				if (userId == rooms[i].userId) {
					$("#btn-room-add").after(`<li class="nav-item"><a href="#" id="chat-idlist" class="chatroom nav-link active"
									aria-current="page" data-val=` + rooms[i].roomIdx + `> <svg class="bi pe-none me-2" width="16"
									height="16">
			                            <use xlink:href="#home" />
			                    </svg>` + rooms[i].roomTitle +
						`</a></li>`);
				} else {
					$("#btn-room-add").after(`<li class="nav-item"><a href="#" id="chat-idlist" class="chatroom nav-link active"
									aria-current="page" data-val=` + rooms[i].roomIdx + `> <svg class="bi pe-none me-2" width="16"
									height="16">
			                            <use xlink:href="#home" />
			                    </svg>` + rooms[i].userId +
						`</a></li>`);
				}
			}

		},
		error: function() {
			console.log("연결 실패 ..");
		}
	});
}());

// 채팅방 클릭시 채팅화면 로딩 
$(document).on("click", ".chatroom", function(event) {
	event.preventDefault();
	roomIdx = $(this).attr("data-val");
	// 채팅방 html
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















	let path = "ws://localhost:8080/WeatherFit/websocket/" + roomIdx;


	console.log(path);
	// 웹소켓 초기화
	webSocketInit(path, userId);

	$.ajax({
		url: "Chats.ajax",
		data: { "roomIdx": roomIdx },
		dataType: "json",
		type: "post",
		success: function(chats) {
			console.log(chats);
			for (let i = 0; i < chats.length; i++) {
				let messageElement = document.createElement("div");

				if (chats[i].chatter == userId) {
					messageElement.classList.add("message", "mine");
				} else {
					messageElement.classList.add("message", "theirs");
				}

				let textElement = document.createElement("div");
				textElement.textContent = chats[i].chat;
				messageElement.appendChild(textElement);

				let timestampElement = document.createElement("div");
				timestampElement.classList.add("timestamp");
				timestampElement.textContent = chats[i].chattedAt;
				messageElement.appendChild(timestampElement);

				let container = document.getElementById("chat-container");
				container.appendChild(messageElement);
				container.scrollTop = container.scrollHeight; // 스크롤을 가장 아래로 이동
			}
		},
		error: function() {
			console.log("채팅 목록 조회 실패..");
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

function webSocketInit(path, userId) {
	webSocket = new WebSocket(
		path);
	webSocket.onopen = function(event) {
		socketOpen(event);
	};
	webSocket.onclose = function(event) {
		socketClose(event);
	};
	webSocket.onmessage = function(event) {
		socketMessage(event, userId);
	};
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
	// 웹소켓이 닫혀있으면 재연결을 시도합니다.
	webSocketInit();
}

// 메시지 송신
function socketMsgSend(userId, roomIdx, sendMessage) {
	let msg = {
		roomIdx: roomIdx,
		message: sendMessage,
		userId: userId
	};
	// 세션리스트에 메시지를 송신한다.
	webSocket.send(JSON.stringify(msg));
}

// 웹소켓 메시지 수신
function socketMessage(event, userId) {
	let receiveData = event.data; // 수신 data
	let receiveMessage = JSON.parse(receiveData);

	let messageElement = document.createElement("div");

	if (receiveMessage.userId == userId) {
		messageElement.classList.add("message", "mine");
	} else {
		messageElement.classList.add("message", "theirs");
	}

	// 메시지 내용을 담을 요소 생성 
	let textElement = document.createElement("div");
	textElement.textContent = receiveMessage.message;
	messageElement.appendChild(textElement);

	// 시각을 담을 요소 생성 
	let timestamp = new Date().toLocaleTimeString(); // 현재 시각 가져오기 
	let timestampElement = document.createElement("div");
	timestampElement.classList.add("timestamp");
	timestampElement.textContent = timestamp; // 시각 표시
	messageElement.appendChild(timestampElement);

	let container = document.getElementById("chat-container");
	container.appendChild(messageElement);
	container.scrollTop = container.scrollHeight; // 스크롤을 가장 아래로 이동

}

// 웹소켓 에러
function socketError(event) {
	console.error("WebSocket 에러 발생 : ", event);
	alert("에러가 발생하였습니다.");
}

// 웹소켓 종료
function disconnect() {
	webSocket.close();
}