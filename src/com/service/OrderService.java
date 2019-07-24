package com.service;

import com.bean.OrderType;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    List<OrderType> getOrderTypes() throws SQLException;
}
