package com.smhrd.frontController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.LikeVO;

@WebServlet("/PostLikeUpdate")
public class PostLikeUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		HttpSession session = request.getSession();
//		String userId = (String)session.getAttribute("userId");
//		int postIdx = (Integer)session.getAttribute("postIdx"); 
		
		String userId = request.getParameter("userId");
		int postIdx = Integer.parseInt(request.getParameter("postIdx"));
		
		LikeVO lvo = new LikeVO();
		lvo.setPostIdx(postIdx);
		lvo.setUserId(userId);
		
		DAO dao = new DAO();
		LikeVO resultVO = dao.selectLike(lvo);
		if(resultVO != null) {
			int row = dao.deleteLike(lvo);
			if(row > 0) {
				System.out.print("좋아요 삭제완료");				
			} else {
				System.out.println("좋아요 삭제실패");
			}
		} else {
			int row = dao.insertLike(lvo);
			if(row > 0) {
				System.out.print("좋아요 추가완료");				
			} else {
				System.out.println("좋아요 추가실패");
			}
		}
		// int row = dao.deleteLike(lvo);
		
//		PrintWriter out = response.getWriter();
//	    Gson gson = new Gson();
//	    String json = gson.toJson(resultVO);
//	    out.print(json);
	}

}
