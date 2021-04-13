package com.rj.bd.dormitory.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.dormitory.entity.Dormitory;
import com.rj.bd.dormitory.service.DormitoryService;


/**
 * @desc 宿舍楼控制层
 * @author 马兴佳
 * @time 2021--04--13
 */
@Controller
@ResponseBody
@CrossOrigin
@RequestMapping("/dormitory")
public class DormitoryController {
	@Autowired
	private DormitoryService dormitoryService;
	
	Dormitory dormitory = new Dormitory();
	
	
	/**
	 * @desc 查询全部宿舍楼
	 * @param request
	 * @return
	 */
	@RequestMapping("/query")
	public Map<String, Object> queryDormitory(HttpServletRequest request){
	Map<String, Object> json = new HashMap<String, Object>();
	List<Dormitory> list = dormitoryService.queryAll();
	for (Dormitory dormitory : list) {
		System.out.println(dormitory);
	}
	if (list==null|| list.size()==0) {
		json.put("code", -1);
		json.put("msg", "还没有宿舍楼请加急建设");
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
	
	String id = request.getParameter("dormitory_id");
	
	int dormitory_id  =Integer.parseInt(id);
	if (dormitory_id==0) {
		json.put("code", -1);
		json.put("msg", "请选择");
		
		return json;
		
	}
		dormitoryService.deletet(dormitory_id);
		json.put("code", 200);
		json.put("msg", "请求成功");
		
		return json;
	}
	
	
	/**
	 * @desc 添加楼层
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	public Map<String, Object> add( String dormitory_name,String dormitory_count, HttpServletRequest request){
		Map<String, Object> json = new HashMap<String, Object>();
		//String dormitory_name = request.getParameter("dormitory_name");
		//String num = request.getParameter("dormitory_count");
		//Integer dormitory_count=Integer.parseInt(num);
		if (dormitory_name==null || dormitory_name.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入宿舍楼名字");
			return json;
		}
		if (dormitory_count == null || dormitory_count.equals("")) {
			
			json.put("code", -1);
			json.put("msg", "请输入宿舍楼层总容量");
			return json;
		}
		Dormitory dormitory = new Dormitory();
		dormitory.setDormitory_name(dormitory_name);
		try {
			dormitory.setDormitory_count(Integer.parseInt(dormitory_count));
		} catch (NumberFormatException e) {
			json.put("code", -1);
			json.put("msg", "请输入正确的数值");
			return json;
		}
		dormitory.toString();
		
		
		dormitoryService.add(dormitory);
		json.put("code", 200);
		json.put("msg", "添加成功");
		
		
		return json;
	}
	
	
}
