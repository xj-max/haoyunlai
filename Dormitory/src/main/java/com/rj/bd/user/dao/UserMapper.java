package com.rj.bd.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.rj.bd.user.entity.User;
@Repository("userMapper")
public interface UserMapper {
	@Select("select u.*,d.dormitory_name from user u,dormitory d where u.dormitory_id=d.dormitory_id")
	List<User> queryAll();
	
	@Delete("DELETE FROM Charge WHERE u_id = #{u_id}")
	void deletetCharge(int u_id);
	
	@Delete("DELETE FROM USER WHERE u_id = #{u_id}")
	void deletet(int u_id);
	
	
	@Select("select *from user where u_id = #{u_id}")
	User queryUserById(int u_id);
	@Select("select * from dormitory")
	void queryDormitory();
	
	@Update("update user set u_name=#{u_name},u_password=#{u_password},u_tel=#{u_tel},dormitory_id=#{dormitory_id} where u_id =#{u_id}")
	void update(User user);
	
	
	@Insert("insert into user(u_name,u_password,u_tel,dormitory_id) values (#{u_name},#{u_password},#{u_tel},#{dormitory_id})")
	void add(User user);

	

}
