package org.wlgzs.recruit.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Service;
import org.wlgzs.recruit.dao.QuestionDao;
import org.wlgzs.recruit.domain.Question;
import org.wlgzs.recruit.service.QuestionService;
import org.wlgzs.recruit.util.result.Result;
import org.wlgzs.recruit.util.result.ResultCodeEnum;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 阿杰
 * Create 2018-08-09 20:57
 * Description:
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionDao questionDao;

    @Override
    public Result addQuestion(Question question) {
        if(question==null) {
            return new Result(ResultCodeEnum.UNSAVE);
        }
        questionDao.insert(question);
        return new Result(ResultCodeEnum.SAVE);
    }

    @Override
    public Page<Question> findAll(int pageNum, int pageSize) {
        Page<Question> questionPage = new Page<>(pageNum,pageSize);
        Wrapper<Question> questionEntityWrapper = new EntityWrapper<>();
        questionEntityWrapper.orderBy("question_id",true);
        questionPage.setRecords(questionDao.selectPage(questionPage,questionEntityWrapper));
        return questionPage;
    }

    @Override
    public Result delete(int questionId) {
        if(questionId==0) {
            return new Result(ResultCodeEnum.UNDELETE);
        }
        questionDao.deleteById(questionId);
        return new Result(ResultCodeEnum.DELETE);
    }

    @Override
    public Result findById(int scoreItemId) {
        return new Result(ResultCodeEnum.FIND,questionDao.selectById(scoreItemId));
    }

    @Override
    public Result update(int questionId, String questionName) {
        Result result = new Result(ResultCodeEnum.UPDATE);
        if(questionName==null) {
            result.setMsg("面试题不能为空");
            return result;
        }
        Question scoreItem = new Question();
        scoreItem.setQuestionId(questionId);
        scoreItem.setQuestionName(questionName);
        questionDao.updateById(scoreItem);
        return result;
    }

    @Override
    public List<Question> findAllQuestion() {
        Wrapper<Question> questionEntityWrapper = new EntityWrapper<>();
        return questionDao.selectList(questionEntityWrapper);
    }

    @Override
    public Question findOne() {
        return questionDao.findOne();
    }
}
