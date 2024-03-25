package com.smhrd.model;

public class FileVO {
	
	private int fileIdx;
	
	private int postIdx;
	
	private String fileRname;
	
	private int fileSize;
	
	private String fileExt;

	
	public FileVO() {

	}

	public FileVO(int fileIdx, int postIdx, String fileRname, int fileSize, String fileExt) {
		this.fileIdx = fileIdx;
		this.postIdx = postIdx;
		this.fileRname = fileRname;
		this.fileSize = fileSize;
		this.fileExt = fileExt;
	}

	public int getFileIdx() {
		return fileIdx;
	}

	public void setFileIdx(int fileIdx) {
		this.fileIdx = fileIdx;
	}

	public int getPostIdx() {
		return postIdx;
	}

	public void setPostIdx(int postIdx) {
		this.postIdx = postIdx;
	}

	public String getFileRname() {
		return fileRname;
	}

	public void setFileRname(String fileRname) {
		this.fileRname = fileRname;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	
	
	
	
}
