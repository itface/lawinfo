/**
 * Created by wangrongtao on 15/10/28.
 */

var exeLawyerTreeSelectedNode = null;
var exeLawyerTree = null;

var exeLawyer = {
    saveActionAlert:function(msg){
        jQuery('#caseinfo-exelawyer-modal .error-label').empty();
        jQuery('#caseinfo-exelawyer-modal .error-label').text(msg);
        jQuery('#caseinfo-exelawyer-modal .has-error').show();
    },
    initClickEvent:function(){
        jQuery('#caseinfo-exelawyer-modal .submit').unbind('click');
    },
    init:function(){
        var self = this;
        self.initClickEvent();
        self.initExeLawyerTree($.proxy(self.buildExeLawyerTree,self));
        jQuery('#caseinfo-exelawyer-modal .submit').on('click',function(e){
            var exeajbh = jQuery('#caseinfo-exelawyer-modal  #exeajbh').val();
            var exelawyers = jQuery('#caseinfo-exelawyer-modal  #exelawyers').val();
            var exelawyerids = jQuery('#caseinfo-exelawyer-modal  #exelawyerids').val();
            if (!exeajbh) {
                self.saveActionAlert('执行案优件号不能为空');
                return false;
            }
            if (!exelawyers) {
                self.saveActionAlert('执行律师不能为空');
                return false;
            }
            if (!exelawyerids) {
                self.saveActionAlert('执行律师不能为空');
                return false;
            }
            if (!selectCaseinfoId) {
                self.saveActionAlert('无效的案件号，请重新选择');
                return false;
            }
            jQuery.ajax({
                url:'/lawinfo/front/caseinfo/exelawyer/add',
                data:{id:selectCaseinfoId,exeajbh:exeajbh,exelawyers:exelawyers,exelawyerids:exelawyerids},
                type:'POST',
                success:function(data) {
                    self.saveActionAlert('保存成功');
                    caseinfo.initCaseinfoTable();
                    jQuery('#caseinfo-exelawyer-modal').modal('hide');
                },
                error:function() {
                    self.saveActionAlert('保存异常');
                }
            });
        });
    },
    findSelectedExeLawyerNodess:function(){
        return exeLawyerTree.treeview('getChecked');
    },
    setExeLawyer:function(){
        var self = this;
        var checkedNode = self.findSelectedExeLawyerNodess();
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
            $('#caseinfo-exelawyer-modal #exelawyers').val(users);
            $('#caseinfo-exelawyer-modal #exelawyerids').val(userids);
        }
    },
    buildExeLawyerTree : function(treedata) {
        var self = this;
        exeLawyerTree = $('#caseinfo-exelawyer-tree').treeview({
            data: treedata,
            showCheckbox: true,
            onNodeSelected: function (event, node) {
                exeLawyerTreeSelectedNode = node;
            },
            onNodeUnselected: function (event, node) {
            },
            onNodeChecked: function (event, node) {
                self.setExeLawyer();
            },
            onNodeUnchecked: function (event, node) {
                self.setExeLawyer();
            }
            });
        exeLawyerTree.treeview('collapseAll');
    },
    initExeLawyerTree:function(callback) {
        var self = this;
        var treedata = null;
        jQuery.ajax({
            url:'/lawinfo/front/caseinfo/user/findlawyertree',
            type:'GET',
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