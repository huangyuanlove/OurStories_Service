package com.huangyuanlove.ourstories.controller;

import com.huangyuanlove.ourstories.bean.RequestResultBean;
import com.huangyuanlove.ourstories.bean.RequestResultListBean;
import com.huangyuanlove.ourstories.entites.Confidence;
import com.huangyuanlove.ourstories.service.ConfidenceService;
import com.huangyuanlove.ourstories.utils.Config;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class ConfidenceController {

    private ConfidenceService confidenceService;

    @Autowired
    public ConfidenceController(ConfidenceService confidenceService) {
        this.confidenceService = confidenceService;
    }

    private static Logger logger = Logger.getLogger(ConfidenceController.class);


    @RequestMapping("getConfidenceById")
    public
    @ResponseBody
    RequestResultBean<Confidence> findConfidenceById(int id) {
        RequestResultBean<Confidence> requestResultBean = new RequestResultBean<Confidence>();

        Confidence confidenceById = confidenceService.findConfidenceById(id);
        if (confidenceById != null) {
            requestResultBean.setStatus(Config.SUCCESS);
            requestResultBean.setData(confidenceById);
        } else {
            requestResultBean.setStatus(Config.ERROR);
            requestResultBean.setErrmsg("没有找到对应数据");
        }
        return requestResultBean;
    }


    @RequestMapping("findConfidencesByFromUser")
    public
    @ResponseBody
    RequestResultListBean<Confidence> findConfidencesByFromUser(int fromUserid) {
        RequestResultListBean<Confidence> requestResultListBean = new RequestResultListBean<Confidence>();

        List<Confidence> confidenceByFromUserId = confidenceService.findConfidenceByFromUserId(fromUserid);
        if (confidenceByFromUserId != null) {
            requestResultListBean.setData(confidenceByFromUserId);
            requestResultListBean.setStatus(Config.SUCCESS);
        }
        return requestResultListBean;
    }


    @RequestMapping("findConfidencesByTargetName")
    public
    @ResponseBody
    RequestResultListBean<Confidence> findConfidencesByTargetName(String targetName) {
        RequestResultListBean<Confidence> requestResultListBean = new RequestResultListBean<Confidence>();

        List<Confidence> confidenceByTargetName = confidenceService.findConfidenceByTargetName(targetName);
        if (confidenceByTargetName != null) {
            requestResultListBean.setData(confidenceByTargetName);
            requestResultListBean.setStatus(Config.SUCCESS);
        }
        return requestResultListBean;
    }


    @RequestMapping("insertConfidence")
    public @ResponseBody
    RequestResultBean<Confidence> insertConfidence(Confidence confidence, int fromUserId) {
        RequestResultBean<Confidence> requestResultBean = new RequestResultBean<Confidence>();
        confidence.setFromUserId(fromUserId);
        confidence.setTime(new Date());

        int id = confidenceService.insertConfidence(confidence);
        if (id != 0) {
            confidence.setId(id);
            requestResultBean.setStatus(Config.SUCCESS);
            requestResultBean.setData(confidence);
        } else {
            requestResultBean.setStatus(Config.ERROR);
            requestResultBean.setErrmsg("添加失败，请重试");
        }
        return requestResultBean;
    }


    @RequestMapping("deleteConfidenceById")
    public @ResponseBody RequestResultBean<Object> deleteConfidenceByid(int id){
        RequestResultBean<Object> requestResultBean = new RequestResultBean<Object>();
        int i = confidenceService.deleteConfidenceById(id);
        if(i!=0){
            requestResultBean.setStatus(Config.SUCCESS);
        }else{
            requestResultBean.setStatus(Config.ERROR);
            requestResultBean.setErrmsg("删除失败，请重试");
        }
        return  requestResultBean;
    }

}
