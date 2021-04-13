package com.rj.bd.user.entity;

import lombok.Data;

/**
 * @desc 管理员实体类
 * @author 马兴佳
 * @time 2021--04--13
 */
@Data
public class User {
	private int u_id;
	private String u_name;
	private String u_password;
	private String u_imgs;
	private String u_tel;
	private int dormitory_id;
}
