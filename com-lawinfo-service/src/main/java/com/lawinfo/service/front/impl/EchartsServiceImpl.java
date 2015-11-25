package com.lawinfo.service.front.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.lawinfo.domain.front.CaseInfo;
import com.lawinfo.domain.front.CaseInfoUser;
import com.lawinfo.domain.front.charts.CaseInfoChart;
import com.lawinfo.domain.front.charts.EchartsSerie;
import com.lawinfo.domain.front.query.CaseInfoQuery;
import com.lawinfo.domain.front.query.CaseInfoUserQuery;
import com.lawinfo.domain.org.User;
import com.lawinfo.service.front.CaseInfoService;
import com.lawinfo.service.front.CaseInfoUserService;
import com.lawinfo.service.front.EchartsService;
import com.lawinfo.service.org.UserService;
import com.lawinfo.service.org.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by wangrongtao on 15/11/20.
 */
@Service
public class EchartsServiceImpl implements EchartsService {
    private static Logger logger = LoggerFactory.getLogger(EchartsServiceImpl.class);

    @Resource
    private CaseInfoService caseInfoService;
    @Resource
    private UserService userService;
    @Resource
    private CaseInfoUserService caseInfoUserService;
    @Override
    public CaseInfoChart getCountCaseinfoChartData(String userid) {

        return null;
    }

    private int getYear(long time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        int year = calendar.get(Calendar.YEAR);
        return year;
    }
    private Multimap<String, CaseInfo> buildMultimap(List<CaseInfoUser> caseInfoUsers,List<CaseInfo> list) {
        if (!CollectionUtils.isEmpty(caseInfoUsers)&&!CollectionUtils.isEmpty(list)) {
            Multimap<String, CaseInfo> caseInfoMultimap = ArrayListMultimap.create();
            for (CaseInfoUser caseInfoUser : caseInfoUsers) {
                for (CaseInfo caseInfo : list) {
                    if (caseInfoUser.getCaseinfoid() == caseInfo.getId()) {
                        String userid = caseInfoUser.getUserid();
                        String key = userid+","+getYear(caseInfo.getCreatetime());
                        caseInfoMultimap.put(key, caseInfo);
                    }
                }
            }
            return caseInfoMultimap;
        }
        return null;
    }
    private CaseInfoChart buildCaseInfoChart(Multimap<String, CaseInfo> caseInfoMultimap) {
        if (caseInfoMultimap != null&&caseInfoMultimap.size()>0) {
            logger.info("buildCaseInfoChart caseInfoMultimap size:"+caseInfoMultimap.size());
            CaseInfoChart caseInfoChart = new CaseInfoChart();
            List<String> xAxisDataList = new ArrayList<String>();
            List<EchartsSerie> series = new ArrayList<EchartsSerie>();
            List<String> legendData = new ArrayList<String>();
            List<EchartsSerie> caseCounts = new ArrayList<EchartsSerie>();
            Set<String> keySet = caseInfoMultimap.keySet();
            Set<String> yearSet = new HashSet<String>();
            Map<String, String> userMap = new HashMap<String, String>();
            logger.info("buildCaseInfoChart keySet size:"+keySet.size());
            for (Iterator<String> iter = keySet.iterator(); iter.hasNext();) {
                String key = iter.next();
                String[] useridAndYear = key.split(",");
                String userid = useridAndYear[0];
                String year = useridAndYear[1];
                yearSet.add(year);
                User user = UserUtils.findByUserid(userid);
                if (user != null) {
                    userMap.put(userid, user.getName());
                }
                logger.info("buildCaseInfoChart key :"+key);
            }
            Set<String> userids = userMap.keySet();
            for (Iterator<String> iter = yearSet.iterator(); iter.hasNext();) {
                String year = iter.next();
                legendData.add(year);
                EchartsSerie echartsSerie = new EchartsSerie();
                echartsSerie.setName(year);
                echartsSerie.setType("bar");
                EchartsSerie echartsSerie2 = new EchartsSerie();
                echartsSerie2.setName(year);
                echartsSerie2.setType("bar");
                List<String> money = new ArrayList<String>();
                List<String> count = new ArrayList<String>();
                logger.info("buildCaseInfoChart year:"+year);
                for (Iterator<String> iter2 = userids.iterator(); iter2.hasNext();) {
                    String userid = iter2.next();
                    String name = userMap.get(userid);
                    xAxisDataList.add(name);
                    String key = userid+","+year;
                    Collection<CaseInfo> caseInfos = caseInfoMultimap.get(key);
                    logger.info("buildCaseInfoChart key:"+key+",caseinfos size:"+caseInfos.size());
                    double totalPrice = 0;
                    for (CaseInfo caseInfo : caseInfos) {
                        long time = caseInfo.getCreatetime();
                        String caseinfoYear = String.valueOf(getYear(time));
                        logger.info("buildCaseInfoChart key:"+key+",caseinfoYear"+caseinfoYear);
                        if (year.equals(caseinfoYear)) {
                            double price = caseInfo.getRealtotalprice();
                            totalPrice += price;
                            logger.info("buildCaseInfoChart key:"+key+",price"+price);

                        }
                    }
                    logger.info("buildCaseInfoChart key:"+key+",totalPrice:"+totalPrice);
                    money.add(String.valueOf(totalPrice));
                    count.add(String.valueOf(caseInfos.size()));
                }
                echartsSerie.setData(money);
                echartsSerie2.setData(count);
                series.add(echartsSerie);
                caseCounts.add(echartsSerie2);
            }
            caseInfoChart.setSeries(series);
            caseInfoChart.setLegendData(legendData);
            caseInfoChart.setxAxisDataList(xAxisDataList);
            caseInfoChart.setCaseCounts(caseCounts);
            return caseInfoChart;
        }
        return null;
    }
    @Override
    public CaseInfoChart getCountCaseinfoMoneyChartData(String userid)throws Exception{
        try {
            List<String> subordinate = userService.findAllSubordinate(userid);
            if (subordinate==null) {
                subordinate = new ArrayList<String>();
            }
            subordinate.add(userid);
            CaseInfoUserQuery caseInfoUserQuery = new CaseInfoUserQuery();
            caseInfoUserQuery.setUserids(subordinate);
            List<CaseInfoUser> caseInfoUsers = caseInfoUserService.findList(caseInfoUserQuery);
            if (!CollectionUtils.isEmpty(caseInfoUsers)) {
                List<Long> allCaseinfoid = new ArrayList<Long>();
                for (CaseInfoUser caseInfoUser : caseInfoUsers) {
                    allCaseinfoid.add(caseInfoUser.getCaseinfoid());
                }
                CaseInfoQuery caseInfoQuery = new CaseInfoQuery();
                caseInfoQuery.setCaseinfoids(allCaseinfoid);
                List<CaseInfo> list = caseInfoService.findList(caseInfoQuery);
                Multimap<String, CaseInfo> caseInfoMultimap = buildMultimap(caseInfoUsers,list);
                if (caseInfoMultimap != null) {
                    CaseInfoChart caseInfoChart = buildCaseInfoChart(caseInfoMultimap);
                    return caseInfoChart;
                }
            }
        } catch (Exception e) {
            logger.error("getCountCaseinfoMoneyChartData error",e);
            throw e;
        }
        return null;
    }
}
