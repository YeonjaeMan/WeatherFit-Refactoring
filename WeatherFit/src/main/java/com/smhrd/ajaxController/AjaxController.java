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
import com.smhrd.ajax.Comments;
import com.smhrd.ajax.DeleteLike;
import com.smhrd.ajax.Images;
import com.smhrd.ajax.InsertLike;
import com.smhrd.ajax.SelectLike;
import com.smhrd.ajax.LoginCheck;
import com.smhrd.ajax.Postdetail;
import com.smhrd.ajax.UserPosts;
import com.smhrd.ajax.Posts;
import com.smhrd.ajax.RecentPosts;
import com.smhrd.ajax.Rooms;
import com.smhrd.ajax.Searchs;


// .ajax로 끝나는 모든 url을 이곳에서 처리한다
@WebServlet("*.ajax")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HashMap<String, AjaxCommand> map = new HashMap<String, AjaxCommand>();
	// 해당하는 클래스로 연결한다.
	@Override
	public void init() throws ServletException {
		
		map.put("Posts.ajax", new Posts());// select를 하려고할때 ??
		map.put("Searchs.ajax", new Searchs());
		map.put("Rooms.ajax",new Rooms());
		map.put("Chats.ajax",new Chats());
		map.put("UserPosts.ajax",new UserPosts());
		map.put("Comments.ajax", new Comments());
		map.put("Images.ajax", new Images());
		map.put("RecentPosts.ajax", new RecentPosts());
		map.put("LoginCheck.ajax", new LoginCheck());
		map.put("SelectLike.ajax", new SelectLike());
		map.put("InsertLike.ajax", new InsertLike());
		map.put("DeleteLike.ajax", new DeleteLike());
		map.put("Postdetail.ajax", new Postdetail());
	}
	// 요청이 들어온 url을 수정한다.
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
