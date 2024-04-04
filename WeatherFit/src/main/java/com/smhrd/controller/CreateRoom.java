package com.smhrd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.database.DAO;
import com.smhrd.model.RoomVO;

// 나의 userId와 상대 userId를 가져와 기본 채팅방이 없다면 채팅방을 만들어 DB에 저장해주는 클래스
public class CreateRoom implements Command {

   public String execute(HttpServletRequest request, HttpServletResponse response) {
      String sendUserId = request.getParameter("sendUserId");
      String receiveUserId = request.getParameter("receiveUserId");
      
      RoomVO rvo = new RoomVO();
      rvo.setUserId(sendUserId);
      rvo.setRoomTitle(receiveUserId);
      rvo.setRoomLimit(2);
      rvo.setRoomStatus("생성");
      
      DAO dao = new DAO();
      RoomVO resultVO =  dao.checkRoom(rvo);
      if(resultVO == null) {
         int row = dao.createRoom(rvo);
      }
      
      return "redirect:/gochat.do";
   }
   
}