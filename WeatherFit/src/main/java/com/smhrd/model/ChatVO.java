package com.smhrd.model;

import java.sql.Timestamp;

public class ChatVO {
	 // 채팅 식별자
    private int chatIdx;

    // 방 식별자
    private int  roomIdx;

    // 발화 자
    private String chatter;

    // 발화 내용
    private String chat;

    // 발화 이모티콘
    private String emoticon;

    // 발화 시간
    private Timestamp chattedAt;

    
    
    
    
    
    public ChatVO() {
    	
	}

	public ChatVO(int chatIdx, int roomIdx, String chatter, String chat, String emoticon, Timestamp chattedAt) {
		this.chatIdx = chatIdx;
		this.roomIdx = roomIdx;
		this.chatter = chatter;
		this.chat = chat;
		this.emoticon = emoticon;
		this.chattedAt = chattedAt;
	}

	public int getChatIdx() {
        return chatIdx;
    }

    public void setChatIdx(int chatIdx) {
        this.chatIdx = chatIdx;
    }

    public int getRoomIdx() {
        return roomIdx;
    }

    public void setRoomIdx(int roomIdx) {
        this.roomIdx = roomIdx;
    }

    public String getChatter() {
        return chatter;
    }

    public void setChatter(String chatter) {
        this.chatter = chatter;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getEmoticon() {
        return emoticon;
    }

    public void setEmoticon(String emoticon) {
        this.emoticon = emoticon;
    }

    public Timestamp getChattedAt() {
        return chattedAt;
    }

    public void setChattedAt(Timestamp chattedAt) {
        this.chattedAt = chattedAt;
    }
}
