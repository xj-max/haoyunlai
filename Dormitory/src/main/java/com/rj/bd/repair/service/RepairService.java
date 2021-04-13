package com.rj.bd.repair.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.bd.repair.dao.RepairMapper;
import com.rj.bd.repair.entity.Repair;
@Service("repairService")
public class RepairService {
	@Autowired
	private RepairMapper repairMapper;
	
	public List<Repair> queryAll() {
		return repairMapper.queryAll();
	}

	public void add(Repair repair) {
		
		repairMapper.add(repair);
	}

}
