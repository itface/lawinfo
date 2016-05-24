/**
 * Created by wangrongtao on 15/10/28.
 */
var selectCaseinfoId = null;
var customOrgTreeSelectedNode = null;
var customerTreeSelectedNode = null;
var lawyerTreeSelectedNode = null;
var customOrgTree = null;
var customerTree = null;
var lawyerTree = null;
//var currentCaseinfoList = {};
var caseinfoIndex = 0;
var caseinfo = {

    saveActionAlert:function(msg){
        jQuery('#caseinfo-modal .error-label').empty();
        jQuery('#caseinfo-modal .error-label').text(msg);
        jQuery('#caseinfo-modal .has-error').show();
    },
    initClickEvent:function(){
        jQuery('#caseinfo-list').unbind();
        jQuery('#caseinfo-modal .submit').unbind();
        $('.caseinfo-more').unbind();
    },
    goPage:function(page){
        var self = this;
        self.showCaseinfoTableNew(page,$.proxy(self.buildCaseinfoTableNew,self));
    },
    /**
     * 有编辑权限(律师)初始化
     */
    initEditPrivil:function(){
        var self = this;
        self.initCustomOrgTree($.proxy(self.buildCustomOrgTree,self));
        self.initCustomerTree($.proxy(self.buildCustomerTree,self));
        self.initLawyerTree($.proxy(self.buildLawyerTree,self));
        jQuery('#caseinfo-modal .submit').on('click',function(e){
            jQuery(e.target).addClass('disabled');
            var casetype = jQuery('#caseinfo-modal  #casetype').is(':checked')?1:0;
            var caseorgid = $.trim(jQuery('#caseinfo-modal  #caseorgid').val());
            var caseorgname = $.trim($("#caseinfo-modal  #caseorgname").val());
            var contacts = $.trim(jQuery('#caseinfo-modal  #contacts').val());
            var contactids = $.trim(jQuery('#caseinfo-modal  #contactids').val());
            var debtorinfo = $.trim(jQuery('#caseinfo-modal  #debtorinfo').val());
            var iscreditorrelated = jQuery('#caseinfo-modal  #iscreditorrelated').is(':checked')?1:0;
            var debtorpropertyinfo = $.trim(jQuery('#caseinfo-modal  #debtorpropertyinfo').val());
            var ay = $.trim(jQuery('#caseinfo-modal  #ay').val());
            var zqbj = $.trim(jQuery('#caseinfo-modal  #zqbj').val());
            var zqdqr = $.trim(jQuery('#caseinfo-modal  #zqdqr').val());
            var guarantorinfo = $.trim(jQuery('#caseinfo-modal  #guarantorinfo').val());
            var isguarantorrelated = jQuery('#caseinfo-modal  #isguarantorrelated').is(':checked')?1:0;
            var guaranteetype = $.trim(jQuery('#caseinfo-modal  #guaranteetype').val());
            var guarantorpropertyinfo = $.trim(jQuery('#caseinfo-modal  #guarantorpropertyinfo').val());
            var pawninfo = $.trim(jQuery('#caseinfo-modal  #pawninfo').val());
            var pawnvalue = $.trim(jQuery('#caseinfo-modal  #pawnvalue').val());
            var caseprocedure = $.trim(jQuery('#caseinfo-modal  #caseprocedure').val());
            var court = $.trim(jQuery('#caseinfo-modal  #court').val());
            var judge = $.trim(jQuery('#caseinfo-modal  #judge').val());
            /*var exelawyers = jQuery('#caseinfo-modal  #exelawyers').val();
             var exelawyerids = jQuery('#caseinfo-modal  #exelawyerids').val();*/
            var sslawyerids = $.trim(jQuery('#caseinfo-modal  #sslawyerids').val());
            var sslawyers = $.trim(jQuery('#caseinfo-modal  #sslawyers').val());
            var totalprice = $.trim(jQuery('#caseinfo-modal  #totalprice').val());
            var ssajbh = $.trim(jQuery('#caseinfo-modal  #ssajbh').val());
            if (!caseorgid) {
                self.saveActionAlert('案件所属机构不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!contacts) {
                self.saveActionAlert('案件联系人不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!debtorinfo) {
                self.saveActionAlert('债务人信息不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!debtorpropertyinfo) {
                self.saveActionAlert('债务人财产状况不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!ay) {
                self.saveActionAlert('案由不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!zqbj||isNaN(zqbj)) {
                self.saveActionAlert('债权本金必须是数值');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!zqdqr) {
                self.saveActionAlert('债权到期日不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!guarantorinfo) {
                self.saveActionAlert('担保人信息不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!guaranteetype) {
                self.saveActionAlert('担保方式不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!pawninfo) {
                self.saveActionAlert('抵押物信息不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!pawnvalue||isNaN(pawnvalue)) {
                self.saveActionAlert('抵押物评估价值必须是数值');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!caseprocedure) {
                self.saveActionAlert('案件程序不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!court) {
                self.saveActionAlert('受理法院不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!judge) {
                self.saveActionAlert('承办法官不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!sslawyerids) {
                self.saveActionAlert('诉讼律师不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!totalprice||isNaN(totalprice)) {
                self.saveActionAlert('律师费总额必须是数值');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!guarantorpropertyinfo) {
                self.saveActionAlert('担保人财产状况不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            /*if (!ssajbh) {
             self.saveActionAlert('诉讼案号不能为空');
             return false;
             }*/
            var obj = {};
            //obj.ssajbh = ssajbh;
            obj.casetype = casetype;
            obj.caseorgid = caseorgid;
            obj.caseorgname = caseorgname;
            obj.contacts = contacts;
            obj.contactids = contactids;
            obj.debtorinfo = debtorinfo;
            obj.iscreditorrelated = iscreditorrelated;
            obj.debtorpropertyinfo = debtorpropertyinfo;
            obj.ay = ay;
            obj.zqbj = zqbj;
            obj.zqdqrdate = zqdqr;
            obj.guarantorinfo = guarantorinfo;
            obj.isguarantorrelated = isguarantorrelated;
            obj.guaranteetype = guaranteetype;
            obj.guarantorpropertyinfo = guarantorpropertyinfo;
            obj.pawninfo = pawninfo;
            obj.pawnvalue = pawnvalue;
            obj.caseprocedure = caseprocedure;
            obj.court = court;
            obj.judge = judge;
            obj.sslawyers = sslawyers;
            obj.sslawyerids = sslawyerids;
            obj.totalprice = totalprice;
            jQuery.ajax({
                url:'/lawinfo/front/caseinfo/add',
                data:obj,
                type:'POST',
                cache:false,
                success:function(data) {
                    if (data==1) {
                        caseinfoIndex=0;
                        currentPage=1;
                        self.showCaseinfoTableNew(1,$.proxy(self.buildCaseinfoTableNew,self));
                        $('#caseinfo-modal').modal('hide');
                        $('#caseinfo-form')[0].reset();
                    }else{
                        self.saveActionAlert('保存失败');
                    }
                    jQuery(e.target).removeClass('disabled');
                },
                error:function() {
                    self.saveActionAlert('保存异常');
                    jQuery(e.target).removeClass('disabled');
                }
            });
        });
    },
    init:function(){
        var self=this;
        $("form[id='query_form']")[0].reset();
        self.initClickEvent();
        $.proxy(self.goPage,self);
        self.goPage(1);
    },
    initCaseinfoTable:function(){
        var self=this;
        self.showCaseinfoTableNew(1,$.proxy(self.buildCaseinfoTableNew,self));
    },
    buildCaseinfoTable:function(data) {
        var self = this;
        if (currentPage<=1) {
            $('#caseinfo-list tbody').empty();
            caseinfoIndex=0;
        }
        if (data&&data.list) {
            var html = '';
            var list = data.list;
            var havenext = data.havenext;
            for(var i=0;i<list.length;i++) {
                var o = list[i];
                var exelawyerids = o.exelawyers;
                var caseinfoid = o.id;
                html+='<tr caseinfoid="'+ o.id+'" id="caseinfo_tr_'+ o.id+'" caseinfoid="'+ o.id+'" preprice="'+ o.preprice+'" sufprice="'+ o.sufprice+'" ystj="'+ o.ystj+'" estj="'+ o.estj+'" sfss="'+ o.sfss+'" ssajbh="'+ (o.ssajbh==null?'':o.ssajbh)+'">';
                html+=' <td caseinfoid="'+ o.id+'">';
                if (canRemoveCase) {
                    html+='     <button type="button" class="close btn-caseinfo-rm" aria-hidden="true" style="color: red;opacity:1">&times;</button>';
                }else{
                    html+='&nbsp;';
                }
                html+=' </td>';
                html+=' <td class="caseinfo-row" >&nbsp;&nbsp;';
                html+='     '+ (++caseinfoIndex);
                html+=' &nbsp;&nbsp;</td>';
                html+=' <td class="caseinfo-row">';
                html+='     '+ (o.optuserid);
                html+=' </td>';
                html+=' <td class="caseinfo-row">';
                html+='     '+ (o.createtimestr);
                html+=' </td>';
                html+=' <td class="caseinfo-row caseinfo-title">';
                html+='     '+ o.title;
                html+=' </td>';
                html+=' <td>';
                if (!exelawyerids) {
                    var display = self.checkExelawyerAdd() ? "block" : "none";
                    html+='     <button type="button" class="btn btn-primary btn-saveExelawyerids" style="display:'+display+'">添加执行律师</button>';
                }else{
                    html+='&nbsp;';
                }
                html+=' </td>';
                html+='</tr>';
            }
            $('#caseinfo-list tbody').append(html);
            if (havenext==true) {
                $('.caseinfo-more-div').show();
            }else{
                $('.caseinfo-more-div').hide();
            }
            $('.btn-saveExelawyerids').unbind();
            $('.btn-saveExelawyerids').on('click', $.proxy(self.saveExelawyeridsEvent,self));
            $('.btn-caseinfo-rm').unbind();
            $('.btn-caseinfo-rm').on('click', $.proxy(self.rmCaseinfoEvent,self));
        }else{
            $('.caseinfo-more-div').hide();
        }
    },
    buildCaseinfoTableNew:function(data) {
        var self = this;
        $('#caseinfo-list tbody').empty();
        jQuery('#pager').empty();
        if (currentPage<=1) {
            caseinfoIndex=0;
        }
        if (data&&data.list) {
            var html = '';
            var list = data.list;
            var havenext = data.havenext;
            var startIndex = (data.page-1)*(data.pagesize)+1;
            for(var i=0;i<list.length;i++) {
                var o = list[i];
                var exelawyerids = o.exelawyers;
                var caseinfoid = o.id;
                html+='<tr caseinfoid="'+ o.id+'" id="caseinfo_tr_'+ o.id+'" caseinfoid="'+ o.id+'" preprice="'+ o.preprice+'" sufprice="'+ o.sufprice+'" ystj="'+ o.ystj+'" estj="'+ o.estj+'" sfss="'+ o.sfss+'" ssajbh="'+ (o.ssajbh==null?'':o.ssajbh)+'">';
                /*html+=' <td caseinfoid="'+ o.id+'">';
                if (canRemoveCase) {
                    html+="     <a class='glyphicon glyphicon-trash btn btn-del btn-caseinfo-rm' title='删除' href='javascript:void(0)'></a>";
                }else{
                    html+='&nbsp;';
                }
                html+=' </td>';*/
                html+=' <td width="50px">';
                html+='     '+ (startIndex+(caseinfoIndex++));
                html+=' </td>';
                html+=' <td>';
                html+='     '+ (o.optuserid);
                html+=' </td>';
                html+=' <td width="100px">';
                html+='     '+ (o.createtimestr);
                html+=' </td>';
                html+=' <td class="caseinfo-title">';
                html+='     '+ o.title;
                html+=' </td>';
                html+=' <td caseinfoid="'+ o.id+'">';
                html+="<a class='glyphicon glyphicon-eye-open btn caseinfo-row' title='案件进度详情' href='javascript:void(0)'>案件进度详情</a>";
                if (canRemoveCase) {
                    html+="     <a class='glyphicon glyphicon-trash btn btn-del btn-caseinfo-rm' title='删除' href='javascript:void(0)'>删除</a>";
                }
                /*if (!exelawyerids) {
                    var display = self.checkExelawyerAdd() ? "block" : "none";
                    //html+="<a class='glyphicon glyphicon-edit btn btn-saveExelawyerids' title='添加执行律师' href='javascript:void(0)' style='display:"+display+"'>添加执行律师</a>";
                    //html+='     <button type="button" class="btn btn-primary btn-saveExelawyerids" style="display:'+display+'">添加执行律师</button>';
                }*/
                html+=' </td>';
                html+='</tr>';
            }
            $('#caseinfo-list tbody').append(html);
            if (data.total>data.pagesize) {
                jQuery('#pager').my_page({goPage:$.proxy(self.goPage,self),currentPage:data.page,total:data.total,pageSize:data.pagesize});
            }
            $('.caseinfo-row').unbind();
            $('.caseinfo-row').on('click', $.proxy(self.registCaseinfoTableRowEvent,self));
            $('.btn-saveExelawyerids').unbind();
            $('.btn-saveExelawyerids').on('click', $.proxy(self.saveExelawyeridsEvent,self));
            $('.btn-caseinfo-rm').unbind();
            $('.btn-caseinfo-rm').on('click', $.proxy(self.rmCaseinfoEvent,self));
        }
    },
    checkExelawyerAdd : function(){
        /*if (actionList) {
            var actionArr = actionList.split(",");
            var actionArr = actionList.split(",");
            var actionArr = actionList.split(",");
            var actionArr = actionList.split(",");
            for (var i=0;i<actionArr.length;i++) {
                if(actionArr[i] == "front-caseinfo-exelawyer-add"){
                    return true;
                }
            }
        }*/
        return false;
    },
    rmCaseinfoEvent:function(e){
        var self = this;
        var caseinfoid = $(e.target).parent().attr('caseinfoid');
        if (caseinfoid<1) {
            mainAlert('无效的案件编号');
            return false;
        }
        selectCaseinfoId = caseinfoid;
        mainConfirm('您确定要删除吗？',jQuery.proxy(self.doRmCaseinfo,this));
    },
    doRmCaseinfo:function(e){
        var self = this;
        jQuery.ajax({
            url:'/lawinfo/front/caseinfo/remove',
            data:{caseinfoid:selectCaseinfoId},
            type:'GET',
            cache:false,
            success:function(data) {
                self.showCaseinfoTableNew(1,$.proxy(self.buildCaseinfoTableNew,self));
            },
            error:function() {
                mainAlert('删除案件异常');
            }
        });
    },
    saveExelawyeridsEvent:function(e){
        //e.preventBubble();
        selectCaseinfoId = $(e.target).parent().parent().attr('caseinfoid');
        jQuery('#caseinfo-exelawyer-modal').modal({backdrop: 'static', keyboard: false}).modal('show');
    },
    registCaseinfoTableRowEvent:function(e){
        var tr = jQuery(e.target).parent().parent();
        selectCaseinfoId = tr.attr('caseinfoid');
        /*selectCasePreprice = tr.attr('preprice');
        selectCaseSufprice = tr.attr('sufprice');
        selectCaseYstj = tr.attr('ystj');
        selectCaseEstj = tr.attr('estj');
        selectCaseSfss = tr.attr('sfss');
        selectCaseSsajbh = tr.attr('ssajbh');*/
        var progresstitle = $('.caseinfo-title',tr).text();
        $('#caseinfo-progress-modal #casetitle').empty();
        $('#caseinfo-progress-modal #casetitle').append(progresstitle);
        jQuery('#caseinfo-progress-modal').modal({backdrop: 'static', keyboard: false}).modal('show');
    },
    showCaseinfoTable:function(callback){
        var self = this;
        var caseinfosPageVo = null;
        jQuery.ajax({
            url:'/lawinfo/front/caseinfo/find',
            data:{userid:currentUser,currenttabtype:currentTabType,page:currentPage},
            type:'GET',
            cache:false,
            //async:false,
            success:function(data) {
                caseinfosPageVo = data;
            },
            error:function() {
                mainAlert('获取案件信息异常');
            }
        }).done(function(e){
            callback && callback(caseinfosPageVo);
        });
    },
    showCaseinfoTableNew:function(page,callback){
        var self = this;
        var caseinfosPageVo = null;
        if (!page) {
            page=1;
        }
        var status =$('#status_query').val();
        var lxr = $('#lxr_query').val();
        var jg = $('#jg_query').val();
        var zxah = $('#zxah_query').val();
        var ssah = $('#ssah_query').val();
        jQuery.ajax({
            url:'/lawinfo/front/caseinfo/find',
            data:{userid:currentUser,currenttabtype:status,page:page,contact:lxr,caseorgname:jg,exeajbh:zxah,ssajbh:ssah},
            type:'POST',
            cache:false,
            //async:false,
            success:function(data) {
                caseinfosPageVo = data;
            },
            error:function() {
                mainAlert('获取案件信息异常');
            }
        }).done(function(e){
            callback && callback(caseinfosPageVo);
        });
    },
    buildCustomOrgTree : function(treedata) {
        var self = this;
        customOrgTree = $('#progress-org-tree').treeview({
            data: treedata,
            onNodeSelected: function(event, node) {
                customOrgTreeSelectedNode=node;
                if (node.parentorgid>0) {
                    $('#caseinfo-modal #caseorgid').val(node.id);
                    $('#caseinfo-modal #caseorgname').val(node.text);
                }
            },
            onNodeUnselected: function (event, node) {
            }
        });
        //customOrgTree.treeview('expandAll');
        customOrgTree.treeview('collapseAll');
    },
    initCustomOrgTree:function(callback) {
        var self = this;
        var treedata = null;
        jQuery.ajax({
            url:'/lawinfo/front/caseinfo/org/findcustomorgtree',
            type:'GET',
            cache:false,
            //async:false,
            success:function(data) {
                treedata = data;
            },
            error:function() {
                mainAlert('获取机构信息异常');
            }
        }).done(function(){
            callback && callback(treedata);
        });
    },
    findSelectedCustomerNodess:function(){
        return customerTree.treeview('getChecked');
    },
    setCustomer:function(){
        var self = this;
        var checkedNode = self.findSelectedCustomerNodess();
        if (checkedNode) {
            var userids = '';
            var users = '';
            for (var i=0;i<checkedNode.length;i++) {
                var nodetype = checkedNode[i].type;
                if (nodetype==1) {
                    userids+=checkedNode[i].userid;
                    userids+=',';
                    users+=checkedNode[i].text+"["+checkedNode[i].userid+"]";
                    users+=',';
                }
            }
            userids = userids.substring(0, userids.length - 1);
            users = users.substring(0, users.length - 1);
            $('#caseinfo-modal #contacts').val(users);
            $('#caseinfo-modal #contactids').val(userids);
        }
    },
    buildCustomerTree : function(treedata) {
        var self = this;
        customerTree = $('#progress-user-tree').treeview({
            data: treedata,
            showCheckbox: true,
            onNodeSelected: function (event, node) {
                customerTreeSelectedNode = node;
            },
            onNodeUnselected: function (event, node) {
            },
            onNodeChecked: function (event, node) {
                /*if (node.type!=1) {
                 }
                 mainAlert('请选择用户，不能选择机构');
                 node.state.checked=false;*/
                self.setCustomer();
            },
            onNodeUnchecked: function (event, node) {
                self.setCustomer();
            }
        });
        //$('#progress-user-tree .check-icon').remove();
        //customOrgTree.treeview('expandAll');
        customerTree.treeview('collapseAll');
    },
    initCustomerTree:function(callback) {
        var self = this;
        var treedata = null;
        jQuery.ajax({
            url:'/lawinfo/front/caseinfo/user/findcustomertree',
            type:'GET',
            cache:false,
            //async:false,
            success:function(data) {
                treedata = data;
            },
            error:function() {
                mainAlert('获取用户信息异常');
            }
        }).done(function(){
            callback && callback(treedata);
        });
    },
    findSelectedLawyerNodess:function(){
        return lawyerTree.treeview('getChecked');
    },
    setLawyer:function(){
        var self = this;
        var checkedNode = self.findSelectedLawyerNodess();
        if (checkedNode) {
            var userids = '';
            var users = '';
            for (var i=0;i<checkedNode.length;i++) {
                var nodetype = checkedNode[i].type;
                if (nodetype==1) {
                    userids+=checkedNode[i].userid;
                    userids+=',';
                    users+=checkedNode[i].text+"["+checkedNode[i].userid+"]";
                    users+=',';
                }
            }
            userids = userids.substring(0, userids.length - 1);
            users = users.substring(0, users.length - 1);
            $('#caseinfo-modal #sslawyers').val(users);
            $('#caseinfo-modal #sslawyerids').val(userids);
        }
    },
    buildLawyerTree : function(treedata) {
        var self = this;
        lawyerTree = $('#progress-lawyer-tree').treeview({
            data: treedata,
            showCheckbox: true,
            onNodeSelected: function (event, node) {
                lawyerTreeSelectedNode = node;
                self.setCustomer();
            },
            onNodeUnselected: function (event, node) {
            },
            onNodeChecked: function (event, node) {
                self.setLawyer();
            },
            onNodeUnchecked: function (event, node) {
                self.setLawyer();
            }
            });
        //$('#progress-user-tree .check-icon').remove();
        //customOrgTree.treeview('expandAll');
        lawyerTree.treeview('collapseAll');
    },
    initLawyerTree:function(callback) {
        var self = this;
        var treedata = null;
        jQuery.ajax({
            url:'/lawinfo/front/caseinfo/user/findlawyertree',
            type:'GET',
            cache:false,
            //async:false,
            success:function(data) {
                treedata = data;
            },
            error:function() {
                mainAlert('获取律师信息异常');
            }
        }).done(function(){
            callback && callback(treedata);
        });
    }
}