package com.service.impl;

import com.bean.Authority;
import com.bean.PageModel;
import com.bean.User;
import com.dao.AuthorityDao;
import com.dao.PublicDao;
import com.dao.UserDao;
import com.dao.impl.PublicDaoImpl;
import com.service.UserService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Component("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userDao")
    private UserDao dao;
    @Autowired
    @Qualifier("publicDao")
    private PublicDao publicDao;
    @Autowired
    @Qualifier("authorityDao")
    private AuthorityDao authorityDao;

    public void setAuthorityDao(AuthorityDao authorityDao) {
        this.authorityDao = authorityDao;
    }

    public void setUserDao(UserDao dao) {
        this.dao = dao;
    }

    public void setPublicDao(PublicDao publicDao) {
        this.publicDao = publicDao;
    }


    @Override
    public User getUser(String tel,String pwd) throws SQLException {
        User user=dao.getUser(tel,pwd);
        if(user==null)return null;
        Authority authority=authorityDao.getAuthrity(user.getAuthorityId());
        user.setAuthority(authority);
        return user;
    }

    @Override
    public PageModel find(int page, DetachedCriteria dc,String url) throws SQLException {
        int totalRecords=(int)publicDao.resultCount(dc);
        System.out.println("totalRecords:"+totalRecords);
        dc.setProjection(null).setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        List<User> list=dao.query(page,pageSize,dc);
        System.out.println(list.toString());
        PageModel pageModel=new PageModel(page,totalRecords,pageSize);
        pageModel.setRecords(list);
        pageModel.setUrl(url);
        return pageModel;
    }

    @Override
    public User getUser(String id) throws SQLException {
        return dao.getUser(id);
    }

    @Override
    public User getUserByName(String userName) throws SQLException {
        return dao.getUserByName(userName);
    }

    @Override
    public void save(User user) throws SQLException {
        PublicDao dao=new PublicDaoImpl();
        dao.saveOrUpdate(user);
    }
}
