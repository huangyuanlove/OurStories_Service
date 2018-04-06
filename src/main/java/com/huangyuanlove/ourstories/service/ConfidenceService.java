package com.huangyuanlove.ourstories.service;

import com.huangyuanlove.ourstories.entites.Confidence;

import java.util.Date;
import java.util.List;

public interface ConfidenceService {

    public int insertConfidence(Confidence confidence);

    public int updateConfidenceById(String targetName, String content, String images, Date time, int id);

    public Confidence findConfidenceById(int id);

    public List<Confidence> findConfidenceByFromUserId(int fromUserId);


    public List<Confidence> findConfidenceByTargetName(String targetName);


    public int deleteConfidenceById(int id);
}
