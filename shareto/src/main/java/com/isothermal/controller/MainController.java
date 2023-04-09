package com.isothermal.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.protobuf.Method;
import com.isothermal.dao.CommentDao;
import com.isothermal.dao.LoveDao;
import com.isothermal.dao.OutlookDao;
import com.isothermal.dao.UserDao;
import com.isothermal.model.Comment;
import com.isothermal.model.CommentJson;
import com.isothermal.model.Love;
import com.isothermal.model.Outlook;
import com.isothermal.model.User;

@Controller
public class MainController {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm:ss");
    
    String convert(Timestamp ts) {
        return ts.toLocalDateTime().format(FORMATTER);
    }
    
    @Autowired
    private OutlookDao outlookDao;
    @Autowired
    private LoveDao loveDao;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private CommentDao commentDao;
    
    @RequestMapping(path = {"/home",""},method = {RequestMethod.GET})
    public String home(@RequestAttribute("loggedInUser") User loggedInUser,Model model){
        System.out.println(loggedInUser);
        if(loggedInUser.is_anonymous()){
            return "home_anonymous";
        }

        List<Outlook> outlooks = outlookDao.getOutlooks();

        for(Outlook outlook:outlooks){
            if(this.loveDao.getLoveByOutlookUser(outlook, loggedInUser)!=null){
                outlook.setLovedByLoggedInUser(true);
            }
            outlook.setUpdated_at_formatted(this.convert(outlook.getUpdated_at()));
            Set<Comment>comments=outlook.getComments();
            List<Comment>comments_formatted=new ArrayList<>();
            for(Comment comment:comments){
                comment.setUpdated_at_formatted(this.convert(comment.getUpdated_at()));
                comments_formatted.add(comment);
            }
            Collections.sort(comments_formatted,new Comparator<Comment>() {

                @Override
                public int compare(Comment o1, Comment o2) {
                    // TODO Auto-generated method stub
                    // throw new UnsupportedOperationException("Unimplemented method 'compare'");

                    if(o1.getId()<o2.getId()){
                        return 1;
                    }

                    return -1;
                }
                
            });
            outlook.setComments_formatted(comments_formatted);
        }

        

        Collections.reverse(outlooks);

        System.out.println(outlooks.get(0));

        model.addAttribute("outlooks", outlooks);
        model.addAttribute("loggedInUser", loggedInUser);

        return "home";
    }

    @RequestMapping(value = "/outlook",method = {RequestMethod.POST})
    public String createoutlook(@RequestAttribute("loggedInUser") User loggedInUser,@ModelAttribute("outlook") Outlook outlook){
        if(loggedInUser.is_anonymous()){
            return "redirect:/home";
        }
        System.out.println(outlook);
        Timestamp timestamp=Timestamp.valueOf(LocalDateTime.now());
        outlook.setUser(loggedInUser);
        outlook.setCreated_at(timestamp);
        outlook.setUpdated_at(timestamp);

        outlookDao.createOrUpdateOutlook(outlook);

        return "redirect:/home";

    }

    @RequestMapping(value="/love/{outlook_id}",method = {RequestMethod.GET})
    @ResponseBody
    public String loveOutlook(@RequestAttribute("loggedInUser") User loggedInUser,@PathVariable("outlook_id") int outlook_id){
        if(loggedInUser.is_anonymous()){
            return "redirect:/home";
        }
        System.out.println(loggedInUser);
        Outlook outlook = this.outlookDao.getOutlook(outlook_id);

        Love love=this.loveDao.getLoveByOutlookUser(outlook, loggedInUser);

        if(love!=null){
            love.setOutlook(null);
            love.setUser(null);
            this.loveDao.createOrUpdateLove(love);
            this.loveDao.deleteLove(love.getId());
            outlook=this.outlookDao.getOutlook(outlook_id);
            return "false "+outlook.getLoves().size();
        }
        Love new_love=new Love();
        new_love.setOutlook(outlook);
        if(outlook.getUser().equals(loggedInUser)){
            new_love.setUser(outlook.getUser());
        }
        else{
            new_love.setUser(loggedInUser);
        }    
        System.out.println(new_love);
        // System.out.println(loggedInUser.hashCode());
        // System.out.println(outlook.getUser().hashCode());
        // System.out.println("###");
        // System.out.println(loggedInUser.equals(outlook.getUser()));
        // System.out.println(loggedInUser==(outlook.getUser()));
        this.loveDao.createOrUpdateLove(new_love);
        outlook=this.outlookDao.getOutlook(outlook_id);

        return "true "+outlook.getLoves().size();

    }

