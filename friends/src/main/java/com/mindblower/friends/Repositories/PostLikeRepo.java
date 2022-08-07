package com.mindblower.friends.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.mindblower.friends.entities.PostLikes;

@Repository
@EnableJpaRepositories
public interface PostLikeRepo extends JpaRepository<PostLikes, Integer>{

	List<PostLikes> findByPost(Integer id);
}
