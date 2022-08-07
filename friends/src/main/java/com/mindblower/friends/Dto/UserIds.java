package com.mindblower.friends.Dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public class UserIds {

	@NotNull(message = "Id Can not be Null !!")
	private List<Integer> id;

	public List<Integer> getId() {
		return id;
	}

	public void setId(List<Integer> id) {
		this.id = id;
	}

	
	
}
