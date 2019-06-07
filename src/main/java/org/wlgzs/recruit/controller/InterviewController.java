package org.wlgzs.recruit.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.recruit.base.BaseController;
import org.wlgzs.recruit.domain.*;
import org.wlgzs.recruit.util.CommaUtil;
import org.wlgzs.recruit.util.result.Result;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 阿杰
 * Create 2018-08-08 14:53
 * Description:
 */
@RestController
@RequestMapping("/interview")
public class InterviewController extends BaseController {

    /**
     * Description 加入面试
     * Param [studentId]
     **/
    @RequestMapping("/joinInterview")
    public Result joinInterview(int studentId, HttpSession session) {
        return studentService.joinInterview(studentId, session);
    }

    /**
     * Description 退出面试
     * Param [studentId]
     **/
    @RequestMapping("/exitInterview")
    public Result exitInterview(int studentId) {
        return studentService.exitInterview(studentId);
    }

    /**
     * Description 面试人员列表
     **/
    @RequestMapping("/interview")
    public ModelAndView interview(Model model, @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        Page<Student> pageInfo = studentService.findInterviewStudent(pageNum, pageSize);
        model.addAttribute("Number", pageInfo.getCurrent());  //当前页数
        model.addAttribute("TotalPages", pageInfo.getPages());    //总页数
        model.addAttribute("students", pageInfo.getRecords());    //学生集合
        List<String> times = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (Student student : pageInfo.getRecords()) {
            if (student.getInterviewTime() != null) {
                times.add(formatter.format(student.getInterviewTime()));
            } else {
                times.add("暂无");
            }
        }
        model.addAttribute("times",times);
        return new ModelAndView("interview");
    }

    /**
     * Description 搜索面试人员列表
     **/
    @RequestMapping("/searchInterview")
    public ModelAndView searchInterview(Model model, String keyword, @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        Page<Student> pageInfo = studentService.searchInterview(keyword,pageNum, pageSize);
        model.addAttribute("Number", pageInfo.getCurrent());  //当前页数
        model.addAttribute("TotalPages", pageInfo.getPages());    //总页数
        model.addAttribute("students", pageInfo.getRecords());    //学生集合
        model.addAttribute("keyword",keyword);
        List<String> times = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (Student student : pageInfo.getRecords()) {
            if (student.getInterviewTime() != null) {
                times.add(formatter.format(student.getInterviewTime()));
            } else {
                times.add("暂无");
            }
        }
        model.addAttribute("times",times);
        return new ModelAndView("searchInterview");
    }
    /**
     * Description 面试
     * Param [studentId]
     **/
    @RequestMapping("/interviewById")
    public ModelAndView interviewById(Model model, int studentId) {
        List<ScoreItem> scoreItems = scoreItemService.findAllScoreItem();
        model.addAttribute("scoreItems", scoreItems);
//        List<Question> questionList = questionService.findAllQuestion();
//        model.addAttribute("questionList", questionList);
        Student student = studentService.findStudentById(studentId);
        if (student.getStudentId() != 0) {
            if ("笔试通过".equals(student.getStatus())) {
                model.addAttribute("msg", "该学生正在进行一面");
            }
            if ("一面通过".equals(student.getStatus())) {
                model.addAttribute("msg", "该学生正在进行二面");
            }
            if ("二面通过".equals(student.getStatus())) {
                model.addAttribute("msg", "该学生正在进行三面");
            }
            List<String> strings = new ArrayList<>();
            if (student.getWrittenTestImg() != null && student.getWrittenTestImg().contains(",")) {
                String[] img = student.getWrittenTestImg().split(",");
                strings = Arrays.asList(img);
            }
            model.addAttribute("img", strings);
        } else {
            model.addAttribute("msg", "现在没有面试的学生");
        }
        model.addAttribute("student", student);
        return new ModelAndView("Being-interviewed");
    }

    /**
     * Description 提交面试打分
     * Param [studentId, detail]
     **/
    @RequestMapping("/submitInterview")
    public ModelAndView submitInterview(HttpSession session, int studentId, String detail) {
        String interviewer = (String) session.getAttribute("userName");
        Student student = studentService.findStudentById(studentId);
        interviewService.submitInterview(student, detail, interviewer);
        return new ModelAndView("redirect:/student/findAllStudent");
    }

