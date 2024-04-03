package com.smhrd.controller;

import java.io.File;
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

// 게시글 작성 내용과 파일을 불러와 게시글의 내용과 파일을 DB에 저장해주는 컨트롤러
public class CreatePost implements Command {

   public String execute(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException, InterruptedException {

      HttpSession session = request.getSession();
      UserVO uvo = (UserVO) session.getAttribute("member");

      int sizeLimit = 500 * 1024 * 1024;

      String realPath = "C:/Users/smhrd/Desktop/-SNS/WeatherFit/src/main/webapp/assets/uploads";

      File dir = new File(realPath);
      if (!dir.exists())
         dir.mkdirs();

      MultipartRequest multipartRequest = null;
      multipartRequest = new MultipartRequest(request, realPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

      String userId = uvo.getUserId();
      String postContent = multipartRequest.getParameter("postContent");
      String hashTags = multipartRequest.getParameter("hashTags");
      
      int postTemp;
      try {
         postTemp = Integer.parseInt(multipartRequest.getParameterValues("postTemp")[0]);
      } catch(Exception e) {
         postTemp = -999;
      }

      PostVO pvo = new PostVO();
      pvo.setUserId(userId);
      pvo.setPostContent(postContent);
      pvo.setPostTemp(postTemp);
      pvo.setHashTag(hashTags);

      DAO dao = new DAO();
      int postIdx = dao.insertPost(pvo);

      if (postIdx >= 0) {
         FileVO fvo = new FileVO();
         fvo.setFileRname(multipartRequest.getFilesystemName("postImg")); // 업로드한 파일 이름
         fvo.setFileSize(multipartRequest.getFile("postImg").length()); // 파일 크기
         fvo.setFileExt(FilenameUtils.getExtension(multipartRequest.getFilesystemName("postImg"))); // 파일 확장자
         fvo.setPostIdx(postIdx);

         dao.insertFile(fvo);

         TimeUnit.SECONDS.sleep(4);

      }

      return "redirect:/gomain.do";

   }

}