/**
 * Created by wangrongtao on 15/10/28.
 */

var orgSelectedNode = null;
var orgTree = null;
var org = {
    init:function(){
        var self = this;
        self.initClickEvent();
        self.initOrgTree($.proxy(self.buildOrgTree,self));
    },
    saveOrgAlert:function(msg){
        jQuery('#orgform .error-label').text(msg);
        jQuery('#orgform .has-error').show();
    },
    initClickEvent:function(){
        var self = this;
        jQuery('.btn-add-org').unbind('click');
        jQuery('.btn-update-org').unbind('click');
        jQuery('.btn-save-org').unbind('click');
        jQuery('.btn-rm-org').unbind('click');
        jQuery('.btn-add-org').on('click', function (e) {
            if(orgSelectedNode==null||orgSelectedNode.id==0){
                mainAlert('请先选择上级机构,不能添加一级机构');
                return false;
            }
            jQuery('#orgform  #orgid').val(0);
            jQuery('#orgform  #orgname').val('');
            jQuery('#orgform  #orgtag').val('');
            jQuery('#orgModal').modal('show');
        });
        jQuery('.btn-update-org').on('click', function (e) {
            if(orgSelectedNode==null||orgSelectedNode.id==0||orgSelectedNode.parentorgid==0){
                mainAlert('请先选择要更新的机构,不能更新一级机构');
                return false;
            }
            self.showupdateform();
        });
        jQuery('.btn-save-org').on('click',function(e){
            var orgid = jQuery('#orgform  #orgid').val();
            var orgname = jQuery('#orgform  #orgname').val();
            var orgtag = jQuery('#orgform  #orgtag').val();
            if (orgSelectedNode==null||orgSelectedNode.id==null) {
                self.saveOrgAlert('请先选择上级机构');
                return false;
            }
            if (orgname!='') {
                jQuery.ajax({
                    url:'/lawinfo/admin/org/add',
                    data:{id:orgid,name:orgname,orgtag:orgtag,parentorgid:orgSelectedNode.id},
                    type:'POST',
                    success:function(data) {
                        if (data==1) {
                            orgTree = self.initOrgTree(self.buildOrgTree);
                            $('#orgModal').modal('hide');
                        }else{
                            self.saveOrgAlert('保存异常');
                        }
                    },
                    error:function() {
                        self.saveOrgAlert('保存异常');
                    }
                });
            }else{
                self.saveOrgAlert('机构名称不能为空');
                return false;
            }
        });
        jQuery('.btn-rm-org').on('click',$.proxy(this.delOrg,this));
    },
    showupdateform:function(){
        if (orgSelectedNode&&orgSelectedNode.id>0) {
            $.ajax({
                url:'/lawinfo/admin/org/findbyid',
                data:{id:orgSelectedNode.id},
                cache:false,
                success:function(data){
                    if (data&&data.id>0) {
                        $('#orgform #orgid').val(data.id);
                        jQuery('#orgform  #orgname').val(data.name);
                        jQuery('#orgform  #orgtag').val(data.orgtag?data.orgtag:'');
                        jQuery('#orgModal').modal('show');
                    }else{
                        mainAlert('查询机构数据异常');
                    }
                },
                error:function() {
                    mainAlert('保存异常');
                }
            });
        }else{
            mainAlert('查询机构数据异常');
        }
    },
    buildOrgTree : function(treedata) {
        var self = this;
        //var treedata = self.getOrgTreeData();
        if (treedata==null||treedata=='') {
            treedata = [
                {
                    text: '机构',
                    id:0,
                    parentid:-1,
                }
            ];
        }
        orgTree = $('#treeview-selectable').treeview({
            data: treedata,
            showTags: true,
            multiSelect: $('#chk-select-multi').is(':checked'),
            onNodeSelected: function(event, node) {
                orgSelectedNode=node;
            },
            onNodeUnselected: function (event, node) {
            }
        });
        orgTree.treeview('expandAll');
    },
    initOrgTree:function(callback) {
        var orgtree = null;
        jQuery.ajax({
            url:'/lawinfo/admin/org/findtree',
            type:'GET',
            //async:false,
            success:function(data) {
                orgtree = data;
            },
            error:function() {
                mainAlert('获取机构信息异常');
            }
        }).done(function(){
            callback && callback(orgtree);
        });
    },
    delOrg:function() {
        var self = this;
        if (orgSelectedNode==null||orgSelectedNode.id<=0||orgSelectedNode.parentorgid==0) {
            mainAlert('请选择您要删除的机构,不能删除一级机构');
            return false;
        }
        mainConfirm('您确认要删除吗？',jQuery.proxy(self.doDelOrg,this));
    },
    doDelOrg:function(){
        var self = this;
        jQuery.ajax({
            url:'/lawinfo/admin/org/remove?id='+orgSelectedNode.id,
            type:'GET',
            //async:false,
            success:function(data) {
                orgSelectedNode=null;
                orgTree = self.initOrgTree(self.buildOrgTree);
                mainAlert('删除成功');
            },
            error:function() {
                mainAlert('删除机构异常');
            }
        });
    }
}