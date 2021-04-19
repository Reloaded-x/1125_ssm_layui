package com.sy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.mapper.UserMapper;
import com.sy.model.User;
import com.sy.service.UserService;
import com.sy.util.RedisClient;
import com.sy.util.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date: 2021/4/13 10:41
 * @Version 1.0
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;
    @Override
    public User findWithLogin(User user) throws Exception {
        return mapper.selectWithLogin(user);
    }

    @Override
    public User findById(int id) throws Exception {
        return mapper.selectById(id);
    }

    @Override
    public List<User> findPage(User user) throws Exception {
        return mapper.selectPage(user);
    }

    /**
     * 当前用户登录成功，查询列表数据，第一次从数据库读，以后都从redis中读
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo<User> findPage(User user, int pageNum, int pageSize, User sessionUser) throws Exception {

//        PageHelper.startPage(pageNum, pageSize);
//        List<User> list = mapper.selectPage(user);
//        PageInfo<User> pageInfo = new PageInfo<>(list);
        //1.先从redis中查询，如果有，直接返回，没有，则从数据库中读取，放入redis中
        String key = RedisKey.USER_KEY+sessionUser.getId()+pageNum+user.toString();
        Object object = RedisClient.getObject(key);
        PageInfo<User> pageInfo = null;
        if(object==null){
            PageHelper.startPage(pageNum, pageSize);
            List<User> list = mapper.selectPage(user);
            pageInfo = new PageInfo<>(list);
            RedisClient.setObject(key, pageInfo);
        }else{
            pageInfo = (PageInfo)object;
        }
        return pageInfo;
    }


    @Transactional
    @Override
    public int save(User user) throws Exception {
        int insert = mapper.insert(user);
        if(insert>0){
            RedisClient.invalidataStartWith(RedisKey.USER_KEY);
        }
        return insert;
    }

    @Transactional
    @Override
    public int remove(int id) throws Exception {
        int delete = mapper.delete(id);
        if(delete>0){
            RedisClient.invalidataStartWith(RedisKey.USER_KEY);
        }
        return delete;
    }

    @Transactional
    @Override
    public int modify(User user) throws Exception {
        int update = mapper.update(user);
        if(update>0){
            RedisClient.invalidataStartWith(RedisKey.USER_KEY);
        }
        return update;
    }
}
