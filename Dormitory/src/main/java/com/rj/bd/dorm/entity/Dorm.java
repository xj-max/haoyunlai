package com.rj.bd.dorm.entity;

import com.rj.bd.dormitory.entity.Dormitory;

import lombok.Data;

/**
 * @desc 寝室实体类
 * @author 马兴佳
 * @time 2021--04--14
 */
@Data
public class Dorm {

	private int dorm_id;
	private String dorm_number;
	private Dormitory dormitory;
	private int dorm_sum;
	private int dorm_count;
}
