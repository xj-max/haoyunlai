package com.rj.bd.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.bd.login.dao.LoginMapper;
import com.rj.bd.login.entity.Login;
@Service("loginService")
public class LoginService {
	@Autowired
	private LoginMapper loginMapper;
	/**
	 * 登入
	 * @param u_name
	 * @param u_password
	 * @return
	 */
	public Login queryUserByNameAndPass(String u_name, String u_password) {
		
		return loginMapper.queryUserByNameAndPass(u_name,u_password);
	}
	/**
	 *  跟新token
	 * @param token
	 * @param u_id
	 */
	public void updateToken(String token, int u_id) {
		
		loginMapper.updateToken(token,u_id);
	}

}
