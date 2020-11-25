package com.mobile.application.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mobile.application.model.User;
import com.mobile.application.repository.UserRepository;

@Service
@Transactional
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void saveMyUser(User user) {
		userRepository.save(user);
	}
	
	public Optional<User> validateMyUser(User user ) {
		Optional<User> u =  userRepository.findById(user.getEmail());
		return u;
	}

	public User findByEmailAndPassword(String email, String password) {
		return ((UserService) userRepository).findByEmailAndPassword(email, password);
	}

}
