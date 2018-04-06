package com.huangyuanlove.ourstories.serviceImpl;

import com.huangyuanlove.ourstories.entites.Confidence;
import com.huangyuanlove.ourstories.mapper.ConfidenceMapper;
import com.huangyuanlove.ourstories.service.ConfidenceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ConfidenceServiceImpl implements ConfidenceService {


    @Autowired
    private ConfidenceMapper confidenceMapper;



    public int insertConfidence(Confidence confidence) {
        confidenceMapper.insertConfidence(confidence);
        return confidence.getId();
    }

    public int updateConfidenceById(String targetName, String content, String images, Date time, int id) {
        confidenceMapper.updateConfidenceById(targetName,content,images,time,id);
        return id;
    }


    public Confidence findConfidenceById(int id) {
        return confidenceMapper.findConfidenceById(id);
    }

    public List<Confidence> findConfidenceByFromUserId(int fromUserId) {
        return confidenceMapper.findConfidenceByFromUserId(fromUserId);
    }

    public List<Confidence> findConfidenceByTargetName(String targetName) {
        return confidenceMapper.findConfidenceByTargetName(targetName);
    }

    public int deleteConfidenceById(int id) {
        return confidenceMapper.deleteConfidenceById(id);
    }
}
