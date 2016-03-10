(function($) {
    $.fn.extend({
        "my_page": function(options) {
            var $this = this;
            var defaults = {
                url:'',
                currentPage:1,
                pageCount:1,
                formname:''
            };
            var pageinfo  = $.extend({},defaults,options||{});
            var pagenoClass = 'my_pageno';
            var pagePrevClass = 'my_page_prev';
            var pageNextClass = 'my_page_next';
            var firstPageClass = 'my_page_first';
            var lastPageClass = 'my_page_last';
            var paginationClass = 'pagination';

            //定义分页结构体
            // var pageinfo = {
            //     url: $(this).attr("url"),
            //     currentPage: $(this).attr("currentPage") * 1,
            //     // 当前页码
            //     pageCount: $(this).attr("pageCount") * 1 // 总页码
            // };
            if (pageinfo.pageCount < 2){
                return false;
            }
            buildPageHtml($this,pageinfo);
            initEvent($this,pageinfo);
            function initEvent($this,pageinfo){
                $this.find("."+pagenoClass).bind("click",
                    function() {
                        alert($(this).html());
                        redirectTo($(this).html());
                    }
                );
                $this.find("."+pagePrevClass).bind("click",
                    function() {
                        redirectTo(pageinfo.currentPage - 1);
                    }
                );
                $this.find("."+pageNextClass).bind("click",
                    function() {
                        redirectTo(pageinfo.currentPage + 1);
                    }
                );
                $this.find("."+firstPageClass).bind("click",
                    function() {
                        redirectTo(1);
                    }
                );
                $this.find("."+lastPageClass).bind("click",
                    function() {
                        redirectTo(pageinfo.pageCount);
                    }
                );
            }
            function redirectTo(pageinfo) {
                var url = pageinfo.url;
                if (url.indexOf("?") == -1) url += "?";
                else url += "&";
                url += "page=" + page;
                $("form[name='" + pageinfo.formname + "']").attr("action", url);
                $("form[name='" + pageinfo.formname + "']").submit();

            }
            function buildPageHtml($this,pageinfo){
                var html = [];
                //初始起始页数、结束页数
                var start = 0,
                end = 10;
                if (pageinfo.currentPage >= 10){
                    start = pageinfo.currentPage - 5;
                }
                if (pageinfo.pageCount > pageinfo.currentPage + 5){
                    end = pageinfo.currentPage + 5;
                } else{
                    end = pageinfo.pageCount;
                }
                html.push("<ul class='"+paginationClass+"'>");
                if (pageinfo.currentPage != 1){
                    //如果不是第一页则有前一页
                    html.push("<li><a class='"+firstPageClass+"'>前一页</a></li>");
                    html.push("<li><a class='"+pagePrevClass+"'>前一页</a></li>");
                }
                if (pageinfo.pageCount > 10 && pageinfo.currentPage > 9){
                    html.push("<li><a class='"+pagenoClass+"'>1</a></li>");
                } 
                for (var i = start; i < end; i++) {
                    if ((i + 1) == pageinfo.currentPage){
                        html.push("<li class='active'><a class='"+pagenoClass+"'>" + (i + 1) + "</a></li>");
                    }else{
                        html.push("<li><a class='"+pagenoClass+"'>" + (i + 1) + "</a></li>");
                    }
                }
                if (pageinfo.pageCount > 10 && pageinfo.currentPage < pageinfo.pageCount - 4){
                    html.push("<li><a >" + pageinfo.pageCount + "</a></li>");
                }
                if (pageinfo.currentPage != pageinfo.pageCount){
                    html.push("<li class='"+pageNextClass+"'><a >后一页</a></li>");
                    html.push("<li class='"+lastPageClass+"'><a >最后一页</a></li>");
                }
                html.push("</ul>");
                $this.html(html.join(""));
            }
            return $this;
        },
    });
})(jQuery);