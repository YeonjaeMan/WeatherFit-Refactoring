package com.smhrd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.database.DAO;
import com.smhrd.model.UserVO;

// 회원탈퇴를 하면 DB에 저장된 회원 정보를 삭제해주는 클래스
public class DeleteUserInfo implements Command {

   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException {
   
      UserVO sessionVO = (UserVO)request.getSession().getAttribute("member");
      UserVO uvo = new UserVO();
      uvo.setUserId(sessionVO.getUserId());
      
      DAO dao = new DAO();
      dao.deleteUserInfo(uvo);
      
      return "redirect:/gomain.do";
   }
   
}