    /**
     * Description 查询全部面试结果
     * Param [pageNum, pageSize]
     **/
    @RequestMapping("/allInterviewResult")
    public ModelAndView allInterviewResult(Model model, @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                           @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        Page<Student> pageInfo = studentService.allInterviewResult(pageNum, pageSize);
        model.addAttribute("Number", pageInfo.getCurrent());  //当前页数
        model.addAttribute("TotalPages", pageInfo.getPages());      //总页数
        model.addAttribute("students", pageInfo.getRecords());    //集合
        model.addAttribute("msg", "查询成功");
        return new ModelAndView("result");
    }
    /**
     * Description 搜索学生面试结果
     * Param [pageNum, pageSize]
     **/
    @RequestMapping("/searchInterviewResult")
    public ModelAndView searchInterviewResult(Model model, String keyword, @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                           @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        Page<Student> pageInfo = studentService.searchInterviewResult(keyword,pageNum, pageSize);
        model.addAttribute("Number", pageInfo.getCurrent());  //当前页数
        model.addAttribute("TotalPages", pageInfo.getPages());      //总页数
        model.addAttribute("students", pageInfo.getRecords());    //集合
        model.addAttribute("keyword",keyword);
        model.addAttribute("msg", "查询成功");
        return new ModelAndView("searchResult");
    }

    /**
     * Description 我的面试人员
     **/
    @RequestMapping("/personalResult")
    public ModelAndView personalResult(Model model, HttpSession session, @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                           @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        String interviewer = (String) session.getAttribute("userName");
        Page<Student> pageInfo = studentService.personalResult(interviewer,pageNum, pageSize);
        model.addAttribute("Number", pageInfo.getCurrent());  //当前页数
        model.addAttribute("TotalPages", pageInfo.getPages());    //总页数
        model.addAttribute("students", pageInfo.getRecords());    //集合
        model.addAttribute("msg", "查询成功");
        return new ModelAndView("personalResult");
    }
    /**
     * Description 搜索我的面试人员
     **/
    @RequestMapping("/searchPersonalResult")
    public ModelAndView searchPersonalResult(Model model, String keyword, HttpSession session, @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                       @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        String interviewer = (String) session.getAttribute("userName");
        Page<Student> pageInfo = studentService.searchPersonalResult(keyword,interviewer,pageNum, pageSize);
        model.addAttribute("Number", pageInfo.getCurrent());  //当前页数
        model.addAttribute("TotalPages", pageInfo.getPages());    //总页数
        model.addAttribute("students", pageInfo.getRecords());    //集合
        model.addAttribute("keyword",keyword);
        model.addAttribute("msg", "查询成功");
        return new ModelAndView("searchPersonalResult");
    }

    /**
     * Description 查询一面结果
     * Param [model, pageNum, pageSize]
     **/
    @RequestMapping("/oneInterviewList")
    public ModelAndView oneInterviewList(Model model, @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        Page<Student> pageInfo = studentService.OneInterviewList(pageNum, pageSize);
        model.addAttribute("Number", pageInfo.getCurrent());  //当前页数
        model.addAttribute("TotalPages", pageInfo.getPages());      //总页数
        model.addAttribute("students", pageInfo.getRecords());    //集合
        model.addAttribute("msg", "查询成功");
        return new ModelAndView("result/result1");
    }

    /**
     * Description 查询二面结果
     * Param [model, pageNum, pageSize]
     **/
    @RequestMapping("/twoInterviewList")
    public ModelAndView twoInterviewList(Model model, @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        Page<Student> pageInfo = studentService.TwoInterviewList(pageNum, pageSize);
        model.addAttribute("Number", pageInfo.getCurrent());  //当前页数
        model.addAttribute("TotalPages", pageInfo.getPages());      //总页数
        model.addAttribute("students", pageInfo.getRecords());    //集合
        model.addAttribute("msg", "查询成功");
        return new ModelAndView("result/result2");
    }

