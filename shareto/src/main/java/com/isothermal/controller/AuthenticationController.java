package com.isothermal.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.isothermal.dao.LoginTokenDao;
import com.isothermal.dao.UserDao;
import com.isothermal.model.LoginToken;
import com.isothermal.model.User;

@Controller
public class AuthenticationController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginTokenDao loginTokenDao;

    public static String getAlphaNumericString(int n)
    {
    
     // choose a Character random from this String
     String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";
    
     // create StringBuffer size of AlphaNumericString
     StringBuilder sb = new StringBuilder(n);
    
     for (int i = 0; i < n; i++) {
    
      // generate a random number between
      // 0 to AlphaNumericString variable length
      int index
       = (int)(AlphaNumericString.length()
         * Math.random());
    
      // add Character one by one in end of sb
      sb.append(AlphaNumericString
         .charAt(index));
     }
    
     return sb.toString();
    }

    
    @RequestMapping(path = {"/register"},method = {RequestMethod.GET,RequestMethod.POST})
    public String register(@ModelAttribute("user") User user,HttpServletRequest request){
        System.out.println(request.getMethod());
        System.out.println(RequestMethod.POST.toString());
        if(request.getMethod().equals(RequestMethod.POST.toString())){
               System.out.println(user);
               LoginToken loginToken=new LoginToken();
               loginTokenDao.createOrUpdateLoginToken(loginToken);
               Integer tokenId=loginToken.getId();
               String token=tokenId.toString()+"_"+getAlphaNumericString(10);
               loginToken.setToken(token);
               loginTokenDao.createOrUpdateLoginToken(loginToken);

               user.setLoginToken(loginToken);
               userDao.createOrUpdateUser(user);
               System.out.println(user);
               return "redirect:/login";

        }

        return "register";
    }
    
    @RequestMapping(path = {"/login"},method = {RequestMethod.GET,RequestMethod.POST})
    public String login(@RequestParam(required = false) String email,@RequestParam(required = false) String password,HttpServletRequest request,HttpServletResponse response){
        if(request.getMethod().equals(RequestMethod.POST.toString())){
             User user=userDao.getUserFromEmailAndPassword(email, password);
             if(user==null){
                return "login";
             }
             Cookie cookie= new Cookie("loginToken", user.getLoginToken().getToken());
             response.addCookie(cookie);
             return "redirect:/home";
        }

        return "login";
    }


    @RequestMapping("/logout")
    public String logout(HttpServletResponse response){
        Cookie cookie =new Cookie("loginToken", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/login";
    }

    
    
}
