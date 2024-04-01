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

public class UserPosts implements AjaxCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		UserVO uvo =(UserVO)request.getSession().getAttribute("userProfileInfo");
		
		DAO dao = new DAO();
		List<PostVO> userPosts = dao.selectUserPosts(uvo);
		
		Gson gson = new Gson();
		String json = gson.toJson(userPosts);

		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(json);
	}
	

}
