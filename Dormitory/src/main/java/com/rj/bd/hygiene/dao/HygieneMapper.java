package com.rj.bd.hygiene.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @desc
 * @author 马兴佳
 * @time 2021--04--13
 */
import com.rj.bd.hygiene.entity.Hygiene;
@Repository("hygieneMapper")
public interface HygieneMapper {
	@Select("select * from hygiene")
	List<Hygiene> queryAll();
	@Select("select *from hygiene where h_id = #{h_id}")
	Hygiene queryHygieneById(int h_id);
	
	
	@Update("update hygiene set dorm_id=#{dorm_id},h_level=#{h_level},h_time=#{h_time} where h_id =#{h_id}")
	void update(Hygiene hygiene);
	
	
	@Insert("insert into hygiene(h_level,h_time,dorm_id) values (#{h_level},#{h_time},#{dorm.dorm_id})")
	void add(Hygiene hygiene);

}
