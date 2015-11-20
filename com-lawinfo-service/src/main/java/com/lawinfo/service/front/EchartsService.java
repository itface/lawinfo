package com.lawinfo.service.front;

import com.lawinfo.domain.front.charts.CaseInfoChart;

/**
 * Created by wangrongtao on 15/11/20.
 */
public interface EchartsService {
    public CaseInfoChart getCountCaseinfoChartData(String userid);
    public CaseInfoChart getCountCaseinfoMoneyChartData(String userid)throws Exception;

}
