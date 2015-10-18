package com.lawinfo.service.org.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.lawinfo.dao.org.CaseProgressDao;
import com.lawinfo.domain.org.CaseInfo;
import com.lawinfo.domain.org.CaseNode;
import com.lawinfo.domain.org.CaseProgress;
import com.lawinfo.domain.org.CaseProgress;
import com.lawinfo.domain.org.query.CaseInfoQuery;
import com.lawinfo.domain.org.query.CaseProgressQuery;
import com.lawinfo.service.org.CaseProgressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.*;

/**
 * Created by wangrongtao on 15/10/15.
 */
@Service
public class CaseProgressServiceImpl implements CaseProgressService {
    private static Logger logger = LoggerFactory.getLogger(CaseProgressServiceImpl.class);

    @Resource
    private CaseProgressDao caseProgressDao;
    /**
     * nodeid与nodeindex关系映射，当添加或者除结点时，此映射关系也要作相应的调整
     */
    private static Map<Integer, Integer> nodeidAndNodeindexRelationMap = new HashMap<Integer, Integer>();
    static {
        nodeidAndNodeindexRelationMap.put(1, 1);
        nodeidAndNodeindexRelationMap.put(2, 2);
        nodeidAndNodeindexRelationMap.put(3, 3);
        nodeidAndNodeindexRelationMap.put(4, 4);
        nodeidAndNodeindexRelationMap.put(5, 5);
        nodeidAndNodeindexRelationMap.put(6, 6);
        nodeidAndNodeindexRelationMap.put(7, 7);
        nodeidAndNodeindexRelationMap.put(8, 8);
        nodeidAndNodeindexRelationMap.put(9, 9);
        nodeidAndNodeindexRelationMap.put(10, 10);
        nodeidAndNodeindexRelationMap.put(11, 11);
        nodeidAndNodeindexRelationMap.put(12, 12);
        nodeidAndNodeindexRelationMap.put(13, 13);
        nodeidAndNodeindexRelationMap.put(14, 14);
        nodeidAndNodeindexRelationMap.put(15, 15);
        nodeidAndNodeindexRelationMap.put(16, 16);
        nodeidAndNodeindexRelationMap.put(17, 17);
        nodeidAndNodeindexRelationMap.put(18, 18);
        nodeidAndNodeindexRelationMap.put(19, 19);
        nodeidAndNodeindexRelationMap.put(20, 20);
        nodeidAndNodeindexRelationMap.put(21, 21);
        nodeidAndNodeindexRelationMap.put(22, 22);
        nodeidAndNodeindexRelationMap.put(23, 23);
        nodeidAndNodeindexRelationMap.put(24, 24);
        nodeidAndNodeindexRelationMap.put(25, 25);
        nodeidAndNodeindexRelationMap.put(26, 26);
        nodeidAndNodeindexRelationMap.put(27, 27);
        nodeidAndNodeindexRelationMap.put(28, 28);
        nodeidAndNodeindexRelationMap.put(29, 29);
        nodeidAndNodeindexRelationMap.put(30, 30);
        nodeidAndNodeindexRelationMap.put(31, 31);
        nodeidAndNodeindexRelationMap.put(32, 32);
    }
    private String getCaseNode(){
        /**
         * #1、合约恰谈：银行委托恰谈，合同已签订，初期律师费已支出，诉前财产保全
         #2、一审立案：立案，财产保全，文书送达，确定开庭日
         #3、一审已开庭：已开庭，调解进行中，代理文书提交，判决书跟进
         #4、一审已判决：法院已出具判决书，判决送达
         #如果上诉，才有5-8节点
         #5、上诉期：已上诉
         #6、二审已立案：立案，文书送达，确定开庭日
         #7、二审已开庭：已开庭，调解进行中，代理文书提交，判决书跟进
         #8、二审已判决：法院已出具判决书，判决书送达
         #执行阶段
         #9、执行阶段：已申请执行立案、已确定法官，执行文书送达，财产拍卖，结案
         #10、律师费结算：提交律师费结算、开出发票、律师费到帐
         #comments的值是动态的，所有用占位符，占位符位置与index保持一致，comments的内容[],或者[{"id":11,"caseid":"111","nodeid":1,"comment":"",nexttask:""}]
         添加或删除节点，id不变，变的是index
         */
        String casenode = "[" +
                "'{'\"id\":1,\"name\":\"银行委托恰谈\",\"index\":1,\"level\":1,\"levelname\":\"合约恰谈\",\"comments\":{0}}," +
                "'{'\"id\":2,\"name\":\"合同已签订\",\"index\":2,\"level\":1,\"levelname\":\"合约恰谈\",\"comments\":{1}}," +
                "'{'\"id\":3,\"name\":\"初期律师费已支出\",\"index\":3,\"level\":1,\"levelname\":\"合约恰谈\",\"comments\":{2}}," +
                "'{'\"id\":4,\"name\":\"诉前财产保全\",\"index\":4,\"level\":1,\"levelname\":\"合约恰谈\",\"comments\":{3}}," +
                "'{'\"id\":5,\"name\":\"立案\",\"index\":5,\"level\":2,\"levelname\":\"一审立案\",\"comments\":{4}}," +
                "'{'\"id\":6,\"name\":\"财产保全\",\"index\":6,\"level\":2,\"levelname\":\"一审立案\",\"comments\":{5}}," +
                "'{'\"id\":7,\"name\":\"文书送达\",\"index\":7,\"level\":2,\"levelname\":\"一审立案\",\"comments\":{6}}" +
                "'{'\"id\":8,\"name\":\"确定开庭日\",\"index\":8,\"level\":2,\"levelname\":\"一审立案\",\"comments\":{7}}," +
                "'{'\"id\":9,\"name\":\"已开庭\",\"index\":9,\"level\":3,\"levelname\":\"一审已开庭\",\"comments\":{8}}," +
                "'{'\"id\":10,\"name\":\"调解进行中\",\"index\":10,\"level\":3,\"levelname\":\"一审已开庭\",\"comments\":{9}}," +
                "'{'\"id\":11,\"name\":\"代理文书提交\",\"index\":11,\"level\":3,\"levelname\":\"一审已开庭\",\"comments\":{10}}," +
                "'{'\"id\":12,\"name\":\"判决书跟进\",\"index\":12,\"level\":3,\"levelname\":\"一审已开庭\",\"comments\":{11}}" +
                "'{'\"id\":13,\"name\":\"法院已出具判决书\",\"index\":13,\"level\":4,\"levelname\":\"一审已判决\",\"comments\":{12}}," +
                "'{'\"id\":14,\"name\":\"判决送达\",\"index\":14,\"level\":4,\"levelname\":\"一审已判决\",\"comments\":{13}}," +
                "'{'\"id\":15,\"name\":\"已上诉\",\"index\":15,\"level\":5,\"levelname\":\"上诉期\",\"comments\":{14}}," +
                "'{'\"id\":16,\"name\":\"立案\",\"index\":16,\"level\":6,\"levelname\":\"二审已立案\",\"comments\":{15}}," +
                "'{'\"id\":17,\"name\":\"文书送达\",\"index\":17,\"level\":6,\"levelname\":\"二审已立案\",\"comments\":{16}}," +
                "'{'\"id\":18,\"name\":\"确定开庭日\",\"index\":18,\"level\":6,\"levelname\":\"二审已立案\",\"comments\":{17}}," +
                "'{'\"id\":19,\"name\":\"已开庭\",\"index\":19,\"level\":7,\"levelname\":\"二审已开庭\",\"comments\":{18}}," +
                "'{'\"id\":20,\"name\":\"调解进行中\",\"index\":20,\"level\":7,\"levelname\":\"二审已开庭\",\"comments\":{19}}," +
                "'{'\"id\":21,\"name\":\"代理文书提交\",\"index\":21,\"level\":7,\"levelname\":\"二审已开庭\",\"comments\":{20}}," +
                "'{'\"id\":22,\"name\":\"判决书跟进\",\"index\":22,\"level\":7,\"levelname\":\"二审已开庭\",\"comments\":{21}}," +
                "'{'\"id\":23,\"name\":\"法院已出具判决书\",\"index\":23,\"level\":8,\"levelname\":\"二审已判决\",\"comments\":{22}}," +
                "'{'\"id\":24,\"name\":\"判决书送达\",\"index\":24,\"level\":8,\"levelname\":\"二审已判决\",\"comments\":{23}}," +
                "'{'\"id\":25,\"name\":\"已申请执行立案\",\"index\":25,\"level\":9,\"levelname\":\"执行阶段\",\"comments\":{24}}," +
                "'{'\"id\":26,\"name\":\"已确定法官\",\"index\":26,\"level\":9,\"levelname\":\"执行阶段\",\"comments\":{25}}," +
                "'{'\"id\":27,\"name\":\"执行文书送达\",\"index\":27,\"level\":9,\"levelname\":\"执行阶段\",\"comments\":{26}}," +
                "'{'\"id\":28,\"name\":\"财产拍卖\",\"index\":28,\"level\":9,\"levelname\":\"执行阶段\",\"comments\":{27}}," +
                "'{'\"id\":29,\"name\":\"结案\",\"index\":29,\"level\":9,\"levelname\":\"执行阶段\",\"comments\":{28}}," +
                "'{'\"id\":30,\"name\":\"提交律师费结算\",\"index\":30,\"level\":10,\"levelname\":\"律师费结算\",\"comments\":{29}}," +
                "'{'\"id\":31,\"name\":\"开出发票\",\"index\":31,\"level\":10,\"levelname\":\"律师费结算\",\"comments\":{30}}," +
                "'{'\"id\":32,\"name\":\"律师费到帐\",\"index\":32,\"level\":10,\"levelname\":\"律师费结算\",\"comments\":{31}}" +
                "]";
        return casenode;
    }
    public static void main(String[] args) {
        String s = MessageFormat.format("['{'\"id\":1,\"name\":\"银行委托恰谈\",\"index\":1,\"level\":1,\"levelname\":\"合约恰谈\",\"comments\":{0}}]","\"aaaa\"");
        List<CaseNode> list = JSON.parseArray(s, CaseNode.class);
        System.out.println(list.size());
        /*CaseProgress caseProgress = new CaseProgress();
        caseProgress.setId(100l);
        caseProgress.setCaseid("ea100");
        caseProgress.setCasenodeid(1);
        caseProgress.setComment("已\\\"经\"恰'谈");
        caseProgress.setNexttask("准\t备}起]诉<td>aaaa");
        List<CaseProgress> caseProgresses = new ArrayList<CaseProgress>();
        caseProgresses.add(caseProgress);
        String[] filterField = {"id", "caseid", "casenodeid", "comment", "nexttask"};
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(CaseProgress.class, filterField);
        String progress = JSON.toJSONString(caseProgresses, filter);
        System.out.println(progress);
        String[] formats = new String[32];
        formats[0] = progress;
        String s = String.format(CASE_NODE, formats);
        List<CaseNode> list = JSON.parseArray(s, CaseNode.class);
        System.out.println(list.size());*/
    }
    @Override
    public String findCaseProgressWithNodeInfo(String caseid) {
        logger.info("findCaseProgressWithNodeInfo begin,caseid=" + caseid);
        String caseProgressWithNodeInfo = null;
        try {
            CaseProgressQuery caseProgressQuery = new CaseProgressQuery();
            caseProgressQuery.setCaseid(caseid);
            List<CaseProgress> list = this.find(caseProgressQuery);
            if (!CollectionUtils.isEmpty(list)) {
                String[] caseProgresses = new String[nodeidAndNodeindexRelationMap.size()];
                Multimap<Integer, CaseProgress> multimap = ArrayListMultimap.create();
                for (CaseProgress caseProgress : list) {
                    int casenodeid = caseProgress.getCasenodeid();
                    Integer index = nodeidAndNodeindexRelationMap.get(casenodeid);
                    if (index != null && index > 0) {
                        multimap.put(casenodeid, caseProgress);
                    } else {
                        logger.error("findCaseProgressWithNodeInfo error,index is " + index + ",caseid=" + caseid);
                    }
                }
                Iterator<Integer> iterator = multimap.keySet().iterator();
                while (iterator.hasNext()) {
                    int casenodeid = iterator.next();
                    Collection<CaseProgress> list1 = multimap.get(casenodeid);
                    int index = nodeidAndNodeindexRelationMap.get(casenodeid) - 1;
                    String[] filterField = {"id", "caseid", "casenodeid", "comment", "nexttask"};
                    SimplePropertyPreFilter filter = new SimplePropertyPreFilter(CaseProgress.class, filterField);
                    String s = JSONArray.toJSONString(list1,filter);
                    caseProgresses[index] = s;
                }
                caseProgressWithNodeInfo = MessageFormat.format(getCaseNode(), caseProgresses);
//                List<CaseNode> list3 = JSON.parseArray(caseProgressWithNodeInfo, CaseNode.class);
            }
        } catch (Exception e) {
            logger.error("findCaseProgressWithNodeInfo error,caseid="+ caseid,e);
        }
        return caseProgressWithNodeInfo;
    }

