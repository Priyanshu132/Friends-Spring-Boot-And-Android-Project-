package com.mindblower.friends.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mindblower.friends.Repositories.EducationRepo;
import com.mindblower.friends.entities.Education;
import com.mindblower.friends.entities.User;
import com.mindblower.friends.exception.ResourceNotFoundException;
import com.mindblower.friends.services.EducationService;
import com.mindblower.friends.services.UserService;

public class EductaionServiceImpl implements EducationService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EducationRepo educationRepo;

	@Override
	public Education createEducation(Education education, Integer userIdInteger) {
		

		User user = userService.getUserbyId(userIdInteger);
		Education updatedEducation = educationRepo.save(education);
		
		user.getEducation().add(updatedEducation);
		userService.updateUser(user, userIdInteger);
		
		return updatedEducation;
	}

	@Override
	public Education updateEducation(Education education, Integer educationIdInteger) {
		
		educationRepo.findById(educationIdInteger).orElseThrow(()->new ResourceNotFoundException("Education", "Id", educationIdInteger));
		Education updatedEducation = educationRepo.save(education);
		return updatedEducation;
	}

	@Override
	public void deleteEducation(Integer educationIdInteger) {
		
		try {
			educationRepo.deleteById(educationIdInteger);
		} catch (Exception e) {
			
			throw new ResourceNotFoundException("Education", "Id", educationIdInteger);
		}
	}

}
