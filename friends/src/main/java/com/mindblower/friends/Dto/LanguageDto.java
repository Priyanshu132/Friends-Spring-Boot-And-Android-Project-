package com.mindblower.friends.Dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LanguageDto {

	private int id;
	
	@NotNull(message = "User Id can't be Null !!")
	private Integer userIdInteger;
	
	@NotBlank(message = "Name Can't be Null/Blank !! ")
	private String name;
	
	@NotBlank(message = "Provide that Who can see your Education ?")
	private String visibility;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getUserIdInteger() {
		return userIdInteger;
	}

	public void setUserIdInteger(Integer userIdInteger) {
		this.userIdInteger = userIdInteger;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	
}
