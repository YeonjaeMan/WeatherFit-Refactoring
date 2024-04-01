package com.smhrd.frontController;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.controller.Command;
import com.smhrd.controller.Comment;
import com.smhrd.controller.CreatePost;
import com.smhrd.controller.CreateRoom;
import com.smhrd.controller.DeleteFollowing;
import com.smhrd.controller.DeletePost;
import com.smhrd.controller.DeleteUserInfo;
import com.smhrd.controller.InsertFollowing;
import com.smhrd.controller.Join;
import com.smhrd.controller.Login;
import com.smhrd.controller.Logout;
import com.smhrd.controller.Post;
import com.smhrd.controller.Profile;
import com.smhrd.controller.Update;
import com.smhrd.controller.UpdatePost;
import com.smhrd.controller.UpdateUserInfo;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HashMap<String, Command> map = new HashMap<String, Command>();

	@Override
	public void init() throws ServletException {
		map.put("Join.do", new Join());
		map.put("Login.do", new Login());
		map.put("Logout.do", new Logout());
		map.put("Post.do", new Post());
		map.put("Update.do", new Update());
		map.put("CreateRoom.do", new CreateRoom());
		map.put("CreatePost.do", new CreatePost());
		map.put("Comment.do", new Comment());
		map.put("UpdatePost.do", new UpdatePost());
		map.put("DeletePost.do", new DeletePost());
		map.put("UpdateUserInfo.do", new UpdateUserInfo());
		map.put("DeleteUserInfo.do", new DeleteUserInfo());
		map.put("Profile.do", new Profile());
		map.put("InsertFollowing.do", new InsertFollowing());
		map.put("DeleteFollowing.do", new DeleteFollowing());
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String cp = request.getContextPath();
		String path = uri.substring(cp.length() + 1);

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String finalPath = null;
		Command com = map.get(path);

	
		
		
		if (path.startsWith("go")) {
			finalPath = path.replace("go", "").replace(".do", "");
		} else {
			try {
				finalPath = com.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		

		if (finalPath == null) {

		} else if (finalPath.contains("redirect:/")) {
			response.sendRedirect(finalPath.split("/")[1]);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/" + finalPath + ".jsp");
			rd.forward(request, response);
		}

	}

}