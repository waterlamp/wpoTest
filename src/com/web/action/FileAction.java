package com.web.action;

import com.alibaba.fastjson.JSON;
import com.bean.PageModel;
import com.bean.Project;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.bean.File;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.service.FileService;
import com.service.ProjectService;

import com.utils.ProjectFileExcelUtil;
import com.utils.TestUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component("fileAction")
@Scope("prototype")
public class FileAction extends ActionSupport implements ModelDriven<File> {
    private File file=new File();
    private int num;
    private String result;

    @Autowired
    @Qualifier("fileService")
    private FileService service;
    @Autowired
    @Qualifier("projectService")
    private ProjectService projectService;

    public void setFileService(FileService service) {
        this.service = service;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public File getModel() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
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

    public String find(){
        ValueStack stack= ActionContext.getContext().getValueStack();
        stack.set("errMsg",this.getResult());
        int projectId=file.getProjectId();
        System.out.println("projectId:"+projectId);
        String fileName=file.getFileName();
        System.out.println(file.toString());
        String url="file_find.action?projectId="+projectId;
        DetachedCriteria dc=DetachedCriteria.forClass(File.class,"file");
        dc.add(Restrictions.eq("projectId",projectId));
        if (!TestUtils.isEmpty(fileName)){
            dc.add(Restrictions.like("fileName","%"+fileName+"%"));
            url+="&fileName="+fileName;
        }
        int num=getNum()==0?1:getNum();
        try {

            PageModel model=service.find(num,dc,url);
            stack.set("page",model);
            //ProjectService projectService= (ProjectService) ApplicationCtxUtil.getBean("projectService");
            Project project=projectService.getProject(projectId);
            stack.push(project);
            return "findSuccess";
        } catch (SQLException e) {
            e.printStackTrace();
            addActionError("查询错误");
            return "info";
        }
    }
    public String batchSave(){
        int projectId=file.getProjectId();
        System.out.println(result);
        System.out.println(projectId);
        List<File> list= null;
        try {
            list = JSON.parseArray(result, File.class);
        } catch (Exception e) {
            this.setResult("文件格式错误");
            return SUCCESS;
        }
        try {

            service.batchSave(projectId,list);
            this.setResult("200");
        } catch (SQLException e) {
            e.printStackTrace();
            this.setResult("添加错误");
        }
        return SUCCESS;
    }
    public String gotoBatchPage(){
        ValueStack stack=ActionContext.getContext().getValueStack();
        stack.set("projectId",file.getProjectId());
        stack.set("downLoadURL","/download/文件清单导入模板.xlsx");

        return "batchPage";
    }
    public String gotoCountPage(){
        ValueStack stack=ActionContext.getContext().getValueStack();
        try {

            Map<String,List<String[]>> map=service.perTypeCount(file.getProjectId());
            stack.set("map",map);

            Project project=projectService.getProject(file.getProjectId());
            stack.push(project);
            return "totalPage";
        } catch (SQLException e) {
            e.printStackTrace();
            addActionError("获取数据失败");
            return "info";
        }
    }
    public String catalogue(){
        ValueStack stack=ActionContext.getContext().getValueStack();
        try {

            List<File> list=service.findAll(file.getProjectId());
            stack.set("list",list);

            Project project=projectService.getProject(file.getProjectId());
            stack.push(project);
            return "catalogue";
        } catch (SQLException e) {
            e.printStackTrace();
            addActionError("获取数据失败");
            return "info";
        }
    }

    public String save(){
        ValueStack stack= ActionContext.getContext().getValueStack();
        System.out.println(file.toString());
        try {

            service.save(file);
            stack.set("projectId",file.getProjectId());
            return "saveSuccess";
        } catch (SQLException e) {
            e.printStackTrace();
            stack.push(file);
            addActionError("数据更新失败，请重试");
            return "savePage";
        }

    }

    public String delete(){
        try {

            System.out.println(file.toString());
            service.delete(file);
            this.setResult("success");
        } catch (SQLException e) {
            e.printStackTrace();
            this.setResult("error");
        }
        return SUCCESS;
    }

    public String gotoSavePage(){

        ValueStack stack=ActionContext.getContext().getValueStack();
        stack.push(file);
        return "savePage";
    }

    //获取新的文件编号
    public String getNewRecordNum(){
        try {

            this.setResult(service.getNewRecorNum(file.getProjectId()));
        } catch (SQLException e) {
            e.printStackTrace();
            this.setResult("获取信息失败");
        }
        return SUCCESS;
    }

    //获取文件
    public String getFileDetail(){
        try {

            File newFile=service.getFile(file.getFileId());
            String json=JSON.toJSONString(newFile);
            System.out.println(json);
            this.setResult(json);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    //下载EXCEL文件
    public String downLoadExcel(){

        try {

            Map<String,List<String[]>> map=service.perTypeCount(file.getProjectId());
            List<File> list=service.findAll(file.getProjectId());
            Project project=projectService.getProject(file.getProjectId());
            ProjectFileExcelUtil.fileExport(ServletActionContext.getResponse(),project,list,map);
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
