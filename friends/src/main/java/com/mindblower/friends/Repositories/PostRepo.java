package com.mindblower.friends.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindblower.friends.entities.Post;

public interface PostRepo extends JpaRepository<Post, Integer> {

}
