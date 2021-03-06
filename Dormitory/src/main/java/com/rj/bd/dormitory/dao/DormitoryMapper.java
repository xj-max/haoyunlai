package com.rj.bd.dormitory.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.rj.bd.dormitory.entity.Dormitory;

@Repository("dormitoryMapper")
public interface DormitoryMapper {
	@Select("select * from dormitory ")
	List<Dormitory> queryAll();
	
	@Select("select dormitory_id,sum(dorm_count) countdorm from dorm group by dormitory_id")
	List<Map<String, Object>> queryCount();
	
	@Delete("delete from dormitory where dormitory_id = #{dormitory_id}")
	void delete(int dormitory_id);
	
	@Insert("insert into dormitory(dormitory_name,dormitory_count) values (#{dormitory_name},#{dormitory_count})")
	void add(Dormitory dormitory);
	
	@Select("select * from dormitory where dormitory_id = #{dormitory_id}")
	Dormitory queryDormitoryById(int dormitory_id);
	
}
