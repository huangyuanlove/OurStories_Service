package com.huangyuanlove.ourstories.mapper;

import com.huangyuanlove.ourstories.bean.TestBean;
import com.huangyuanlove.ourstories.entites.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by huangyuan on 16-11-27.
 */
public interface UserMapper {

    @Select("select id,name,email,age,rongIMToken from user where id=#{id}")
    @ResultType(User.class)
    public User findUserById(int id);

    @Delete("delete from user where id=#{id}")
    @ResultType(Integer.class)
    public int deleteUserById(int id);

    @Update("update user set rongIMToken = #{0} where id = #{1}" )
    public int updateUserRongIMTokenById(String rongIMToken, int id);

    @SelectKey(before = false, keyProperty = "id", resultType = int.class, statement = "SELECT LAST_INSERT_ID() AS id")
    @Insert("INSERT INTO user (name,email,age,rongIMToken,password) " +
            "values (#{name},#{email},#{age},#{rongIMToken},#{password})")
    @ResultType(Integer.class)
    public int registerUser(User user);


    @Select("select id,name,email,age,rongIMToken from user where email = #{email}")
    @ResultType(User.class)
    public User findUserByEmail(String email);

    @Select("select id,name,email,age,rongIMToken* from user where email= #{email} and password=#{password}")
    @ResultType(User.class)
    public User findUserByEmailAndPWD(String email, String password);

    @Select("select * from actor")
    public List<TestBean> test();

}
