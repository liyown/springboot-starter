package com.lyw.springbootstarter.model.vo.question;

import com.lyw.springbootstarter.model.dto.question.JudgeConfig;
import com.lyw.springbootstarter.model.vo.UserVO;
import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Date;
import java.util.List;

/**
 * @author: liuyaowen
 * @poject: springboot-starter
 * @create: 2024-07-22 10:40
 * @Description:
 */
@Data
public class QuestionVO {

    private Long id;

    private String title;

    private String content;

    private List<String> tags;

    private JudgeConfig judgeConfig;

    private Integer submitNum;

    private Integer thumbNum;

    private Integer favourNum;

    private Long userid;

    private UserVO userVO;

}
