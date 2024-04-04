package com.smhrd.model;

public class CrawlingVO {

	private String src;
	private String tag;
	private String season;
	private String gender;
	private int crawlingIdx;

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getCrawlingIdx() {
		return crawlingIdx;
	}

	public void setCrawlingIdx(int crawlingIdx) {
		this.crawlingIdx = crawlingIdx;
	}

	public CrawlingVO(String src, String tag, String season, String gender, int crawlingIdx) {
		this.src = src;
		this.tag = tag;
		this.season = season;
		this.gender = gender;
		this.crawlingIdx = crawlingIdx;
	}

	public CrawlingVO() {

	}

}
