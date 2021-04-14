package com.rj.bd.student.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.rj.bd.student.entity.Student;
@Repository("studentMapper")
public interface StudentMapper {

	@Select("select * from student")
	List<Student> queryAll();
	@Select("select *from student where s_id = #{s_id}")
	Student queryStudenById(int s_id);
	
	
	
	@Update("update student set s_number=#{s_number},s_name=#{s_name},s_sex=#{s_sex},s_tel=#{s_tel},c_id=#{classes.c_id},dorm_id=#{dorm.dorm_id} where s_id =#{s_id}")
	void update(Student student);

}
