package com.mindblower.friends.services;

import com.mindblower.friends.entities.Language;

public interface LanguageService {

	Language createLanguage(Language language,Integer userIdInteger);
	
	Language updateLanguage(Language language,Integer languageIdInteger);
	
	void deleteLanguage(Integer languageIdInteger);
}
