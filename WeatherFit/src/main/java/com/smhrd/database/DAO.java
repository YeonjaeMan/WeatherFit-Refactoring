package com.smhrd.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.smhrd.model.ChatVO;
import com.smhrd.model.CommentVO;
import com.smhrd.model.FileVO;
import com.smhrd.model.LikeVO;
import com.smhrd.model.PostVO;
import com.smhrd.model.RoomVO;
import com.smhrd.model.UserVO;

public class DAO {

	private SqlSessionFactory factory = MySqlSessionManager.getSqlSessionFactory();

	// 로그인
	public UserVO login(UserVO vo) {

		SqlSession session = factory.openSession();
		UserVO resultVO = session.selectOne("login", vo);
		session.close();
		return resultVO;
	}

	// 회원가입
	public int join(UserVO vo) {

		SqlSession session = factory.openSession(true);
		int row = session.insert("join", vo);
		session.close();
		return row;
	}

	// 개인정보 수정
	public int update(UserVO vo) {
		SqlSession session = factory.openSession(true);
		int row = session.update("update", vo);
		session.close();
		return row;
	}

	// 게시물 작성
	public int post(PostVO pvo) {
		SqlSession session = factory.openSession(true);
		int row = session.insert("post", pvo);
		session.close();
		return row;
	}

	public List<Map<String, Object>> getpost() {
		SqlSession session = factory.openSession();
//		PostVO resultPVO = (PostVO)session.selectList("getpost");
		List<Map<String, Object>> result = session.selectList("getpost");
		session.close();
		return result;
	}

	public List<PostVO> Postselect() {

		SqlSession session = factory.openSession();

		List<PostVO> resultList = session.selectList("postselect");
		session.close();
		return resultList;

	}

	public void Comment() {
		SqlSession session = factory.openSession();
		List<CommentVO> resultList = session.selectList(null);
		session.close();

	}

	public List<Map<String, Object>> Search(String keyWord) {
		SqlSession session = factory.openSession();
		List<Map<String, Object>> result = session.selectList("search","%"+keyWord+"%");
		session.close();
		return result;
	}
	
	
	public int sendChat(ChatVO cvo) {
		SqlSession session = factory.openSession(true);
		int row = session.insert("sendchat", cvo);
		session.close();
		return row;
	}
	

	public int createRoom(RoomVO rvo) {
		SqlSession session = factory.openSession(true);
		int row = session.insert("createroom", rvo);
		session.close();
		return row;
	}

	public List<RoomVO> selectRooms(RoomVO rvo) {
		SqlSession session = factory.openSession();
		List<RoomVO> resultList = session.selectList("selectrooms", rvo);
		session.close();
		return resultList;
	}

	public List<ChatVO> selectChats(ChatVO cvo) {
		SqlSession session = factory.openSession();
		List<ChatVO> resultList = session.selectList("selectchats", cvo);
		session.close();
		return resultList;
	}

	public RoomVO checkRoom(RoomVO rvo) {
		SqlSession session = factory.openSession(true);
		RoomVO resultVO = session.selectOne("checkroom", rvo);
		session.close();
		return resultVO;
	}
	
	// 좋아요 기능
	public int insertLike(LikeVO lvo) {
		SqlSession session = factory.openSession(true);
		int row = session.insert("insertLike", lvo);
		session.close();
		return row;
	}
	
	public int deleteLike(LikeVO lvo) {
		SqlSession session = factory.openSession(true);
		int row = session.delete("deleteLike", lvo);
		session.close();
		return row;
	}
	

	public LikeVO selectLike(LikeVO lvo) {
		SqlSession session = factory.openSession();
		LikeVO resultVO = session.selectOne("selectLike", lvo);
		session.close();
		return resultVO;
	}

	public List<LikeVO> countLike(LikeVO lvo) {
		SqlSession session = factory.openSession();
		List<LikeVO> resultList = session.selectList("countLike", lvo);
		session.close();
		return resultList;
	}

	// 내 게시글 확인 기능
	public List<PostVO> selectMinePosts(UserVO uvo) {
		SqlSession session = factory.openSession();
		List<PostVO> resultList = session.selectList("selectMinePosts", uvo);
		session.close();
		return resultList;
	}

	public int insertPost(PostVO pvo) {
		SqlSession session = factory.openSession(true);
		session.insert("insertPost", pvo);
		int lastInsertIdx = session.selectOne("lastInsertId");
		session.close();
		return lastInsertIdx;
	}

	public int insertFile(FileVO fvo) {
		SqlSession session = factory.openSession(true);
		Map<String, Object> param = new HashMap<>();
		param.put("fileRname", fvo.getFileRname());
		param.put("fileData", fvo.getFileData());
		param.put("fileSize", fvo.getFileSize());
		param.put("fileExt", fvo.getFileExt());
		param.put("postIdx", fvo.getPostIdx());
		int row = session.insert("insertFile", param);
		session.close();
		return row;
	}
	
	
}
