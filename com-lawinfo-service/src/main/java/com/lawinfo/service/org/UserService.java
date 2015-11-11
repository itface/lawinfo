package com.lawinfo.service.org;

import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.query.UserQuery;
import com.lawinfo.domain.org.vo.OrgVo;
import com.lawinfo.domain.org.vo.UserTreeVo;
import com.lawinfo.domain.org.vo.UserVo;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
public interface UserService {

    public void initCache() throws Exception;
    public List<User> findAll()throws Exception;
    public List<UserVo> findAllUservo()throws Exception;
    public List<User> findAllFromDb()throws Exception;
    public User findByUserid(String userid)throws Exception;
    public int save(User user)throws Exception;
    public int saveByUservo(UserVo userVo)throws Exception;

    public User findById(long id)throws Exception;
    public List<OrgVo> findUserTreeVo()throws Exception;

    public List<User> findList(UserQuery userQuery)throws Exception;

    public List<User> findListByPage(UserQuery userQuery)throws Exception;

    public int count(UserQuery userQuery)throws Exception;

    public int deleteById(long id)throws Exception;
    public int updateLoginStatus(User user)throws Exception;
    public List<OrgVo> findSubordinateTree(String userid)throws Exception;
    public List<OrgVo> findCustomerTree()throws Exception;
    public List<OrgVo> findLawyerTree()throws Exception;
    public List<String> findAllSubordinate(String userid)throws Exception;
}
