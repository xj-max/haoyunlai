package com.rj.bd.dormitory.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.rj.bd.dormitory.entity.Dormitory;

@Repository("dormitoryMapper")
public interface DormitoryMapper {
	@Select("select * from dormitory")
	List<Dormitory> queryAll();
	
	
	@Delete("delete from dormitory where dormitory_id = #{dormitory_id}")
	void delete(int dormitory_id);
	
	@Insert("insert into dormitory(dormitory_name,dormitory_count) values (#{dormitory_name},#{dormitory_count})")
	void add(Dormitory dormitory);

}
