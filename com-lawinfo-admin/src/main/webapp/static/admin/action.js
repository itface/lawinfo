/**
 * Created by wangrongtao on 15/10/28.
 */
var selectActionId = null;
var action = {

    saveActionAlert:function(msg){
        jQuery('#actionform .error-label').text(msg);
        jQuery('#actionform .has-error').show();
    },
    init:function(){
        var self = this;
        jQuery('#action').on('click','.action-row', $.proxy(self.registActionTableRowEvent,this));
        jQuery('#action .btn-add').on('click', function (e) {
            jQuery('#actionModal').modal('show');
        });
        jQuery('#actionModal .btn-save').on('click',function(e){
            var actionname = jQuery('#actionform  #actionname').val();
            var key = jQuery('#actionform  #actionkey').val();
            var tag = jQuery('#actionform  #actiontag').val();
            if (!actionname) {
                self.saveActionAlert('动作名称不能为空');
                return false;
            }
            if (!key) {
                self.saveActionAlert('动作key不能为空');
                return false;
            }
            if (!tag) {
                self.saveActionAlert('动作标签不能为空');
                return false;
            }
            jQuery.ajax({
                url:'/lawinfo/admin/action/add',
                data:{name:actionname,actionkey:key,tag:tag},
                type:'POST',
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
        });
        jQuery('#action .btn-rm').on('click',$.proxy(this.delAction,this));
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