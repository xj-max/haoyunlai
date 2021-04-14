package com.rj.bd.repair.entity;

import lombok.Data;

/**
 * @desc 维修表实体类
 * @author 马兴佳
 * @time 2021--04--13
 */
@Data
public class Repair {
	private int r_id;
	private int dorm_id;
	private String r_name;
	private String r_tel;
	private String r_cause;
	private String r_time;
	
	
}
