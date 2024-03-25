<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<!-- bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<!-- bootstrap -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<!-- jquery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link rel="stylesheet" href="././assets/css/message.css">
</head>
<body>
	
	<!-- 날씨 위젯과 상단 네비 분리 -->
	<%@ include file="includeNavi.jsp"%>

    <main>
        <div class="container-fluid d-flex p-3 bg-light">
            <ul class="nav mb-auto">
                <li class="nav-item">
                    <a href="#" class="nav-link active" aria-current="page">
                        <svg class="bi pe-none me-2" width="16" height="16">
                            <use xlink:href="#home" />
                        </svg>
                        Chatroom1
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link">
                        <svg class="bi pe-none me-2" width="16" height="16">
                            <use xlink:href="#speedometer2" />
                        </svg>
                        Chatroom2
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link">
                        <svg class="bi pe-none me-2" width="16" height="16">
                            <use xlink:href="#table" />
                        </svg>
                        Chatroom3
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link">
                        <svg class="bi pe-none me-2" width="16" height="16">
                            <use xlink:href="#grid" />
                        </svg>
                        Chatroom4
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link">
                        <svg class="bi pe-none me-2" width="16" height="16">
                            <use xlink:href="#people-circle" />
                        </svg>
                        Chatroom5
                    </a>
                </li>
            </ul>
        </div>
        <div class="container">
            <div class="chat-container" id="chat-container">
                <!-- 채팅 메시지가 표시될 영역 -->
              </div>
              <input type="text" id="message-input" placeholder="메시지를 입력하세요...">
              <button onclick="sendMessage()">전송</button>
        </div>
    </main>
    
    
    
    
    <script src="assets/js/login.js"></script>
    <script src="././assets/js/message.js"></script>
</body>
</html>