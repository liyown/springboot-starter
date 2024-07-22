package com.lyw.springbootstarter.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName question
 */
@TableName(value ="question")
@Data
public class Question implements Serializable {
    private Long id;

    private String title;

    private String content;

    private String tags;

    private String answer;

    private String judgecase;

    private String judgeconfig;

    private Integer submitnum;

    private Integer thumbnum;

    private Integer favournum;

    private Long userid;

    private Date createtime;

    private Date updatetime;

    private Integer isdelete;

    private static final long serialVersionUID = 1L;
}