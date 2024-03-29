package com.smhrd.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.smhrd.ajax.AjaxCommand;
import com.smhrd.database.DAO;
import com.smhrd.model.FileVO;
import com.smhrd.model.PostVO;
import com.smhrd.model.UserVO;

public class UpdatePost implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserVO uvo = (UserVO) session.getAttribute("member");

		int sizeLimit = 1024 * 1024;

		String realPath = "C:/Users/smhrd/Desktop/-SNS/WeatherFit/src/main/webapp/assets/uploads";
		System.out.println("파일 경로 : " + realPath);

		File dir = new File(realPath);
		if (!dir.exists())
			dir.mkdirs();

		MultipartRequest multipartRequest = null;
		multipartRequest = new MultipartRequest(request, realPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

		String userId = uvo.getUserId();
		String postContent = multipartRequest.getParameter("postContent");
		String hashTags = multipartRequest.getParameter("hashTags");
		int postIdx = Integer.parseInt(multipartRequest.getParameter("postIdx"));

		int postTemp;
		try {
			postTemp = Integer.parseInt(multipartRequest.getParameterValues("postTemp")[0]);
		} catch (Exception e) {
			postTemp = -999;
		}

		System.out.println("이 Post에 해당되는 기온 : " + postTemp + " 'C");

		PostVO pvo = new PostVO();
		pvo.setUserId(userId);
		pvo.setPostContent(postContent);
		pvo.setPostTemp(postTemp); // temp 자리 수정필요!!!
		pvo.setHashTag(hashTags);

		DAO dao = new DAO();
		int row = dao.updatePost(pvo);

		FileVO fvo = new FileVO();
		fvo.setFileRname(multipartRequest.getFilesystemName("postImg")); // 업로드한 파일 이름
		fvo.setFileSize(multipartRequest.getFile("postImg").length()); // 파일 크기
		fvo.setFileExt(FilenameUtils.getExtension(multipartRequest.getFilesystemName("postImg"))); // 파일 확장자
		fvo.setPostIdx(postIdx);

		dao.updateFile(fvo);
		
		return "redirect:/gomain.do";
	}

}
