/**
 * Created by wangrongtao on 15/10/28.
 */
var selectActionId = null;
var action = {

    saveActionAlert:function(msg){
        jQuery('#actionform .error-label').text(msg);
        jQuery('#actionform .has-error').show();
    },
    initClickEvent:function(){
        var self = this;
        jQuery('#action').unbind('click');
        jQuery('#action .action-row').unbind('click');
        jQuery('#action .btn-add').unbind('click');
        jQuery('#action .btn-action-update').unbind('click');
        jQuery('#actionModal .btn-save').unbind('click');
        jQuery('#action .btn-rm').unbind('click');
        jQuery('#action').on('click','.action-row', $.proxy(self.registActionTableRowEvent,this));
        jQuery('#action .btn-add').on('click', function (e) {
            self.initformdata();
            jQuery('#actionModal').modal('show');
        });
        jQuery('#action .btn-action-update').on('click', function (e) {
            if (selectActionId==null||selectActionId<=0) {
                mainAlert('请选择您要更新的动作');
                return false;
            }
            self.showupdateform();
        });
        jQuery('#actionModal .btn-save').on('click',function(e){
            var actionname = jQuery('#actionform  #actionname').val();
            var key = jQuery('#actionform  #actionkey').val();
            var tag = jQuery('#actionform  #actiontag').val();
            var id = jQuery('#actionform  #actionrowid').val();
            if (!actionname) {
                self.saveActionAlert('动作名称不能为空');
                return false;
            }
            /*if (!key) {
             self.saveActionAlert('动作key不能为空');
             return false;
             }*/
            if (!tag) {
                self.saveActionAlert('动作标签不能为空');
                return false;
            }
            jQuery.ajax({
                url:'/lawinfo/admin/action/add',
                data:{id:id,name:actionname,actionkey:key,tag:tag},
                type:'POST',
                cache:false,
                success:function(data) {
                    if (data==1) {
                        self.showActionTable($.proxy(self.buildActionTable,this));
                        $('#actionModal').modal('hide');
                    }else{
                        self.saveActionAlert('保存异常');
                    }
                },
                error:function() {
                    self.saveActionAlert('保存异常');
                }
            });
            selectActionId=0;
        });
        jQuery('#action .btn-rm').on('click',$.proxy(this.delAction,this));
    },
    initformdata:function(){
        jQuery('#actionform  #actionrowid').val(0);
        jQuery('#actionform  #actionname').val('');
        jQuery('#actionform  #actionkey').val('');
        jQuery('#actionform  #actiontag').val('');
    },
    setFormData:function(data){
        if (data&&data.id>0) {
            jQuery('#actionform  #actionrowid').val(data.id);
            jQuery('#actionform  #actionname').val(data.name);
            jQuery('#actionform  #actionkey').val(data.actionkey);
            jQuery('#actionform  #actiontag').val(data.tag);
        }
    },
    showupdateform:function(){
        var self = this;
        self.initformdata();
        if (selectActionId&&selectActionId>0) {
            jQuery.ajax({
                url:'/lawinfo/admin/action/findbyid',
                data:{id:selectActionId},
                success:function(data) {
                    if (data&&data.id>0) {
                        self.setFormData(data);
                    }else{
                        self.saveActionAlert('查询动作信息出错');
                    }
                },
                error:function() {
                    self.saveActionAlert('查询动作信息异常');
                }
            }).done(function(){
                jQuery('#actionModal').modal('show');
            });
        }
    },
    init:function(){
        var self = this;
        self.initClickEvent();
        self.showActionTable($.proxy(self.buildActionTable,this));
    },
    buildActionTable:function(actionData) {
        var self = this;
        if (actionData) {
            $('.action-table tbody').empty();
            var html = '';
            for(var i=0;i<actionData.length;i++) {
                var o = actionData[i];
                html+='<tr actionid="'+ o.id+'" class="action-row">';
                html+=' <td>';
                html+='     '+ (i+1);
                html+=' </td>';
                html+=' <td>';
                html+='     '+ o.name;
                html+=' </td>';
                html+=' <td>';
                html+='     '+ o.actionkey;
                html+=' </td>';
                html+=' <td>';
                html+='     '+ o.tag;
                html+=' </td>';
                html+='</tr>';
            }
            $('.action-table tbody').append(html);
        }
    },
    registActionTableRowEvent:function(e){
        selectActionId = jQuery(e.currentTarget).attr('actionid');
    },
    showActionTable:function(callback){
        var self = this;
        var actionData = null;
        jQuery.ajax({
            url:'/lawinfo/admin/action/find',
            type:'GET',
            cache:false,
            //async:false,
            success:function(data) {
                actionData = data;
            },
            error:function() {
                mainAlert('获取动作信息异常');
            }
        }).done(function(e){
            callback && callback(actionData);
        });
    },
    delAction:function() {
        var self = this;
        if (selectActionId==null||selectActionId<=0) {
            mainAlert('请选择您要删除的动作');
            return false;
        }
        mainConfirm('您确认要删除吗？',jQuery.proxy(self.doDelAction,this));
    },
    doDelAction:function(){
        var self = this;
        jQuery.ajax({
            url:'/lawinfo/admin/action/remove/?id='+selectActionId,
            type:'GET',
            cache:false,
            success:function(data) {
                selectActionId=null;
                mainAlert('删除成功');
            },
            error:function() {
                mainAlert('删除动作异常');
            }
        }).done(function(){
            self.showActionTable($.proxy(self.buildActionTable,this));
        });
    }
}