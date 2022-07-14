package com.mindblower.friends.services;

import com.mindblower.friends.entities.Education;

public interface EducationService {

	Education createEducation(Education education,Integer userIdInteger);
	
	Education updateEducation(Education education,Integer contactIdInteger);
	
	void deleteEducation(Integer contactIdInteger);
}
