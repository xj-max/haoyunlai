package com.rj.bd.student.entity;

import com.rj.bd.classes.entity.Classes;
import com.rj.bd.dorm.entity.Dorm;

import lombok.Data;

/**
 * @desc 学生管理控制层
 * @author 马兴佳
 * @tiem 2021--04--14
 */
@Data
public class Student {

	private String s_name;
	private String s_number;
	private String s_sex;
	private int s_id;
	private String s_tel;
	private Classes classes;
	private Dorm dorm;
	
	
	
}
