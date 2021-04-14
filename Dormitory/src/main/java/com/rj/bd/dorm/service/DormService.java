package com.rj.bd.dorm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.bd.dorm.dao.DormMapper;
import com.rj.bd.dorm.entity.Dorm;
@Service("dormService")
public class DormService {
	@Autowired
	private DormMapper dormMapper;
	
	/**
	 * @desc 查询全部
	 * @return
	 */
	public List<Dorm> queryAll() {
		
		return dormMapper.queryAll();
	}

	public List<Dorm> queryUserById(int dorm_id) {
		
		return dormMapper.queryUserById(dorm_id);
	}

	/**
	 * @desc 修改
	 * @param dorm
	 */
	public void update(Dorm dorm) {
		
		dormMapper.update(dorm);
		
	}

	/**
	 * @desc 进行添加
	 * @param dorm
	 */
	public void add(Dorm dorm) {
		dormMapper.add(dorm);
		
	}


	
}
