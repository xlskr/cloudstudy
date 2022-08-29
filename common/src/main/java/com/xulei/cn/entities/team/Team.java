package com.xulei.cn.entities.team;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 联赛实体类
 */
@Data
public class Team {


    private String id;
    @NotEmpty(message = "联赛名称不能为空")
    private String name;
    @NotEmpty(message = "举办年份不能为空")
    private Integer year;
    //报名时间
    @NotEmpty(message = "报名时间不能为空")
    private Date registrationTimeStart;
    @NotEmpty(message = "报名时间不能为空")
    private Date registrationTimeEnd;
    @NotEmpty(message = "是否收费不能为空")
    private Integer state;
    //费用
    private BigDecimal price;
    //比赛时间开始
    private Date teamTimeStart;
    //比赛时间结束
    private Date teamTimeEnd;
    @NotEmpty(message = "logo不能为空")
    private String logo;
    @NotEmpty(message = "图片不能为空")
    private String pic;
    @NotEmpty(message = "联赛规程不能为空")
    private String describe;
    //创建时间
    private Date createTime;

}
