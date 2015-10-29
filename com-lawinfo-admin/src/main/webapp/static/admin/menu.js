/**
 * Created by wangrongtao on 15/10/28.
 */

var menuSelectedNode = null;
var menuTree = null;
var menu = {
    saveMenuAlert:function(msg){
        jQuery('#menuform .error-label').text(msg);
        jQuery('#menuform .has-error').show();
    },
    init:function(){
        var self = this;
        menuTree = self.initMenuTree($.proxy(self.buildMenuTree,this));
        jQuery('#menu .btn-add').on('click', function (e) {
            if(menuSelectedNode==null){
                mainAlert('请先选择父菜单');
                return false;
            }
            jQuery('#menuModal').modal('show');
        });
        jQuery('#menu .btn-rm').on('click',$.proxy(this.delMenu,this));
        jQuery('#menuModal .btn-save').on('click',function(e){
            if (menuSelectedNode==null||menuSelectedNode.id==null) {
                self.saveMenuAlert('请先选择父菜单');
                return false;
            }
            var menuname = jQuery('#menuform  #menuname').val();
            var menuurl = jQuery('#menuform  #menuurl').val();
            if (!menuurl) {
                self.saveMenuAlert('菜单url不能为空');
                return false;
            }
            if (!menuname) {
                self.saveMenuAlert('菜单名称不能为空');
                return false;
            }
            jQuery.ajax({
                url:'/lawinfo/admin/menu/add',
                data:{name:menuname,url:menuurl,parentmenuid:menuSelectedNode.id},
                type:'POST',
                success:function(data) {
                    if (data==1) {
                        menuTree = self.initMenuTree(self.buildMenuTree);
                        $('#menuModal').modal('hide');
                    }else{
                        self.savemenuAlert('保存异常');
                    }
                },
                error:function() {
                    self.savemenuAlert('保存异常');
                }
            });
        });
    },
    buildMenuTree : function(treedata) {
        var self = this;
        if (treedata==null||treedata=='') {
            treedata = [
                {
                    text: '菜单管理',
                    id:0,
                    parentid:-1
                }
            ];
        }
        return $('#treeview-menu').treeview({
            data: treedata,
            onNodeSelected: function(event, node) {
                menuSelectedNode=node;
            },
            onNodeUnselected: function (event, node) {
            }
        });
    },
    initMenuTree:function(callback) {
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
    delMenu:function() {
        var self = this;
        if (menuSelectedNode==null||menuSelectedNode.id<=0) {
            self.mainAlert('请选择您要删除的菜单');
            return false;
        }
        mainConfirm('您确认要删除吗？',jQuery.proxy(self.doDelMenu,this));
    },
    doDelMenu:function(){
        var self = this;
        jQuery.ajax({
            url:'/lawinfo/admin/menu/remove?id='+menuSelectedNode.id,
            type:'GET',
            success:function(data) {
                menuSelectedNode=null;
                menuTree = self.initMenuTree($.proxy(self.buildMenuTree,this));
                mainAlert('删除成功');
            },
            error:function() {
                mainAlert('删除菜单异常');
            }
        });
    }
}