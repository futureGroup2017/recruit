package org.wlgzs.recruit.service;

import com.baomidou.mybatisplus.plugins.Page;
import org.wlgzs.recruit.domain.Question;
import org.wlgzs.recruit.util.result.Result;

import java.util.List;

public interface QuestionService {
    /**
     * Description 添加问题
     * Param [question, model]
     **/
    Result addQuestion(Question question);
    /**
     * Description 分页查询所有问题
     * Param [pageNum, pageSize]
     **/
    Page<Question> findAll(int pageNum, int pageSize);
    /**
     * Description 删除问题
     * Param [questionId]
     **/
    Result delete(int questionId);
    /**
     * Description 跳转至修改问题页面
     * Param [scoreItemId]
     **/
    Result findById(int scoreItemId);
    /**
     * Description 修改问题
     * Param [questionId, questionName]
     **/
    Result update(int questionId, String questionName);

    List<Question> findAllQuestion();

    Question findOne();
}
