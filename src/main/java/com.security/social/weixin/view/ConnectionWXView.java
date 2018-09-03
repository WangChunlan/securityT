package com.security.social.weixin.view;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConnectionWXView extends AbstractView {
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        httpServletResponse.setContentType("application/json;charset=UTF-8");

        if(map.get("connections")==null){
            httpServletResponse.getWriter().write("<h3>解绑成功</h3>");

        }else{
            httpServletResponse.getWriter().write("<h3>绑定成功</h3>");
        }
    }
}
