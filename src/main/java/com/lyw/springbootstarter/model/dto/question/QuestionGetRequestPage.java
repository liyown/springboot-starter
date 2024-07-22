package com.lyw.springbootstarter.model.dto.question;

import com.baomidou.mybatisplus.annotation.TableField;
import com.lyw.springbootstarter.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author: liuyaowen
 * @poject: springboot-starter
 * @create: 2024-07-22 10:28
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionGetRequestPage extends PageRequest implements Serializable {

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    private Long id;

    private String title;

    private String content;

    private List<String> tags;

    private Long userId;

}
