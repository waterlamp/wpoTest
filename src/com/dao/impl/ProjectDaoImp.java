package com.dao.impl;

import com.bean.Project;
import com.bean.User;
import com.dao.ProjectDao;
import com.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Component("projectDao")
public class ProjectDaoImp extends HibernateDaoSupport implements ProjectDao {

    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
    @Override
    public List<Project> query(int page, int pageSize, DetachedCriteria dc) {
        List<Project> list=(List<Project>) this.getHibernateTemplate().findByCriteria(dc,(page-1)*pageSize,pageSize);
        return list;
    }

    @Override
    public Project getProject(int projectId) throws SQLException {
//        Session session= HibernateUtil.getCurrentSession();
//        Transaction tx=session.beginTransaction();
//        Project project=session.get(Project.class,projectId);
//        String hql="select new User (u.userId,u.userName) from User u where u.userId=:userId";
//        Query query=session.createQuery(hql);
//        query.setParameter("userId",project.getUserId());
//        List<User> list=query.list();
//        project.setUser(list.get(0));
//        tx.commit();

        return this.currentSession().get(Project.class,projectId);
    }

    @Override
    public Project getProject(String mis) throws SQLException {

//        Transaction tx=session.beginTransaction();
        String hql="from Project p where p.projectMis=:mis";

        Query query=this.currentSession().createQuery(hql);
        query.setParameter("mis",mis);
        List<Project> list=query.list();
//        tx.commit();

        if (list.size()==0){
            return null;
        }
        return list.get(0);

    }

    @Override
    public void update(Project project) throws SQLException {
        this.currentSession().update(project);
    }
    //    @Override
//    public void updateProject(Project project) throws SQLException {
//        Session session= HibernateUtil.getCurrentSession();
//        Transaction tx=session.beginTransaction();
//        String hql="update Project p set p.projectMis=:mis,p.projectName=:name,p.projectFindate=:finDate," +
//                "p.projectType=:projectType,p.projectStorageLife=:life,p.projectAddress=:address," +
//                "p.userId=:userId where p.projectId=:projectId";
//        Query query=session.createQuery(hql);
//        query.setParameter("mis",project.getProjectMis());
//        query.setParameter("name",project.getProjectName());
//        query.setParameter("finDate",project.getProjectFindate());
//        query.setParameter("projectType",project.getProjectType());
//        query.setParameter("life",project.getProjectStorageLife());
//        query.setParameter("address",project.getProjectAddress());
//        query.setParameter("userId",project.getUserId());
//        query.setParameter("projectId",project.getProjectId());
//        query.executeUpdate();
//        tx.commit();
//            this.getHibernateTemplate().execute("update Project p set p.projectMis=:mis,p.projectName=:name,p.projectFindate=:finDate," +
//              "p.projectType=:projectType,p.projectStorageLife=:life,p.projectAddress=:address," +
//               "p.userId=:userId where p.projectId=:projectId",);
//    }
//
//    @Override
//    public void updateTurn(int projectId) throws SQLException {
//
//        Session session= HibernateUtil.getCurrentSession();
//        Transaction tx=session.beginTransaction();
//        String hql="update Project p set p.projectTurn=:turn,p.projectTurndate=:date where p.projectId=:projectId";
//        Query query=session.createQuery(hql);
//        query.setParameter("turn",true);
//        query.setParameter("date",new Date());
//        query.setParameter("projectId",projectId);
//        query.executeUpdate();
//        tx.commit();
//
//    }
//
//    @Override
//    public void updateCount(int projectId, int boxCount, int fileCount) throws SQLException {
//
//        Session session= HibernateUtil.getCurrentSession();
//        Transaction tx=session.beginTransaction();
//        String hql="update Project p set p.projectBoxsCount=:boxCount,p.projectFilesCount=:fileCount where p.projectId=:projectId";
//        Query query=session.createQuery(hql);
//        query.setParameter("boxCount",boxCount);
//        query.setParameter("fileCount",fileCount);
//        query.setParameter("projectId",projectId);
//        query.executeUpdate();
//        tx.commit();
//
//    }

    @Override
    public void delete(Project project) throws SQLException {
        this.currentSession().delete(project);
    }
}
