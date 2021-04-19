package com.sy.service;

import com.github.pagehelper.PageInfo;
import com.sy.model.User;

import java.util.List;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Administrator
 * @Date: 2021/4/13 10:36
 * @Version 1.0
 */
public interface UserService {

    User findWithLogin(User user)throws Exception;

    User findById(int id) throws Exception;

    List<User> findPage(User user) throws Exception;

    PageInfo<User> findPage(User user, int pageNum, int pageSize, User sessionUser)throws Exception;

    int save(User user) throws Exception;

    int remove(int id) throws Exception;

    int modify(User user) throws Exception;
}
