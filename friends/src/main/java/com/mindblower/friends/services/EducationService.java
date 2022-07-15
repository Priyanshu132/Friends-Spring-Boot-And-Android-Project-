package com.mindblower.friends.services;

import com.mindblower.friends.entities.Education;
import com.mindblower.friends.entities.User;

public interface EducationService {

	Education createEducation(Education education,User user);
	
	Education updateEducation(Education education,Integer contactIdInteger);
	
	void deleteEducation(Integer contactIdInteger);
}
