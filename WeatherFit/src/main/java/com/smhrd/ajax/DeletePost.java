package com.smhrd.ajax;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.database.DAO;
import com.smhrd.model.FileVO;
import com.smhrd.model.PostVO;

public class DeletePost implements AjaxCommand {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int postIdx = Integer.parseInt(request.getParameter("postIdx"));

		PostVO pvo = new PostVO();
		pvo.setPostIdx(postIdx);
		
//		FileVO fvo = new FileVO();
//		fvo.setPostIdx(postIdx);
		
		DAO dao = new DAO();
//		FileVO resultVO = dao.selectFile(fvo);
//		String fileName = resultVO.getFileRname();
//		System.out.println(fileName);
//		File file = new File("C:/Users/smhrd/Desktop/-SNS/WeatherFit/src/main/webapp/assets/uploads/" + fileName);
//		if(file.exists()) {
//			boolean isDeleted = file.delete();
//			
//			if(isDeleted) {
//				System.out.println("파일 삭제 성공~!");
//			} else {
//				System.out.println("파일 삭제 실패.. ㅠ");
//			}
//		} else {
//			System.out.println("파일이 존재하지 않습니다..ㅠ");
//		}
//		dao.deleteFile(fvo);
		dao.deletePost(pvo);
	}
	
}
