package com.service.impl;

import com.bean.OrderType;
import com.dao.OrderDao;
import com.dao.impl.OrderDaoImp;
import com.service.OrderService;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component("orderService")
public class OrderServiceImp implements OrderService{
    @Override
    public List<OrderType> getOrderTypes() throws SQLException {
        OrderDao dao=new OrderDaoImp();
        return dao.getOrderTypes();
    }
}
