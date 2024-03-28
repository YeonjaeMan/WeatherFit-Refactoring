package com.smhrd.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.smhrd.database.DAO;
import com.smhrd.model.FileVO;
import com.smhrd.model.PostVO;
import com.smhrd.model.UserVO;

public class CreatePost implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		UserVO uvo = (UserVO) session.getAttribute("member");

		String filename = "";
		int sizeLimit = 1024 * 1024;

//		C:\Users\smhrd\Desktop\-SNS\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\WeatherFit\assets\images
		String realPath = "C:/Users/smhrd/Desktop/-SNS/WeatherFit/src/main/webapp/assets/uploads";
//		String realPath = request.getServletContext().getRealPath("/assets/uploads");
		System.out.println("파일 경로 : " + realPath);

		File dir = new File(realPath);
		if (!dir.exists())
			dir.mkdirs();

		MultipartRequest multipartRequest = null;
		multipartRequest = new MultipartRequest(request, realPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

		String userId = uvo.getUserId();
		String postContent = multipartRequest.getParameter("postContent");
		String postTempStr = multipartRequest.getParameter("postTemp");
		String hashTags = multipartRequest.getParameter("hashTags");

		int postTemp = 0;
		if (postTempStr != null) {
			postTemp = Integer.parseInt(postTempStr);
		} else {
			postTemp = 999;
		}

		System.out.println("이 Post에 해당되는 기온 : " + postTemp + " 'C");

		PostVO pvo = new PostVO();
		pvo.setUserId(userId);
		pvo.setPostContent(postContent);
		pvo.setPostTemp(postTemp); // temp 자리 수정필요!!!
		pvo.setHashTag(hashTags);

		DAO dao = new DAO();
		int postIdx = dao.insertPost(pvo);

		if (postIdx >= 0) {
			System.out.println("게시글 저장 성공!");

			FileVO fvo = new FileVO();
			fvo.setFileRname(multipartRequest.getFilesystemName("postImg")); // 업로드한 파일 이름
			fvo.setFileSize(multipartRequest.getFile("postImg").length()); // 파일 크기
			fvo.setFileExt(FilenameUtils.getExtension(multipartRequest.getFilesystemName("postImg"))); // 파일 확장자
			fvo.setPostIdx(postIdx);

			int row = dao.insertFile(fvo);

			if (row > 0) {
				System.out.println("이미지파일 저장 성공 !");
			} else {
				System.out.println("이미지파일 저장 실패..");
			}

		} else {
			System.out.println("게시글 저장 실패..");
		}

		return "redirect:/gomain.do";

	}

}
