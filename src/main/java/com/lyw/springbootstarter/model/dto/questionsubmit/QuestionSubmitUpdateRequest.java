package com.lyw.springbootstarter.model.dto.questionsubmit;

import lombok.Data;

import java.util.Date;

/**
 * @author: liuyaowen
 * @poject: springboot-starter
 * @create: 2024-07-22 10:49
 * @Description:
 */
@Data
public class QuestionSubmitUpdateRequest {

    private Long id;

    private String language;

    private String code;

    private String status;

    private Long questionId;

    private Long userid;


}
