package com.service.impl;

import com.bean.PageModel;
import com.bean.Project;
import com.bean.User;
import com.dao.ProjectDao;
import com.dao.PublicDao;
import com.dao.UserDao;
import com.dao.impl.PublicDaoImpl;
import com.dao.impl.UserDaoImp;
import com.service.ProjectService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Transactional
@Component("projectService")
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    @Qualifier("publicDao")
    private PublicDao publicDao;
    @Autowired
    @Qualifier("projectDao")
    private ProjectDao projectDao;
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setPublicDao(PublicDao publicDao) {
        this.publicDao = publicDao;
    }

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }


    @Override
    public PageModel find(int page, DetachedCriteria dc,String url) throws SQLException {
//        PublicDao publicDao=new PublicDaoImpl();
        int totalRecords=(int)publicDao.resultCount(dc);

        //dc.setProjection(pList).setResultTransformer(DetachedCriteria.ROOT_ENTITY);
//        ProjectionList pList= Projections.projectionList();
//        pList.add(Projections.property("p.projectId"));
//        pList.add(Projections.property("p.projectMis"));
//        pList.add(Projections.property("p.projectName"));
//        pList.add(Projections.property("p.projectFindate"));
//        pList.add(Projections.property("p.projectPredate"));
//        pList.add(Projections.property("p.projectTurn"));
//        pList.add(Projections.property("p.projectTurndate"));
//        pList.add(Projections.property("p.projectType"));
//        pList.add(Projections.property("p.projectStorageLife"));
//        pList.add(Projections.property("p.user.userName").as("userName"));
        dc.setProjection(null).setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        //dc.setProjection(pList).setResultTransformer(Transformers.aliasToBean(Project.class));
        List<Project> list=projectDao.query(page,pageSize,dc);
        //System.out.println(list.get(0));
        PageModel pageModel=new PageModel(page,totalRecords,pageSize);
        pageModel.setRecords(list);
        pageModel.setUrl(url);
        return pageModel;
    }

    @Override
    public void save(Project project) throws SQLException {

        User user=userDao.getUser(project.getUserId());
        project.setUser(user);
        project.setProjectBoxsCount(0);
        project.setProjectFilesCount(0);
        project.setProjectPredate(new Date());
        project.setProjectTurn(false);

        publicDao.saveOrUpdate(project);
    }

    @Override
    public void update(Project project)  throws SQLException{
        Project oldProject=projectDao.getProject(project.getProjectId());

        User user=userDao.getUser(project.getUserId());
        project.setUser(user);
        project.setProjectTurn(oldProject.getProjectTurn());
        project.setProjectTurndate(oldProject.getProjectTurndate());
        project.setProjectBoxsCount(oldProject.getProjectBoxsCount());
        project.setProjectFilesCount(oldProject.getProjectFilesCount());
        projectDao.update(project);
    }

    @Override
    public Project getProject(int projectId) throws SQLException {
        Project project=projectDao.getProject(projectId);
        User user=userDao.getUser(project.getUserId());
        project.setUser(user);
        return project;
    }

    @Override
    public Project getProject(String mis) throws SQLException {
        //ProjectDao dao=new ProjectDaoImp();
        return projectDao.getProject(mis);
    }

    @Override
    public void turnProject(int projectId) throws SQLException {
        //ProjectDao dao=new ProjectDaoImp();
        Project project=projectDao.getProject(projectId);
        project.setProjectTurn(true);
        project.setProjectTurndate(new Date());
        projectDao.update(project);
    }


    @Override
    public void delete(int projectId) throws SQLException {
        Project project=projectDao.getProject(projectId);
        projectDao.delete(project);
    }
}
