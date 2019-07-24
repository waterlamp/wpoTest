package com.dao.impl;

import com.bean.User;
import com.dao.UserDao;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.query.Query;
import org.junit.Test;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Component("userDao")
public class UserDaoImp extends HibernateDaoSupport implements UserDao{

    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void add(User user) throws SQLException {

       this.currentSession().save(user);

    }

    @Override
    public List<User> query(int page, int pageSize, DetachedCriteria dc) {
        List<User> list=(List<User>)this.getHibernateTemplate().findByCriteria(dc,(page-1)*pageSize,pageSize);
        System.out.println(list);
        return list;
    }

    @Override
    public User getUserByName(String name) throws SQLException {

        String hql="from User where userName=:name";
        Query query=this.currentSession().createQuery(hql);
        query.setParameter("name",name);
        List<User> list=query.list();

        if (list.size()==0){
            return null;
        }else {
            return list.get(0);
        }
    }


    @Override
    public User getUser(String id) throws SQLException {

        return this.currentSession().get(User.class,id);
    }

    @Override
    public User getUser(String tel,String pwd) throws SQLException {

        //String hql="select distinct u from User u inner join fetch u.authority where u.userTel=?0 and u.userPwd=?1";
        String hql="from User where userTel=:tel and userPwd=:pwd";
        Query query=this.currentSession().createQuery(hql);
        query.setParameter("tel",tel);
        query.setParameter("pwd",pwd);
        List<User> list=query.list();

        if (list.size()==0) return null;

        return list.get(0);

    }

    @Test
    public void testGet()throws SQLException{
        User user=getUser("402880eb68b3baa10168b3baaa4b0000");
        System.out.println(user.toString());
        System.out.println(user.getAuthority().getAuthorityName());
    }

    @Test
    public void test() throws SQLException{
        User user=new User();
        user.setUserName("张华荣");
        user.setUserTel("13950050777");
        user.setUserPwd("gjb!1234");
        user.setUserCenter("全业务");
        user.setUserActive(true);

        add(user);
    }



}
