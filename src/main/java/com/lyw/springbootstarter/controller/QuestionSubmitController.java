package com.lyw.springbootstarter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyw.springbootstarter.annotation.AuthCheck;
import com.lyw.springbootstarter.common.*;
import com.lyw.springbootstarter.constant.UserConstant;
import com.lyw.springbootstarter.exception.BusinessException;
import com.lyw.springbootstarter.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.lyw.springbootstarter.model.dto.questionsubmit.QuestionSubmitGetRequestPage;
import com.lyw.springbootstarter.model.entity.QuestionSubmit;
import com.lyw.springbootstarter.model.vo.question.QuestionSubmitVO;
import com.lyw.springbootstarter.service.QuestionService;
import com.lyw.springbootstarter.service.QuestionSubmitService;
import com.lyw.springbootstarter.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lyw.springbootstarter.constant.QuestionSubmitConstant;

import java.util.List;
import java.util.Objects;

/**
 * 用户接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
@RequestMapping("/submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private QuestionService questionService;

    @Resource
    private UserService userService;

    // questionSubmit的增删改查接口

    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.DEFAULT_ROLE)
    public BaseResponse<Long> add(@RequestBody QuestionSubmitAddRequest request, HttpServletRequest RawRequest) {
        if (questionService.getById(request.getQuestionId()) == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "题目不存在");
        }

        if (!QuestionSubmitConstant.Language.contains(request.getLanguage())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "不支持的语言");
        }
        Long userId = userService.getLoginUser(RawRequest).getId();
        Long id = questionSubmitService.addQuestionSubmit(request, userId);
        return ResultUtils.success(id);
    }


    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.DEFAULT_ROLE)
    public BaseResponse<Boolean> delete(@RequestBody DeleteRequest request) {
        questionSubmitService.removeById(request.getId());
        return ResultUtils.success(true);
    }

    @PostMapping("/getVo")
    @AuthCheck(mustRole = UserConstant.DEFAULT_ROLE)
    public BaseResponse<QuestionSubmitVO> get(@RequestBody QuestionSubmitGetRequestPage request) {
        QueryWrapper<QuestionSubmit> queryWrapper = questionSubmitService.getQueryWrapper(request);
        QuestionSubmit one = questionSubmitService.getOne(queryWrapper);
        if (one == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "提交不存在");
        }

        QuestionSubmitVO vo = questionSubmitService.toVO(one);
        vo.setUserVO(userService.toVO(userService.getById(one.getUserid())));

        if (!Objects.equals(one.getUserid(), UserHolder.getUserID())) {
            if (UserHolder.getUserRole().equals(UserConstant.DEFAULT_ROLE)) {
                vo.setCode(null);
            }
        }
        return ResultUtils.success(vo);
    }

    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<QuestionSubmit>> list(@RequestBody QuestionSubmitGetRequestPage request) {
        QueryWrapper<QuestionSubmit> queryWrapper = questionSubmitService.getQueryWrapper(request);
        Page<QuestionSubmit> page = questionSubmitService.page(new Page<>(request.getCurrent(), request.getPageSize()), queryWrapper);
        return ResultUtils.success(page);
    }

    @PostMapping("/list/page/submitVO")
    @AuthCheck(mustRole = UserConstant.DEFAULT_ROLE)
    public BaseResponse<Page<QuestionSubmitVO>> listSubmitVO(@RequestBody @Validated QuestionSubmitGetRequestPage request) {
        QueryWrapper<QuestionSubmit> queryWrapper = questionSubmitService.getQueryWrapper(request);
        Page<QuestionSubmit> page = questionSubmitService.page(new Page<>(request.getCurrent(), request.getPageSize()), queryWrapper);
        Page<QuestionSubmitVO> questionSubmitVOPage = questionSubmitService.convertPageVO(page);

        // 获取用户信息
        List<QuestionSubmitVO> questionSubmitVOS = questionSubmitService.mapUserVO(questionSubmitVOPage.getRecords());
        questionSubmitVOPage.setRecords(questionSubmitVOS);

        if (UserHolder.getUserRole().equals(UserConstant.DEFAULT_ROLE)) {
            questionSubmitVOS.forEach((v)->{
                if (!Objects.equals(v.getUserid(), UserHolder.getUserID())) {
                    v.setCode(null);
                }
            });
        }
        return ResultUtils.success(questionSubmitVOPage);
    }


}
