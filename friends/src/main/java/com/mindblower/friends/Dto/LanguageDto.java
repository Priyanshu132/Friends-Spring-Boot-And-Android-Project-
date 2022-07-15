package com.mindblower.friends.Dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LanguageDto {

	private Integer id;

	
	@NotBlank(message = "Name Can't be Null/Blank !! ")
	private String name;
	
	@NotBlank(message = "Provide that Who can see your Education ?")
	private String visibility;

	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
