package com.rj.bd.student.dao;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.rj.bd.student.entity.Student;
@Repository("studentMapper")
public interface StudentMapper {

	@Results({
		@Result(column="s_id",property="s_id"),
		@Result(column="s_number",property="s_number"),
		@Result(column="s_name",property="s_name"),
		@Result(column="s_sex",property="s_sex"),
		@Result(column="s_tel",property="s_tel"),
		@Result(column="c_id",property="classes",one=@One(select="com.rj.bd.classes.dao.ClassesMapper.queryClassesById"))
	})
	
	@Select("select * from student")
	List<Student> queryAll();
	@Select("select *from student where s_id = #{s_id}")
	Student queryStudenById(int s_id);
	
	
	
	@Update("update student set s_number=#{s_number},s_name=#{s_name},s_sex=#{s_sex},s_tel=#{s_tel},c_id=#{c_id},dorm_id=#{dorm_id} where s_id =#{s_id}")
	void update(Student student);

}
