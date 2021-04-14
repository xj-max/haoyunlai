package com.rj.bd.user.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.Blob;
import com.rj.bd.dormitory.entity.Dormitory;
import com.rj.bd.dormitory.service.DormitoryService;
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
	@Autowired
	private DormitoryService dormitoryService;
	
	
	User user = new User();
	private File imgs;
	
	/**
	 * @desc 查询全部管理员
	 * @param request
	 * @return
	 * @throws SQLException 
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value="/query",method=RequestMethod.GET)
	public Map<String, Object> queryUser(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException{
	Map<String, Object> json = new HashMap<String, Object>();
	List<User> list = userService.queryAll();
	for (User user : list) {
		System.out.println(user);
		/*Blob blob = (Blob) ((Map<String, Object>) user).get("u_imgs");
		byte[] bytes = blob.getBytes(1L, (int) blob.length());  // blob.getBytes

		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");  //设置输出流内容格式为图片格式
		InputStream in1 = new ByteArrayInputStream(bytes);  //将字节流转换为输入流
		IOUtils.copy(in1, response.getOutputStream());//将字节从 InputStream复制到OutputStream中
*/
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
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public Map<String, Object>deletet(HttpServletRequest request){
		System.out.println("delete");
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
	@ResponseBody
	@RequestMapping(value="/editPage",method=RequestMethod.GET)
	public Map<String, Object> editPage(HttpServletRequest request){
		System.out.println("editPage---->");
		Map<String, Object> json = new HashMap<String, Object>();
		String id = request.getParameter("u_id");
		int u_id  =Integer.parseInt(id);
		System.out.println("u_id---->"+u_id);
		if (u_id==0) {
			json.put("code", -1);
			json.put("msg", "请选择管理员");
			return json;
		}
		//Object dormitory = userService.queryDormitory();
		List<Dormitory> list = dormitoryService.queryAll();
		List<User> user1 = userService.queryUserById(u_id);
		if (user1 ==null) {
			json.put("code", -1);
			json.put("msg", "获取管理员信息失败");
			return json;
		}
		
		json.put("code", 200);
		json.put("msg", "获取管理员信息成功");
		json.put("data", user1);
		json.put("dormitory", list);
		return json;
	}
	
	
	/**
	 * @desc 修改数据
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public Map<String, Object> edit(HttpServletRequest request) throws IOException{
		System.out.println("edit---->");
		Map<String, Object> json = new HashMap<String, Object>();
		String sid = request.getParameter("u_id");
		
		int u_id  =Integer.parseInt(sid);
		String u_name = request.getParameter("u_name");
		String u_password = request.getParameter("u_password");
		String u_tel = request.getParameter("u_tel");
		String id = request.getParameter("dormitory_id");
		System.out.println(u_id+"------>"+u_name+"------>"+u_password+"------>"+u_tel+"------>"+id);
		int dormitory_id  =Integer.parseInt(id);
		System.out.println(u_id+"------>"+u_name+"------>"+u_password+"------>"+u_tel+"------>"+dormitory_id);
		
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
		/*//文件转为二进制*/
		/*InputStream inputStream = new FileInputStream(imgs);
		byte[] bytes = FileCopyUtils.copyToByteArray(inputStream); 
		String img = bytes.toString();
		user.setU_imgs(img);*/
		user.setU_id(u_id);
		user.setU_name(u_name);
		user.setU_password(u_password);
		Dormitory dormitory=new Dormitory();
		dormitory.setDormitory_id(dormitory_id);
		user.setDormitory(dormitory);
		user.setU_tel(u_tel);
		userService.update(user);
		System.out.println(user.toString());
		json.put("code", 200);
		json.put("msg", "请求成功");
		return json;
	}
	
	/**
	 * @desc 进入添加页面
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addPage",method=RequestMethod.GET)
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
	 * @desc 添加管理员
	 * @param user
	 * @param request
	 * @return
	 */
	@CrossOrigin
	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public Map<String, Object> add( User user, HttpServletRequest request){
		System.out.println("add--------->");
		Map<String, Object> json = new HashMap<String, Object>();
		String u_name = request.getParameter("u_name");
		String u_password = request.getParameter("u_password");
		String u_tel = request.getParameter("u_tel");
		String id = request.getParameter("dormitory_id");
		System.out.println(u_name+"------>"+u_password+"------>"+u_tel+"------>"+id);
		int dormitory_id  =Integer.parseInt(id);
		System.out.println(u_name+"------>"+u_password+"------>"+u_tel+"------>"+dormitory_id);
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
		System.out.println("1----->");
		user.setU_name(u_name);
		user.setU_password(u_password);
		user.setU_tel(u_tel);
		System.out.println("2----->");
		Dormitory dormitory=new Dormitory();
		dormitory.setDormitory_id(dormitory_id);
		System.out.println("3----->");
		user.setDormitory(dormitory);
		System.out.println("4----->");
		
		userService.add(user);
		System.out.println("5----->");
		json.put("code", 200);
		json.put("msg", "添加成功");
		json.put("data", " ");
		System.out.println("6----->");
		
		return json;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public File getImgs() {
		return imgs;
	}

	public void setImgs(File imgs) {
		this.imgs = imgs;
	}
	
	
}
