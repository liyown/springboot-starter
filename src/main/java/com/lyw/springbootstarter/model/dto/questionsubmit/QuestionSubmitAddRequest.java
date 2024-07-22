package com.lyw.springbootstarter.model.dto.questionsubmit;

import lombok.Data;

/**
 * @author: liuyaowen
 * @poject: springboot-starter
 * @create: 2024-07-22 10:46
 * @Description:
 */
@Data
public class QuestionSubmitAddRequest {

    private String language;

    private String code;

    private Long questionId;

}
