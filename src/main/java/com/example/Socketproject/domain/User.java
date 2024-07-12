package com.example.Socketproject.domain;

public class User {

	private String id;
	private String name;
    private String gender;
    private String interestedIn;
    private Boolean isAvailable=true;
	public String getName() {
		return name;
	}
	public Boolean getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getInterestedIn() {
		return interestedIn;
	}
	public void setInterestedIn(String interestedIn) {
		this.interestedIn = interestedIn;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
