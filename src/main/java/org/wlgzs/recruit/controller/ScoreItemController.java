package org.wlgzs.recruit.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.recruit.base.BaseController;
import org.wlgzs.recruit.domain.ScoreItem;
import org.wlgzs.recruit.util.result.Result;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 阿杰
 * Create 2018-08-08 15:14
 * Description:
 */
@RestController
@RequestMapping("/scoreItem")
public class ScoreItemController extends BaseController {

    /**
     * Description 分页查询所有打分项
     * Param [pageNum, pageSize]
     **/
    @RequestMapping("/findAll")
    public ModelAndView findAll(Model model,@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize){
        Page<ScoreItem> pageInfo = scoreItemService.findAll(pageNum,pageSize);
        model.addAttribute("Number",pageInfo.getCurrent());  //当前页数
        model.addAttribute("TotalPages",pageInfo.getPages());      //总页数
        model.addAttribute("scoreItems",pageInfo.getRecords());    //集合
        model.addAttribute("SignUp",canSignUp);
        model.addAttribute("msg","查询成功");
        return new ModelAndView("set");
    }
    /**
     * Description 添加打分项
     * Param [scoreItem]
     **/
    @RequestMapping("/addScoreItem")
    public Result addScoreItem(ScoreItem scoreItem, HttpSession session){
        return scoreItemService.addScoreItem(scoreItem,session);
    }
    /**
     * Description 删除打分项
     * Param [scoreItemId]
     **/
    @RequestMapping("/deleteScoreItem")
    public Result deleteScoreItem(int scoreItemId,HttpSession session){
        return scoreItemService.deleteScoreItem(scoreItemId,session);
    }
    /**
     * Description 查找打分项
     * Param [scoreItemId]
     **/
    @RequestMapping("/findById")
    public Result findById(int scoreItemId){
        return scoreItemService.findById(scoreItemId);
    }
    /**
     * Description 修改
     * Param [scoreItemId, scoreName]
     **/
    @RequestMapping("/update")
    public Result update(int scoreItemId,String scoreName,HttpSession session){
        return scoreItemService.update(scoreItemId,scoreName,session);
    }
}
