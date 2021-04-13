package com.rj.bd.repair.controller;

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
import com.rj.bd.repair.entity.Repair;
import com.rj.bd.repair.service.RepairService;
import com.rj.bd.user.entity.User;

/**
 * @desc 维修表 控制层
 * @author 马兴佳
 * @time 2021--04--17
 */
@Controller
@ResponseBody
@CrossOrigin
@RequestMapping("/repair")
public class RepairController {
	@Autowired
	private RepairService repairService;
	
	Repair repair = new Repair();
	
	
	
	/**
	 * @desc 查询全部
	 * 
	 */
	
	@RequestMapping("/query")
	public Map<String, Object> queryRepair(HttpServletRequest request){
	Map<String, Object> json = new HashMap<String, Object>();
	List<Repair> list = repairService.queryAll();
	for (Repair repair : list) {
		System.out.println(repair);
	}
	if (list==null|| list.size()==0) {
		json.put("code", -1);
		json.put("msg", "暂时没有维修记录");
		return json;
	}
		json.put("code", 200);
		json.put("msg", "查询成功");
		json.put("data",list);
		return json;
	}
	
	
	/**
	 * @desc 添加维修记录
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	public Map<String, Object> add( Repair repair, HttpServletRequest request){
		Map<String, Object> json = new HashMap<String, Object>();
		String r_name = request.getParameter("r_name");
		String r_tel = request.getParameter("r_tel");
		String r_cause = request.getParameter("r_cause");
		String r_time = request.getParameter("r_time");
		String id = request.getParameter("dorm_id");
		int dorm_id  =Integer.parseInt(id);
		if (r_name==null || r_name.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入维修师傅名字");
			return json;
		}
		if (r_tel==null || r_tel.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入维修师傅电话");
			return json;
		}
		if (r_time==null || r_time.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入维修时间");
			return json;
		}
		if (r_cause==null || r_cause.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入维修事件");
			return json;
		}
		if (dorm_id==0) {
			json.put("code", -1);
			json.put("msg", "请输入宿舍楼id");
			return json;
		}
		
		repair.setR_name(r_name);
		repair.setR_tel(r_tel);
		repair.setR_tel(r_tel);
		repair.setR_cause(r_cause);
		repair.setDorm_id(dorm_id);
		
		
	
		
		repairService.add(repair);
		json.put("code", 200);
		json.put("msg", "添加成功");
		
		
		return json;
	}
	
}
