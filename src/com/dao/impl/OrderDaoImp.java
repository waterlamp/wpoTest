package com.dao.impl;

import com.bean.OrderFlow;
import com.bean.OrderType;
import com.dao.OrderDao;
import com.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Component("orderDao")
public class OrderDaoImp extends HibernateDaoSupport implements OrderDao {
    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
    @Override
    public List<OrderType> getOrderTypes() throws SQLException {

        List<OrderType> list=this.currentSession().createQuery("from OrderType").list();

        return list;
    }

    @Override
    public OrderFlow getFirstState(int orderTypeId) throws SQLException {
        return null;
    }

    @Override
    public OrderFlow getNextState(int orderflowId) throws SQLException {
        return null;
    }
}
