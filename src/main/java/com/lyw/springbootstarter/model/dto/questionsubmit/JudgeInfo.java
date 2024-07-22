package com.lyw.springbootstarter.model.dto.questionsubmit;

import lombok.Data;

/**
 * @author: liuyaowen
 * @poject: springboot-starter
 * @create: 2024-07-22 10:47
 * @Description:
 */
@Data
public class JudgeInfo {

    public static final JudgeInfo DEFAULT = new JudgeInfo();

    static {
        DEFAULT.setMemory(0L);
        DEFAULT.setTime(0L);
        DEFAULT.setMessage("默认信息");
    }

    private String message;

    private Long memory;

    private Long time;
}
