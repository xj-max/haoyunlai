package com.rj.bd.classes.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.classes.entity.Classes;
import com.rj.bd.classes.service.ClassesService;
import com.rj.bd.dormitory.entity.Dormitory;
import com.rj.bd.student.entity.Student;


/**
 * @desc 班级的控制层
 * @author 马兴佳
 *
 */

@Controller
@ResponseBody
@CrossOrigin
@RequestMapping("/classes")
public class ClassesController {
	@Autowired
	private ClassesService classesService;
	Classes classes = new Classes();

	/**
	 * @desc 查询全部管理员
	 * @param request
	 * @return
	 */
	@RequestMapping("/query")
	public Map<String, Object> queryClasses(HttpServletRequest request){
	Map<String, Object> json = new HashMap<String, Object>();
	List<Classes> list = classesService.queryAll();
	for (Classes classes : list) {
		System.out.println(classes);
	}
	if (list==null|| list.size()==0) {
		json.put("code", -1);
		json.put("msg", "还未拥有管理员");
		return json;
	}
		json.put("code", 200);
		json.put("msg", "查询成功");
		json.put("data",list);
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
	System.out.println("进行删除");
	String id = request.getParameter("c_id");
	int c_id  =Integer.parseInt(id);
	if (c_id==0) {
		json.put("code", -1);
		json.put("msg", "请选择");
		
		return json;
		
	}
		classesService.deleteStudentAndByCid(c_id);
		classesService.deletet(c_id);
		json.put("code", 200);
		json.put("msg", "请求成功");
		
		return json;
	}
	
	/**
	 * @desc 添加班级
	 */
	@RequestMapping("/add")
	public Map<String, Object> add(Classes classes ,HttpServletRequest request)
	{
	Map<String, Object> json = new HashMap<String, Object>();
	String c_tel = request.getParameter("c_tel");
	String c_name = request.getParameter("c_name");
	String c_counselor = request.getParameter("c_counselor");
	
	if (c_tel==null || c_tel.equals("")) {
		json.put("code", -1);
		json.put("msg", "请输入班级编号");
		return json;
	}
	
	if (c_name==null || c_name.equals("")) {
		json.put("code", -1);
		json.put("msg", "请输入班级编号");
		return json;
	}
	
	if (c_counselor==null || c_counselor.equals("")) {
		json.put("code", -1);
		json.put("msg", "请输入班级编号");
		return json;
	}
	
		classes.setC_tel(c_tel);
		classes.setC_name(c_name);
		classes.setC_counselor(c_counselor);
		
		classesService.add(classes);
		json.put("code", 200);
		json.put("msg", "请求成功");
		
		return json;
	}
	
	
	/**
	 * @desc 进入班级修改页面
	 * 
	 * 
	 */
	@RequestMapping("/editPage")
	public Map<String, Object> editPage(HttpServletRequest request){
		Map<String, Object> json = new HashMap<String, Object>();
		String id = request.getParameter("c_id");
		int c_id  =Integer.parseInt(id);
		
		if (c_id==0) {
			json.put("code", -1);
			json.put("msg", "请选择班级");
			return json;
			
		}
		
		Classes classes = classesService.queryClassesById(c_id);
		
		if (classes==null) {
			json.put("code", -1);
			json.put("msg","获取班级信息失败");
			return json;
		}
		
		
		json.put("code", 200);
		json.put("msg", "查询成功");
		json.put("data", classes);
			return json;
	}
	/**
	 * @desc 修改数据
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Map<String, Object> edit(HttpServletRequest request) throws IOException{
		System.out.println("edit---->");
		Map<String, Object> json = new HashMap<String, Object>();
		String sid = request.getParameter("c_id");
		
		int c_id  =Integer.parseInt(sid);
		String c_name = request.getParameter("c_name");
		String c_counselor = request.getParameter("c_counselor");
		String c_tel = request.getParameter("c_tel");
		System.out.println(c_id+"------>"+c_name+"------>"+c_tel+"------>"+c_id);
		
		if (c_name==null || c_name.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入管理员名字");
			return json;
		}
		if (c_counselor==null || c_counselor.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入管理员密码");
			return json;
		}
		if (c_tel==null || c_tel.equals("")) {
			json.put("code", -1);
			json.put("msg", "请输入管理员电话");
			return json;
		}
		classes.setC_id(c_id);
		classes.setC_name(c_name);
		classes.setC_tel(c_tel);
		classes.setC_counselor(c_counselor);
		classesService.update(classes);
		System.out.println(classes.toString());
		json.put("code", 200);
		json.put("msg", "请求成功");
		return json;
	}
	
	
}
