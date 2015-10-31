/**
 * Created by wangrongtao on 15/10/28.
 */
var selectUserId = null;
var userRoleTree = null;
var userRoleSelectedNode=null;
var user = {

    saveUserAlert:function(msg){
        jQuery('#userform .error-label').text(msg);
        jQuery('#userform .has-error').show();
    },
    init:function(){
        var self = this;
        jQuery('#user').on('click','.user-row', $.proxy(self.registUserTableRowEvent,this));
        jQuery('#user .btn-add').on('click', function (e) {
            jQuery('#userModal').modal('show');
        });
        $('#userModal').on('show.bs.modal', function () {
            self.initUserRoleTree($.proxy(self.buildUserRoleTree,self));
        });
        jQuery('#userModal .btn-save').on('click',function(e){
            var username = jQuery('#userform  #username').val();
            var userid = jQuery('#userform  #userid').val();
            var roleids = jQuery('#userform  #userroleids').val();
            if (!username) {
                self.saveUserAlert('用户名称不能为空');
                return false;
            }
            if (!userid) {
                self.saveUserAlert('用户id不能为空');
                return false;
            }if (!roleids) {
                self.saveUserAlert('角色不能为空');
                return false;
            }
            jQuery.ajax({
                url:'/lawinfo/admin/user/add',
                data:{name:username,userid:userid,roleids:roleids},
                type:'POST',
                success:function(data) {
                    if (data==1) {
                        self.showUserTable($.proxy(self.buildUserTable,this));
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
        userRoleTree = $('#treeview-user-menu').treeview({
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
    registUserTableRowEvent:function(e){
        selectUserId = jQuery(e.currentTarget).attr('userrowid');
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
        if (selectUserId==null||selectUserId<=0) {
            mainAlert('请选择您要用户');
            return false;
        }
        mainConfirm('您确认要删除吗？',jQuery.proxy(self.doDelUser,this));
    },
    doDelUser:function(){
        var self = this;
        jQuery.ajax({
            url:'/lawinfo/admin/user/remove/?userid='+selectUserId,
            type:'GET',
            success:function(data) {
                selectUserId=null;
                mainAlert('删除成功');
            },
            error:function() {
                mainAlert('删除用户异常');
            }
        }).done(function(){
            self.showUserRoleTable($.proxy(self.buildUserTable,this));
        });
    }
}