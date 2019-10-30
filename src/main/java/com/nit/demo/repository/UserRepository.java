package com.nit.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nit.demo.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
				@Query(value="SELECT email FROM UserEntity")
				public List<String> findAllEmails();
	
				@Query(value="select email from UserEntity where id=:uid")
				public String findUserEmailById(Integer uid);

}
