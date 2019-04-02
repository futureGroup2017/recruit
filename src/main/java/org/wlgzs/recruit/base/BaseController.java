package org.wlgzs.recruit.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.wlgzs.recruit.service.*;

import java.io.Serializable;

/**
 * @description 简化控制层代码
 */
public abstract class BaseController implements Serializable {

    //自动注入业务层
    @Autowired
    protected StudentService studentService;
    @Autowired
    protected InterviewService interviewService;
    @Autowired
    protected ScoreItemService scoreItemService;
    @Autowired
    protected QuestionService questionService;
    @Autowired
    protected LoginService loginService;

    public static boolean canSignUp = false;
}
