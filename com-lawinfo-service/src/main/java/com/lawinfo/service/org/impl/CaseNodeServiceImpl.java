package com.lawinfo.service.org.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.lawinfo.domain.org.CaseNode;
import com.lawinfo.domain.org.CaseProgress;
import com.lawinfo.domain.org.query.CaseNodeQuery;
import com.lawinfo.service.org.CaseNodeService;
import org.apache.commons.lang.StringEscapeUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangrongtao on 15/10/15.
 */
public class CaseNodeServiceImpl implements CaseNodeService {

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
     #comments的值是动态的，所有用占位符，占位符位置与id和index保持一致，comments的内容[],或者[{"id":11,"caseid":"111","nodeid":1,"comment":"",nexttask:""}]

     */
    private static String CASE_NODE = "[" +
            "{\"id\":1,\"name\":\"银行委托恰谈\",\"index\":1,\"level\":1,\"level_name\":\"合约恰谈\",\"comments\":%s}," +
            "{\"id\":2,\"name\":\"合同已签订\",\"index\":2,\"level\":1,\"level_name\":\"合约恰谈\",\"comments\":%s}" +
            "{\"id\":3,\"name\":\"初期律师费已支出\",\"index\":3,\"level\":1,\"level_name\":\"合约恰谈\",\"comments\":%s}" +
            "{\"id\":4,\"name\":\"诉前财产保全\",\"index\":4,\"level\":1,\"level_name\":\"合约恰谈\",\"comments\":%s}" +
            "{\"id\":5,\"name\":\"立案\",\"index\":5,\"level\":2,\"level_name\":\"一审立案\",\"comments\":%s}" +
            "{\"id\":6,\"name\":\"财产保全\",\"index\":6,\"level\":2,\"level_name\":\"一审立案\",\"comments\":%s}" +
            "{\"id\":7,\"name\":\"文书送达\",\"index\":7,\"level\":2,\"level_name\":\"一审立案\",\"comments\":%s}" +
            "{\"id\":8,\"name\":\"确定开庭日\",\"index\":8,\"level\":2,\"level_name\":\"一审立案\",\"comments\":%s}" +
            "{\"id\":9,\"name\":\"已开庭\",\"index\":9,\"level\":3,\"level_name\":\"一审已开庭\",\"comments\":%s}" +
            "{\"id\":10,\"name\":\"调解进行中\",\"index\":10,\"level\":3,\"level_name\":\"一审已开庭\",\"comments\":%s}" +
            "{\"id\":11,\"name\":\"代理文书提交\",\"index\":11,\"level\":3,\"level_name\":\"一审已开庭\",\"comments\":%s}" +
            "{\"id\":12,\"name\":\"判决书跟进\",\"index\":12,\"level\":3,\"level_name\":\"一审已开庭\",\"comments\":%s}" +
            "{\"id\":13,\"name\":\"法院已出具判决书\",\"index\":13,\"level\":4,\"level_name\":\"一审已判决\",\"comments\":%s}" +
            "{\"id\":14,\"name\":\"判决送达\",\"index\":14,\"level\":4,\"level_name\":\"一审已判决\",\"comments\":%s}" +
            "{\"id\":15,\"name\":\"已上诉\",\"index\":15,\"level\":5,\"level_name\":\"上诉期\",\"comments\":%s}" +
            "{\"id\":16,\"name\":\"立案\",\"index\":16,\"level\":6,\"level_name\":\"二审已立案\",\"comments\":%s}" +
            "{\"id\":17,\"name\":\"文书送达\",\"index\":17,\"level\":6,\"level_name\":\"二审已立案\",\"comments\":%s}" +
            "{\"id\":18,\"name\":\"确定开庭日\",\"index\":18,\"level\":6,\"level_name\":\"二审已立案\",\"comments\":%s}" +
            "{\"id\":19,\"name\":\"已开庭\",\"index\":19,\"level\":7,\"level_name\":\"二审已开庭\",\"comments\":%s}" +
            "{\"id\":20,\"name\":\"调解进行中\",\"index\":20,\"level\":7,\"level_name\":\"二审已开庭\",\"comments\":%s}" +
            "{\"id\":21,\"name\":\"代理文书提交\",\"index\":21,\"level\":7,\"level_name\":\"二审已开庭\",\"comments\":%s}" +
            "{\"id\":22,\"name\":\"判决书跟进\",\"index\":22,\"level\":7,\"level_name\":\"二审已开庭\",\"comments\":%s}" +
            "{\"id\":23,\"name\":\"法院已出具判决书\",\"index\":23,\"level\":8,\"level_name\":\"二审已判决\",\"comments\":%s}" +
            "{\"id\":24,\"name\":\"判决书送达\",\"index\":24,\"level\":8,\"level_name\":\"二审已判决\",\"comments\":%s}" +
            "{\"id\":25,\"name\":\"已申请执行立案\",\"index\":25,\"level\":9,\"level_name\":\"执行阶段\",\"comments\":%s}" +
            "{\"id\":26,\"name\":\"已确定法官\",\"index\":26,\"level\":9,\"level_name\":\"执行阶段\",\"comments\":%s}" +
            "{\"id\":27,\"name\":\"执行文书送达\",\"index\":27,\"level\":9,\"level_name\":\"执行阶段\",\"comments\":%s}" +
            "{\"id\":28,\"name\":\"财产拍卖\",\"index\":28,\"level\":9,\"level_name\":\"执行阶段\",\"comments\":%s}" +
            "{\"id\":29,\"name\":\"结案\",\"index\":29,\"level\":9,\"level_name\":\"执行阶段\",\"comments\":%s}" +
            "{\"id\":30,\"name\":\"提交律师费结算\",\"index\":30,\"level\":10,\"level_name\":\"律师费结算\",\"comments\":%s}" +
            "{\"id\":31,\"name\":\"开出发票\",\"index\":31,\"level\":10,\"level_name\":\"律师费结算\",\"comments\":%s}" +
            "{\"id\":32,\"name\":\"律师费到帐\",\"index\":32,\"level\":10,\"level_name\":\"律师费结算\",\"comments\":%s}" +
            "]";