    /**
     * Description 查询三面结果
     * Param [model, pageNum, pageSize]
     **/
    @RequestMapping("/threeInterviewList")
    public ModelAndView threeInterviewList(Model model, @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                           @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        Page<Student> pageInfo = studentService.ThreeInterviewList(pageNum, pageSize);
        model.addAttribute("Number", pageInfo.getCurrent());  //当前页数
        model.addAttribute("TotalPages", pageInfo.getPages());      //总页数
        model.addAttribute("students", pageInfo.getRecords());    //集合
        model.addAttribute("msg", "查询成功");
        return new ModelAndView("result/result3");
    }
    /**
     * Description 取消当前面试成绩
     * Param [studentId]
     **/
    @RequestMapping("/cancelResult")
    public Result cancelResult(int studentId){
        return studentService.cancelResult(studentId);
    }
    /**
     * Description 查看面试详情
     * Param [studentId, model]
     **/
    @RequestMapping("/interviewResult")
    public ModelAndView interviewResult(int studentId, Model model) {
        Student student = studentService.findStudentById(studentId);
        model.addAttribute("student", student);
        /*
         * Description 第一次面试成绩
         **/
        List<Interview> oneInterviews = interviewService.oneInterviewResult(studentId);
        Map<String, List> mapOne = new HashMap<>(oneInterviews.size());
        if (oneInterviews.size() != 0) {
            String strOne = oneInterviews.get(0).getDetail();//行为举止，言谈举止，个人素养-7,8,4
            strOne = strOne.substring(0, strOne.indexOf("-"));
            String[] scoreItemsOne = strOne.split(",");
            List<String> scoreItemOne = new ArrayList<>(Arrays.asList(scoreItemsOne));
            model.addAttribute("scoreItemOne", scoreItemOne);//打分项集合
            for (int i = 0; i < oneInterviews.size(); i++) {
                String str1 = oneInterviews.get(i).getDetail();//行为举止,言谈举止,个人素养,综合评价-7,8,4,还可以
                str1 = str1.substring(str1.indexOf("-") + 1, str1.length());
                String[] scores1 = str1.split(",");
                //处理英文逗号
                CommaUtil commaUtil = new CommaUtil();
                List<String> list = commaUtil.comma(scores1,scoreItemsOne);
                float score = 0;
                for (int j = 0; j < list.size()-1; j++) {
                    score += Float.parseFloat(list.get(j));
                }
                score = score/(list.size()-1)*10;
                List<String> score1 = new ArrayList<>();
                score1.add(oneInterviews.get(i).getInterviewer());
                score1.addAll(list);
                score1.add((float)(Math.round(score*100))/100+"");
                mapOne.put(i + "", score1);
            }
        }
        System.out.println(mapOne + "--------------");
        model.addAttribute("mapOne", mapOne);//评委及分数集合
        /*
         * Description 第二次面试成绩
         **/
        List<Interview> twoInterviews = interviewService.twoInterviewResult(studentId);
        Map<String, List> mapTwo = new HashMap<>(twoInterviews.size());
        if (twoInterviews.size() != 0) {
            String strTwo = twoInterviews.get(0).getDetail();//行为举止，言谈举止，个人素养-7,8,6
            strTwo = strTwo.substring(0, strTwo.indexOf("-"));
            String[] scoreItemsTwo = strTwo.split(",");
            List<String> scoreItemTwo = new ArrayList<>(Arrays.asList(scoreItemsTwo));
            model.addAttribute("scoreItemTwo", scoreItemTwo);//打分项集合
            for (int i = 0; i < twoInterviews.size(); i++) {
                String str2 = twoInterviews.get(i).getDetail();//行为举止，言谈举止，个人素养-7,8,4
                str2 = str2.substring(str2.indexOf("-") + 1, str2.length());
                String[] scores2 = str2.split(",");
                //处理英文逗号
                CommaUtil commaUtil = new CommaUtil();
                List<String> list = commaUtil.comma(scores2,scoreItemsTwo);
                float score = 0;
                for (int j = 0; j < list.size()-1; j++) {
                    score += Float.parseFloat(list.get(j));
                }
                score = score/(list.size()-1)*10;
                List<String> score2 = new ArrayList<>();
                score2.add(twoInterviews.get(i).getInterviewer());
                score2.addAll(list);
                score2.add((float)(Math.round(score*100))/100+"");
                mapTwo.put(i + "", score2);
                System.out.println("第二次面试成绩");
            }
        }
        System.out.println(mapTwo + "-------------");
        model.addAttribute("mapTwo", mapTwo);//评委及分数集合
        /*
         * Description 第三次面试成绩
         **/
        List<Interview> threeInterviews = interviewService.threeInterviewResult(studentId);
        Map<String, List> mapThree = new HashMap<>(threeInterviews.size());
        if (threeInterviews.size() != 0) {
            String strThree = threeInterviews.get(0).getDetail();//行为举止，言谈举止，个人素养-7,8,6
            strThree = strThree.substring(0, strThree.indexOf("-"));
            String[] scoreItemsThree = strThree.split(",");
            List<String> scoreItemThree = new ArrayList<>(Arrays.asList(scoreItemsThree));
            model.addAttribute("scoreItemThree", scoreItemThree);//打分项集合
            for (int i = 0; i < threeInterviews.size(); i++) {
                String str3 = threeInterviews.get(i).getDetail();//行为举止，言谈举止，个人素养-7,8,4
                str3 = str3.substring(str3.indexOf("-") + 1, str3.length());
                String[] scores3 = str3.split(",");
                //处理英文逗号
                CommaUtil commaUtil = new CommaUtil();
                List<String> list = commaUtil.comma(scores3,scoreItemsThree);
                float score = 0;
                for (int j = 0; j < list.size()-1; j++) {
                    score += Float.parseFloat(list.get(j));
                }
                score = score/(list.size()-1)*10;
                List<String> score3 = new ArrayList<>();
                score3.add(threeInterviews.get(i).getInterviewer());
                score3.addAll(list);
                score3.add((float)(Math.round(score*100))/100+"");
                mapThree.put(i + "", score3);
                System.out.println("第三次面试成绩");
            }
        }
        System.out.println(mapThree + "-------------");
        model.addAttribute("mapThree", mapThree);//评委及分数集合
        return new ModelAndView("perinfor");
    }

