package com.rj.bd.classes.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.rj.bd.classes.entity.Classes;
@Repository("classesMapper")
public interface ClassesMapper {
	@Select("select * from classes")
	List<Classes> queryAll();

	@Delete("DELETE FROM student WHERE c_id = #{c_id}")
	void deleteStudentAndByCid(int c_id);
	
	@Delete("delete from classes where c_id = #{c_id}")
	void delete(int c_id);
	
	

	@Insert("insert into classes(c_sum,c_name,c_counselor) values (#{c_sum},#{c_name},#{c_counselor})")
	void add(Classes classes);

	
	@Select("select *from classes where c_id = #{c_id}")
	Classes queryClassesById(int c_id);

	

}
