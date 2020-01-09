package com.framework.admin.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.framework.admin.entity.FwPermissionEntity;
import com.framework.admin.service.FwPermissionService;
@Service
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	 private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	    private org.slf4j.Logger LOG = LoggerFactory.getLogger(getClass());
	    
	    private final static Integer BUTTON_TYPE=2;
	    @Autowired
	    private FwPermissionService fwPermissionService;

	    /**
	     * 加载资源，初始化资源变量
	     */
	    @PostConstruct
	    public void loadResourceDefine() {
	    	LOG.info("初始化资源权限");
	        if (resourceMap == null) {
	            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
	            List<FwPermissionEntity> resources = fwPermissionService.queryByType(BUTTON_TYPE);//获取按钮资源
	            for (FwPermissionEntity resource : resources) {
	                Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
	                ConfigAttribute configAttribute = new SecurityConfig(resource.getUrl());
	                configAttributes.add(configAttribute);
	                resourceMap.put(resource.getUrl(), configAttributes);
	            }
	            LOG.info("所有资源："+JSONObject.toJSONString(resourceMap));
	        }
	        LOG.info("########加载所有资源成功########!!");
	    }

	    @Override
	    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
	        if (resourceMap == null) loadResourceDefine();
	        String requestUrl = ((FilterInvocation) object).getRequestUrl();
//	        LOG.info("请求URL为----------------"+requestUrl);
	        requestUrl = requestUrl.split("\\?")[0];
//	        System.out.println("请求URL转换后为----------------"+requestUrl);
//	        LOG.info("返回所需求的权限："+JSONObject.toJSON(resourceMap.get(requestUrl)));
	       // 返回当前 url  所需要的权限
	         return resourceMap.get(requestUrl);
	    }



	    @Override
	    public Collection<ConfigAttribute> getAllConfigAttributes() {
	        return null;
	    }

	    @Override
	    public boolean supports(Class<?> aClass) {
	        return true;
	    }

}
