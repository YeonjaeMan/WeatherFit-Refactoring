package com.smhrd.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.smhrd.database.DAO;
import com.smhrd.model.FileVO;
import com.smhrd.model.PostVO;

public class CreatePost implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		request.setCharacterEncoding("UTF-8");
		
		String filename = "";
		int sizeLimit = 300 * 1024;
		
		String realPath = request.getServletContext().getRealPath("assets/images");
		System.out.println("파일 경로 : " + realPath);
		
		File dir = new File(realPath);
		if(!dir.exists())
			dir.mkdirs();
		
		MultipartRequest multipartRequest = null;
		multipartRequest = new MultipartRequest(request, realPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		
		if(sizeLimit < multipartRequest.getFile("postImg").length()) {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('이미지 업로드 실패..');</script>");
			
			return "gomain.do";
		} else {
			String userId = multipartRequest.getParameter("userId");
			String postContent = multipartRequest.getParameter("postContent");
//		String postOption = Integer.parseInt(multipartRequest.getParameter("postOption"));
			String hashTags = multipartRequest.getParameter("hashTags");
			
			PostVO pvo = new PostVO();
			pvo.setUserId(userId);
			pvo.setPostContent(postContent);
			pvo.setTemp(0); // temp 자리 수정필요!!!
			pvo.setHashTag(hashTags);
			
			DAO dao = new DAO();
			int postIdx = dao.insertPost(pvo);
			
			if(postIdx >= 0) {
				System.out.println("게시글 저장 성공!");
				
				FileVO fvo = new FileVO();
				fvo.setFileData(Files.readAllBytes(multipartRequest.getFile("postImg").toPath()));
				fvo.setFileRname(multipartRequest.getFilesystemName("postImg"));
				fvo.setFileSize(multipartRequest.getFile("postImg").length());
				fvo.setFileExt(FilenameUtils.getExtension(multipartRequest.getFilesystemName("postImg")));
				fvo.setPostIdx(postIdx);
				
				int row = dao.insertFile(fvo);
				
				if(row > 0) {
					System.out.println("이미지파일 저장 성공 !");
				} else {
					System.out.println("이미지파일 저장 실패..");
				}
				
			} else {
				System.out.println("게시글 저장 실패..");
			}
			
			System.out.println("작성자 : " + userId);
			System.out.println("업로드한 파일 이름 : " + multipartRequest.getFilesystemName("postImg"));
			System.out.println("파일 확장자 : " + FilenameUtils.getExtension(filename));
			System.out.println("파일 크기 : " + multipartRequest.getFile("postImg").length());
			
			return "redirect:/gomain.do";
		}
		
	}
	
}
