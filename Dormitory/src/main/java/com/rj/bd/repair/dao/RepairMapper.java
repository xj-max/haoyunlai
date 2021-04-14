package com.rj.bd.repair.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.rj.bd.repair.entity.Repair;
@Repository("repairMapper")
public interface RepairMapper {

	@Select("select * from repair")
	List<Repair> queryAll();
	@Insert("insert into repair(r_name,r_tel,r_cause,dorm_id,r_time) values (#{r_name},#{r_tel},#{r_cause},#{dorm_id},#{r_time})")
	void add(Repair repair);

}
