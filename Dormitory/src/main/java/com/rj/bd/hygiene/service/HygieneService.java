package com.rj.bd.hygiene.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.bd.hygiene.dao.HygieneMapper;
import com.rj.bd.hygiene.entity.Hygiene;
@Service("hygieneService")
public class HygieneService {
	@Autowired
	private HygieneMapper hygieneMapper;
	public List<Hygiene> queryAll() {
		
		return hygieneMapper.queryAll() ;
	}
	public Hygiene queryHygieneById(int h_id) {
		
		return hygieneMapper.queryHygieneById(h_id);
	}
	public void update(Hygiene hygiene) {
		hygieneMapper.update(hygiene);
		
	}
	public void add(Hygiene hygiene) {
		
		hygieneMapper.add(hygiene);
	}



}
