/**
 * Created by wangrongtao on 15/10/28.
 */

var userSelectedNode = null;
var userTree = null;
var user = {
    init:function(){
        var self = this;
        self.initUserTree($.proxy(self.buildUserTree,self));
    },
    refreshPage : function() {
        if (userSelectedNode != null) {
            currentPage = 1;
            if (userSelectedNode.type == 1 && userSelectedNode.id > 0) {
                $('#case-doing-tab').click();
                currentTabType=1;
                currentUser=userSelectedNode.userid;
                caseinfo.init();
            }else if (userSelectedNode.type == 0 && userSelectedNode.id == 0) {
                $('#case-doing-tab').click();
                currentTabType=1;
                currentUser=loginuser;
                caseinfo.init();
            }
        }
    },
    buildUserTree : function(treedata) {
        var self = this;
        if (treedata==null||treedata=='') {
            $('#nav-panel').hide();
            $('#content-pane').removeClass().addClass("col-xs-12");
        }else{
            $('#nav-panel').show();
            $('#content-pane').removeClass().addClass("col-xs-9");
            userTree = $('#nav-tree').treeview({
                data: treedata,
                onNodeSelected: function(event, node) {
                    userSelectedNode=node;
                    self.refreshPage();
                },
                onNodeUnselected: function (event, node) {
                }
            });
            userTree.treeview('expandAll');
        }
    },
    initUserTree:function(callback) {
        var self = this;
        var treedata = null;
        jQuery.ajax({
            url:'/lawinfo/front/user/findtree',
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
    }
}