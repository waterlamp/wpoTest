package com.dao.impl;

import com.dao.PublicDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Component("publicDao")
public class PublicDaoImpl<T> extends HibernateDaoSupport implements PublicDao {
    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
    @Override
    public List<T> query(int page, int pageSize, DetachedCriteria dc) throws SQLException {
//        Session session= HibernateUtil.getCurrentSession();
//        Transaction tx=session.beginTransaction();
//        Criteria criteria=dc.getExecutableCriteria(session);
        //Criteria criteria=dc.setProjection(null).getExecutableCriteria(session);
        //Criteria criteria=dc.setProjection(null).setResultTransformer(DetachedCriteria.ROOT_ENTITY).getExecutableCriteria(session);
        //        criteria.setFirstResult((page-1)*pageSize);
//        criteria.setMaxResults(pageSize);
        //System.out.println((page-1)*pageSize+":"+page*pageSize);
//        List<T> list=criteria.list();
//        tx.commit();
        return (List<T>) this.getHibernateTemplate().findByCriteria(dc,(page-1)*pageSize,pageSize);
    }

    @Override
    public long resultCount(DetachedCriteria dc) throws SQLException {
//        Session session= HibernateUtil.getCurrentSession();
//        Transaction tx=session.beginTransaction();
//        Criteria criteria=dc.setProjection(Projections.rowCount()).getExecutableCriteria(session);
//        long resultCount=(long)criteria.uniqueResult();
//        tx.commit();
        dc.setProjection(Projections.rowCount());
        Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        Criteria criteria=dc.setProjection(Projections.rowCount()).getExecutableCriteria(session);
        long resultCount=(long)criteria.uniqueResult();
        return resultCount;
    }

    @Override
    public void save(Object obj) throws SQLException{
//        Session session= HibernateUtil.openSession();
//        session.save(obj);
//        session.close();
        this.getHibernateTemplate().save(obj);
    }

    @Override
    public void saveOrUpdate(Object obj) throws SQLException{
//        Session session= HibernateUtil.getCurrentSession();
//        Transaction tx=session.beginTransaction();
//        session.saveOrUpdate(obj);
//        tx.commit();
        this.getHibernateTemplate().saveOrUpdate(obj);
    }

    @Override
    public void delete(Object obj)  throws SQLException{

//        Session session= HibernateUtil.getCurrentSession();
//        Transaction tx=session.beginTransaction();
//        session.delete(obj);
//        tx.commit();
        this.getHibernateTemplate().delete(obj);
    }
}
