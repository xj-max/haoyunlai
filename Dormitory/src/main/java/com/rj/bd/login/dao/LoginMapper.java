package com.rj.bd.login.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.rj.bd.login.entity.Login;
@Repository("loginMapper")
public interface LoginMapper {
	@Select("select * from user where u_name=#{0} and u_password=#{1}")
	Login queryUserByNameAndPass(String u_name, String u_password);
	@Update("update user set token=#{0} where u_id=#{1}")
	void updateToken(String token, int u_id);

}
