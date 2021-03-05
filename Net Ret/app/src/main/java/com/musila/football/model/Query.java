package com.musila.football.model;

import com.google.gson.annotations.SerializedName;

public class Query{

	@SerializedName("srsearch")
	private String srsearch;

	@SerializedName("apikey")
	private String apikey;

	@SerializedName("format")
	private String format;

	@SerializedName("list")
	private String list;

	@SerializedName("country_id")
	private String countryId;

	public String getSrsearch(){
		return srsearch;
	}

	public String getApikey(){
		return apikey;
	}

	public String getFormat(){
		return format;
	}

	public String getList(){
		return list;
	}

	public String getCountryId(){
		return countryId;
	}
}