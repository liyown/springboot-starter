package com.lyw.springbootstarter.model.vo.question;

import cn.hutool.json.JSONUtil;
import com.lyw.springbootstarter.model.dto.question.JudgeCase;
import com.lyw.springbootstarter.model.dto.question.JudgeConfig;
import com.lyw.springbootstarter.model.dto.question.QuestionUpdateRequest;
import com.lyw.springbootstarter.model.entity.Question;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: liuyaowen
 * @poject: springboot-starter
 * @create: 2024-07-22 10:40
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionUpdateVO extends QuestionUpdateRequest {

    public static QuestionUpdateVO from(Question question) {
        QuestionUpdateVO questionUpdateVO = new QuestionUpdateVO();
        questionUpdateVO.setId(question.getId());
        questionUpdateVO.setTitle(question.getTitle());
        questionUpdateVO.setContent(question.getContent());
        questionUpdateVO.setTags(JSONUtil.toList(question.getTags(), String.class));
        questionUpdateVO.setAnswer(question.getAnswer());
        questionUpdateVO.setJudgeCase(JSONUtil.toList(question.getJudgeCase(), JudgeCase.class));
        questionUpdateVO.setJudgeConfig(JSONUtil.toBean(question.getJudgeConfig(), JudgeConfig.class));
        return questionUpdateVO;
    }
}
