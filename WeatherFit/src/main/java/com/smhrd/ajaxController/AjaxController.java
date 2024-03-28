package com.smhrd.ajaxController;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.ajax.AjaxCommand;
import com.smhrd.ajax.Chats;
import com.smhrd.ajax.MinePosts;
import com.smhrd.ajax.Posts;
import com.smhrd.ajax.Rooms;
import com.smhrd.ajax.Searchs;



@WebServlet("*.ajax")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HashMap<String, AjaxCommand> map = new HashMap<String, AjaxCommand>();
	
	@Override
	public void init() throws ServletException {
		
		map.put("Posts.ajax", new Posts());// select를 하려고할때 ??
		map.put("Searchs.ajax", new Searchs());
		map.put("Rooms.ajax",new Rooms());
		map.put("Chats.ajax",new Chats());
		map.put("MinePosts.ajax",new MinePosts());
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();
		String cp = request.getContextPath();
		String path = uri.substring(cp.length() + 1);
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		AjaxCommand com = map.get(path);
		if (com != null) {
			com.execute(request, response);
		}else {
            // 해당하는 Command 객체가 없는 경우, 에러 처리
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
		
		
	}

}
