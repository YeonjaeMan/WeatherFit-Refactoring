package com.smhrd.model;


import java.sql.Timestamp;

public class RoomVO {
	// 방 식별자
    private int roomIdx;

    // 방 제목
    private String roomTitle;

    // 방 개설자
    private String userId;

    // 방 인원수
    private int roomLimit;

    // 방 상태
    private String roomStatus;

    // 방 개설일자
    private Timestamp opendAt;

    
    
    
    public RoomVO() {
    	
	}

	public RoomVO(int roomIdx, String roomTitle, String userId, int roomLimit, String roomStatus,
			Timestamp opendAt) {
		this.roomIdx = roomIdx;
		this.roomTitle = roomTitle;
		this.userId = userId;
		this.roomLimit = roomLimit;
		this.roomStatus = roomStatus;
		this.opendAt = opendAt;
	}

	public int getRoomIdx() {
        return roomIdx;
    }

    public void setRoomIdx(int roomIdx) {
        this.roomIdx = roomIdx;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRoomLimit() {
        return roomLimit;
    }

    public void setRoomLimit(int roomLimit) {
        this.roomLimit = roomLimit;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Timestamp getOpendAt() {
        return opendAt;
    }

    public void setOpendAt(Timestamp opendAt) {
        this.opendAt = opendAt;
    }
}