    @Override
    public List<CaseProgress> findAllCaseProgress() throws Exception {
        List<CaseProgress> list = null;
        try {
            list = caseProgressDao.findAll();
        } catch (Exception e) {
            logger.error("findAll error",e);
            throw e;
        }
        return list;    }

    @Override
    @Transactional
    public int save(CaseProgress caseProgress) throws Exception {
        int effectrows = 0;
        try {
            if (caseProgress!=null) {
                caseProgress.initBaseDomain();
                effectrows = caseProgressDao.save(caseProgress);
                logger.info("save success,effectrows:"+effectrows+","+caseProgress.getCaseid());
            }
        } catch (Exception e) {
            logger.error("findAll error,caseid="+(caseProgress==null?0l:caseProgress.getCaseid()),e);
            throw e;
        }
        return effectrows;
    }


    @Override
    public int deleteById(long id) throws Exception {
        logger.info("deleteById begin,id=" + id);
        int effectrows = 0;
        try {
            effectrows = caseProgressDao.deleteById(id);
        } catch (Exception e) {
            logger.error("deleteById error,id=" + id, e);
        }
        return effectrows;
    }

    @Override
    public List<CaseProgress> find(CaseProgressQuery caseProgressQuery) throws Exception {
        logger.info("findList begin,CaseProgressQuery=" + caseProgressQuery == null ? "null" : caseProgressQuery.toLogString());
        List<CaseProgress> list = null;
        try {
            list = caseProgressDao.findList(caseProgressQuery);
        } catch (Exception e) {
            logger.error("findList error,CaseProgressQuery=" + caseProgressQuery==null?"null":caseProgressQuery.toLogString(), e);
        }
        return list;
    }

    @Override
    public List<CaseProgress> findByPage(CaseProgressQuery caseProgressQuery) throws Exception {
        logger.info("findListByPage begin,CaseProgressQuery=" + caseProgressQuery==null?"null":caseProgressQuery.toLogString());
        List<CaseProgress> list = null;
        try {
            list = caseProgressDao.findListByPage(caseProgressQuery);
        } catch (Exception e) {
            logger.error("findListByPage error,CaseProgressQuery=" + caseProgressQuery==null?"null":caseProgressQuery.toLogString(), e);
        }
        return list;
    }
}