    /**
     * Description 查看我的面试人员详情
     * Param [studentId, model]
     **/
    @RequestMapping("/interviewPersonalResult")
    public ModelAndView interviewPersonalResult(int studentId, Model model) {
        Student student = studentService.findStudentById(studentId);
        model.addAttribute("student", student);
        /*
         * Description 第一次面试成绩
         **/
        List<Interview> oneInterviews = interviewService.oneInterviewResult(studentId);
        Map<String, List> mapOne = new HashMap<>(oneInterviews.size());
        if (oneInterviews.size() != 0) {
            String strOne = oneInterviews.get(0).getDetail();//行为举止，言谈举止，个人素养-7,8,4
            strOne = strOne.substring(0, strOne.indexOf("-"));
            String[] scoreItemsOne = strOne.split(",");
            List<String> scoreItemOne = new ArrayList<>(Arrays.asList(scoreItemsOne));
            model.addAttribute("scoreItemOne", scoreItemOne);//打分项集合
            for (int i = 0; i < oneInterviews.size(); i++) {
                System.out.println(" ");
                String str1 = oneInterviews.get(i).getDetail();//行为举止，言谈举止，个人素养-7,8,4
                str1 = str1.substring(str1.indexOf("-") + 1, str1.length());
                String[] scores1 = str1.split(",");
                //处理英文逗号
                CommaUtil commaUtil = new CommaUtil();
                List<String> list = commaUtil.comma(scores1,scoreItemsOne);
                float score = 0;
                for (int j = 0; j < list.size()-1; j++) {
                    score += Float.parseFloat(list.get(j));
                }
                score = score/(list.size()-1)*10;
                List<String> score1 = new ArrayList<>();
                score1.add(oneInterviews.get(i).getInterviewer());
                score1.addAll(list);
                score1.add((float)(Math.round(score*100))/100+"");
                mapOne.put(i + "", score1);
            }
        }
        System.out.println(mapOne + "-------------");
        model.addAttribute("mapOne", mapOne);//评委及分数集合
        /*
         * Description 第二次面试成绩
         **/
        List<Interview> twoInterviews = interviewService.twoInterviewResult(studentId);
        Map<String, List> mapTwo = new HashMap<>(twoInterviews.size());
        if (twoInterviews.size() != 0) {
            String strTwo = twoInterviews.get(0).getDetail();//行为举止，言谈举止，个人素养-7,8,6
            strTwo = strTwo.substring(0, strTwo.indexOf("-"));
            String[] scoreItemsTwo = strTwo.split(",");
            List<String> scoreItemTwo = new ArrayList<>(Arrays.asList(scoreItemsTwo));
            model.addAttribute("scoreItemTwo", scoreItemTwo);//打分项集合
            for (int i = 0; i < twoInterviews.size(); i++) {
                System.out.println(" ");
                String str2 = twoInterviews.get(i).getDetail();//行为举止，言谈举止，个人素养-7,8,4
                str2 = str2.substring(str2.indexOf("-") + 1, str2.length());
                String[] scores2 = str2.split(",");
                //处理英文逗号
                CommaUtil commaUtil = new CommaUtil();
                List<String> list = commaUtil.comma(scores2,scoreItemsTwo);
                float score = 0;
                for (int j = 0; j < list.size()-1; j++) {
                    score += Float.parseFloat(list.get(j));
                }
                score = score/(list.size()-1)*10;
                List<String> score2 = new ArrayList<>();
                score2.add(twoInterviews.get(i).getInterviewer());
                score2.addAll(list);
                score2.add((float)(Math.round(score*100))/100+"");
                mapTwo.put(i + "", score2);
                System.out.println("第二次面试成绩");
            }
        }
        System.out.println(mapTwo + "-------------");
        model.addAttribute("mapTwo", mapTwo);//评委及分数集合
        /*
         * Description 第三次面试成绩
         **/
        List<Interview> threeInterviews = interviewService.threeInterviewResult(studentId);
        Map<String, List> mapThree = new HashMap<>(threeInterviews.size());
        if (threeInterviews.size() != 0) {
            String strThree = threeInterviews.get(0).getDetail();//行为举止，言谈举止，个人素养-7,8,6
            strThree = strThree.substring(0, strThree.indexOf("-"));
            String[] scoreItemsThree = strThree.split(",");
            List<String> scoreItemThree = new ArrayList<>(Arrays.asList(scoreItemsThree));
            model.addAttribute("scoreItemThree", scoreItemThree);//打分项集合
            for (int i = 0; i < threeInterviews.size(); i++) {
                System.out.println(" ");
                String str3 = threeInterviews.get(i).getDetail();//行为举止，言谈举止，个人素养-7,8,4
                str3 = str3.substring(str3.indexOf("-") + 1, str3.length());
                String[] scores3 = str3.split(",");
                //处理英文逗号
                CommaUtil commaUtil = new CommaUtil();
                List<String> list = commaUtil.comma(scores3,scoreItemsThree);
                float score = 0;
                for (int j = 0; j < list.size()-1; j++) {
                    score += Float.parseFloat(list.get(j));
                }
                score = score/(list.size()-1)*10;
                List<String> score3 = new ArrayList<>();
                score3.add(threeInterviews.get(i).getInterviewer());
                score3.addAll(list);
                score3.add((float)(Math.round(score*100))/100+"");
                mapThree.put(i + "", score3);
                System.out.println("第三次面试成绩");
            }
        }
        System.out.println(mapThree + "-------------");
        model.addAttribute("mapThree", mapThree);//评委及分数集合
        return new ModelAndView("personalPerinfor");
    }

    /**
     * Description 面试通过
     * Param [studentId]
     **/
    @RequestMapping("/passInterview")
    public Result passInterview(int studentId) {
        return studentService.passInterview(studentId);
    }

    /**
     * Description 面试失败
     * Param [studentId]
     **/
    @RequestMapping("/outInterview")
    public Result outInterview(int studentId) {
        return studentService.outInterview(studentId);
    }
}
