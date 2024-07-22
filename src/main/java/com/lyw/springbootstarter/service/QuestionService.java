package com.lyw.springbootstarter.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyw.springbootstarter.model.dto.question.QuestionGetRequestPage;
import com.lyw.springbootstarter.model.entity.Question;
import com.lyw.springbootstarter.model.vo.question.QuestionVO;

import java.util.List;

/**
* @author liuya
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2024-07-22 10:05:15
*/
public interface QuestionService extends IService<Question> {

    QueryWrapper<Question> getQueryWrapper(QuestionGetRequestPage questionGetRequest);

    List<QuestionVO> mapUserVO(List<QuestionVO> records);

    QuestionVO toVO(Question question);

    Question toEntity(QuestionVO questionVO);

    Page<QuestionVO> convertPageVO(Page<Question> page);
}
