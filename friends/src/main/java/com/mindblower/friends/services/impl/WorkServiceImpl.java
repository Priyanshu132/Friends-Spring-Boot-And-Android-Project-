package com.mindblower.friends.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindblower.friends.Repositories.WorkRepo;
import com.mindblower.friends.entities.Language;
import com.mindblower.friends.entities.User;
import com.mindblower.friends.entities.Work;
import com.mindblower.friends.exception.ResourceNotFoundException;
import com.mindblower.friends.services.UserService;
import com.mindblower.friends.services.WorkService;

@Service
public class WorkServiceImpl implements WorkService{
	
	@Autowired
	private	UserService userService;
	
	@Autowired
	private WorkRepo workRepo;
	

	@Override
	public Work createWork(Work work, User user) {
		
		Work updatedWork = workRepo.save(work);
		
		user.getWork_place().add(updatedWork);
		userService.updateUser(user);
		
		return updatedWork;
	}

	@Override
	public Work updateWork(Work work, Integer workIdInteger) {
		
		workRepo.findById(workIdInteger).orElseThrow(()->new ResourceNotFoundException("Work", "Id", workIdInteger));
		Work updatedWork = workRepo.save(work);
		return updatedWork;
	}

	@Override
	public void deleteWork(Integer workIdInteger) {
		
		try {
			workRepo.deleteById(workIdInteger);
		} catch (Exception e) {
			
			throw new ResourceNotFoundException("Work", "Id", workIdInteger);
		}
	}

}
