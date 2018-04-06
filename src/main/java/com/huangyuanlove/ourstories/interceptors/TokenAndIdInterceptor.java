package com.huangyuanlove.ourstories.interceptors;

import com.google.gson.reflect.TypeToken;
import com.huangyuanlove.ourstories.bean.RequestResultBean;
import com.huangyuanlove.ourstories.rongim.io.rong.util.GsonUtil;
import com.huangyuanlove.ourstories.utils.Config;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenAndIdInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String id = httpServletRequest.getParameter("id");
        String token = httpServletRequest.getParameter("token");
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(token)) {
            RequestResultBean<Object> requestResultBean = new RequestResultBean<Object>();
            requestResultBean.setErrmsg("id或token不正确");
            requestResultBean.setStatus(Config.ERROR);
            httpServletResponse.getWriter().write(GsonUtil.toJson(requestResultBean, new TypeToken<String>() {
            }.getType()));
            return false;
        }
        return true;

    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
