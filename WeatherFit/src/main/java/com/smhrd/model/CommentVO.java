package com.smhrd.model;

import java.sql.Timestamp;

public class CommentVO {
	// 댓글

	    // 댓글 식별자
	    private int cmtIdx;

	    // 원글 식별자
	    private int postIdx;

	    // 댓글 내용
	    private String cmtContent;

	    // 댓글 작성일자
	    private Timestamp createdAt;

	    // 댓글 작성자
	    private String userId;

	    // 댓글 공감수
	    private Integer cmtLikes;

	    
	    
	    
	    
	    
	    
	    public CommentVO() {
	    	
		}

		public CommentVO(int cmtIdx, int postIdx, String cmtContent, Timestamp createdAt, String userId,
				Integer cmtLikes) {
			this.cmtIdx = cmtIdx;
			this.postIdx = postIdx;
			this.cmtContent = cmtContent;
			this.createdAt = createdAt;
			this.userId = userId;
			this.cmtLikes = cmtLikes;
		}

		public int getCmtIdx() {
	        return cmtIdx;
	    }

	    public void setCmtIdx(int cmtIdx) {
	        this.cmtIdx = cmtIdx;
	    }

	    public int getPostIdx() {
	        return postIdx;
	    }

	    public void setPostIdx(int postIdx) {
	        this.postIdx = postIdx;
	    }

	    public String getCmtContent() {
	        return cmtContent;
	    }

	    public void setCmtContent(String cmtContent) {
	        this.cmtContent = cmtContent;
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

	    public Integer getCmtLikes() {
	        return cmtLikes;
	    }

	    public void setCmtLikes(Integer cmtLikes) {
	        this.cmtLikes = cmtLikes;
	    }

	   
}
