package com.isothermal.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.isothermal.dao.LoginTokenDao;
import com.isothermal.model.LoginToken;
import com.isothermal.model.User;



public class LoginInterceptor extends HandlerInterceptorAdapter {
    
    @Autowired
    private LoginTokenDao loginTokenDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO Auto-generated method stub
        Cookie cookie=WebUtils.getCookie(request, "loginToken");
        
        User user=new User();
        
        
        if(cookie!=null){
            String token=cookie.getValue();
            LoginToken loginToken=loginTokenDao.getLoginTokenByToken(token);
            
            if(loginToken!=null){
                user=loginToken.getUser();
            }
        }
        
        
        System.out.println(user);
        request.setAttribute("loggedInUser", user);
        return true;
    }
    

    

}
