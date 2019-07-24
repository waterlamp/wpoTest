package com.dao.impl;

import com.bean.Authority;
import com.dao.AuthorityDao;
import org.hibernate.Session;
import com.utils.HibernateUtil;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.SQLException;

@Component("authorityDao")
public class AuthorityDaoImp extends HibernateDaoSupport implements AuthorityDao {

    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public Authority getAuthrity(int id) throws SQLException {

        return this.currentSession().get(Authority.class,id);
    }
}
