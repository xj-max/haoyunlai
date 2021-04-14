package com.rj.bd.dorm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.rj.bd.dorm.entity.Dorm;
@Repository("dormMapper")
public interface DormMapper {

	@Results({
		@Result(column="dorm_id",property="dorm_id"),
		@Result(column="dorm_number",property="dorm_number"),
		@Result(column="dorm_sum",property="dorm_sum"),
		@Result(column="dorm_count",property="dorm_count"),
		@Result(column="dormitory_id",property="dormitory",one=@One(select="com.rj.bd.dormitory.dao.DormitoryMapper.queryDormitoryById"))
	})
	@Select("select * from dorm")
	List<Dorm> queryAll();

	
	
	@Results({
		@Result(column="dorm_id",property="dorm_id"),
		@Result(column="dorm_number",property="dorm_number"),
		@Result(column="dorm_sum",property="dorm_sum"),
		@Result(column="dorm_count",property="dorm_count"),
		@Result(column="dormitory_id",property="dormitory",one=@One(select="com.rj.bd.dormitory.dao.DormitoryMapper.queryDormitoryById"))
	})
	@Select("select *from Dorm where dorm_id = #{dorm_id}")
	List<Dorm> queryUserById(int dorm_id);


	
	
	@Update("update dorm set dorm_number=#{dorm_number},dorm_sum=#{dorm_sum},dorm_count=#{dorm_count},dormitory_id=#{dormitory.dormitory_id} where dorm_id =#{dorm_id}")
	void update(Dorm dorm);

	
	@Insert("insert into dorm(dorm_number,dorm_sum,dorm_count,dormitory_id) values (#{dorm_number},#{dorm_sum},#{dorm_count},#{dormitory.dormitory_id})")
	void add(Dorm dorm);


}
