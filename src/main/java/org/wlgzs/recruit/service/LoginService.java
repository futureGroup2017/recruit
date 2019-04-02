package org.wlgzs.recruit.service;

import javax.servlet.http.HttpSession;

public interface LoginService {

    boolean login(String userName, String password, HttpSession session);
}
