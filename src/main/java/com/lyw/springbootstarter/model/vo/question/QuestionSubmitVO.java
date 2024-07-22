package com.lyw.springbootstarter.model.vo.question;

import com.lyw.springbootstarter.model.dto.questionsubmit.JudgeInfo;
import com.lyw.springbootstarter.model.vo.UserVO;
import lombok.Data;

import java.util.Date;

/**
 * @author: liuyaowen
 * @poject: springboot-starter
 * @create: 2024-07-22 11:11
 * @Description:
 */
@Data
public class QuestionSubmitVO {
    private Long id;

    private String language;

    private String code;

    private JudgeInfo judgeInfo;

    private String status;

    private Long questionId;

    private Long userid;

    private UserVO userVO;
}
