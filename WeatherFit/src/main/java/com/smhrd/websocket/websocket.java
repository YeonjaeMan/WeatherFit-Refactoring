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
// import com.fasterxml.jackson.databind.ObjectMapper;

@ServerEndpoint("/websocket/{roomIdx}")
public class websocket {

//    static List<Session> sessionList = Collections.synchronizedList(new ArrayList<>());
	private static final Map<String, List<Session>> roomSessions = Collections.synchronizedMap(new HashMap<>());
	
    // 웹 소켓 연결시 호출
    @OnOpen
    public void handleOpen(Session userSession, @PathParam("roomIdx") String roomIdx) {
        roomSessions.putIfAbsent(roomIdx, Collections.synchronizedList(new ArrayList<>()));
        List<Session> sessions = roomSessions.get(roomIdx);
        sessions.add(userSession);
    	System.out.println("웹 소켓 연결 : " + roomIdx);
        // 웹 소켓 연결시 세션리스트에 추가
        

    }

    // 웹소켓 메시지 수신시 호출
    @OnMessage
    public void handleMessage(String message, Session userSession, @PathParam("roomIdx") String roomIdx) {
        System.out.println("수신 된 메시지 : " + message + " from " + roomIdx);
        // 메시지 처리 로직 구현
        List<Session> sessions = roomSessions.get(roomIdx);
        for(Session session : sessions) {
        	try {
        		session.getBasicRemote().sendText(message);
        		
        		Gson gson = new Gson();
        		JsonObject json = gson.fromJson(message, JsonObject.class);
        		
        		int sendRoomIdx = json.get("roomIdx").getAsInt();
        		String sendMessage = json.get("message").getAsString();
        		String sendUserId = json.get("userId").getAsString();
        		ChatVO cvo = new ChatVO();
        		cvo.setChatIdx(2); // chatIdx
        		cvo.setRoomIdx(sendRoomIdx); // roomIdx
        		cvo.setChatter(sendUserId); // chatter
        		cvo.setChat(sendMessage); // chat
        		DAO dao = new DAO();
        		int row = dao.sendChat(cvo);
        		if(row > 0) {
        			System.out.println("보낸 메세지 저장 성공!");
        		} else {
        			System.out.println("보낸 메세지 저장 실패..");
        		}
        	} catch(IOException e) {
        		System.out.println("메시지 전송 중 오류 발생 : " + e.getMessage());
        		e.printStackTrace();
        	}
        }
        
    }

    // 웹소켓 연결 종료시 호출
    @OnClose
    public void handleClose(Session userSession, @PathParam("roomIdx") String roomIdx) {
        List<Session> sessions = roomSessions.get(roomIdx);
        sessions.remove(userSession);
        if(sessions.isEmpty()) {
        	roomSessions.remove(roomIdx);
        }
        System.out.println("웹 소켓 연결 종료 : " + roomIdx);
    }

    // 웹소켓 에러 발생시 호출
    @OnError
    public void handleError(Throwable t) {
        t.printStackTrace();
    }
}