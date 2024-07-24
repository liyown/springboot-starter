package com.lyw.springbootstarter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyw.springbootstarter.common.ErrorCode;
import com.lyw.springbootstarter.constant.CommonConstant;
import com.lyw.springbootstarter.exception.BusinessException;
import com.lyw.springbootstarter.mapper.QuestionMapper;
import com.lyw.springbootstarter.model.dto.question.JudgeConfig;
import com.lyw.springbootstarter.model.dto.question.QuestionGetRequestPage;
import com.lyw.springbootstarter.model.entity.Question;
import com.lyw.springbootstarter.model.entity.User;
import com.lyw.springbootstarter.model.vo.UserVO;
import com.lyw.springbootstarter.model.vo.question.QuestionVO;
import com.lyw.springbootstarter.service.QuestionService;
import com.lyw.springbootstarter.service.UserService;
import com.lyw.springbootstarter.utils.SqlUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.stream.Collectors;

/**
* @author liuya
* @description 针对表【question(题目)】的数据库操作Service实现
* @createDate 2024-07-22 10:05:15
*/
@Service
@Slf4j
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService {


    @Resource
    private UserService userService;

    @Override
    public QueryWrapper<Question> getQueryWrapper(QuestionGetRequestPage questionGetRequest) {
        if (questionGetRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = questionGetRequest.getId();
        String title = questionGetRequest.getTitle();
        String content = questionGetRequest.getContent();
        List<String> tags = questionGetRequest.getTags();
        String tagSql = tags == null ? null : tags.stream().map(tag -> "tags Like '%" + tag + "%'").collect(Collectors.joining(" AND "));
        String sortField = questionGetRequest.getSortField();
        String sortOrder = questionGetRequest.getSortOrder();
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(title), "title", title)
                    .like(StringUtils.isNotBlank(content), "content", content)
                    .eq(id != null, "id", id)
                    .and(SqlUtils.validSortField(tagSql), i -> i.apply(tagSql))
                    .orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                             sortField);
        return queryWrapper;
    }

    @Override
    public List<QuestionVO> mapUserVO(List<QuestionVO> records) {
        records.forEach(questionVO -> {
            User user = userService.getById(questionVO.getUserid());
            if (user != null) {
                UserVO vo = userService.toVO(user);
                questionVO.setUserVO(vo);
            }
        });
        return records;
    }

    @Override
    public QuestionVO toVO(Question question) {
        QuestionVO questionVO = new QuestionVO();
        BeanUtil.copyProperties(question, questionVO, "tags", "judgeConfig");
        String tags = question.getTags();
        if (tags != null) {
            questionVO.setTags(JSONUtil.toList(tags, String.class));
        }
        String judgeConfig = question.getJudgeConfig();
        if (judgeConfig != null) {
            questionVO.setJudgeConfig(JSONUtil.toBean(judgeConfig, JudgeConfig.class));
        }
        return questionVO;
    }

    @Override
    public Question toEntity(QuestionVO questionVO) {
        Question question = new Question();
        BeanUtil.copyProperties(questionVO, question, "tags", "judgeConfig");
        if (questionVO.getTags() != null) {
            question.setTags(JSONUtil.toJsonStr(questionVO.getTags()));
        }
        if (questionVO.getJudgeConfig() != null) {
            question.setJudgeConfig(JSONUtil.toJsonStr(questionVO.getJudgeConfig()));
        }
        return question;
    }

    @Override
    public Page<QuestionVO> convertPageVO(Page<Question> page) {
        Page<QuestionVO> pageVO = new Page<>();
        BeanUtil.copyProperties(page, pageVO);
        pageVO.setRecords(page.getRecords().stream().map(this::toVO).collect(Collectors.toList()));
        return pageVO;
    }

}




