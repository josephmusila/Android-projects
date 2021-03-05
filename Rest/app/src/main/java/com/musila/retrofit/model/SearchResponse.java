package com.musila.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class SearchResponse{

	@SerializedName("batchcomplete")
	private String batchcomplete;

	@SerializedName("continue")
	private JsonMemberContinue jsonMemberContinue;

	@SerializedName("query")
	private Query query;

	public String getBatchcomplete(){
		return batchcomplete;
	}

	public JsonMemberContinue getJsonMemberContinue(){
		return jsonMemberContinue;
	}

	public Query getQuery(){
		return query;
	}
}