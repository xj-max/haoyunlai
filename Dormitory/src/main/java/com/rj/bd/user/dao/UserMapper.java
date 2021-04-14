package com.rj.bd.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.rj.bd.user.entity.User;
@Repository("userMapper")
public interface UserMapper {
	@Results({
		@Result(column="u_id",property="u_id"),
		@Result(column="u_name",property="u_name"),
		@Result(column="u_password",property="u_password"),
		@Result(column="u_imgs",property="u_imgs"),
		@Result(column="u_tel",property="u_tel"),
		@Result(column="dormitory_id",property="dormitory",one=@One(select="com.rj.bd.dormitory.dao.DormitoryMapper.queryDormitoryById"))
	})
	@Select("select * from user")
	List<User> queryAll();
	
	@Delete("DELETE FROM Charge WHERE u_id = #{u_id}")
	void deletetCharge(int u_id);
	
	@Delete("DELETE FROM USER WHERE u_id = #{u_id}")
	void deletet(int u_id);
	
	@Results({
		@Result(column="u_id",property="u_id"),
		@Result(column="u_name",property="u_name"),
		@Result(column="u_password",property="u_password"),
		@Result(column="u_imgs",property="u_imgs"),
		@Result(column="u_tel",property="u_tel"),
		@Result(column="dormitory_id",property="dormitory",one=@One(select="com.rj.bd.dormitory.dao.DormitoryMapper.queryDormitoryById"))
	})
	@Select("select *from user where u_id = #{u_id}")
	List<User> queryUserById(int u_id);
	@Select("select * from dormitory")
	void queryDormitory();
	
	@Update("update user set u_name=#{u_name},u_password=#{u_password},u_tel=#{u_tel},dormitory_id=#{dormitory.dormitory_id} where u_id =#{u_id}")
	void update(User user);
	
	
	@Insert("insert into user(u_name,u_password,u_tel,dormitory_id) values (#{u_name},#{u_password},#{u_tel},#{dormitory.dormitory_id})")
	void add(User user);

	

}
