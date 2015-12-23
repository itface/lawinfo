package com.lawinfo.domain.front.charts;

import java.util.List;

/**
 * Created by wangrongtao on 15/11/20.
 */
public class CaseInfoChart {
    private List<String> xAxisDataList;
    private List<EchartsSerie> series;
    private List<String> legendData;
    private List<EchartsSerie> caseCounts;

    private List<String> totallegendData;
    private List<EchartsSerie> totalcaseCounts;
    private List<EchartsSerie> totalcaseAmount;

    public List<String> getxAxisDataList() {
        return xAxisDataList;
    }

    public void setxAxisDataList(List<String> xAxisDataList) {
        this.xAxisDataList = xAxisDataList;
    }

    public List<EchartsSerie> getSeries() {
        return series;
    }

    public void setSeries(List<EchartsSerie> series) {
        this.series = series;
    }

    public List<String> getLegendData() {
        return legendData;
    }

    public void setLegendData(List<String> legendData) {
        this.legendData = legendData;
    }

    public List<EchartsSerie> getCaseCounts() {
        return caseCounts;
    }

    public void setCaseCounts(List<EchartsSerie> caseCounts) {
        this.caseCounts = caseCounts;
    }

    public List<EchartsSerie> getTotalcaseCounts() {
        return totalcaseCounts;
    }

    public void setTotalcaseCounts(List<EchartsSerie> totalcaseCounts) {
        this.totalcaseCounts = totalcaseCounts;
    }

    public List<EchartsSerie> getTotalcaseAmount() {
        return totalcaseAmount;
    }

    public void setTotalcaseAmount(List<EchartsSerie> totalcaseAmount) {
        this.totalcaseAmount = totalcaseAmount;
    }

    public List<String> getTotallegendData() {
        return totallegendData;
    }

    public void setTotallegendData(List<String> totallegendData) {
        this.totallegendData = totallegendData;
    }
}
