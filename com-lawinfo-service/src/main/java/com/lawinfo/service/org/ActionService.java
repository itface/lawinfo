package com.lawinfo.service.org;

import com.lawinfo.domain.org.Action;
import com.lawinfo.domain.org.query.ActionQuery;

import java.util.List;

/**
 * Created by wangrongtao on 15/10/14.
 */
public interface ActionService {

    public void initCache() throws Exception;
    public List<Action> findAll()throws Exception;
    public List<Action> findAllFromDb() throws Exception;
    public int save(Action Action)throws Exception;

    public Action findById(long id)throws Exception;

    public List<Action> findList(ActionQuery actionQuery)throws Exception;

    public List<Action> findListByPage(ActionQuery actionQuery)throws Exception;

    public int count(ActionQuery actionQuery)throws Exception;

    public int deleteById(long id)throws Exception;
}
