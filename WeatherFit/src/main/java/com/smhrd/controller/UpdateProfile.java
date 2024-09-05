package com.smhrd.controller;

import java.io.File;
import java.io.FileInputStream;
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

// 포로필 정보를 수정하여 DB에 저장해주는 클래스
public class UpdateProfile implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, InterruptedException {

		HttpSession session = request.getSession();
		UserVO uvo = (UserVO) session.getAttribute("member");

		int sizeLimit = 500 * 1024 * 1024;

		String realPath = "C:/Users/smhrd/Desktop/-SNS/WeatherFit/src/main/webapp/assets/user_profile";

		File dir = new File(realPath);
		if (!dir.exists())
			dir.mkdirs();

		// cos 라이브러리를 사용해 multipart/form-data 처리
		MultipartRequest multipartRequest = new MultipartRequest(request, realPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

		String userId = uvo.getUserId();
		String userNick = multipartRequest.getParameter("userNick");
		String userProfileInfo = multipartRequest.getParameter("userProfileInfo");

		uvo.setUserNick(userNick);
		uvo.setUserProfileInfo(userProfileInfo);
//		uvo.setUserProfileImg(multipartRequest.getFilesystemName("profileImg"));
		File file = new File(realPath + "/" + multipartRequest.getFilesystemName("profileImg"));
		byte[] fileImg = new byte[(int) file.length()];
		try(FileInputStream fis = new FileInputStream(file)) {
			fis.read(fileImg);
		}
		uvo.setUserProfileImg(fileImg);

		DAO dao = new DAO();
		dao.updateProfileInfo(uvo);

		session.setAttribute("member", uvo);
		session.setAttribute("userProfileInfo", uvo);

		return "redirect:/goprofile.do";
	}

}