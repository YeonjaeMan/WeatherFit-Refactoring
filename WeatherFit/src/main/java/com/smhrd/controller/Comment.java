package com.smhrd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.database.DAO;
import com.smhrd.model.CommentVO;
import com.smhrd.model.UserVO;

// postIdx 네임의 데이터를 불러와 게시글에 댓글을 작성하면 DB에 저장해주는 컨트롤러
public class Comment implements Command {

   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) {
      
      int postIdx = Integer.parseInt(request.getParameter("postIdx"));
      String cmtContent = request.getParameter("cmtContent");
      
      HttpSession session = request.getSession();
      UserVO uvo = (UserVO)(session.getAttribute("member"));
      String userId = uvo.getUserId();

      CommentVO cvo = new CommentVO();
      cvo.setPostIdx(postIdx);
      cvo.setCmtContent(cmtContent);
      cvo.setUserId(userId);
      
      DAO dao = new DAO();
      dao.comment(cvo);
      
      return null;
   }

}