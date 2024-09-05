package com.smhrd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.smhrd.database.DAO;
import com.smhrd.model.UserVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

// 회원의 정보를 수정하여 DB에 저장해주는 클래스
public class Update implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int sizeLimit = 500 * 1024 * 1024;

		String realPath = "C:/Users/smhrd/Desktop/-SNS/WeatherFit/src/main/webapp/assets/user_profile";

		File dir = new File(realPath);
		if (!dir.exists())
			dir.mkdirs();

		MultipartRequest multipartRequest = new MultipartRequest(request, realPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());


		String userPw = multipartRequest.getParameter("userPw");
		String userNick = multipartRequest.getParameter("userNick");
		String userRegion = multipartRequest.getParameter("userRegion");
		double userWeight = Double.parseDouble(multipartRequest.getParameter("userWeight"));
		double userHeight = Double.parseDouble(multipartRequest.getParameter("userHeight"));
		String userProfileInfo = multipartRequest.getParameter("userProfileInfo");
//		String userProfileImg = request.getParameter("userProfileImg");

		UserVO sessionVo = (UserVO) (request.getSession().getAttribute("member"));
		String userId = sessionVo.getUserId();

		UserVO vo = new UserVO();
		vo.setUserId(userId);
		vo.setUserPw(userPw);
		vo.setUserNick(userNick);
		vo.setUserWeight(userWeight);
		vo.setUserHeight(userHeight);
		vo.setUserRegion(userRegion);
//		vo.setUserProfileImg(userProfileImg);
		vo.setUserProfileInfo(userProfileInfo);

		File file = new File(realPath + "/" + multipartRequest.getFilesystemName("profileImg"));
		byte[] fileImg = new byte[(int) file.length()];
		try(FileInputStream fis = new FileInputStream(file)) {
			fis.read(fileImg);
		}
		vo.setUserProfileImg(fileImg);

		DAO dao = new DAO();

		dao.update(vo);

		request.getSession().setAttribute("member", vo);

		return "redirect:/gomain.do";

	}

}