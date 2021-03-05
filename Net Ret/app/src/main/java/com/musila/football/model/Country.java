package com.musila.football.model;

import com.google.gson.annotations.SerializedName;

public class Country{

	@SerializedName("continent")
	private String continent;

	@SerializedName("country_code")
	private String countryCode;

	@SerializedName("name")
	private String name;

	@SerializedName("country_id")
	private int countryId;

	public String getContinent(){
		return continent;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public String getName(){
		return name;
	}

	public int getCountryId(){
		return countryId;
	}
}