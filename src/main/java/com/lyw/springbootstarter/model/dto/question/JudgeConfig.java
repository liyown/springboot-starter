package com.lyw.springbootstarter.model.dto.question;

import lombok.Data;

/**
 * @author: liuyaowen
 * @poject: springboot-starter
 * @create: 2024-07-22 10:37
 * @Description:
 */
@Data
public class JudgeConfig {

    private Long timeLimit;

    private Long memoryLimit;

    private Long stackLimit;
}
