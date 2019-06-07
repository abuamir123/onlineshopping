package com.info.service;

import com.info.model.User;

public interface UserService {
	
	public User findByEmail(String email);
	
	public void save(User user);
	
	public void update(User user);
}
