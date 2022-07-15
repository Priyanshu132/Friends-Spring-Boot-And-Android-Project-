package com.mindblower.friends.services;

import com.mindblower.friends.entities.Language;
import com.mindblower.friends.entities.User;

public interface LanguageService {

	Language createLanguage(Language language,User user);
	
	Language updateLanguage(Language language,Integer languageIdInteger);
	
	void deleteLanguage(Integer languageIdInteger);
}
