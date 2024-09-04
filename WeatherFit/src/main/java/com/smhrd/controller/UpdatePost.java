package com.smhrd.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.smhrd.database.DAO;
import com.smhrd.model.FileVO;
import com.smhrd.model.PostVO;
import com.smhrd.model.UserVO;

// 게시물을 수정하여 DB에 저장해주는 클래스
public class UpdatePost implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, InterruptedException {

		HttpSession session = request.getSession();
		UserVO uvo = (UserVO) session.getAttribute("member");

		int sizeLimit = 500 * 1024 * 1024;

		String realPath = "C:/Users/smhrd/Desktop/-SNS/WeatherFit/src/main/webapp/assets/uploads";

		// cos 라이브러리를 사용해 multipart/form-data 처리
//		MultipartRequest multipartRequest = null;
		MultipartRequest multipartRequest = new MultipartRequest(request, realPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
//		multipartRequest = new MultipartRequest(request, "", sizeLimit, "utf-8", new DefaultFileRenamePolicy());

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

		PostVO pvo = new PostVO();
		pvo.setUserId(userId);
		pvo.setPostContent(postContent);
		pvo.setPostTemp(postTemp);
		pvo.setHashTag(hashTags);
		pvo.setPostIdx(postIdx);

		DAO dao = new DAO();
		dao.updatePost(pvo);

		FileVO fvo = new FileVO();
		fvo.setPostIdx(postIdx);

		FileVO resultVO = dao.selectFile(fvo);
		File recentFile = new File(realPath + resultVO.getFileRname());
		if (recentFile.exists()) {
			recentFile.delete();
		}

		fvo.setFileRname(multipartRequest.getFilesystemName("postImg")); // 업로드한 파일 이름
		fvo.setFileSize(multipartRequest.getFile("postImg").length()); // 파일 크기
		fvo.setFileExt(FilenameUtils.getExtension(multipartRequest.getFilesystemName("postImg"))); // 파일 확장자

		File file = new File(realPath + "/" + multipartRequest.getFilesystemName("postImg"));
//		File file = new File(multipartRequest.getFilesystemName("postImg"));
		byte[] fileImg = new byte[(int) file.length()];
		try(FileInputStream fis = new FileInputStream(file)) {
			fis.read(fileImg);
		}
		fvo.setFileImg(fileImg);

		dao.updateFile(fvo);

		return "redirect:/gomain.do";
	}

}