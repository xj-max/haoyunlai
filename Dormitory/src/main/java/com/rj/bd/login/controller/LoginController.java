package com.rj.bd.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.login.entity.Login;
import com.rj.bd.login.service.LoginService;

/**
 * @desc 登入
 * @author 马兴佳
 * @time 2021--04--12
 */
@Controller
@RequestMapping("/user")
public class LoginController {
	@Autowired
	private LoginService loginService;
	/**
	 * 登入模块
	 * 
	 */
	
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request){
		System.out.println("login");
		//存放json
		Map<String, Object> json = new HashMap<String, Object>();
		String u_name=request.getParameter("u_name");
		String u_password=request.getParameter("u_password");
		
		//判空
		
		if (u_name==null||u_name.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入用户名字");
			return json;
		}
		
		if (u_password==null || u_password.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入密码");
			return json;
		}
		
		Login login = loginService.queryUserByNameAndPass(u_name,u_password);
		if (login==null) {
			json.put("code", -1);
			json.put("msg", "用户名或密码错误");
			return json;
		}
		
		
		//更新token
		String token =DigestUtils.md5Hex(u_name+u_password+request.getSession().getId());
		loginService.updateToken(token,login.getU_id());
		
		
		json.put("code", 200);
		json.put("msg", "请求成功");
		json.put("token", token);
		System.out.println(token);
		return json;
		
		
		
		
		
	}
	
	
	
	
}
