package com.rj.bd.hygiene.entity;

import lombok.Data;

/**
 * @desc 卫生管理模块实体类
 * @author 马兴佳
 * @time 2021--04--13
 */
@Data
public class Hygiene {
private int h_id;
private int dorm_id;
private String  h_level;
private String h_time;

}
