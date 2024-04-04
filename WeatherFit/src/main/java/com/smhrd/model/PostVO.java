package com.smhrd.model;

import java.sql.Timestamp;

public class PostVO {

	// 게시글

	// 글 식별자
	private int postIdx;

	// 글 내용
	private String postContent;

	// 작성 일자
	private Timestamp createdAt;

	// 작성자 아이디
	private String userId;
	// UserVO ? String? nickname?

	// 글 조회수
	private int postViews;

	// 글 좋아요수
	private int postLikes;

	// 해시 태그
	private String hashTag;

	// 기온
	private int postTemp;

	public int getPostTemp() {
		return postTemp;
	}

	public void setPostTemp(int postTemp) {
		this.postTemp = postTemp;
	}

	public int getPostIdx() {
		return postIdx;
	}

	public void setPostIdx(int postIdx) {
		this.postIdx = postIdx;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getPostViews() {
		return postViews;
	}

	public void setPostViews(int postViews) {
		this.postViews = postViews;
	}

	public int getPostLikes() {
		return postLikes;
	}

	public void setPostLikes(int postLikes) {
		this.postLikes = postLikes;
	}

	public String getHashTag() {
		return hashTag;
	}

	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}

	// tbPost 모델 복사
//	    public void CopyData(tbPost param)
//	    {
//	        this.postIdx = param.getPostIdx();
//	        this.postContent = param.getPostContent();
//	        this.createdAt = param.getCreatedAt();
//	        this.userId = param.getUserId();
//	        this.postViews = param.getPostViews();
//	        this.postLikes = param.getPostLikes();
//	        this.hashTag = param.getHashTag();
//	    }

	public PostVO() {

	}

	public PostVO(int postIdx, String postContent, String userId, int postViews, int postLikes, String hashTag) {
		this.postIdx = postIdx;
		this.postContent = postContent;
		this.userId = userId;
		this.postViews = postViews;
		this.postLikes = postLikes;
		this.hashTag = hashTag;
	}

}
