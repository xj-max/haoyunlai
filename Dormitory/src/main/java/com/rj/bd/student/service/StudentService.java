package com.rj.bd.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.bd.student.dao.StudentMapper;
import com.rj.bd.student.entity.Student;
@Service("studentService")
public class StudentService {
	@Autowired
	private StudentMapper studentMapper;
	public List<Student> queryAll() {
		
		return studentMapper.queryAll() ;
	}
	public Student queryStudenById(int s_id) {
		
		return studentMapper.queryStudenById(s_id);
	}
	public void update(Student student) {
	
		studentMapper.update(student);
	}

}
