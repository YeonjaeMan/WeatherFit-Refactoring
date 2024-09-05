package com.smhrd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.database.DAO;
import com.smhrd.model.UserVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// 회원가입에 필요한 정보들을 불러오고 회원정보를 DB에 저장해주는 클래스
public class Join implements Command {

   public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

      String userId = request.getParameter("userId");
      String userPw = request.getParameter("userPw");
      String userName = request.getParameter("userName");
      String userNick = request.getParameter("userNick");
      String userGender = request.getParameter("userGender");
      String userRegion = request.getParameter("userRegion");
      double userWeight = Double.parseDouble(request.getParameter("userWeight"));
      double userHeight = Double.parseDouble(request.getParameter("userHeight"));

      UserVO vo = new UserVO();
      vo.setUserId(userId);
      vo.setUserPw(userPw);
      vo.setUserName(userName);
      vo.setUserNick(userNick);
      vo.setUserGender(userGender);
      vo.setUserWeight(userWeight);
      vo.setUserHeight(userHeight);
      vo.setUserRegion(userRegion);
//      vo.setUserProfileImg("base_profile.png");
      vo.setUserProfileInfo("자기소개를 등록해주세요.");

      File file = new File("/Users/jeong-yeonjae/Desktop/dev/WeatherFit-Refactoring/WeatherFit/src/main/webapp/assets/images/user_profile/base_profile.png");
      byte[] fileImg = new byte[(int) file.length()];
      try(FileInputStream fis = new FileInputStream(file)) {
         fis.read(fileImg);
      }
      vo.setUserProfileImg(fileImg);

      DAO dao = new DAO();
      
      int row = dao.join(vo);
      if (row > 0) {
         request.setAttribute("userId", userId);
      }
      
      return "redirect:/gomain.do";
   }

}