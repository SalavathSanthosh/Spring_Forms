package com.nit.demo.restcontroller;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nit.demo.entity.UserEntity;
import com.nit.demo.pojo.User;
import com.nit.demo.repository.UserRepository;

@RestController
public class UserRestController {
	
		@Autowired
		UserRepository repo;

		@GetMapping(value="/getUser",produces={"application/json","application/xml"})
		public User getUserById(@RequestParam("id") Integer id) {
			Optional<UserEntity> optUser = repo.findById(id);
			User user = new User();
			if (optUser.isPresent()) {
			UserEntity entity = optUser.get();
			BeanUtils.copyProperties(entity, user);
			}
			return user;
	}

}
