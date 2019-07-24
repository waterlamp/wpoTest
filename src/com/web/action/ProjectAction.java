package com.web.action;

import com.alibaba.fastjson.JSON;
import com.bean.Project;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.service.ProjectService;
import com.utils.TestUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component("projectAction")
@Scope("prototype")
public class ProjectAction extends ActionSupport implements ModelDriven<Project>{
    private Project project=new Project();
    private int num;
    private String result="";
    @Autowired
    @Qualifier("projectService")
    private ProjectService service;

    public void setProjectService(ProjectService service) {
        this.service = service;
    }

    @Override
    public Project getModel() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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

    public String gotoSavePage(){
        ValueStack stack=ActionContext.getContext().getValueStack();

        //System.out.println(project.getProjectId());
        stack.set("projectId",project.getProjectId());
        return "savePage";
    }
//    private void setService(){
//        if(service!=null)return;
//        // 创建一个工厂类:
////        ServletContext sc = ServletActionContext.getServletContext();
////        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(sc);
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//        service=(ProjectService)applicationContext.getBean("projectService");
//
//    }
    public String find(){

        ValueStack stack=ActionContext.getContext().getValueStack();
        stack.set("errMsg",this.getResult());
        String mis=project.getProjectMis();
        String name=project.getUser()==null?"":project.getUser().getUserName();

        String url="project_find.action?projectTurn="+project.getProjectTurn();

        DetachedCriteria dc=DetachedCriteria.forClass(Project.class,"p");

        dc.add(Restrictions.eq("projectTurn",project.getProjectTurn()));

        //System.out.println(project.getProjectTurn());
        if(!TestUtils.isEmpty(mis)){
            dc.add(Restrictions.like("projectMis","%"+mis+"%"));
            url+="&mis="+mis;
        }
        dc.createAlias("user", "u");
        if(!TestUtils.isEmpty(name)){
            dc.add(Restrictions.like("u.userName","%"+name+"%"));
            url+="&user.userName="+name;
        }

        int num=getNum()==0?1:getNum();

        try {
            System.out.println("加载service");
            stack.set("page",service.find(num,dc,url));
            return "findSuccess";
        } catch (SQLException e) {
            e.printStackTrace();
            addActionError("查询出错");
            return "info";
        }

    }
    //保存项目
    public String save(){

        ValueStack stack=ActionContext.getContext().getValueStack();
        if(project.getProjectId()==0){
            try {
                System.out.println(project.toString());
                service.save(project);
                return "saveSuccess";
            } catch (SQLException e) {
                e.printStackTrace();
                stack.set("projectId",project.getProjectId());
                this.addActionError("保存失败，请检查项目名称是否重复");
                return "savePage";
            }
        }else {
            try {
                service.update(project);
                return SUCCESS;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stack.set("projectId",project.getProjectId());
            this.addActionError("更新失败");
            return "updateError";
        }

    }

    //项目归档
    public String turn(){

        try {
            service.turnProject(project.getProjectId());
            return "saveSuccess";
        } catch (SQLException e) {
            e.printStackTrace();
            return "fail";
        }
    }

    public String checkMis(){

        try {
            Project project1=service.getProject(project.getProjectMis());
            //System.out.println(project1.getProjectMis());
            if (project1!=null){
                this.setResult("success");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    public String detail(){
        System.out.println(project.getProjectId());

        try {
            project=service.getProject(project.getProjectId());

            String json= JSON.toJSONString(project);
            System.out.println(json);
            this.setResult(json);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    public String delete(){

        try {
            service.delete(project.getProjectId());
            return "saveSuccess";
        } catch (SQLException e) {
            e.printStackTrace();
            return "fail";
        }
    }
//    @Test
//    public void test()throws SQLException{
//        DetachedCriteria dc=DetachedCriteria.forClass(Project.class);
//        dc.add(Restrictions.eq("projectTurn",true));
//        dc.add(Restrictions.like("projectMis","%B1%"));
//
//        dc.createAlias("user", "u");
//        //dc.add(Restrictions.like("u.userName","%邓%"));
//
//
//        ProjectService projectService=new ProjectServiecImpl();
//        PageModel model=projectService.find(1,dc,"");
//
//        List<Project> list=model.getRecords();
//        for (Project project:list){
//            System.out.println(project.toString());
//            System.out.println(project.getUser().getUserName());
//        }
//
//    }
    @Test
    public void test() throws Exception{

        Project project=service.getProject(36);
        System.out.println(project.toString());
    }
}
