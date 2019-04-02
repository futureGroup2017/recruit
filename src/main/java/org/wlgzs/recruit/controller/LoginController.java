package org.wlgzs.recruit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.recruit.base.BaseController;
import org.wlgzs.recruit.domain.Student;
import org.wlgzs.recruit.domain.User;
import org.wlgzs.recruit.util.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 阿杰
 * Create 2018-08-18 9:50
 * Description: 登录
 */
@RestController
public class LoginController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    /**
     * Description 进入报名
     **/
    @RequestMapping("/signup")
    public ModelAndView toIndex(){
        return new ModelAndView("Frontindex");
    }
    /**
     * Description 请求报名
     * Param [student]
     **/
    @RequestMapping("/insert")
    public Result insert(Student student) {
        return studentService.insert(student);
    }
    /**
     * Description 检查session
     **/
    @GetMapping("/")
    public Object checkToken(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("userName")!=null) {
            return new ModelAndView("index");
        }
        return new ModelAndView("login");
    }
    /**
     * Description 跳转至登录
     **/
    @RequestMapping("/toLogin")
    public ModelAndView toLogin(){
        return new ModelAndView("login");
    }
    /**
     * Description 登录
     **/
    @RequestMapping("/login")
    public ModelAndView login(Model model,String username, String password, HttpSession session){
        if(loginService.login(username,password,session)){
            model.addAttribute("msg","登录成功");
            return new ModelAndView("index");
        }
        model.addAttribute("msg","账号或密码错误");
        return new ModelAndView("login");
    }
    /**
     * Description 退出登录
     **/
    @GetMapping("toLoginOut")
    public ModelAndView toLoginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("userName");
        return new ModelAndView("login");
    }
    /**
     * Description 进入主页
     **/
    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

}
