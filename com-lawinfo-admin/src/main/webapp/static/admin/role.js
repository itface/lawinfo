/**
 * Created by wangrongtao on 15/10/28.
 */
var selectRoleId = null;
var roleMenuTree = null;
var roleActionTree = null;
var roleMenuSelectedNode=null;
var roleActionSelectedNode=null;
var role = {

    saveRoleAlert:function(msg){
        jQuery('#roleform .error-label').text(msg);
        jQuery('#roleform .has-error').show();
    },
    initClickEvent:function(){
        jQuery('#role').unbind('click');
        jQuery('#role .role-row').unbind('click');
        jQuery('#role .btn-add').unbind('click');
        jQuery('#roleModal .btn-save').unbind('click');
        jQuery('#role .btn-rm').unbind('click');
    },
    init:function(){
        var self = this;
        self.initClickEvent();
        jQuery('#role').on('click','.role-row', $.proxy(self.registRoleTableRowEvent,this));
        jQuery('#role .btn-add').on('click', function (e) {
            jQuery('#roleModal').modal('show');
        });
        $('#roleModal').on('show.bs.modal', function () {
            self.initRoleMenuTree($.proxy(self.buildRoleMenuTree,self));
            self.initRoleActionTree($.proxy(self.buildRoleActionTree,self));
        });
        jQuery('#roleModal .btn-save').on('click',function(e){
            var rolename = jQuery('#roleform  #rolename').val();
            var roleactionids = jQuery('#roleform  #roleactionids').val();
            var rolemenuids = jQuery('#roleform  #rolemenuids').val();
            if (!rolename) {
                self.saveRoleAlert('角色名称不能为空');
                return false;
            }
            jQuery.ajax({
                url:'/lawinfo/admin/role/add',
                data:{rolename:rolename,menuids:rolemenuids,actionids:roleactionids},
                type:'POST',
                success:function(data) {
                    if (data==1) {
                        self.showRoleTable($.proxy(self.buildRoleTable,this));
                        $('#roleModal').modal('hide');
                    }else{
                        self.saveRoleAlert('保存异常');
                    }
                },
                error:function() {
                    self.saveRoleAlert('保存异常');
                }
            });
        });
        jQuery('#role .btn-rm').on('click',$.proxy(this.delRole,this));
        self.showRoleTable($.proxy(self.buildRoleTable,this));
    },
    findCheckedRoleMenuNodes : function(nodeTexts) {
        return roleMenuTree.treeview('search', [ nodeTexts, { ignoreCase: false, exactMatch: false } ]);
    },
    buildRoleMenuTree:function(menuTreeData){
        var self = this;
        if (!menuTreeData) {
            menuTreeData = [
                {
                    text: '菜单管理',
                    id:0,
                    parentid:-1,
                    showCheckbox:false
                }
            ];
        }
        roleMenuTree = $('#treeview-role-menu').treeview({
            data: menuTreeData,
            showIcon: false,
            showCheckbox: true,
            onNodeChecked: function(event, node) {
                self.setRoleMenuIds();
            },
            onNodeUnchecked: function (event, node) {
                self.setRoleMenuIds();
            },
            onNodeSelected: function(event, node) {
                roleMenuSelectedNode=node;
                /*roleMenuTree.treeview('checkNode',node);
                var levels = $('#select-expand-all-levels').val();
                roleMenuTree.treeview('collapseAll',{silent:false});
                roleMenuTree.treeview('expandAll');*/
                var checkednode = self.findCheckedRoleMenuNodess(node);
                roleMenuTree.treeview('toggleNodeChecked',checkednode);
            },
            onNodeUnselected: function (event, node) {
                /*var checkednode = self.findCheckedRoleMenuNodess(node);
                roleMenuTree.treeview('toggleNodeChecked',checkednode);*/
            }
        });
        roleMenuTree.treeview('expandAll');
    },
    setRoleMenuIds:function(){
        var self = this;
        var checkedNode = self.findSelectedRoleMenuNodess();
        if (checkedNode) {
            var menuids = '';
            for (var i=0;i<checkedNode.length;i++) {
                var nodeid = checkedNode[i].id;
                if (nodeid!=0) {
                    menuids+=checkedNode[i].id;
                    menuids+=',';
                }
            }
            menuids = menuids.substring(0, menuids.length - 1);
            $('#rolemenuids').val(menuids);
        }
    },
    findParentRoleMenuNodes:function(node){
        var self = this;
       var parentnode = roleMenuTree.treeview('getParent',node);
        if (!parentnode) {
            return node;
        }
        self.findParentRoleMenuNodes(parentnode);
    },
    findSelectedRoleMenuNodess:function(){
        return roleMenuTree.treeview('getChecked');
    },
    findCheckedRoleMenuNodess : function(node) {
        return roleMenuTree.treeview('search', [ node.text, { ignoreCase: false, exactMatch: false } ]);
    },
    initRoleMenuTree:function(callback){
        var menutree = null;
        jQuery.ajax({
            url:'/lawinfo/admin/menu/findtree',
            type:'GET',
            success:function(data) {
                menutree = data;
            },
            error:function() {
                mainAlert('获取菜单信息异常');
            }
        }).done(function(){
            callback && callback(menutree);
        });
    },
    buildRoleTable:function(roleData) {
        var self = this;
        if (roleData) {
            $('.role-table tbody').empty();
            var html = '';
            for(var i=0;i<roleData.length;i++) {
                var o = roleData[i];
                html+='<tr roleid="'+ o.id+'" class="role-row">';
                html+=' <td>';
                html+='     '+ (i+1);
                html+=' </td>';
                html+=' <td>';
                html+='     '+ o.name;
                html+=' </td>';
                html+=' <td>';
                html+='     '+ (o.menuname!=null?o.menuname:'&nbsp;');
                html+=' </td>';
                html+=' <td>';
                html+='     '+ (o.actionname!=null?o.actionname:'&nbsp;');
                html+=' </td>';
                html+='</tr>';
            }
            $('.role-table tbody').append(html);
        }
    },
    registRoleTableRowEvent:function(e){
        selectRoleId = jQuery(e.currentTarget).attr('roleid');
    },
    showRoleTable:function(callback){
        var self = this;
        var roleData = null;
        jQuery.ajax({
            url:'/lawinfo/admin/role/find',
            type:'GET',
            //async:false,
            success:function(data) {
                roleData = data;
            },
            error:function() {
                mainAlert('获取角色信息异常');
            }
        }).done(function(e){
            callback && callback(roleData);
        });
    },
    delRole:function() {
        var self = this;
        if (selectRoleId==null||selectRoleId<=0) {
            mainAlert('请选择您要删除的角色');
            return false;
        }
        mainConfirm('您确认要删除吗？',jQuery.proxy(self.doDelRole,this));
    },
    doDelRole:function(){
        var self = this;
        jQuery.ajax({
            url:'/lawinfo/admin/role/remove/?id='+selectRoleId,
            type:'GET',
            success:function(data) {
                selectRoleId=null;
                mainAlert('删除成功');
            },
            error:function() {
                mainAlert('删除角色异常');
            }
        }).done(function(){
            self.showRoleTable($.proxy(self.buildRoleTable,this));
        });
    },
    initRoleActionTree:function(callback){
        var treedata = null;
        jQuery.ajax({
            url:'/lawinfo/admin/action/findtree',
            type:'GET',
            success:function(data) {
                treedata = data;
            },
            error:function() {
                mainAlert('获取权限信息异常');
            }
        }).done(function(){
            callback && callback(treedata);
        });
    },
    buildRoleActionTree:function(actionTreeData){
        var self = this;
        if (!actionTreeData) {
            actionTreeData = [
                {
                    text: '权限管理',
                    id:0,
                    parentid:-1,
                    showCheckbox:false
                }
            ];
        }
        roleActionTree = $('#treeview-role-action').treeview({
            data: actionTreeData,
            showIcon: false,
            showCheckbox: true,
            onNodeChecked: function(event, node) {
                self.setRoleActionIds();
            },
            onNodeUnchecked: function (event, node) {
                self.setRoleActionIds();
            },
            onNodeSelected: function(event, node) {
                roleActionSelectedNode=node;
                /*roleMenuTree.treeview('checkNode',node);
                 var levels = $('#select-expand-all-levels').val();
                 roleMenuTree.treeview('collapseAll',{silent:false});
                 roleMenuTree.treeview('expandAll');*/
                var checkednode = self.findCheckedRoleActionNodess(node);
                roleActionTree.treeview('toggleNodeChecked',checkednode);
            },
            onNodeUnselected: function (event, node) {
                /*var checkednode = self.findCheckedRoleActionNodess(node);
                roleActionTree.treeview('toggleNodeChecked',checkednode);*/
            }
        });
        roleActionTree.treeview('expandAll');
    },
    setRoleActionIds:function(){
        var self = this;
        var checkedNode = self.findSelectedRoleActionNodes();
        if (checkedNode) {
            var actionids = '';
            for (var i=0;i<checkedNode.length;i++) {
                var nodeid = checkedNode[i].id;
                if (nodeid!=0) {
                    actionids+=checkedNode[i].id;
                    actionids+=',';
                }
            }
            actionids = actionids.substring(0, actionids.length - 1);
            $('#roleactionids').val(actionids);
        }
    },
    findParentRoleActionNodes:function(node){
        var self = this;
        var parentnode = roleActionTree.treeview('getParent',node);
        if (!parentnode) {
            return node;
        }
        self.findParentRoleActionNodes(parentnode);
    },
    findSelectedRoleActionNodes:function(){
        return roleActionTree.treeview('getChecked');
    },
    findCheckedRoleActionNodess : function(node) {
        return roleActionTree.treeview('search', [ node.text, { ignoreCase: false, exactMatch: false } ]);
    }
}