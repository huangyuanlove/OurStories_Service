package com.huangyuanlove.ourstories.mapper;

import com.huangyuanlove.ourstories.entites.Confidence;
import com.huangyuanlove.ourstories.entites.User;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface ConfidenceMapper {

    @SelectKey(before = false, keyProperty = "id", resultType = int.class, statement = "SELECT LAST_INSERT_ID() AS id")
    @Insert("INSERT INTO confidence (fromUserId,targetName,content,images,time) " +
            "values (#{fromUserId},#{targetName},#{content},#{images},#{time})")
    @ResultType(Integer.class)
    public int insertConfidence(Confidence confidence);


    @Update("update confidence set targetName = #{targetName}, content = #{content}, images = #{images}, time= #{time}   where id = #{id}")
    public int updateConfidenceById(String targetName, String content, String images, Date time, int id);

    @ResultType(Confidence.class)
    @Select("select id,fromUserId,targetName,content,images,time from confidence where id = #{id}")
    public Confidence findConfidenceById(int id);

    @Select("select id,fromUserId,targetName,content,images,time from confidence where fromUserId = #{fromUserId}")
    public List<Confidence> findConfidenceByFromUserId(int fromUserId);


    @Select("select id,fromUserId,targetName,content,images,time from confidence where targetName like #{targetName}")
    public List<Confidence> findConfidenceByTargetName(String targetName);


    @Delete("delete from confidence where id = #{id}")
    public int deleteConfidenceById(int id);


}
