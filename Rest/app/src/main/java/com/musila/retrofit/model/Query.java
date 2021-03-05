package com.musila.retrofit.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Query{

	@SerializedName("search")
	private List<SearchItem> search;

	@SerializedName("searchinfo")
	private Searchinfo searchinfo;

	public List<SearchItem> getSearch(){
		return search;
	}

	public Searchinfo getSearchinfo(){
		return searchinfo;
	}
}