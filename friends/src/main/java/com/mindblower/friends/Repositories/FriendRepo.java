package com.mindblower.friends.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.mindblower.friends.entities.Friend;

@Repository
@EnableJpaRepositories
public interface FriendRepo extends JpaRepository<Friend, Integer> {

	Friend findByUserId(Integer userId);
}
