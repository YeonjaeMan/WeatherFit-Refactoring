package com.smhrd.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.smhrd.database.DAO;
import com.smhrd.model.ChatVO;

// roomIdx를 이용해 채팅방별 사용자 연결
@ServerEndpoint("/websocket/{roomIdx}")
public class websocket {

	// 채팅이 동시에 진행될 수 있기 때문에 synchronizedMap을 사용하여 동시처리
	private static final Map<String, List<Session>> roomSessions = Collections.synchronizedMap(new HashMap<>());

	// 웹 소켓 연결시 호출
	@OnOpen
	public void handleOpen(Session userSession, @PathParam("roomIdx") String roomIdx) {
		// key값에 roomIdx가 있다면, 해당 key의 value List에 접속한 사용자를 소켓 세션에 넣고,
		// 			 		없다면, 해당 key의 value에 List를 생성한다.
		roomSessions.putIfAbsent(roomIdx, Collections.synchronizedList(new ArrayList<>()));
		List<Session> sessions = roomSessions.get(roomIdx);
		
		// 웹 소켓 연결시 해당 roomIdx에 세션리스트에 추가
		sessions.add(userSession);

	}

	// 웹소켓 메시지 수신시 호출
	@OnMessage
	public void handleMessage(String message, Session userSession, @PathParam("roomIdx") String roomIdx) {
		// 메시지 처리 로직 구현
		List<Session> sessions = roomSessions.get(roomIdx);
		for (Session session : sessions) {
			try {
				// 사용자가 보내온 메세지를 양쪽 모두에게 전달
				session.getBasicRemote().sendText(message);

				Gson gson = new Gson();
				
				// 클라이언트에서 보낸 데이터가 String 타입이기 때문에 key값별 데이터 분류를 위해 Json 형식으로 변환
				JsonObject json = gson.fromJson(message, JsonObject.class);

				// 해당 key값에 들어있는 value값 가져오기
				int sendRoomIdx = json.get("roomIdx").getAsInt();
				String sendMessage = json.get("message").getAsString();
				String sendUserId = json.get("userId").getAsString();
				
				// DB에 저장하기 위해 ChatVO를 세팅하고 보내주기
				ChatVO cvo = new ChatVO();
				cvo.setChatIdx(2); // chatIdx
				cvo.setRoomIdx(sendRoomIdx); // roomIdx
				cvo.setChatter(sendUserId); // chatter
				cvo.setChat(sendMessage); // chat
				
				DAO dao = new DAO();
				dao.sendChat(cvo);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// 웹소켓 연결 종료시 호출
	@OnClose
	public void handleClose(Session userSession, @PathParam("roomIdx") String roomIdx) {
		List<Session> sessions = roomSessions.get(roomIdx);
		
		// 소켓 세션에서 연결을 끊은 사용자 지우기
		sessions.remove(userSession);
		if (sessions.isEmpty()) {
			roomSessions.remove(roomIdx);
		}
	}

	// 웹소켓 에러 발생시 호출
	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}
}