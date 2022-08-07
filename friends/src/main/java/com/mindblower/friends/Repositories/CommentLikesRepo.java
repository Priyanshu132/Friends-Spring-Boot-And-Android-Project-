package com.mindblower.friends.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.mindblower.friends.entities.CommentLikes;

@Repository
@EnableJpaRepositories
public interface CommentLikesRepo extends JpaRepository<CommentLikes, Integer> {

	List<CommentLikes> findByComment(Integer commentIds);
}
