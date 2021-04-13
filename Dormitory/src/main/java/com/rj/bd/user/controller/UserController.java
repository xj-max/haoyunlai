package com.rj.bd.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.user.entity.User;
import com.rj.bd.user.service.UserService;

/**
 * @desc 管理员模块
 * @author 马兴佳
 * @time 2021--04--12
 */
@Controller
@ResponseBody
@CrossOrigin
@RequestMapping("/user")

public class UserController {
	@Autowired
	private UserService userService;
	
	
	User user = new User();
	
	/**
	 * @desc 查询全部管理员
	 * @param request
	 * @return
	 */
	@RequestMapping("/query")
	public Map<String, Object> queryUser(HttpServletRequest request){
	Map<String, Object> json = new HashMap<String, Object>();
	List<User> list = userService.queryAll();
	for (User user : list) {
		System.out.println(user);
	}
	if (list==null|| list.size()==0) {
		json.put("code", -1);
		json.put("msg", "还未拥有管理员");
		return json;
	}
		json.put("code", 200);
		json.put("msg", "查询成功");
		return json;
	}
	
	/**
	 * @desc 删除
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	public Map<String, Object>deletet(HttpServletRequest request){
	Map<String, Object> json = new HashMap<String, Object>();
	
	String id = request.getParameter("u_id");
	
	int u_id  =Integer.parseInt(id);
	if (u_id==0) {
		json.put("code", -1);
		json.put("msg", "请选择");
		
		return json;
		
	}
		userService.deletetCharge(u_id);
		userService.deletet(u_id);
		json.put("code", 200);
		json.put("msg", "请求成功");
		
		return json;
	}
	
	/**
	 * @desc 进入修改页面
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/editPage")
	public Map<String, Object> editPage(HttpServletRequest request){
		Map<String, Object> json = new HashMap<String, Object>();
		String id = request.getParameter("u_id");
		int u_id  =Integer.parseInt(id);
		if (u_id==0) {
			json.put("code", -1);
			json.put("msg", "请选择管理员");
			
			return json;
		}
		//Object dormitory = userService.queryDormitory();
		
		User user1 = userService.queryUserById(u_id);
		if (user1 ==null) {
			json.put("code", -1);
			json.put("msg", "获取管理员信息失败");
			return json;
		}
		
		json.put("code", 200);
		json.put("msg", "获取管理员信息成功");
		json.put("date", user1);
		return json;
	}
	
	
	/**
	 * @desc 修改数据
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/edit")
	public Map<String, Object> edit(HttpServletRequest request){
		Map<String, Object> json = new HashMap<String, Object>();
		String sid = request.getParameter("u_id");
		int u_id  =Integer.parseInt(sid);
		String u_name = request.getParameter("u_name");
		String u_password = request.getParameter("u_password");
		String u_tel = request.getParameter("u_tel");
		String id = request.getParameter("dormitory_id");
		int dormitory_id  =Integer.parseInt(id);
		
		if (u_name==null || u_name.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入管理员名字");
			return json;
		}
		if (u_password==null || u_password.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入管理员密码");
			return json;
		}
		if (u_tel==null || u_tel.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入管理员电话");
			return json;
		}
		if (dormitory_id==0) {
			json.put("code", -1);
			json.put("msg", "请输入管理员管理所管理楼层id");
			return json;
		}
		user.setU_id(u_id);
		user.setU_name(u_name);
		user.setU_password(u_password);
		user.setDormitory_id(dormitory_id);
		user.setU_tel(u_tel);
		userService.update(user);
		System.out.println(user.toString());
		json.put("code", 200);
		json.put("msg", "请求成功");
		return json;
	}
	
	
	/**
	 * @desc 添加管理员
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	public Map<String, Object> add( User user, HttpServletRequest request){
		Map<String, Object> json = new HashMap<String, Object>();
		String u_name = request.getParameter("u_name");
		String u_password = request.getParameter("u_password");
		String u_tel = request.getParameter("u_tel");
		String id = request.getParameter("dormitory_id");
		int dormitory_id  =Integer.parseInt(id);
		if (u_name==null || u_name.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入管理员名字");
			return json;
		}
		if (u_password==null || u_password.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入管理员密码");
			return json;
		}
		if (u_tel==null || u_tel.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入管理员电话");
			return json;
		}
		if (dormitory_id==0) {
			json.put("code", -1);
			json.put("msg", "请输入管理员管理所管理楼层id");
			return json;
		}
		
		user.setU_name(u_name);
		user.setU_password(u_password);
		user.setU_tel(u_tel);
		user.setDormitory_id(dormitory_id);
		
		userService.add(user);
		json.put("code", 200);
		json.put("msg", "添加成功");
		
		
		return json;
	}
	
	
}
