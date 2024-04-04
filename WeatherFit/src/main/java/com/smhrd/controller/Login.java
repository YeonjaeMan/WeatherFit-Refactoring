package com.smhrd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.database.DAO;
import com.smhrd.model.UserVO;

// 로그인을 하기 위해 DB에 들어있는 ID와 PW가 맞다면 DB에서 회원정보를 가져오는 클래스
public class Login implements Command {
   
   public String execute(HttpServletRequest request, HttpServletResponse response) {
      
      String userId = request.getParameter("email_id"); 
      String userPw = request.getParameter("email_pw");

      UserVO vo = new UserVO();
      vo.setUserId(userId);
      vo.setUserPw(userPw);
      
      DAO dao = new DAO();
      
      UserVO resultVO = dao.login(vo);

      if(resultVO != null) {
         HttpSession session = request.getSession();
         session.setAttribute("member", resultVO);
      }

      return "redirect:/gomain.do";
      
   }   
}