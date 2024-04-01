package com.smhrd.database;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.smhrd.model.ChatVO;
import com.smhrd.model.CommentVO;
import com.smhrd.model.FileVO;
import com.smhrd.model.FollowingVO;
import com.smhrd.model.LikeVO;
import com.smhrd.model.PostVO;
import com.smhrd.model.RoomVO;
import com.smhrd.model.UserVO;
import com.smhrd.model.CrawlingVO;

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

	public int comment(CommentVO cvo) {
		SqlSession session = factory.openSession(true);
		int row = session.insert("comment", cvo);
		session.close();
		return row;
	}
	public FileVO selectFile(FileVO fvo) {
		SqlSession session = factory.openSession();
		FileVO resultVO = session.selectOne("selectFile", fvo);
		session.close();
		return resultVO;
	}

	public List<PostVO> selectPosts(PostVO pvo) {

		SqlSession session = factory.openSession();
		List<PostVO> resultVO = session.selectList("selectPosts", pvo);
		session.close();
		return resultVO;

	}
	
	public List<PostVO> Postdetail(PostVO pvo){
		SqlSession session = factory.openSession();
		List<PostVO> resultPVO = session.selectList("postdetail", pvo);
		session.close();
		return resultPVO;
	}
	

	public List<CommentVO> Commentselect(int postIdx) {
		SqlSession session = factory.openSession();
		List<CommentVO> resultList = session.selectList("commentselect",postIdx);
		session.close();
		return resultList;

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

	// 특정 유저 게시글 확인 기능
	public List<PostVO> selectUserPosts(UserVO uvo) {
		SqlSession session = factory.openSession();
		List<PostVO> resultList = session.selectList("selectUserPosts", uvo);
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
		int row = session.insert("insertFile", fvo);
		session.close();
		return row;
	}
	
	// 크롤링 데이터 가져오기
	
	public List<CrawlingVO> selectCrawling(CrawlingVO cvo) {
		SqlSession session = factory.openSession();
		List<CrawlingVO> resultList = session.selectList("selectCrawling", cvo);
		session.close();
		return resultList;
	}

	public List<PostVO> selectRecentPosts() {
		SqlSession session = factory.openSession();
		List<PostVO> resultList = session.selectList("selectRecentPosts");
		session.close();
		return resultList;
	}
	
	public int deletePost(PostVO pvo) {
		SqlSession session = factory.openSession(true);
		int row = session.delete("deletePost", pvo);
		session.close();
		return row;
	}
	
	public int updatePost(PostVO pvo) {
		SqlSession session = factory.openSession(true);
		int row = session.update("updatePost", pvo);
		session.close();
		return row;
	}

	public int updateFile(FileVO fvo) {
		SqlSession session = factory.openSession(true);
		int row = session.update("updateFile", fvo);
		session.close();
		return row;
	}

	public int updateUserInfo(UserVO uvo) {
		SqlSession session = factory.openSession(true);
		int row = session.update("updateUserInfo", uvo);
		session.close();
		return row;
	}

	public int deleteUserInfo(UserVO uvo) {
		SqlSession session = factory.openSession(true);
		int row = session.update("deleteUserInfo", uvo);
		session.close();
		return row;
	}

	public UserVO selectUserInfo(UserVO uvo) {
		SqlSession session = factory.openSession();
		UserVO resultVO = session.selectOne("selectUserInfo", uvo);
		session.close();
		return resultVO;
	}
	
	public int insertFollowing(FollowingVO flvo) {
		SqlSession session = factory.openSession(true);
		int row = session.insert("insertFollowing", flvo);
		session.close();
		return row;
	}
	
	public int deleteFollowing(FollowingVO flvo) {
		SqlSession session = factory.openSession(true);
		int row = session.delete("deleteFollowing", flvo);
		session.close();
		return row;
	}
	
	public FollowingVO selectFollow(FollowingVO flvo) {
		SqlSession session = factory.openSession();
		FollowingVO resultVO = session.selectOne("selectFollow", flvo);
		session.close();
		return resultVO;
	}

}
