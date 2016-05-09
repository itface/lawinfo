(function($) {
    $.fn.extend({
        "my_page": function(options) {
            var $this = this;
            /*var defaults = {
            };
            var pageinfo  = $.extend({},defaults,options||{});*/
            var pageinfo = options;
            if (!pageinfo||!pageinfo.queryform||pageinfo.total<1||pageinfo.pageSize<1||pageinfo.currentPage<1) {
                return false;
            }
            var pageCount = pageinfo.total%pageinfo.pageSize==0?pageinfo.total/pageinfo.pageSize:parseInt(pageinfo.total/pageinfo.pageSize)+1;
            if (pageCount < 2){
                return false;
            }
            pageinfo.total = parseInt(pageinfo.total);
            pageinfo.pageSize = parseInt(pageinfo.pageSize);
            pageinfo.currentPage = parseInt(pageinfo.currentPage);
            pageinfo.pageCount = pageCount;
            var pagenoClass = 'my_pageno';
            var pagePrevClass = 'my_page_prev';
            var pageNextClass = 'my_page_next';
            var firstPageClass = 'my_page_first';
            var lastPageClass = 'my_page_last';
            var paginationClass = 'pagination';
            buildPageHtml($this,pageinfo);
            initEvent($this,pageinfo);
            function initEvent($this,pageinfo){
                $this.find("."+pagenoClass).bind("click",
                    function() {
                        var pageno = $(this).html();
                        redirectTo(pageinfo,pageno);
                    }
                );
                $this.find("."+pagePrevClass).bind("click",
                    function() {
                        redirectTo(pageinfo,pageinfo.currentPage - 1);
                    }
                );
                $this.find("."+pageNextClass).bind("click",
                    function() {
                        redirectTo(pageinfo,pageinfo.currentPage + 1);
                    }
                );
                $this.find("."+firstPageClass).bind("click",
                    function() {
                        redirectTo(pageinfo,1);
                    }
                );
                $this.find("."+lastPageClass).bind("click",
                    function() {
                        redirectTo(pageinfo,pageinfo.pageCount);
                    }
                );
            }
            function redirectTo(pageinfo,page) {
                $('#page').val(page);
                /*if (url.indexOf("?") == -1) url += "?";
                else url += "&";
                url += "page=" + page;
                window.location.href=url;*/
                $("form[name='" + pageinfo.queryform + "']").attr("action", pageinfo.url);
                $("form[name='" + pageinfo.queryform + "']").attr("method", 'POST');
                $("form[name='" + pageinfo.queryform + "']").submit();

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
                    html.push("<li><a class='"+firstPageClass+"'>第一页</a></li>");
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
                html.push("<li class='disabled'><a>共"+pageinfo.total+"条</a></li>");
                html.push("<li class='disabled'><a>"+pageinfo.pageSize+"条/页</a></li>");
                html.push("<li class='disabled'><a>共"+pageinfo.pageCount+"页</a></li>");
                html.push("</ul>");
                $this.html(html.join(""));
            }
            return $this;
        },
    });
})(jQuery);