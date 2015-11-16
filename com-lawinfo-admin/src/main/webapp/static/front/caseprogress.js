/**
 * Created by wangrongtao on 15/10/28.
 */
var caseProgressTreeSelectedNode = null;
var caseProgressTree = null;
var caseprogress = {
    init:function(){
        var self = this;
        jQuery('#caseinfo-progress-form').hide();
        clearSaveProgressAlert();
        if (selectCaseinfoId&&selectCaseinfoId>0) {
            self.initCaseProgressTree($.proxy(self.buildCaseProgressTree,self));
        }
    },
    showCaseProgressComments:function(node){
        jQuery('#caseinfo-progress-form').show();
        jQuery('#caseinfo-progress-list').empty();
        var comments = node.caseProgressCommentList;
        if (comments!=null) {
            var listHtml = '';
            for (var i=0;i<comments.length;i++) {
                var comment = comments[i];
                var index = i+1;
                var str = index + "、" + "&nbsp;&nbsp;"+comment.createtimestr;
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4">'+str+'</div>';
                listHtml += '<div class="col-xs-4"><textarea  class="form-control" rows="3" readonly>'+comment.comment+'</textarea></div>';
                listHtml += '<div class="col-xs-4"><textarea  class="form-control" rows="3" readonly>'+comment.nexttask+'</textarea></div>';
                /*listHtml += '<div class="col-xs-2">'+comment.optuserid+'</div>';
                listHtml += '<div class="col-xs-3">'+comment.createtimestr+'</div>';*/
                listHtml += '</div>';
            }
            jQuery('#caseinfo-progress-list').append(listHtml);
            clearSaveProgressAlert();
        }
    },
    buildCaseProgressTree : function(treedata) {
        var self = this;
        caseProgressTree = $('#caseinfo-progress-tree').treeview({
            data: treedata,
            showCheckbox: false,
            onNodeSelected: function (event, node) {
                caseProgressTreeSelectedNode = node;
                if (node.parentprocessnodeid>0) {
                    self.showCaseProgressComments(node);
                }else{
                    jQuery('#caseinfo-progress-form').hide();
                    clearSaveProgressAlert();
                }
            },
            onNodeUnselected: function (event, node) {
            }
        });
        //$('#progress-user-tree .check-icon').remove();
        //customOrgTree.treeview('expandAll');
        caseProgressTree.treeview('expandAll');
    },
    initCaseProgressTree:function(callback) {
        var self = this;
        var treedata = null;
        jQuery.ajax({
            url:'/lawinfo/front/caseprogress/findprogresstree',
            data:{caseinfoid:selectCaseinfoId},
            type:'GET',
            //async:false,
            success:function(data) {
                treedata = data;
            },
            error:function() {
                mainAlert('获取案件进度信息异常');
            }
        }).done(function(){
            callback && callback(treedata);
        });
    }
}