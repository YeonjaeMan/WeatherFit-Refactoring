package com.smhrd.controller;

import java.io.*;
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

      // cos 라이브러리를 사용해 multipart/form-data 처리
//      MultipartRequest multipartRequest = null;
      MultipartRequest multipartRequest = new MultipartRequest(request, realPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
//      multipartRequest = new MultipartRequest(request, "", sizeLimit, "utf-8", new DefaultFileRenamePolicy());

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
//         File file = multipartRequest.getFile("postImg"); // 업로드한 파일 객체

//         if(file != null) {
//            fvo.setFileRname(file.getName()); // 업로드한 파일 이름
//            fvo.setFileSize(file.length()); // 파일 크기
//            fvo.setFileExt(FilenameUtils.getExtension(file.getName())); // 파일 확장자
//            fvo.setPostIdx(postIdx);
//
//            // 파일 내용을 byte 배열로 읽기
//            try(FileInputStream fis = new FileInputStream(file);
//                ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
//               byte[] buffer = new byte[1024];
//               int bytesRead;
//               while((bytesRead = fis.read(buffer)) != -1) {
//                  bos.write(buffer, 0, bytesRead);
//               }
//               fvo.setFileImg(bos.toByteArray());
//            } catch(IOException e) {
//               e.printStackTrace();
//            }
//
//            dao.insertFile(fvo);
//         }
         fvo.setFileRname(multipartRequest.getFilesystemName("postImg")); // 업로드한 파일 이름
         fvo.setFileSize(multipartRequest.getFile("postImg").length()); // 파일 크기
         fvo.setFileExt(FilenameUtils.getExtension(multipartRequest.getFilesystemName("postImg"))); // 파일 확장자
         fvo.setPostIdx(postIdx);

         File file = new File(realPath + "/" + multipartRequest.getFilesystemName("postImg"));
//         File file = new File(multipartRequest.getFilesystemName("postImg"));
         byte[] fileImg = new byte[(int) file.length()];
         try(FileInputStream fis = new FileInputStream(file)) {
            fis.read(fileImg);
         }
         fvo.setFileImg(fileImg);

         dao.insertFile(fvo);
      }

      return "redirect:/gomain.do";

   }

}