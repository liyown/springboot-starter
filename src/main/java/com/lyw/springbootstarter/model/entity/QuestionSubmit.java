package com.lyw.springbootstarter.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lyw.springbootstarter.model.dto.questionsubmit.JudgeInfo;
import lombok.Data;

import java.io.Serial;
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

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    private String status;
    private String judgeInfo;
    // 有索引
    private Long questionId;
    // 有索引
    private Long userid;



    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
}