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
import com.smhrd.controller.Join;
import com.smhrd.controller.Login;
import com.smhrd.controller.Logout;
import com.smhrd.controller.Post;
import com.smhrd.controller.Update;

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
			finalPath = com.execute(request, response);
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