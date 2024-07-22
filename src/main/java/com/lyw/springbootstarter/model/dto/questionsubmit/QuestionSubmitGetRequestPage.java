package com.lyw.springbootstarter.model.dto.questionsubmit;
import com.lyw.springbootstarter.common.PageRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;


/**
 * @author: liuyaowen
 * @poject: springboot-starter
 * @create: 2024-07-22 11:08
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionSubmitGetRequestPage extends PageRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String language;

    private String status;

    // 有索引
    @NotNull
    private Long questionId;
    // 有索引

    @NotNull
    private Long userid;
}
