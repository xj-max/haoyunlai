package com.rj.bd.student.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.classes.entity.Classes;
import com.rj.bd.dorm.entity.Dorm;
import com.rj.bd.student.entity.Student;
import com.rj.bd.student.service.StudentService;

/**
 * @desc 学生模块控制层
 * @author 马兴佳
 * @time 2021--04-13
 */
@Controller
@ResponseBody
@CrossOrigin("*")
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	Student student = new Student();
	
	@RequestMapping("/query")
	public Map<String, Object> queryStudent(HttpServletRequest request){
		Map<String, Object> json = new HashMap<String, Object>();
		List<Student> list = studentService.queryAll();
		for (Student student : list) {
			System.out.println(student);
		}
		
		if (list==null || list.size()==0) {
				json.put("code", -1);
				json.put("msg", "未有学生入住");
				
				return json;
		}
		
		json.put("code", 200);
		json.put("msg", "查询成功");
		json.put("data", list);
		
		
		return json;
	}
	
	/**
	 * @desc 进入修改页面
	 * 
	 */
	
	@RequestMapping("/editPage")
	public Map<String, Object> editPage(HttpServletRequest request){
		Map<String, Object> json = new HashMap<String, Object>();
		String id = request.getParameter("s_id");
		int s_id  =Integer.parseInt(id);
		
		if (s_id==0) {
			json.put("code", -1);
			json.put("msg", "请选择学生");
			return json;
			
		}
		
		Student student = studentService.queryStudenById(s_id);
		
		if (student==null) {
			json.put("code", -1);
			json.put("msg","获取学生信息失败");
			return json;
		}
		
		
		json.put("code", 200);
		json.put("msg", "查询成功");
		json.put("data", student);
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
		String sid = request.getParameter("s_id");
		int s_id  =Integer.parseInt(sid);
		String s_name = request.getParameter("s_name");
		String s_number = request.getParameter("s_number");
		String s_tel = request.getParameter("s_tel");
		String cid = request.getParameter("c_id");
		int c_id  =Integer.parseInt(cid);
		String did = request.getParameter("dorm_id");
		int dorm_id  =Integer.parseInt(did);
		
		if (s_name==null || s_name.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入学生名字");
			return json;
		}
		if (s_number==null || s_number.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入学生学号");
			return json;
		}
		if (s_tel==null || s_tel.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入学生电话");
			return json;
		}
		if (dorm_id==0) {
			json.put("code", -1);
			json.put("msg", "请输入宿舍id");
			return json;
		}
		if (c_id==0) {
			json.put("code", -1);
			json.put("msg", "请输入班级id");
			return json;
		}
		
		student.setS_id(s_id);
		student.setS_name(s_name);
		student.setS_tel(s_tel);
		student.setS_number(s_number);
		
		Classes classes = new Classes();
		classes.setC_id(c_id);
		student.setClasses(classes);
		
		Dorm dorm = new Dorm();
		dorm.setDorm_id(dorm_id);
		student.setDorm(dorm);
		
		studentService.update(student);
		
		System.out.println(student.toString());
		
		json.put("code", -1);
		json.put("msg", "修改成功");
		
		return json;
	}
	
	
	
	
	
}
