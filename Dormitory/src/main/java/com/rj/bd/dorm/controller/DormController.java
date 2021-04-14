package com.rj.bd.dorm.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.dorm.entity.Dorm;
import com.rj.bd.dorm.service.DormService;
import com.rj.bd.dormitory.entity.Dormitory;
import com.rj.bd.dormitory.service.DormitoryService;


/**
 * @desc 寝室控制层
 * @author 马兴佳
 * @time 2021--04--14
 */
@Controller
@ResponseBody
@CrossOrigin("*")
@RequestMapping("/dorm")
public class DormController {
	@Autowired
	private DormService dormService;
	
	Dorm dorm = new Dorm();
	@Autowired
	private DormitoryService dormitoryService;
	
	@RequestMapping("/query")
	public Map<String, Object> query(HttpServletRequest request){
		
		Map<String, Object> json = new HashMap<String, Object>();
		
		List<Dorm> list = dormService.queryAll();
		
		if (list==null||list.size()==0) {
			json.put("code", "-10");
			json.put("msg", "暂无此信息");
			return json;
		}
		json.put("code", "200");
		json.put("msg", "获取成功");
		json.put("data", list);
		return json;
	}
	
	
	/**
	 * @desc 进入修改页面
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/editPage")
	public Map<String, Object> editPage(HttpServletRequest request){
		System.out.println("editPage---->");
		Map<String, Object> json = new HashMap<String, Object>();
		String id = request.getParameter("dorm_id");
		int dorm_id  =Integer.parseInt(id);
		System.out.println("u_id---->"+dorm_id);
		if (dorm_id==0) {
			json.put("dorm_id", -1);
			json.put("msg", "请选择宿舍");
			return json;
		}
		//Object dormitory = userService.queryDormitory();
		List<Dormitory> list = dormitoryService.queryAll();
		List<Dorm> dorms = dormService.queryUserById(dorm_id);
		System.out.println(dorms);
		if (dorms ==null || dorms.size()==0) {
			json.put("code", -1);
			json.put("msg", "获取宿舍信息失败");
			return json;
		}
		
		json.put("code", 200);
		json.put("msg", "获取宿舍信息成功");
		json.put("data", dorms);
		json.put("dormitory", list);
		return json;
	}
	
	
	/**
	 * @desc 修改数据
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	
	@RequestMapping("/edit")
	public Map<String, Object> edit(HttpServletRequest request) throws IOException{
		System.out.println("edit---->");
		Map<String, Object> json = new HashMap<String, Object>();
		String did = request.getParameter("dorm_id");
		int dorm_id  =Integer.parseInt(did);
		String id2 = request.getParameter("dormitory_id");
		int dormitory_id  =Integer.parseInt(id2);
		String id3 = request.getParameter("dorm_sum");
		int dorm_sum  =Integer.parseInt(id3);
		String id4 = request.getParameter("dorm_count");
		int dorm_count  =Integer.parseInt(id4);
		String dorm_number = request.getParameter("dorm_number");
		
		
//		String u_name = request.getParameter("u_name");
//		String u_password = request.getParameter("u_password");
//		String u_tel = request.getParameter("u_tel");
//		String id = request.getParameter("dormitory_id");
//		System.out.println(u_id+"------>"+u_name+"------>"+u_password+"------>"+u_tel+"------>"+id);
//		int dormitory_id  =Integer.parseInt(id);
//		System.out.println(u_id+"------>"+u_name+"------>"+u_password+"------>"+u_tel+"------>"+dormitory_id);
		
		if (dorm_number==null || dorm_number.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入宿舍编号");
			return json;
		}
		if (dormitory_id==0) {
			json.put("code", -1);
			json.put("msg", "请输入宿舍楼id");
			return json;
		}
		if (dorm_sum==0) {
			json.put("code", -1);
			json.put("msg", "请输入该宿舍总床位");
			return json;
		}		
		
		dorm.setDorm_id(dorm_id);
		dorm.setDorm_number(dorm_number);
		dorm.setDorm_sum(dorm_sum);
		dorm.setDorm_count(dorm_count);
		Dormitory dormitory=new Dormitory();
		dormitory.setDormitory_id(dormitory_id);
		dorm.setDormitory(dormitory);
		
		dormService.update(dorm);
		System.out.println(dorm.toString());
		json.put("code", 200);
		json.put("msg", "修改成功");
		return json;
	}
	
	
	
	/**
	 * @desc 进入宿舍添加页面
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/addPage")
	public Map<String, Object> addPage(HttpServletRequest request){
		System.out.println("addPage--------->");
		Map<String, Object> json = new HashMap<String, Object>();
		List<Dormitory> list = dormitoryService.queryAll();
		for (Dormitory dormitory : list) {
			System.out.println(dormitory);
			
		}
		if (list==null|| list.size()==0) {
			json.put("code", -1);
			json.put("msg", "获取宿舍楼信息失败");
			return json;
		}
		json.put("code", 200);
		json.put("msg", "获取宿舍楼信息成功");
		json.put("data", list);
		return json;
	}
	
	
	/**
	 * @desc 添加宿舍
	 * @param user
	 * @param request
	 * @return
	 */
	@CrossOrigin
	@RequestMapping("/add")
	public Map<String, Object> add( Dorm dorm, HttpServletRequest request){
		
		System.out.println("add---->");
		Map<String, Object> json = new HashMap<String, Object>();
		String id2 = request.getParameter("dormitory_id");
		int dormitory_id  =Integer.parseInt(id2);
		String id3 = request.getParameter("dorm_sum");
		int dorm_sum  =Integer.parseInt(id3);
		String id4 = request.getParameter("dorm_count");
		int dorm_count  =Integer.parseInt(id4);
		String dorm_number = request.getParameter("dorm_number");
		if (dorm_number==null || dorm_number.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入宿舍编号");
			return json;
		}
		if (dormitory_id==0) {
			json.put("code", -1);
			json.put("msg", "请输入管理员管理所管理楼层id");
			return json;
		}
		
		if (dorm_sum==0) {
			json.put("code", -1);
			json.put("msg", "请输入总床位");
			return json;
		}
		
		System.out.println("1----->");
		dorm.setDorm_number(dorm_number);
		dorm.setDorm_sum(dorm_sum);
		dorm.setDorm_count(dorm_count);
		System.out.println("2----->");
		Dormitory dormitory=new Dormitory();
		dormitory.setDormitory_id(dormitory_id);
		System.out.println("3----->");
		dorm.setDormitory(dormitory);
		System.out.println("4----->");
		
		dormService.add(dorm);
		System.out.println("5----->");
		json.put("code", 200);
		json.put("msg", "添加成功");
		json.put("data", " ");
		System.out.println("6----->");
		
		return json;
	}
	
	
}
