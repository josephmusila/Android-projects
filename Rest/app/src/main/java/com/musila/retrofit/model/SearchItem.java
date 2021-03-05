package com.musila.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class SearchItem{

	@SerializedName("snippet")
	private String snippet;

	@SerializedName("wordcount")
	private int wordcount;

	@SerializedName("size")
	private int size;

	@SerializedName("ns")
	private int ns;

	@SerializedName("title")
	private String title;

	@SerializedName("pageid")
	private int pageid;

	@SerializedName("timestamp")
	private String timestamp;

	public String getSnippet(){
		return snippet;
	}

	public int getWordcount(){
		return wordcount;
	}

	public int getSize(){
		return size;
	}

	public int getNs(){
		return ns;
	}

	public String getTitle(){
		return title;
	}

	public int getPageid(){
		return pageid;
	}

	public String getTimestamp(){
		return timestamp;
	}
}