/**
 * Created by wangrongtao on 15/10/28.
 */
var lawyerTree;
var caseinfoM = {
    init:function(){
        var self=this;
        $("form[id='caseinfo-form']")[0].reset();
        self.initCustomOrgTree($.proxy(self.buildCustomOrgTree,self));
        self.initCustomerTree($.proxy(self.buildCustomerTree,self));
        self.initLawyerTree($.proxy(self.buildLawyerTree,self));
        jQuery('#caseinfo-add .submit').unbind();
        jQuery('#caseinfo-add .submit').on('click',function(e){
            jQuery(e.target).addClass('disabled');
            var casetype = jQuery('#caseinfo-add  #casetype').is(':checked')?1:0;
            var caseorgid = $.trim(jQuery('#caseinfo-add  #caseorgid').val());
            var caseorgname = $.trim($("#caseinfo-add  #caseorgname").val());
            var contacts = $.trim(jQuery('#caseinfo-add  #contacts').val());
            var contactids = $.trim(jQuery('#caseinfo-add  #contactids').val());
            var debtorinfo = $.trim(jQuery('#caseinfo-add  #debtorinfo').val());
            var iscreditorrelated = jQuery('#caseinfo-add  #iscreditorrelated').is(':checked')?1:0;
            var debtorpropertyinfo = $.trim(jQuery('#caseinfo-add  #debtorpropertyinfo').val());
            var ay = $.trim(jQuery('#caseinfo-add  #ay').val());
            var zqbj = $.trim(jQuery('#caseinfo-add  #zqbj').val());
            var zqdqr = $.trim(jQuery('#caseinfo-add  #zqdqr').val());
            var guarantorinfo = $.trim(jQuery('#caseinfo-add  #guarantorinfo').val());
            var isguarantorrelated = jQuery('#caseinfo-add  #isguarantorrelated').is(':checked')?1:0;
            var guaranteetype = $.trim(jQuery('#caseinfo-add  #guaranteetype').val());
            var guarantorpropertyinfo = $.trim(jQuery('#caseinfo-add  #guarantorpropertyinfo').val());
            var pawninfo = $.trim(jQuery('#caseinfo-add  #pawninfo').val());
            var pawnvalue = $.trim(jQuery('#caseinfo-add  #pawnvalue').val());
            var caseprocedure = $.trim(jQuery('#caseinfo-add  #caseprocedure').val());
            var court = $.trim(jQuery('#caseinfo-add  #court').val());
            var judge = $.trim(jQuery('#caseinfo-add  #judge').val());
            var sslawyerids = $.trim(jQuery('#caseinfo-add  #sslawyerids').val());
            var sslawyers = $.trim(jQuery('#caseinfo-add  #sslawyers').val());
            var totalprice = $.trim(jQuery('#caseinfo-add  #totalprice').val());
            var ssajbh = $.trim(jQuery('#caseinfo-add  #ssajbh').val());
            if (!caseorgid) {
                formAlert('caseinfo-form','案件所属机构不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!contacts) {
                formAlert('caseinfo-form','案件联系人不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!debtorinfo) {
                formAlert('caseinfo-form','债务人信息不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!debtorpropertyinfo) {
                formAlert('caseinfo-form','债务人财产状况不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!ay) {
                formAlert('caseinfo-form','案由不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!zqbj||isNaN(zqbj)) {
                formAlert('caseinfo-form','债权本金必须是数值');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!zqdqr) {
                formAlert('caseinfo-form','债权到期日不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!guarantorinfo) {
                formAlert('caseinfo-form','担保人信息不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!guaranteetype) {
                formAlert('caseinfo-form','担保方式不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!pawninfo) {
                formAlert('caseinfo-form','抵押物信息不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!pawnvalue||isNaN(pawnvalue)) {
                formAlert('caseinfo-form','抵押物评估价值必须是数值');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!caseprocedure) {
                formAlert('caseinfo-form','案件程序不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!court) {
                formAlert('caseinfo-form','受理法院不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!judge) {
                formAlert('caseinfo-form','承办法官不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!sslawyerids) {
                formAlert('caseinfo-form','诉讼律师不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!totalprice||isNaN(totalprice)) {
                formAlert('caseinfo-form','律师费总额必须是数值');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            if (!guarantorpropertyinfo) {
                formAlert('caseinfo-form','担保人财产状况不能为空');
                jQuery(e.target).removeClass('disabled');
                return false;
            }
            var obj = {};
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
                        window.location.href = "/lawinfo/mobile";
                    }else{
                        formAlert('caseinfo-form','保存失败');
                    }
                    jQuery(e.target).removeClass('disabled');
                },
                error:function() {
                    jQuery(e.target).removeClass('disabled');
                    formAlert('caseinfo-form','保存异常');
                }
            });
        });
    },
    buildCustomOrgTree : function(treedata) {
        var self = this;
        customOrgTree = $('#progress-org-tree').treeview({
            data: treedata,
            onNodeSelected: function(event, node) {
                customOrgTreeSelectedNode=node;
                if (node.parentorgid>0) {
                    $('#caseinfo-add #caseorgid').val(node.id);
                    $('#caseinfo-add #caseorgname').val(node.text);
                }
            },
            onNodeUnselected: function (event, node) {
            }
        });
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
            $('#caseinfo-add #contacts').val(users);
            $('#caseinfo-add #contactids').val(userids);
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
            $('#caseinfo-add #sslawyers').val(users);
            $('#caseinfo-add #sslawyerids').val(userids);
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