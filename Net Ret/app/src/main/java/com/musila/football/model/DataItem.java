package com.musila.football.model;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("birthday")
	private String birthday;

	@SerializedName("country")
	private Country country;

	@SerializedName("firstname")
	private String firstname;

	@SerializedName("img")
	private String img;

	@SerializedName("player_id")
	private int playerId;

	@SerializedName("weight")
	private Object weight;

	@SerializedName("age")
	private int age;

	@SerializedName("lastname")
	private String lastname;

	@SerializedName("height")
	private int height;

	public String getBirthday(){
		return birthday;
	}

	public Country getCountry(){
		return country;
	}

	public String getFirstname(){
		return firstname;
	}

	public String getImg(){
		return img;
	}

	public int getPlayerId(){
		return playerId;
	}

	public Object getWeight(){
		return weight;
	}

	public int getAge(){
		return age;
	}

	public String getLastname(){
		return lastname;
	}

	public int getHeight(){
		return height;
	}
}