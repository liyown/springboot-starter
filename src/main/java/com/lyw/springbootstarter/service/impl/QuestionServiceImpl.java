package com.lyw.springbootstarter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyw.springbootstarter.mapper.QuestionMapper;
import com.lyw.springbootstarter.model.entity.Question;
import com.lyw.springbootstarter.service.QuestionService;
import org.springframework.stereotype.Service;

/**
* @author liuya
* @description 针对表【question(题目)】的数据库操作Service实现
* @createDate 2024-07-22 10:05:15
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService {

}




