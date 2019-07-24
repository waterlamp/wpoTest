package com.web.action;

import com.alibaba.fastjson.JSON;
import com.bean.Order;
import com.bean.OrderType;
import com.bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.OrderService;
import com.service.impl.OrderServiceImp;
import com.utils.JedisUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component("orderAction")
@Scope("prototype")
public class OrderAction extends ActionSupport implements ModelDriven<Order> {
    Order order=new Order();
    private String result;
    private OrderService service=new OrderServiceImp();
    @Override
    public Order getModel() {
        return order;
    }

    public Order getOrder() {
        return order;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOrderTypes(){
        Jedis jedis= JedisUtils.getJedis();
        String json="";
        String key="orderTypes";
        try {
            if (jedis.exists(key)){
                json=jedis.get(key);
            }else {
                List<OrderType> list=service.getOrderTypes();
                json= JSON.toJSONString(list);
                System.out.println(json);
                jedis.append(key,json);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        JedisUtils.closeJedis(jedis);
        this.setResult(json);
        return SUCCESS;
    }

    public String newOrder(){
        ActionContext context=ActionContext.getContext();
        User user=(User)context.getSession().get("currentUser");
        order.setOrderUserId(user.getUserId());
        order.setOrderStarttime(new Timestamp(new Date().getTime()));
        order.setOrderState(1);
        System.out.println(order);
        return NONE;
    }

}
