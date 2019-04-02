package org.wlgzs.recruit.service;

import com.baomidou.mybatisplus.plugins.Page;
import org.wlgzs.recruit.domain.ScoreItem;
import org.wlgzs.recruit.util.result.Result;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ScoreItemService {
    /**
     * Description 分页查询所有打分项
     * Param [pageNum, pageSize]
     **/
    Page<ScoreItem> findAll(int pageNum, int pageSize);
    /**
     * Description 添加打分项
     * Param [scoreItem]
     **/
    Result addScoreItem(ScoreItem scoreItem, HttpSession session);
    /**
     * Description 删除打分项
     * Param [scoreItemId]
     **/
    Result deleteScoreItem(int scoreItemId, HttpSession session);
    /**
     * Description 跳转至修改页面
     * Param [scoreItemId]
     **/
    Result findById(int scoreItemId);
    /**
     * Description 修改
     * Param [scoreItemId, scoreName]
     **/
    Result update(int scoreItemId, String scoreName, HttpSession session);

    List<ScoreItem> findAllScoreItem();
}