    public static void main(String[] args) {
        CaseProgress caseProgress = new CaseProgress();
        caseProgress.setId(100l);
        caseProgress.setCaseid("ea100");
        caseProgress.setCasenodeid(1);
        caseProgress.setComment("已\\\"经\"恰'谈");
        caseProgress.setNexttask("准\t备}起]诉<td>aaaa");
        List<CaseProgress> caseProgresses = new ArrayList<CaseProgress>();
        caseProgresses.add(caseProgress);
        String[] filterField = {"id","caseid","casenodeid","comment","nexttask"};
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(CaseProgress.class,filterField);
        String progress = JSON.toJSONString(caseProgresses, filter);
        System.out.println(progress);
        String[] formats = new String[32];
        formats[0]=progress;
        String s = String.format(CASE_NODE,formats);
        List<CaseNode> list = JSON.parseArray(s, CaseNode.class);
        System.out.println(list.size());
    }
    @Override
    public List<CaseNode> findAll() throws Exception {
        return null;
    }

    @Override
    public int save(CaseNode caseNode) throws Exception {
        return 0;
    }

    @Override
    public CaseNode findById(long id) throws Exception {
        return null;
    }

    @Override
    public List<CaseNode> findList(CaseNodeQuery caseNodeQuery) throws Exception {
        return null;
    }

    @Override
    public List<CaseNode> findListByPage(CaseNodeQuery caseNodeQuery) throws Exception {
        return null;
    }

    @Override
    public int count(CaseNodeQuery caseNodeQuery) throws Exception {
        return 0;
    }

    @Override
    public int deleteById(long id) throws Exception {
        return 0;
    }
}
