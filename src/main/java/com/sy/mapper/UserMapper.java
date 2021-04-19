package com.sy.mapper;

import com.sy.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author Administrator
 * @Date: 2021/4/13 10:24
 * @Version 1.0
 */
public interface UserMapper {

    @Select("select * from user where username=#{username} and password=#{password}")
    User selectWithLogin(User user)throws Exception;

    @Select("select * from user where id = #{id}")
    User selectById(int id) throws Exception;

    @Select({"<script>"," select * from user\n" +
            "      <where>\n" +
            "          <if test=\"id !=null\">\n" +
            "              and id = #{id}\n" +
            "          </if>\n" +
            "          <if test=\"username !=null and username !=''\">\n" +
            "              and username = #{username}\n" +
            "          </if>\n" +
            "          <if test=\"password !=null and password !=''\">\n" +
            "              and password = #{password}\n" +
            "          </if>\n" +
            "          <if test=\"sex !=null\">\n" +
            "              and sex = #{sex}\n" +
            "          </if>\n" +
            "      </where>\n" +
            "    ","</script>"})
    List<User> selectPage(User user) throws Exception;

    @Insert("insert into user (username,password) values (#{username},#{password})")
    @SelectKey(keyProperty = "id",keyColumn = "ID",resultType = User.class,before = false,statement = "select last_insert_id() as id from dual")
    int insert(User user) throws Exception;

    @Delete("delete from user where id  = #{id}")
    int delete(int id) throws Exception;

    @Update("update user set username=#{username},password=#{password},sex=#{sex} where id = #{id}")
    int update(User user) throws Exception;



}
