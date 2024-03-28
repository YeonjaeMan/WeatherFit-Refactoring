package com.smhrd.ajaxController;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.controller.Command;
import com.smhrd.controller.Postselect;



@WebServlet("*.ajax")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HashMap<String, Command> map = new HashMap<String, Command>();
	
	@Override
	public void init() throws ServletException {
		
		map.put("Postselect.ajax", new Postselect());// select를 하려고할때 ??
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();
		String cp = request.getContextPath();
		String path = uri.substring(cp.length() + 1);
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String finalPath = null;
		Command com = map.get(path);
		
		if(path.startsWith("go")) {
			finalPath = path.replace("go", "").replace(".ajax", "");
		} else {
			finalPath = com.execute(request, response);
		}
		
		if(finalPath == null) {
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/" + finalPath + ".jsp");
			rd.forward(request, response);
		}
		
	}

}
