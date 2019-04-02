package org.wlgzs.recruit.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Service;
import org.wlgzs.recruit.dao.UserDao;
import org.wlgzs.recruit.domain.User;
import org.wlgzs.recruit.service.LoginService;
import org.wlgzs.recruit.util.result.Result;
import org.wlgzs.recruit.util.result.ResultCodeEnum;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 阿杰
 * Create 2018-08-18 9:59
 * Description:
 */
@Service
public class LoginServiceImpl implements LoginService{
    @Resource
    private UserDao userDao;

    @Override
    public boolean login(String userName, String password, HttpSession session) {
        List<User> users = userDao.selectList(new EntityWrapper<>());
        for (User user : users) {
            if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
                session.setMaxInactiveInterval(60 * 60);
                session.setAttribute("userName", user.getName());
                return true;
            }
        }
        return false;
    }

}
