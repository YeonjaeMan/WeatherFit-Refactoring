package com.smhrd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 로그아웃을 하면 회원정보가 저장되어 있는 세션을 삭제해주는 클래스
public class Logout implements Command{

   public String execute(HttpServletRequest request, HttpServletResponse response) {

      HttpSession session = request.getSession();
      session.invalidate();
      
      return "redirect:/gomain.do";
      
   }
}