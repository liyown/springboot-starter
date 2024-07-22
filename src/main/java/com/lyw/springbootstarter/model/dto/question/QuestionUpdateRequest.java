package com.lyw.springbootstarter.model.dto.question;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author: liuyaowen
 * @poject: springboot-starter
 * @create: 2024-07-22 10:20
 * @Description:
 */
@Data
public class QuestionUpdateRequest {

    private Long id;
    private String title;

    private String content;

    private List<String> tags;

    private String answer;

    private List<JudgeCase> judgeCase;

    private JudgeConfig judgeConfig;

}
