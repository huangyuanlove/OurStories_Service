package com.huangyuanlove.ourstories.mapper;

import com.huangyuanlove.ourstories.bean.TestBean;
import com.huangyuanlove.ourstories.entites.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

/**
 * Created by huangyuan on 16-11-27.
 */
public interface UserMapper {

    @Select("select * from user where id=#{id}")
    @ResultType(User.class)
    public User findUserById(int id);


    /**
     * 注册
     * @param user
     * @return
     */
    @SelectKey(before = false,keyProperty = "id",resultType = int.class,statement="SELECT LAST_INSERT_ID() AS id")
    @Insert("INSERT INTO user (isAllowedAccost,nickName,email,password,registerDate,portraitUrl,rongIMToken) " +
            "values (#{isAllowedAccost},#{nickName},#{email}," +
            "#{password},#{registerDate},#{portraitUrl}," +
            "#{rongIMToken})")
    @ResultType(User.class)
    public int registerUser(User user);


    @Select("select * from user where email = #{email}")
    @ResultType(User.class)
    public User findUserByEmail(String email);

    @Select("select * from user where email= #{email} and password=#{password}")
    @ResultType(User.class)
    public User findUserByEmailAndPWD(String email,String password);

    @Select("select * from actor")
    public List<TestBean> test();

}