    @RequestMapping(value = "/delete_outlook/{outlook_id}",method = {RequestMethod.GET})
    public String delete_outlook(@RequestAttribute("loggedInUser") User loggedInUser,@PathVariable("outlook_id") int outlook_id){
          if(loggedInUser.is_anonymous()){
            return "redirect:/home";
          }

          Outlook outlook = this.outlookDao.getOutlook(outlook_id);

          if(loggedInUser.equals(outlook.getUser())==false){
            return "redirect:/home";
          }
          Set<Love> loves=outlook.getLoves();
          for(Love love:loves){
            love.setOutlook(null);
            love.setUser(null);
            this.loveDao.createOrUpdateLove(love);
            this.loveDao.deleteLove(love.getId());
          }

          Set<Comment>comments=outlook.getComments();

          for(Comment comment:comments){
             comment.setUser(null);
             comment.setOutlook(null);
             this.commentDao.createOrUpdateComment(comment);
             this.commentDao.deleteComment(comment.getId());
          }

          outlook.setUser(null);
          this.outlookDao.createOrUpdateOutlook(outlook);
          this.outlookDao.deleteOutlook(outlook_id);

          return "redirect:/home";
    }


    @RequestMapping(value = "/update_outlook",method = {RequestMethod.POST})
    public String update_outlook(@RequestAttribute("loggedInUser") User loggedInUser,@ModelAttribute("outlook") Outlook updated_outlook){
        if(loggedInUser.is_anonymous()){
            return "redirect:/home";
        }
        Outlook outlook=this.outlookDao.getOutlook(updated_outlook.getId());
        if(outlook.getUser().equals(loggedInUser)==false){
            return "redirect:/home";
        }
        System.out.println(outlook);
        Timestamp timestamp=Timestamp.valueOf(LocalDateTime.now());
        outlook.setUpdated_at(timestamp);
        outlook.setTitle(updated_outlook.getTitle());
        outlook.setBody(updated_outlook.getBody());

        outlookDao.createOrUpdateOutlook(outlook);

        return "redirect:/home";

    }

    @RequestMapping(path = {"/create_comment/{outlook_id}"},method = {RequestMethod.POST})
    @ResponseBody
    public String create_comment(@RequestAttribute("loggedInUser") User loggedInUser,@RequestParam("comment") String content,@PathVariable("outlook_id") int outlook_id){
        if(loggedInUser.is_anonymous()){
            return "redirect:/home";
        }
        Outlook outlook=this.outlookDao.getOutlook(outlook_id);
        Comment comment=new Comment();
        comment.setContent(content);
        Timestamp timestamp=Timestamp.valueOf(LocalDateTime.now());
        comment.setUpdated_at(timestamp);
        comment.setCreated_at(timestamp);
        comment.setOutlook(outlook);

        if(loggedInUser.equals(outlook.getUser())){
            comment.setUser(outlook.getUser());
        }
        else{
            comment.setUser(loggedInUser);
        }

        this.commentDao.createOrUpdateComment(comment);

        return "success";
    }

    @RequestMapping("/get_comments/{outlook_id}")
    @ResponseBody
    public  ResponseEntity<List<CommentJson>> get_comments(@RequestAttribute("loggedInUser") User loggedInUser,@PathVariable("outlook_id") int outlook_id){
        List<CommentJson> comments=new ArrayList<>();
        if(loggedInUser.is_anonymous()){
            return new ResponseEntity<List<CommentJson>>(comments,HttpStatus.ACCEPTED);
        }

        Outlook outlook=this.outlookDao.getOutlook(outlook_id);

        Set<Comment>outlook_Comments=outlook.getComments();

        for(Comment comment:outlook_Comments){
            comment.setUpdated_at_formatted(convert(comment.getUpdated_at()));
            CommentJson commentJson=new CommentJson(comment.getId(), comment.getContent(), comment.getUser().getName(), comment.getUpdated_at_formatted(),comment.getUser().getId());
            comments.add(commentJson);
        }

        Collections.sort(comments,new Comparator<CommentJson>() {

            @Override
            public int compare(CommentJson o1, CommentJson o2) {
                // TODO Auto-generated method stub
                // throw new UnsupportedOperationException("Unimplemented method 'compare'");

                if(o1.getId()<o2.getId()){
                    return 1;
                }

                return -1;
            }
            
        });

        return new ResponseEntity<List<CommentJson>>(comments,HttpStatus.ACCEPTED);
    }

    @RequestMapping(path={"/delete_comment/{comment_id}"},method = {RequestMethod.GET})
    public String delete_comment(@RequestAttribute("loggedInUser") User loggedInUser,@PathVariable("comment_id") int id){
        if(loggedInUser.is_anonymous()){
            return "redirect:/home";
        }

        Comment comment = this.commentDao.getComment(id);

        if(comment.getUser().equals(loggedInUser)==false){
            return "redirect:/home";
        }

        comment.setUser(null);
        comment.setOutlook(null);
        this.commentDao.createOrUpdateComment(comment);
        this.commentDao.deleteComment(id);

        return "redirect:/home";
    }
    
}
