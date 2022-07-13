package com.mindblower.friends.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindblower.friends.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
