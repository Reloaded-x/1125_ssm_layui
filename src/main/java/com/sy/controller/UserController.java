package com.sy.controller;

import com.github.pagehelper.PageInfo;
import com.sy.model.Result;
import com.sy.model.User;
import com.sy.service.UserService;
import com.sy.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


/**
 * @ClassName UserController
 * @Description TODO
 * @Author Administrator
 * @Date: 2021/4/13 10:44
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    public Result login(User user, Result result, HttpSession session) throws Exception{
        User findUser = userService.findWithLogin(user);
        if(findUser!=null){
            session.setAttribute(Constant.SESSION_USER, findUser);
        }else {
            result.respError();
        }
        return result;
    }

    @RequestMapping("/list.do")
    public Result list(User user,
                       @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                       Result result,
                       HttpSession session) throws Exception{
        User sessionUser = (User)session.getAttribute(Constant.SESSION_USER);
        //result.setData(sessionUser);
        PageInfo<User> page = userService.findPage(user, pageNum, pageSize,sessionUser);
        result.setPageInfo(page);
        result.setData(page.getList());
        result.setCount(page.getTotal());
        return result;
    }

    @RequestMapping("/save.do")
    public Result save(User user, Result result)throws Exception{
        int i = userService.save(user);
        if(i==0){
            result.respError();
        }
        return result;
    }

    @RequestMapping("/remove.do")
    public Result remove(Integer id,Result result) throws Exception{
        int i = userService.remove(id);
        if(i==0){
            result.respError();
        }
        return result;
    }
    @RequestMapping("/modify.do")
    public Result modify(User user, Result result)throws Exception{
        int i = userService.modify(user);
        if(i==0){
            result.respError();
        }
        return result;
    }
    @RequestMapping("/info.do")
    public Result findById(Integer id,Result result)throws Exception{
        System.out.println(id);
        User user = userService.findById(id);
        result.setData(user);
        return result;
    }
}
