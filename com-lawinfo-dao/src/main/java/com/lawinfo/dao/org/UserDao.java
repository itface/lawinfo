package com.lawinfo.dao.org;

import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.query.UserQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/12.
 */
public interface UserDao {

    public List<User> findAll();

    public int save(User user);

    public User findById(Long id);

    public List<User> findList(UserQuery userQuery);

    public List<User> findListByPage(UserQuery userQuery);

    public int count(UserQuery userQuery);

    public int deleteById(Long id);


}
