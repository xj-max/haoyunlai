package com.rj.bd.dormitory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.bd.dormitory.dao.DormitoryMapper;
import com.rj.bd.dormitory.entity.Dormitory;

@Service("dormitoryService")
public class DormitoryService {
	@Autowired
	private DormitoryMapper dormitoryMapper;

	public List<Dormitory> queryAll() {
		
		return dormitoryMapper.queryAll();
	}

	public void deletet(int dormitory_id) {
	
	dormitoryMapper.delete(dormitory_id);
	}

	public void add(Dormitory dormitory) {
		dormitoryMapper.add(dormitory);
		
	}
	
	
}
