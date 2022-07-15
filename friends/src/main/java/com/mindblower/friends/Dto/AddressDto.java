package com.mindblower.friends.Dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddressDto {

	private Integer id;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String type;
	
	@NotBlank(message = "Provide that Who can see your Address ?")
	private String visibility;


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
}
