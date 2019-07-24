package com.web.interceptor;

import com.bean.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;


public class PrivilegeInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        User currentUser=(User) ServletActionContext.getRequest().getSession().getAttribute("currentUser");
        if (currentUser==null){
            ActionSupport support=(ActionSupport)invocation.getAction();
            support.addActionError("没有登录，无权限访问");
            return support.LOGIN;
        }else {
            return invocation.invoke();
        }
    }


}
