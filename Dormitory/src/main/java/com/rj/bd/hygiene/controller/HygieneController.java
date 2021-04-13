package com.rj.bd.hygiene.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.hygiene.entity.Hygiene;
import com.rj.bd.hygiene.service.HygieneService;

/**
 * @desc 卫生管理模块的控制层
 * @author 马兴佳
 * @time 2021--03-17
 */
@Controller
@ResponseBody
@CrossOrigin
@RequestMapping("/hygiene")
public class HygieneController {

	@Autowired
	private HygieneService hygieneService;
	Hygiene hygiene = new Hygiene();
	
	/**
	 * @desc 查询全部卫生信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/query")
	public Map<String, Object> queryUser(HttpServletRequest request){
	Map<String, Object> json = new HashMap<String, Object>();
	List<Hygiene> list = hygieneService.queryAll();
	for (Hygiene Hygiene : list) {
		System.out.println(Hygiene);
	}
	if (list==null|| list.size()==0) {
		json.put("code", -1);
		json.put("msg", "没有卫生信息");
		return json;
	}
		json.put("code", 200);
		json.put("msg", "查询成功");
		json.put("data",list);
		return json;
	}
	
	
	
	/**
	 * @desc 进入修改页面
	 * 
	 * 
	 */
	
	@RequestMapping("/editPage")
	public Map<String, Object> editPage(HttpServletRequest request){
		Map<String, Object> json = new HashMap<String, Object>();
		String id = request.getParameter("h_id");
		int h_id  =Integer.parseInt(id);
		if (h_id==0) {
			json.put("code", -1);
			json.put("msg", "请选择卫生id");
			
			return json;
		}
		//Object dormitory = userService.queryDormitory();
		
		Hygiene hygiene = hygieneService.queryHygieneById(h_id);
		if (hygiene ==null) {
			json.put("code", -1);
			json.put("msg", "获取卫生信息失败");
			return json;
		}
		
		json.put("code", 200);
		json.put("msg", "获取卫生评级信息成功");
		json.put("date", hygiene);
		return json;
	}
	
	
	/**
	 * @desc 修改数据
	 * 
	 */
	@RequestMapping("/edit")
	public Map<String, Object> edit(HttpServletRequest request){
		Map<String, Object> json = new HashMap<String, Object>();
		System.out.println("开始修改");
		String sid = request.getParameter("h_id");
		int h_id  =Integer.parseInt(sid);
		 String h_level= request.getParameter("h_level");
		 String h_time = request.getParameter("h_time");
		 String id = request.getParameter("dorm_id");
		 int dorm_id  =Integer.parseInt(id);
		 
		 if (h_level==null || h_level.equals("")) {
				json.put("code", -1);
				json.put("msg", "请输入卫生等级");
				return json;
			}
			if (h_time==null || h_time.equals("")) {
				json.put("code", -1);
				json.put("msg", "请输入评级时间");
				return json;
			}
			
			if (dorm_id==0) {
				json.put("code", -1);
				json.put("msg", "请输入所评级宿舍id");
				return json;
			}
		
			hygiene.setH_id(h_id);
			hygiene.setDorm_id(dorm_id);
			hygiene.setH_level(h_level);
			hygiene.setH_time(h_time);
			System.out.println(hygiene.toString());
			hygieneService.update(hygiene);
			
			json.put("code", 200);
			json.put("msg", "修改成功");
			
			
		
		return json;
	}
	/**
	 * @desc 卫生修改
	 * @param hygiene
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	public Map<String, Object>add(Hygiene hygiene , HttpServletRequest request){
		Map<String, Object> json = new HashMap<String, Object>();
		String id = request.getParameter("dorm_id");
		int dorm_id  =Integer.parseInt(id);
		String h_level = request.getParameter("h_level");
		String h_time = request.getParameter("h_time");
		if (h_level==null||h_level.equals("")) {
			json.put("code", "-1");
			json.put("msg", "请输入卫生等级");
			return json;
		}
		if (h_time==null||h_time.equals("")) {
			json.put("code", "-1");
			json.put("msg", "请输入卫生时间");
			return json;
		}
		if (dorm_id==0) {
			json.put("code", "-1");
			json.put("msg", "请输入宿舍的编号");
			return json;
		}
		
		hygiene.setDorm_id(dorm_id);
		hygiene.setH_level(h_level);
		hygiene.setH_time(h_time);
		
		hygieneService.add(hygiene);
		json.put("code", 200);
		json.put("msg", "添加成功");
		
		return json;
	}
	
	
	
}
