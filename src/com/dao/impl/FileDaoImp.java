package com.dao.impl;

import com.bean.File;
import com.bean.Project;
import com.dao.FileDao;
import com.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("fileDao")
public class FileDaoImp extends HibernateDaoSupport implements FileDao {

    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory){

        super.setSessionFactory(sessionFactory);
    }
    @Override
    public void save(File file) throws SQLException {
        this.getHibernateTemplate().save(file);
    }

    @Override
    public List<String> getRecordNums(int projectId) throws SQLException {


        String hql="select f.fileRecordNum from File f where f.projectId=:id";

        Query query=this.currentSession().createQuery(hql);
        query.setParameter("id",projectId);
        List<String> list=query.getResultList();
        return list;
    }

    @Override
    public int fileCount(int projectId) throws SQLException {

        Query query=this.currentSession().createQuery("select sum (f.fileCount) from File f where f.projectId=:projectId");
        query.setParameter("projectId",projectId);

        return (int)(long)query.uniqueResult();
    }

    @Override
    public List<String> getBoxNums(int projectId) throws SQLException {

        String hql="select f.fileBoxNum from File f where f.projectId=:id";

        Query query=this.currentSession().createQuery(hql);
        query.setParameter("id",projectId);
        List<String> list=query.getResultList();
        return list;
    }

    @Override
    public Map<Integer, Integer> perTypeCount(int projectId) throws SQLException {

        Query query=this.currentSession().createQuery("select f.fileTypeId,sum(f.fileCount) from File f where f.projectId=:projectId group by f.fileTypeId");
        query.setParameter("projectId",projectId);
        List<Object[]> list=query.list();

        Map<Integer,Integer> map=new HashMap<>();
        for (Object[] objects:list){
            map.put(Integer.parseInt(objects[0].toString()),Integer.parseInt(objects[1].toString()));
        }
        return map;
    }

    @Override
    public List<File> findAll(int projectId) throws SQLException {

        String hql="from File f where f.projectId=:id order by f.fileRecordNum";

        Query query=this.currentSession().createQuery(hql);
        query.setParameter("id",projectId);
        List<File> list=query.list();

        return list;
    }

    @Override
    public File getFile(int fileId) throws SQLException {

        return this.getHibernateTemplate().get(File.class,fileId);
    }

    @Test
    public void test() throws SQLException{
        int projectId=6;
        List<File> list=findAll(projectId);
        for (File file:list){
            System.out.println(file.toString());
            System.out.println(file.getFileType().getFiletypeName());
        }
    }
}
