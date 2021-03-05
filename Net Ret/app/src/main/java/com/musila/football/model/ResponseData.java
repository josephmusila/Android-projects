package com.musila.football.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseData{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("query")
	private Query query;

	public List<DataItem> getData(){
		return data;
	}

	public Query getQuery(){
		return query;
	}
}