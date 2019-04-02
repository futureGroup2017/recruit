package org.wlgzs.recruit.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Service;
import org.wlgzs.recruit.dao.ScoreItemDao;
import org.wlgzs.recruit.domain.ScoreItem;
import org.wlgzs.recruit.service.ScoreItemService;
import org.wlgzs.recruit.util.result.Result;
import org.wlgzs.recruit.util.result.ResultCodeEnum;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 阿杰
 * Create 2018-08-08 15:15
 * Description:
 */
@Service
public class ScoreItemServiceImpl implements ScoreItemService {
    @Resource
    private ScoreItemDao scoreItemDao;

    @Override
    public Page<ScoreItem> findAll(int pageNum, int pageSize) {
        Page<ScoreItem> scoreItemPage = new Page<>(pageNum,pageSize);
        Wrapper<ScoreItem> scoreItemWrapper = new EntityWrapper<>();
        scoreItemWrapper.orderBy("score_item_id",true);
        scoreItemPage.setRecords(scoreItemDao.selectPage(scoreItemPage,scoreItemWrapper));
        return scoreItemPage;
    }

    @Override
    public Result addScoreItem(ScoreItem scoreItem, HttpSession session) {
        Result result = new Result(ResultCodeEnum.SAVE);
        if(scoreItem==null) {
            return new Result(ResultCodeEnum.UNSAVE);
        }
        if (session.getAttribute("studentId")!=null) {
            result.setMsg("正在进行面试，不能添加打分项");
            return result;
        }
        scoreItemDao.insert(scoreItem);
        return new Result(ResultCodeEnum.SAVE);
    }

    @Override
    public Result deleteScoreItem(int scoreItemId, HttpSession session) {
        if(scoreItemId==0) {
            return new Result(ResultCodeEnum.UNDELETE);
        }
        Result result = new Result(ResultCodeEnum.SAVE);
        if (session.getAttribute("studentId")!=null) {
            result.setMsg("正在进行面试，不能删除打分项");
            return result;
        }
        scoreItemDao.deleteById(scoreItemId);
        return new Result(ResultCodeEnum.DELETE);
    }

    @Override
    public Result findById(int scoreItemId) {
        return new Result(ResultCodeEnum.FIND,scoreItemDao.selectById(scoreItemId));
    }

    @Override
    public Result update(int scoreItemId, String scoreName, HttpSession session) {
        if (session.getAttribute("studentId")!=null) {
            Result result = new Result(ResultCodeEnum.FAIL);
            result.setMsg("正在进行面试，不能修改打分项");
            return result;
        }
        ScoreItem scoreItem = new ScoreItem();
        scoreItem.setScoreItemId(scoreItemId);
        scoreItem.setScoreName(scoreName);
        scoreItemDao.updateById(scoreItem);
        return new Result(ResultCodeEnum.UPDATE);
    }

    @Override
    public List<ScoreItem> findAllScoreItem() {
        Wrapper<ScoreItem> scoreItemWrapper = new EntityWrapper<>();
        return scoreItemDao.selectList(scoreItemWrapper);
    }
}
