package com.lyw.springbootstarter.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName question_submit
 */
@TableName(value ="question_submit")
@Data
public class QuestionSubmit implements Serializable {
    private Long id;

    private String language;

    private String code;

    private String judgeinfo;

    private String status;

    private Long questionid;

    private Long userid;

    private Date createtime;

    private Date updatetime;

    private Integer isdelete;

    private static final long serialVersionUID = 1L;
}