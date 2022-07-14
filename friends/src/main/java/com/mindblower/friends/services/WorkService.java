package com.mindblower.friends.services;

import com.mindblower.friends.entities.Language;
import com.mindblower.friends.entities.Work;

public interface WorkService {

	Work createWork(Work work,Integer userIdInteger);
	
	Work updateWork(Work work,Integer workIdInteger);
	
	void deleteWork(Integer workIdInteger);
}
