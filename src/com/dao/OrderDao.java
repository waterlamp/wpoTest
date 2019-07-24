package com.dao;

import com.bean.OrderFlow;
import com.bean.OrderType;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    List<OrderType> getOrderTypes() throws SQLException;
    OrderFlow getFirstState(int orderTypeId) throws SQLException;
    OrderFlow getNextState(int orderflowId) throws SQLException;

}
