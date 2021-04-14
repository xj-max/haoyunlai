package com.rj.bd.classes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.bd.classes.dao.ClassesMapper;
import com.rj.bd.classes.entity.Classes;
@Service("classesService")
public class ClassesService {
	@Autowired
	private ClassesMapper classesMapper;
	public List<Classes> queryAll() {
		
		return classesMapper.queryAll();
	}
	public void deletet(int c_id) {
		
		classesMapper.delete(c_id);
	}
	public void add(Classes classes) {
		classesMapper.add(classes);
		
	}
	public void deleteStudentAndByCid(int c_id) {
		classesMapper.deleteStudentAndByCid(c_id);
		
	}
	public Classes queryClassesById(int c_id) {
		
		return classesMapper.queryClassesById(c_id);
	}


}
