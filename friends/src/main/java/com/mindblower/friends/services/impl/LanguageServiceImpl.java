package com.mindblower.friends.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindblower.friends.Repositories.LanguageRepo;
import com.mindblower.friends.entities.Education;
import com.mindblower.friends.entities.Language;
import com.mindblower.friends.entities.User;
import com.mindblower.friends.exception.ResourceNotFoundException;
import com.mindblower.friends.services.LanguageService;
import com.mindblower.friends.services.UserService;

@Service
public class LanguageServiceImpl implements LanguageService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LanguageRepo languageRepo;

	@Override
	public Language createLanguage(Language language, User user) {
		
		Language updatedLanguage = languageRepo.save(language);
		
		user.getLanguage_known().add(updatedLanguage);
		userService.updateUser(user, user.getId());
		
		return updatedLanguage;
	}

	@Override
	public Language updateLanguage(Language language, Integer languageIdInteger) {
		
		languageRepo.findById(languageIdInteger).orElseThrow(()->new ResourceNotFoundException("Education", "Id", languageIdInteger));
		Language updatedLanguage = languageRepo.save(language);
		return updatedLanguage;
	}

	@Override
	public void deleteLanguage(Integer languageIdInteger) {
		
		try {
			languageRepo.deleteById(languageIdInteger);
		} catch (Exception e) {
			
			throw new ResourceNotFoundException("Language", "Id", languageIdInteger);
		}
	}

}
