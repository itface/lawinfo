package com.lawinfo.service.front.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
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
import org.springframework.util.StringUtils;

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

    private int getYear(long time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        int year = calendar.get(Calendar.YEAR);
        return year;
    }
    private void buildMultimap(List<CaseInfo> list,Multimap<String, CaseInfo> caseInfoMultimap,Multimap<Integer, Double> totalcaseInfoMultimap) {
        if (!CollectionUtils.isEmpty(list)) {
            for (CaseInfo caseInfo : list) {
                String sslawyers = caseInfo.getSslawyers();
                String exelawyers = caseInfo.getExelawyers();
                int year = getYear(caseInfo.getCreatetime());
                double amount = caseInfo.getRealtotalprice();
                totalcaseInfoMultimap.put(year, amount);
                if (!StringUtils.isEmpty(sslawyers)) {
                    String[] sslawyerArr = sslawyers.split(",");
                    for (int i = 0; i < sslawyerArr.length; i++) {
                        String key = sslawyerArr[i]+","+year;
                        caseInfoMultimap.put(key, caseInfo);
                    }
                }
                if (!StringUtils.isEmpty(exelawyers)) {
                    String[] exelawyerArr = exelawyers.split(",");
                    for (int i = 0; i < exelawyerArr.length; i++) {
                        String key = exelawyerArr[i]+","+year;
                        caseInfoMultimap.put(key, caseInfo);
                    }
                }
            }
        }
    }
    private void setTotal(CaseInfoChart caseInfoChart,Multimap<Integer, Double> totalcaseInfoMultimap){
        if (totalcaseInfoMultimap != null&&totalcaseInfoMultimap.size()>0) {
            List<EchartsSerie> totalCaseCounts = new ArrayList<EchartsSerie>();
            List<EchartsSerie> totalCaseAmount = new ArrayList<EchartsSerie>();
            List<String> totallegendData = new ArrayList<String>();
            Set<Integer> keySet1 = totalcaseInfoMultimap.keySet();
            for (Iterator<Integer> iter = keySet1.iterator(); iter.hasNext();) {
                Integer key = iter.next();
                String year = String.valueOf(key);
                Collection<Double> collection = totalcaseInfoMultimap.get(key);
                List<String> totalmoney = new ArrayList<String>();
                List<String> totalcount = new ArrayList<String>();
                totalcount.add(String.valueOf(collection.size()));
                double amount = 0;
                for (double money : collection) {
                    amount = amount+money;
                }
                totallegendData.add(year);
                totalmoney.add(String.valueOf(amount));
                EchartsSerie echartsSerie3 = new EchartsSerie();
                echartsSerie3.setName(year);
                echartsSerie3.setType("bar");
                echartsSerie3.setData(totalmoney);
                EchartsSerie echartsSerie4 = new EchartsSerie();
                echartsSerie4.setName(year);
                echartsSerie4.setType("bar");
                echartsSerie4.setData(totalcount);
                totalCaseCounts.add(echartsSerie4);
                totalCaseAmount.add(echartsSerie3);
            }
            caseInfoChart.setTotallegendData(totallegendData);
            caseInfoChart.setTotalcaseAmount(totalCaseAmount);
            caseInfoChart.setTotalcaseCounts(totalCaseCounts);
        }
    }
    private void setByPerson(CaseInfoChart caseInfoChart,Multimap<String, CaseInfo> caseInfoMultimap){
        if (caseInfoMultimap != null&&caseInfoMultimap.size()>0) {
            logger.info("buildCaseInfoChart caseInfoMultimap size:"+caseInfoMultimap.size());
            List<String> xAxisDataList = new ArrayList<String>();
            List<EchartsSerie> series = new ArrayList<EchartsSerie>();
            List<String> legendData = new ArrayList<String>();
            List<EchartsSerie> caseCounts = new ArrayList<EchartsSerie>();
            Set<String> keySet = caseInfoMultimap.keySet();
            Set<String> yearSet = new HashSet<String>();
            Map<String, String> userMap = new HashMap<String, String>();
            for (Iterator<String> iter = keySet.iterator(); iter.hasNext();) {
                String key = iter.next();
                String[] useridAndYear = key.split(",");
                String userid = useridAndYear[0];
                String year = useridAndYear[1];
                yearSet.add(year);
                userMap.put(userid, userid.substring(0,userid.indexOf("[")));
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

                for (Iterator<String> iter2 = userids.iterator(); iter2.hasNext();) {
                    String userid = iter2.next();
                    String name = userMap.get(userid);
                    xAxisDataList.add(name);
                    String key = userid+","+year;
                    Collection<CaseInfo> caseInfos = caseInfoMultimap.get(key);
                    double totalPrice = 0;
                    for (CaseInfo caseInfo : caseInfos) {
                        long time = caseInfo.getCreatetime();
                        String caseinfoYear = String.valueOf(getYear(time));
                        if (year.equals(caseinfoYear)) {
                            double price = caseInfo.getRealtotalprice();
                            totalPrice += price;

                        }
                    }
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
        }
    }
    private CaseInfoChart buildCaseInfoChart(Multimap<String, CaseInfo> caseInfoMultimap,Multimap<Integer, Double> totalcaseInfoMultimap) {
        logger.info("buildCaseInfoChart caseInfoMultimap size:"+caseInfoMultimap.size());
        CaseInfoChart caseInfoChart = new CaseInfoChart();
        setByPerson(caseInfoChart,caseInfoMultimap);
        setTotal(caseInfoChart,totalcaseInfoMultimap);
        return caseInfoChart;
    }
    private long getStarttime(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int startYear = year-1;
        calendar.set(startYear,0,1,0,0,0);
        return calendar.getTimeInMillis();
    }
    @Override
    public CaseInfoChart getCountCaseinfoMoneyChartData(String userid)throws Exception{
        try {
            CaseInfoQuery caseInfoQuery = new CaseInfoQuery();
            caseInfoQuery.setStartcreatetime(getStarttime());
            List<CaseInfo> list = caseInfoService.findComputeFieldList(caseInfoQuery, userid);
            Multimap<String, CaseInfo> caseInfoMultimap = ArrayListMultimap.create();
            Multimap<Integer, Double> totalcaseInfoMultimap = ArrayListMultimap.create();
            buildMultimap(list,caseInfoMultimap,totalcaseInfoMultimap);
            if (caseInfoMultimap != null) {
                CaseInfoChart caseInfoChart = buildCaseInfoChart(caseInfoMultimap,totalcaseInfoMultimap);
                return caseInfoChart;
            }
        } catch (Exception e) {
            logger.error("getCountCaseinfoMoneyChartData error",e);
            throw e;
        }
        return null;
    }
}
