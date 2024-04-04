package com.smhrd.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.database.DAO;
import com.smhrd.model.PostVO;
import com.smhrd.model.UserVO;

// 세션에 해당하는 user의 게시물을 가져오는 클래스
public class UserPosts implements AjaxCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		// 세션의 정보를 받는 uvo
		UserVO uvo =(UserVO)request.getSession().getAttribute("userProfileInfo");
		
		DAO dao = new DAO();
		
		// selectUserPosts에 uvo를 보낸다. 반환은 userPosts에 받는다
		List<PostVO> userPosts = dao.selectUserPosts(uvo);
		
		Gson gson = new Gson();
		String json = gson.toJson(userPosts);

		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        
	}
	

}
