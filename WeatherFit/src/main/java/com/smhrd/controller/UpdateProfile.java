package com.smhrd.controller;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.smhrd.database.DAO;
import com.smhrd.model.UserVO;

public class UpdateProfile implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, InterruptedException {
		
		HttpSession session = request.getSession();
		UserVO uvo = (UserVO) session.getAttribute("member");

		int sizeLimit = 500 * 1024 * 1024;

		String realPath = "C:/Users/smhrd/Desktop/-SNS/WeatherFit/src/main/webapp/assets/user_profile";
		System.out.println("파일 경로 : " + realPath);

		File dir = new File(realPath);
		if (!dir.exists())
			dir.mkdirs();

		MultipartRequest multipartRequest = null;
		multipartRequest = new MultipartRequest(request, realPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

		String userId = uvo.getUserId();
		String userNick = multipartRequest.getParameter("userNick");
		String userProfileInfo = multipartRequest.getParameter("userProfileInfo");
		
		uvo.setUserNick(userNick);
		uvo.setUserProfileImg(multipartRequest.getFilesystemName("profileImg"));
		uvo.setUserProfileInfo(userProfileInfo);
		
		DAO dao = new DAO();
		dao.updateProfileInfo(uvo);
		
		session.setAttribute("member", uvo);
		session.setAttribute("userProfileInfo", uvo);
		
		TimeUnit.SECONDS.sleep(4);

		return "redirect:/goprofile.do";
	}

}
