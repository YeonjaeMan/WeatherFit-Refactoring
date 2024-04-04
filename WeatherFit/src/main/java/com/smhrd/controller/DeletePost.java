package com.smhrd.controller;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.database.DAO;
import com.smhrd.model.FileVO;
import com.smhrd.model.PostVO;

// 게시글 삭제를 하면 DB에 담겨진 게시물 내용을 삭제하는 컨트롤러
public class DeletePost implements Command {
   
   public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InterruptedException {
      
      int postIdx = Integer.parseInt(request.getParameter("postIdx"));
      String realPath = "C:/Users/smhrd/Desktop/-SNS/WeatherFit/src/main/webapp/assets/uploads";

      PostVO pvo = new PostVO();
      pvo.setPostIdx(postIdx);

      FileVO fvo = new FileVO();
      fvo.setPostIdx(postIdx);
      
      DAO dao = new DAO();
      
      FileVO resultVO = dao.selectFile(fvo);
      File recentFile = new File(realPath + resultVO.getFileRname());
      if(recentFile.exists()) {
         recentFile.delete();
      }
      
      dao.deletePost(pvo);
      
      TimeUnit.SECONDS.sleep(1);
      
      return "redirect:/gomain.do";
   }
   
}