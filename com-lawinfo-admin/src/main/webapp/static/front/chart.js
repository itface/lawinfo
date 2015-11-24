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
            var myChart = echarts.init(document.getElementById('chart1'));
            myChart.setOption(option1);
            var myChart2 = echarts.init(document.getElementById('chart2'));
            myChart2.setOption(option2);
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