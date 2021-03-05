package com.musila.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class JsonMemberContinue{

	@SerializedName("sroffset")
	private int sroffset;

	@SerializedName("continue")
	private String jsonMemberContinue;

	public int getSroffset(){
		return sroffset;
	}

	public String getJsonMemberContinue(){
		return jsonMemberContinue;
	}
}