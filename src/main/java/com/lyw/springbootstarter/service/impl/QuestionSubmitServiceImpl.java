package com.lyw.springbootstarter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyw.springbootstarter.common.ErrorCode;
import com.lyw.springbootstarter.constant.CommonConstant;
import com.lyw.springbootstarter.constant.QuestionSubmitConstant;
import com.lyw.springbootstarter.exception.BusinessException;
import com.lyw.springbootstarter.mapper.QuestionSubmitMapper;
import com.lyw.springbootstarter.model.dto.questionsubmit.JudgeInfo;
import com.lyw.springbootstarter.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.lyw.springbootstarter.model.dto.questionsubmit.QuestionSubmitGetRequestPage;
import com.lyw.springbootstarter.model.entity.QuestionSubmit;
import com.lyw.springbootstarter.model.vo.question.QuestionSubmitVO;
import com.lyw.springbootstarter.service.QuestionSubmitService;
import com.lyw.springbootstarter.service.UserService;
import com.lyw.springbootstarter.utils.SqlUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author liuya
* @description 针对表【question_submit(题目提交)】的数据库操作Service实现
* @createDate 2024-07-22 10:05:15
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService {

    @Resource
    private UserService userService;


    @Override
    public QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitGetRequestPage questionSubmitGetRequest) {
        if (questionSubmitGetRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = questionSubmitGetRequest.getId();
        String language = questionSubmitGetRequest.getLanguage();
        String status = questionSubmitGetRequest.getStatus();
        Long questionId = questionSubmitGetRequest.getQuestionId();
        Long userid = questionSubmitGetRequest.getUserid();
        String sortField = questionSubmitGetRequest.getSortField();
        String sortOrder = questionSubmitGetRequest.getSortOrder();
        QueryWrapper<QuestionSubmit> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(language), "language", language)
                    .like(StringUtils.isNotBlank(status), "status", status)
                    .eq(questionId != null, "questionId", questionId)
                    .eq(userid != null, "userid", userid)
                    .eq(id != null, "id", id)
                    .orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                             sortField);
        return queryWrapper;

    }
    @Override
    public Page<QuestionSubmitVO> convertPageVO(Page<QuestionSubmit> page) {
        Page<QuestionSubmitVO> pageVO = new Page<>();
        BeanUtil.copyProperties(page, pageVO, "records");
        pageVO.setRecords(page.getRecords().stream().map(this::toVO).collect(Collectors.toList()));
        return pageVO;
    }

    @Override
    public QuestionSubmitVO toVO(QuestionSubmit questionSubmit) {
        QuestionSubmitVO questionSubmitVO = new QuestionSubmitVO();
        BeanUtil.copyProperties(questionSubmit, questionSubmitVO, "judgeInfo");
        if (questionSubmit.getJudgeInfo() != null) {
            questionSubmitVO.setJudgeInfo(JSONUtil.toBean(questionSubmit.getJudgeInfo(), JudgeInfo.class));
        }
        return questionSubmitVO;
    }

    @Override
    public List<QuestionSubmitVO> mapUserVO(List<QuestionSubmitVO> records) {
        records.forEach((questionSubmitVO -> {
            questionSubmitVO.setUserVO(userService.toVO(userService.getById(questionSubmitVO.getUserid())));
        }));
        return records;
    }


    @Override
    public QuestionSubmit toEntity(QuestionSubmitVO questionSubmitVO) {
        QuestionSubmit questionSubmit = new QuestionSubmit();
        BeanUtil.copyProperties(questionSubmitVO, questionSubmit, "judgeInfo");
        if (questionSubmitVO.getJudgeInfo() != null) {
            questionSubmit.setJudgeInfo(JSONUtil.toJsonStr(questionSubmitVO.getJudgeInfo()));
        }
        return questionSubmit;
    }

    @Override
    public Long addQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, Long userId) {
        QuestionSubmit questionSubmit = new QuestionSubmit();
        BeanUtil.copyProperties(questionSubmitAddRequest, questionSubmit);
        questionSubmit.setUserid(userId);
        questionSubmit.setJudgeInfo(JSONUtil.toJsonStr(JudgeInfo.DEFAULT));
        questionSubmit.setStatus(QuestionSubmitConstant.QUESTION_STATUS_SUBMIT);
        save(questionSubmit);
        return questionSubmit.getId();
    }

}




