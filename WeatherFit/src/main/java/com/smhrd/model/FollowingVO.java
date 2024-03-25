package com.smhrd.model;

import java.sql.Timestamp;

public class FollowingVO {
	// 팔로잉 식별자
    private int followIdx;

    // 팔로워
    private String follower;

    // 팔로위
    private String followee;

    // 팔로잉 날짜
    private Timestamp followedAt;

    
    
    
    
    
    
    public FollowingVO() {

    }

	public FollowingVO(int followIdx, String follower, String followee, Timestamp followedAt) {
		this.followIdx = followIdx;
		this.follower = follower;
		this.followee = followee;
		this.followedAt = followedAt;
	}

	public int getFollowIdx() {
        return followIdx;
    }

    public void setFollowIdx(int followIdx) {
        this.followIdx = followIdx;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowee() {
        return followee;
    }

    public void setFollowee(String followee) {
        this.followee = followee;
    }

    public Timestamp getFollowedAt() {
        return followedAt;
    }

    public void setFollowedAt(Timestamp followedAt) {
        this.followedAt = followedAt;
    }
}
