package com.rj.bd.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.rj.bd.user.dao.UserMapper;
import com.rj.bd.user.entity.User;
@Service("userService")
public class UserService {
	@Autowired
	private UserMapper userMapper;
	public List<User> queryAll() {
		
		return userMapper.queryAll();
	}
	public void deletet(int u_id) {
	userMapper.deletet(u_id);
		
	}
	public List<User> queryUserById(int u_id) {
		
		return userMapper.queryUserById(u_id);
	}
	public void deletetCharge(int u_id) {
		userMapper.deletetCharge(u_id);
	}
	public void queryDormitory() {
		userMapper.queryDormitory();
	}
	public void update(User user) {
		
		userMapper.update(user);
	}
	public void add(User user) {
		userMapper.add(user);
		
	}

}
