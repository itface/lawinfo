/**
 * Created by wangrongtao on 15/10/28.
 */
var organizationSelectedNode = null;
//var organizationTree = null;
var organization = {
    init:function(){
        var self = this;
        self.initOrgTree();
        self.initClickEvent();
    },
    saveOrgAlert:function(msg){
        jQuery('#orgform .error-label').text(msg);
        jQuery('#orgform .has-error').show();
    },
    initClickEvent:function(){
        var self = this;
        jQuery('.btn-add-org').unbind('click');
        jQuery('.btn-save-org').unbind('click');
        jQuery('.btn-rm-org').unbind('click');
        jQuery('.btn-add-org').on('click', function (e) {
            jQuery('#orgform  #orgid').val(0);
            jQuery('#orgform  #orgname').val('');
            jQuery('#orgform  #orgtag').val('');
            jQuery('#orgModal').modal('show');
        });
        jQuery('.btn-save-org').on('click',function(e){
            var orgid = jQuery('#orgform  #orgid').val();
            var orgname = jQuery('#orgform  #orgname').val();
            var orgtag = jQuery('#orgform  #orgtag').val();
            if (organizationSelectedNode==null||organizationSelectedNode.id==null) {
                self.saveOrgAlert('请先选择上级机构');
                return false;
            }
            if (orgname!='') {
                jQuery.ajax({
                    url:'/lawinfo/admin/org/add',
                    data:{id:orgid,name:orgname,orgtag:orgtag,parentorgid:organizationSelectedNode.id},
                    type:'POST',
                    success:function(data) {
                        if (data==1) {
                            orgTree = self.initOrgTree(self.buildOrgTree);
                            $('#orgModal').modal('hide');
                        }else{
                            self.saveOrgAlert('保存异常');
                        }
                        organizationSelectedNode=null;
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
        if (organizationSelectedNode&&organizationSelectedNode.id>0) {
            $.ajax({
                url:'/lawinfo/admin/org/findbyid',
                data:{id:organizationSelectedNode.id},
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
    initOrgTree:function() {
        var self = this;
        $('#org-eu-tree').tree({
            url:'/lawinfo/admin/org/findeutree',
            cache:false,
            onDblClick: function(node){
                self.showupdateform();
            },
            onSelect:function(node){
                organizationSelectedNode = node;
            }
        });
    },
    delOrg:function() {
        var self = this;
        if (organizationSelectedNode==null||organizationSelectedNode.id<=0||organizationSelectedNode.parentid==0) {
            mainAlert('请选择您要删除的机构,不能删除一级机构');
            return false;
        }
        mainConfirm('您确认要删除吗？',jQuery.proxy(self.doDelOrg,this));
    },
    doDelOrg:function(){
        var self = this;
        jQuery.ajax({
            url:'/lawinfo/admin/org/remove?id='+organizationSelectedNode.id,
            type:'GET',
            //async:false,
            success:function(data) {
                organizationSelectedNode=null;
                self.initOrgTree();
                mainAlert('删除成功');
            },
            error:function() {
                mainAlert('删除机构异常');
            }
        });
    }
}