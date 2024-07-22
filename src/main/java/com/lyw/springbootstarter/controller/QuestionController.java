package com.lyw.springbootstarter.controller;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyw.springbootstarter.annotation.AuthCheck;
import com.lyw.springbootstarter.common.BaseResponse;
import com.lyw.springbootstarter.common.DeleteRequest;
import com.lyw.springbootstarter.common.ResultUtils;
import com.lyw.springbootstarter.common.UserHolder;
import com.lyw.springbootstarter.constant.UserConstant;
import com.lyw.springbootstarter.model.dto.question.QuestionAddRequest;
import com.lyw.springbootstarter.model.dto.question.QuestionGetRequestPage;
import com.lyw.springbootstarter.model.dto.question.QuestionUpdateRequest;
import com.lyw.springbootstarter.model.entity.Question;
import com.lyw.springbootstarter.model.vo.question.QuestionVO;
import com.lyw.springbootstarter.service.QuestionService;
import com.lyw.springbootstarter.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @Resource
    private UserService userService;

    // question的增删改查接口

    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> add(@RequestBody QuestionAddRequest request) {

        Question question = new Question();
        BeanUtils.copyProperties(request, question);

        if (request.getJudgeConfig() != null) {
            question.setJudgeConfig(JSONUtil.toJsonStr(request.getJudgeConfig()));
        }
        if (request.getJudgeCase() != null) {
            question.setJudgeCase(JSONUtil.toJsonStr(request.getJudgeCase()));
        }
        if (request.getTags() != null) {
            question.setTags(JSONUtil.toJsonStr(request.getTags()));
        }

        question.setUserid(UserHolder.getUserID());
        questionService.save(question);

        return ResultUtils.success(question.getId());
    }

    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<String> update(@RequestBody QuestionUpdateRequest request) {

        Question question = new Question();
        BeanUtils.copyProperties(request, question);

        if (request.getJudgeConfig() != null) {
            question.setJudgeConfig(JSONUtil.toJsonStr(request.getJudgeConfig()));
        }
        if (request.getJudgeCase() != null) {
            question.setJudgeCase(JSONUtil.toJsonStr(request.getJudgeCase()));
        }
        if (request.getTags() != null) {
            question.setTags(JSONUtil.toJsonStr(request.getTags()));
        }



        questionService.updateById(question);
        return ResultUtils.success("成功");
    }

    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Void> delete(@RequestBody DeleteRequest request) {
        questionService.removeById(request.getId());
        return ResultUtils.success(null);
    }

    @PostMapping("/get")
    @AuthCheck(mustRole = UserConstant.DEFAULT_ROLE)
    public BaseResponse<QuestionVO> get(@RequestBody QuestionGetRequestPage request) {
        QueryWrapper<Question> queryWrapper = questionService.getQueryWrapper(request);
        Question question = questionService.getOne(queryWrapper);
        QuestionVO questionVO = questionService.toVO(question);
        // 获取用户信息
        questionVO.setUserVO(userService.toVO(userService.getById(questionVO.getUserid())));
        return ResultUtils.success(questionVO);
    }

    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<Question>> listUserByPage(@RequestBody QuestionGetRequestPage request) {
        QueryWrapper<Question> queryWrapper = questionService.getQueryWrapper(request);
        Page<Question> page = questionService.page(new Page<>(request.getCurrent(), request.getPageSize()), queryWrapper);
        return ResultUtils.success(page);
    }

    @PostMapping("/list/page/vo")
    @AuthCheck(mustRole = UserConstant.DEFAULT_ROLE)
    public BaseResponse<Page<QuestionVO>> listUserByPageVO(@RequestBody QuestionGetRequestPage request) {
        QueryWrapper<Question> queryWrapper = questionService.getQueryWrapper(request);
        Page<Question> page = questionService.page(new Page<>(request.getCurrent(), request.getPageSize()), queryWrapper);
        Page<QuestionVO> questionVOPage = questionService.convertPageVO(page);
        // 获取用户信息
        List<QuestionVO> questionVOS = questionService.mapUserVO(questionVOPage.getRecords());
        questionVOPage.setRecords(questionVOS);
        return ResultUtils.success(questionVOPage);
    }

}
