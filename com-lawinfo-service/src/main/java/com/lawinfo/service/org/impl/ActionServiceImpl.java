package com.lawinfo.service.org.impl;

import com.lawinfo.dao.org.ActionDao;
import com.lawinfo.domain.org.Action;
import com.lawinfo.domain.org.query.ActionQuery;
import com.lawinfo.service.org.ActionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/28.
 */
@Service
public class ActionServiceImpl implements ActionService {
    private static Logger logger = LoggerFactory.getLogger(ActionServiceImpl.class);

    @Resource
    private ActionDao actionDao;
    @Override
    public void initCache() throws Exception {

    }

    @Override
    public List<Action> findAll() throws Exception{
        List<Action> list = null;
        try {
            list = actionDao.findAll();
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;
    }

    @Override
    public List<Action> findAllFromDb() throws Exception {
        List<Action> list = null;
        try {
            list = actionDao.findAll();
        } catch (Exception e) {
            logger.error("findAllFromDb error",e);
            throw e;
        }
        return list;
    }

    @Override
    @Transactional
    public int save(Action action)throws Exception {
        int effectrows = 0;
        try {
            if (action !=null) {
                action.initBaseDomain();
                effectrows = actionDao.save(action);
                logger.info("save success,effectrows:"+effectrows+","+ action.getName());
            }
        } catch (Exception e) {
            logger.error("findAll error,"+ action ==null?"null": action.getName(),e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public Action findById(long id)throws Exception {
        logger.info("findById begin,id:"+id);
        Action Action = null;
        try {
            Action =actionDao.findById(id);
        } catch (Exception e) {
            logger.error("findById error,id=" + id, e);
            throw e;
        }
        return Action;
    }

    @Override
    public List<Action> findList(ActionQuery actionInfoQuery)throws Exception {
        logger.info("findList begin,ActionQuery=" + actionInfoQuery == null ? "null" : actionInfoQuery.toLogString());
        List<Action> list = null;
        try {
            list = actionDao.findList(actionInfoQuery);
        } catch (Exception e) {
            logger.error("findList error,ActionQuery=" + actionInfoQuery==null?"null":actionInfoQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public List<Action> findListByPage(ActionQuery actionInfoQuery)throws Exception {
        logger.info("findListByPage begin,ActionQuery=" + actionInfoQuery==null?"null":actionInfoQuery.toLogString());
        List<Action> list = null;
        try {
            list = actionDao.findListByPage(actionInfoQuery);
        } catch (Exception e) {
            logger.error("findListByPage error,ActionQuery=" + actionInfoQuery==null?"null":actionInfoQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public int count(ActionQuery actionInfoQuery)throws Exception {
        logger.info("count begin,ActionQuery=" + actionInfoQuery==null?"null":actionInfoQuery.toLogString());
        int effectrows = 0;
        try {
            effectrows = actionDao.count(actionInfoQuery);
        } catch (Exception e) {
            logger.error("count error,ActionQuery=" + actionInfoQuery==null?"null":actionInfoQuery.toLogString(), e);
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int deleteById(long id)throws Exception {
        logger.info("deleteById begin,id=" + id);
        int effectrows = 0;
        try {
            effectrows = actionDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
        }
        return effectrows;
    }
}
