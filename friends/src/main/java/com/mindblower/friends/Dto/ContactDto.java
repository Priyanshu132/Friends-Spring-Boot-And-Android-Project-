package com.mindblower.friends.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ContactDto {

	private Integer id;
	
	@NotNull(message = "User Id can't be Null !!")
	private Integer userIdInteger;
	
	@NotNull(message = "Value Can't be Null !! ")
	private String contactValue;
	
	@NotBlank(message = "Provide that which type of contact is this ? ")
	private String type;
	
	@NotBlank(message = "Provide that Who can see your Contact ?")
	private String visibility;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserIdInteger() {
		return userIdInteger;
	}

	public void setUserIdInteger(Integer userIdInteger) {
		this.userIdInteger = userIdInteger;
	}

	public String getContactValue() {
		return contactValue;
	}

	public void setContactValue(String contactValue) {
		this.contactValue = contactValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	
	
}
