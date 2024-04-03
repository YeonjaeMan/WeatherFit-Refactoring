package com.smhrd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.database.DAO;
import com.smhrd.model.PostVO;
import com.smhrd.model.UserVO;

// 게시글을 작성을하면  DB에 내용을 저장해주는 컨트롤러
public class Post implements Command {

   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) {
      
      HttpSession session = request.getSession();
      UserVO vo = (UserVO)(session.getAttribute("member"));
      String userId = vo.getUserId(); 
      
      String postContent = request.getParameter("postContent");
      String hashTag = request.getParameter("hashTag");
      
      PostVO pvo = new PostVO();
      pvo.setUserId(userId);
      pvo.setPostContent(postContent);
      pvo.setHashTag(hashTag);
      
      DAO dao = new DAO();
      int row = dao.post(pvo);
      
      if(row > 0) {
         return "redirect:/gomain.do";
      }else {
         return "redirect:/gomain.do";
      }
      
   
   }
}