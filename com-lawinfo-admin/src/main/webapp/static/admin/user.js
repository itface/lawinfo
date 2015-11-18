/**
 * Created by wangrongtao on 15/10/28.
 */
var selectUserTreeNode = null;
var userTree = null;
var userRoleTree = null;
var userRoleSelectedNode=null;
var user = {
    checkPhoneNo:function(no){
        var pattern=/(^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$)|(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;
        if(pattern.test(no)) {
            return true;
        }else{
            return false;
        }
    },
    saveUserAlert:function(msg){
        jQuery('#userform .error-label').text(msg);
        jQuery('#userform .has-error').show();
    },
    initClickEvent:function(){
        jQuery('#user').unbind('click');
        jQuery('#user .btn-add').unbind('click');
        jQuery('#userModal .btn-save').unbind('click');
        jQuery('#user .btn-rm').unbind('click');
    },
    init:function(){
        var self = this;
        self.initClickEvent();
        self.initOrgUserTree($.proxy(self.buildOrgUserTree,this));

        jQuery('#user .btn-add').on('click', function (e) {
            if (selectUserTreeNode&&selectUserTreeNode.type!=1&&selectUserTreeNode.id>0) {
                jQuery('#userModal').modal('show');
            }else{
                mainAlert('请选择要添加的用户所在的部门，不能选择用户');
            }
        });
        $('#userModal').on('show.bs.modal', function () {
            self.initUserRoleTree($.proxy(self.buildUserRoleTree,self));
        });
        jQuery('#userModal .btn-save').on('click',function(e){
            var username = jQuery('#userform  #username').val();
            var userid = jQuery('#userform  #userid').val();
            var roleids = jQuery('#userform  #userroleids').val();
            var logintype = jQuery('#userform  #logintype').val();
            var orgid = selectUserTreeNode.id;
            if (!self.checkPhoneNo(userid)) {
                self.saveUserAlert('用户id只能是手机号码');
                return false;
            }
            if (!username) {
                self.saveUserAlert('用户名称不能为空');
                return false;
            }
            if (!roleids) {
                self.saveUserAlert('角色不能为空');
                return false;
            }
            if (orgid==null) {
                self.saveUserAlert('机构不能为空');
                return false;
            }
            if (logintype==null||logintype=='') {
                self.saveUserAlert('登录类型不能为空');
                return false;
            }
            jQuery.ajax({
                url:'/lawinfo/admin/user/add',
                data:{name:username,userid:userid,roleids:roleids,orgid:orgid,logintype:logintype},
                type:'POST',
                success:function(data) {
                    if (data==1) {
                        userTree = self.initOrgUserTree($.proxy(self.buildOrgUserTree,this));
                        $('#userModal').modal('hide');
                    }else{
                        self.saveUserAlert('保存异常');
                    }
                },
                error:function() {
                    self.saveUserAlert('保存异常');
                }
            });
        });
        jQuery('#user .btn-rm').on('click',$.proxy(this.delUser,this));
        self.showUserTable($.proxy(self.buildUserTable,this));
    },
    buildOrgUserTree : function(treedata) {
        var self = this;
        if (treedata==null||treedata=='') {
            treedata = [
                {
                    text: '用户管理',
                    id:0,
                    parentid:-1
                }
            ];
        }
        userTree = $('#treeview-user').treeview({
            data: treedata,
            onNodeSelected: function(event, node) {
                selectUserTreeNode=node;
            },
            onNodeUnselected: function (event, node) {
            }
        });
        userTree.treeview('expandAll');
    },
    initOrgUserTree:function(callback) {
        var treedata = null;
        jQuery.ajax({
            url:'/lawinfo/admin/user/findtree',
            type:'GET',
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
    findCheckedUserRoleNodes : function(nodeTexts) {
        return userRoleTree.treeview('search', [ nodeTexts, { ignoreCase: false, exactMatch: false } ]);
    },
    buildUserRoleTree:function(userRoleTreeData){
        var self = this;
        if (!userRoleTreeData) {
            userRoleTreeData = [
                {
                    text: '角色',
                    id:0,
                    parentid:-1,
                    showCheckbox:false
                }
            ];
        }
        userRoleTree = $('#treeview-user-role').treeview({
            data: userRoleTreeData,
            showIcon: false,
            showCheckbox: true,
            onNodeChecked: function(event, node) {
                self.setUserRoleIds();
            },
            onNodeUnchecked: function (event, node) {
                self.setUserRoleIds();
            },
            onNodeSelected: function(event, node) {
                userRoleSelectedNode=node;
                /*roleMenuTree.treeview('checkNode',node);
                var levels = $('#select-expand-all-levels').val();
                roleMenuTree.treeview('collapseAll',{silent:false});
                roleMenuTree.treeview('expandAll');*/
                var checkednode = self.findCheckedUserRoleNodess(node);
                userRoleTree.treeview('toggleNodeChecked',checkednode);
            },
            onNodeUnselected: function (event, node) {
                /*var checkednode = self.findCheckedRoleMenuNodess(node);
                roleMenuTree.treeview('toggleNodeChecked',checkednode);*/
            }
        });
        userRoleTree.treeview('expandAll');
    },
    setUserRoleIds:function(){
        var self = this;
        var checkedNode = self.findSelectedUserRoleNodess();
        if (checkedNode) {
            var roleids = '';
            for (var i=0;i<checkedNode.length;i++) {
                var nodeid = checkedNode[i].id;
                if (nodeid!=0) {
                    roleids+=checkedNode[i].id;
                    roleids+=',';
                }
            }
            roleids = roleids.substring(0, roleids.length - 1);
            $('#userroleids').val(roleids);
        }
    },
    findSelectedUserRoleNodess:function(){
        return userRoleTree.treeview('getChecked');
    },
    findCheckedUserRoleNodess : function(node) {
        return userRoleTree.treeview('search', [ node.text, { ignoreCase: false, exactMatch: false } ]);
    },
    initUserRoleTree:function(callback){
        var roleTree = null;
        jQuery.ajax({
            url:'/lawinfo/admin/role/findtree',
            type:'GET',
            success:function(data) {
                roleTree = data;
            },
            error:function() {
                mainAlert('获取菜单信息异常');
            }
        }).done(function(){
            callback && callback(roleTree);
        });
    },
    buildUserTable:function(userData) {
        var self = this;
        if (userData) {
            $('.user-table tbody').empty();
            var html = '';
            for(var i=0;i<userData.length;i++) {
                var o = userData[i];
                html+='<tr userrowid="'+ o.id+'" class="user-row">';
                html+=' <td>';
                html+='     '+ (i+1);
                html+=' </td>';
                html+=' <td>';
                html+='     '+ (o.userid);
                html+=' </td>';
                html+=' <td>';
                html+='     '+ o.name;
                html+=' </td>';
                html+=' <td>';
                html+='     '+ (o.rolenames==null?"":o.rolenames);
                html+=' </td>';
                html+='</tr>';
            }
            $('.user-table tbody').append(html);
        }
    },
    showUserTable:function(callback){
        var self = this;
        var userRoleData = null;
        jQuery.ajax({
            url:'/lawinfo/admin/user/find',
            type:'GET',
            //async:false,
            success:function(data) {
                userRoleData = data;
            },
            error:function() {
                mainAlert('获取用户信息异常');
            }
        }).done(function(e){
            callback && callback(userRoleData);
        });
    },
    delUser:function() {
        var self = this;
        if (selectUserTreeNode&&selectUserTreeNode.type==1) {
            mainConfirm('您确认要删除吗？',jQuery.proxy(self.doDelUser,this));
        }else{
            mainAlert('请选择您要用户，不能删除机构');
            return false;
        }
    },
    doDelUser:function(){
        var self = this;
        jQuery.ajax({
            url:'/lawinfo/admin/user/remove',
            data:{id:selectUserTreeNode.id},
            type:'GET',
            success:function(data) {
                selectUserTreeNode=null;
                userTree = self.initOrgUserTree($.proxy(self.buildOrgUserTree,this));
                mainAlert('删除成功');
            },
            error:function() {
                mainAlert('删除用户异常');
            }
        }).done(function(){
            //self.showUserRoleTable($.proxy(self.buildUserTable,this));
        });
    }
}