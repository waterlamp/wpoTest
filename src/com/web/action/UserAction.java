package com.web.action;

import com.alibaba.fastjson.JSON;
import com.bean.PageModel;
import com.bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.service.UserService;

import com.utils.TestUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.SQLException;


@Component("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User>{
    private User user=new User();
    private int num;
    private boolean select;
    private String result;

    @Autowired
    @Qualifier("userService")
    private UserService service;

    public void setUserService(UserService service) {
        this.service = service;
    }

    @Override
    public User getModel() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String login(){
        ActionContext actionContext=ActionContext.getContext();
        String tel=user.getUserTel();
        String pwd=user.getUserPwd();

        try {
            User user=service.getUser(tel,pwd);
            if(user==null){
                this.addActionError("用户名或密码错误");
                return LOGIN;
            }else {
                actionContext.getSession().put("currentUser",user);
                return "loginSuccess";
            }
        } catch (SQLException e) {
            this.addActionError("数据连接错误");
            return LOGIN;
        }

    }

    public String find(){
        ValueStack stack=ActionContext.getContext().getValueStack();
        int authorityId=user.getAuthorityId();

        String url="user_find.action?select="+this.select+"&authorityId="+authorityId;
        DetachedCriteria dc=DetachedCriteria.forClass(User.class);

        if(authorityId>0){
            dc.add(Restrictions.eq("authorityId",authorityId));
        }

        String name=user.getUserName();
        if (!TestUtils.isEmpty(name)){
            url+="&name="+name;
            dc.add(Restrictions.like("userName","%"+name+"%"));
        }
        String tel=user.getUserTel();
        if (!TestUtils.isEmpty(tel)){
            url+="&tel="+tel;
            dc.add(Restrictions.like("userTel","%"+tel+"%"));
        }

        int page=num>0?num:1;

        try {
            PageModel pageModel=service.find(page,dc,url);

            stack.set("page",pageModel);
            stack.set("authorityId",authorityId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(select){
            return "select";
        }
        return "findSuccess";

    }

    public String save(){
        ValueStack stack= ActionContext.getContext().getValueStack();
        try {
            if (user.getUserId().equals("")){
                user.setUserId(null);
            }
            service.save(user);
            return "saveSuccess";
        } catch (Exception e) {
            e.printStackTrace();
            stack.set("userId",user.getUserId());
            this.addActionError("电话号码重复");
            return "savePage";
        }
    }

    public String gotoSavePage(){
        ValueStack stack=ActionContext.getContext().getValueStack();
        System.out.println("userId："+user.getUserId());
        stack.set("userId",user.getUserId());
        return "savePage";
    }



    public String detail(){
        try {
            User user1=service.getUser(user.getUserId());
            String json= JSON.toJSONString(user1);
            System.out.println(json);
            this.setResult(json);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String checkUserName(){
        try {
            User user1=service.getUserByName(user.getUserName());
            if(user1!=null){
                this.setResult("success");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String logOut(){
        ActionContext context=ActionContext.getContext();
        context.getSession().put("currentUser",null);
        return LOGIN;

    }
    @Test
    public void test(){

        int page=1;
        int userType=2;
        String tel="1360";
        String name="林";
        DetachedCriteria dc=DetachedCriteria.forClass(User.class);
        if(userType>0){
            dc.add(Restrictions.eq("authorityId",userType));
        }
        if(!TestUtils.isEmpty(name)){
            dc.add(Restrictions.like("userName","%"+name+"%"));
        }
        if(!TestUtils.isEmpty(tel)){
            dc.add(Restrictions.like("userTel","%"+tel+"%"));
        }
        try {
            PageModel pageModel=service.find(page,dc,"");
            System.out.println(pageModel.getPageSize());
            System.out.println(pageModel.getRecords().size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
