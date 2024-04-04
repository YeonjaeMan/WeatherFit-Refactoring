package com.smhrd.model;

public class LikeVO {

	private int likeIdx;
	private int postIdx;
	private String userId;

	public int getLikeIdx() {
		return likeIdx;
	}

	public void setLikeIdx(int likeIdx) {
		this.likeIdx = likeIdx;
	}

	public int getPostIdx() {
		return postIdx;
	}

	public void setPostIdx(int postIdx) {
		this.postIdx = postIdx;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LikeVO(int likeIdx, int postIdx, String userId) {
		this.likeIdx = likeIdx;
		this.postIdx = postIdx;
		this.userId = userId;
	}

	public LikeVO() {

	}

}
