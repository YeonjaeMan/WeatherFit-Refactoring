package com.smhrd.frontController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.PostVO;

@WebServlet("/PostLike")
public class PostLike extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// int postLikes = Integer.parseInt(request.getParameter("postLikes"));
		int postIdx = Integer.parseInt(request.getParameter("postIdx"));
	
		PostVO pvo = new PostVO();
	    // pvo.setPostLikes(postLikes);
	    pvo.setPostIdx(postIdx);
	    
	    DAO dao = new DAO();
	    
	    dao.updateLike(pvo);
	    PostVO resultVO = dao.selectLike(pvo);
	    
	    PrintWriter out = response.getWriter();
	    Gson gson = new Gson();
	    String json = gson.toJson(resultVO);
	    out.print(json);
	    
	}

}
