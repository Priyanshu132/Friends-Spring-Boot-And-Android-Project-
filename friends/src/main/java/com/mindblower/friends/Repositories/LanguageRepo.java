package com.mindblower.friends.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindblower.friends.entities.Language;

public interface LanguageRepo extends JpaRepository<Language, Integer> {

}
