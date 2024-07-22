package com.lyw.springbootstarter.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyw.springbootstarter.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.lyw.springbootstarter.model.dto.questionsubmit.QuestionSubmitGetRequestPage;
import com.lyw.springbootstarter.model.entity.QuestionSubmit;
import com.lyw.springbootstarter.model.vo.question.QuestionSubmitVO;

import java.util.List;

/**
* @author liuya
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-07-22 10:05:15
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitGetRequestPage questionSubmitGetRequest);

    Page<QuestionSubmitVO> convertPageVO(Page<QuestionSubmit> page);

    QuestionSubmitVO toVO(QuestionSubmit questionSubmit);

    List<QuestionSubmitVO> mapUserVO(List<QuestionSubmitVO> records);

    QuestionSubmit toEntity(QuestionSubmitVO questionSubmitVO);

    Long addQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, Long userId);
}
