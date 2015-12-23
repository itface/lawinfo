/**
 * Created by wangrongtao on 15/10/28.
 */
var chart = {
    init:function(){
        var self = this;
        self.getChartData($.proxy(self.buildChart,self));
    },
    buildChart : function(data) {
        var self = this;
        if (data) {
            var option1 = {
                title : {
                    text: '金额',
                    subtext: ''
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:data.legendData
                },
                calculable : true,
                yAxis : [
                    {
                        type : 'value',
                        boundaryGap : [0, 0.01]
                    }
                ],
                xAxis : [
                    {
                        type : 'category',
                        data : data.xAxisDataList
                    }
                ],
                series : data.series
            };
            var option2 = {
                title : {
                    text: '数量',
                    subtext: ''
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:data.legendData
                },
                calculable : true,
                yAxis : [
                    {
                        type : 'value',
                        boundaryGap : [0, 0.01]
                    }
                ],
                xAxis : [
                    {
                        type : 'category',
                        data : data.xAxisDataList
                    }
                ],
                series : data.caseCounts
            };
            var option3 = {
                title : {
                    text: '总案件数量',
                    subtext: ''
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:data.totallegendData
                },
                calculable : true,
                yAxis : [
                    {
                        type : 'value',
                        boundaryGap : [0, 0.01]
                    }
                ],
                xAxis : [
                    {
                        type : 'category',
                        data : data.totallegendData
                    }
                ],
                series : data.totalcaseCounts
            };
            var option4 = {
                title : {
                    text: '总案件金额',
                    subtext: ''
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:data.totallegendData
                },
                calculable : true,
                yAxis : [
                    {
                        type : 'value',
                        boundaryGap : [0, 0.01]
                    }
                ],
                xAxis : [
                    {
                        type : 'category',
                        data : data.totallegendData
                    }
                ],
                series : data.totalcaseAmount
            };
            var myChart = echarts.init(document.getElementById('chart1'));
            myChart.setOption(option1);
            var myChart2 = echarts.init(document.getElementById('chart2'));
            myChart2.setOption(option2);
            var myChart3 = echarts.init(document.getElementById('chart3'));
            myChart3.setOption(option3);
            var myChart4 = echarts.init(document.getElementById('chart4'));
            myChart4.setOption(option4);
        }else{
            mainAlert('暂无数据');
        }
    },
    getChartData:function(callback) {
        var self = this;
        var chartData = null;
        jQuery.ajax({
            url:'/lawinfo/front/chart/get',
            data:{userid:currentUser,currenttabtype:currentTabType},
            type:'GET',
            cache:false,
            //async:false,
            success:function(data) {
                chartData = data;
            },
            error:function() {
                mainAlert('获取报表数据异常');
            }
        }).done(function(){
            callback && callback(chartData);
        });
    }
}