package com.rj.bd.dorm.entity;

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
	private int dormitory_id;
	private int dorm_sum;
	private int dorm_count;
}
