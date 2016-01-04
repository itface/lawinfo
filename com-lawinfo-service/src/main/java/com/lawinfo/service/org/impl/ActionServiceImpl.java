package com.lawinfo.service.org.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.lawinfo.dao.org.ActionDao;
import com.lawinfo.domain.common.EasyuiTree;
import com.lawinfo.domain.org.Action;
import com.lawinfo.domain.org.Menu;
import com.lawinfo.domain.org.RoleAction;
import com.lawinfo.domain.org.User;
import com.lawinfo.domain.org.query.ActionQuery;
import com.lawinfo.domain.org.vo.ActionTreeVo;
import com.lawinfo.domain.org.vo.MenuVo;
import com.lawinfo.service.org.ActionService;
import com.lawinfo.service.org.RoleActionService;
import com.lawinfo.service.org.utils.ActionUtils;
import com.lawinfo.service.org.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by wangrongtao on 15/10/28.
 */
@Service
public class ActionServiceImpl implements ActionService {
    private static Logger logger = LoggerFactory.getLogger(ActionServiceImpl.class);

    @Resource
    private ActionDao actionDao;
    @Resource
    private RoleActionService roleActionService;
    @Override
    public void initCache() throws Exception {
        try {
            ActionUtils.init();
            List<Action> actionList= this.findAllFromDb();
            if (!CollectionUtils.isEmpty(actionList)) {
                for (Action action : actionList) {
                    ActionUtils.add(action);
                }
            }
        } catch (Exception e) {
            logger.error("initcache exception",e);
            throw e;
        }
    }

    @Override
    public List<Action> findAll() throws Exception{
        List<Action> list = null;
        try {
            list = ActionUtils.findAll();
            if (CollectionUtils.isEmpty(list)) {
                list = findAllFromDb();
            }
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;
    }

    @Override
    public List<ActionTreeVo> findAllTree() throws Exception {
        try {
            List<Action> list = findAll();
            if (!CollectionUtils.isEmpty(list)) {
                List<ActionTreeVo> actionTreeVos = new ArrayList<ActionTreeVo>();
                ActionTreeVo root = new ActionTreeVo();
                root.setId(0l);
                root.setParentactionid(-1l);
                root.setText("权限管理");
                List<ActionTreeVo> actionVoList2 = new ArrayList<ActionTreeVo>();
                for (Action action : list) {
                    ActionTreeVo actionTreeVo = new ActionTreeVo();
                    actionTreeVo.setId(action.getId());
                    actionTreeVo.setParentactionid(root.getId());
                    actionTreeVo.setText(action.getName());
                    actionVoList2.add(actionTreeVo);
                }
                root.setNodes(actionVoList2);
                actionTreeVos.add(root);
                return actionTreeVos;
            }
        } catch (Exception e) {
            logger.error("findAllTree error",e);
            throw e;
        }
        return null;
    }

    @Override
    public List<EasyuiTree> findEuTree(String actionids) throws Exception {
        try {
            List<Action> list = findAll();
            if (!CollectionUtils.isEmpty(list)) {
                Set<Long> menuSet = new HashSet<Long>();
                if (!StringUtils.isEmpty(actionids)) {
                    String[] actionarr =actionids.split(",");
                    for (int i = 0; i < actionarr.length; i++) {
                        menuSet.add(Long.parseLong(actionarr[i]));
                    }
                }
                List<EasyuiTree> easyuiTrees = new ArrayList<EasyuiTree>();
                for (Action action : list) {
                    EasyuiTree easyuiTree = new EasyuiTree();
                    easyuiTree.setId(action.getId());
                    if (menuSet.contains(action.getId())) {
                        easyuiTree.setChecked(true);
                    }
                    easyuiTree.setText(action.getName());
                    easyuiTree.setState("open");
                    easyuiTrees.add(easyuiTree);
                }
                return easyuiTrees;
            }
        } catch (Exception e) {
            logger.error("findEuTree error",e);
            throw e;
        }
        return null;
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
            throw e;
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
            throw e;
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
            throw e;
        }
        return effectrows;
    }

    @Override
    @Transactional
    public int deleteById(long id)throws Exception {
        logger.info("deleteById begin,id=" + id);
        int effectrows = 0;
        try {
            roleActionService.deleteByActionid(id);
            effectrows = actionDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
            throw e;
        }
        return effectrows;
    }

    @Override
    public List<Action> findByIds(Long[] ids)throws Exception {
        try {
            return actionDao.findByIds(ids);
        } catch (Exception e) {
            logger.error("findByIds error", e);
            throw e;
        }
    }

    @Override
    public List<Action> findByTags(String[] tags)throws Exception {
        try {
            return actionDao.findByTags(tags);
        } catch (Exception e) {
            logger.error("findByTags error", e);
            throw e;
        }
    }
}
