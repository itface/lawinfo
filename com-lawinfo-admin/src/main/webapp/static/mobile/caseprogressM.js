/**
 * Created by wangrongtao on 15/10/28.
 */
var caseProgressTreeSelectedNode = null;
var caseProgressTree = null;
var exeLawyerTreeSelectedNode = null;
var exeLawyerTree = null;
var caseprogress = {
    initEditPrivil:function(){
        var self = this;
        self.initExeLawyerTree($.proxy(self.buildExeLawyerTree,self));
        jQuery('#caseinfo-progress-ss-end-form .btn').unbind();
        jQuery('#caseinfo-progress-ss-end-form .btn').on('click',function(e){
            var exeajbh = jQuery('#caseinfo-progress-ss-end-form  #exeajbh').val();
            var exelawyers = jQuery('#caseinfo-progress-ss-end-form  #exelawyers').val();
            var exelawyerids = jQuery('#caseinfo-progress-ss-end-form  #exelawyerids').val();
            if (!exeajbh) {
                self.saveProgressAlert('执行案件号不能为空');
                return false;
            }
            if (!exelawyers) {
                self.saveProgressAlert('执行律师不能为空');
                return false;
            }
            if (!exelawyerids) {
                self.saveProgressAlert('执行律师不能为空');
                return false;
            }
            if (!selectCaseinfoId) {
                self.saveProgressAlert('无效的案件号，请重新选择');
                return false;
            }
            jQuery.ajax({
                url:'/lawinfo/front/caseinfo/exelawyer/add',
                data:{id:selectCaseinfoId,exeajbh:exeajbh,exelawyers:exelawyers,exelawyerids:exelawyerids},
                type:'POST',
                cache:false,
                success:function(data) {
                    self.saveProgressAlert('保存成功');
                    self.afterSaveProgress();
                },
                error:function() {
                    self.saveProgressAlert('保存异常');
                }
            });
        });
        jQuery('#progress-execute-end-tab').unbind();
        jQuery('#progress-execute-end-tab').on('click',function(e){
            if (!confirm('您确定要将当前案件归档吗？')){
                return false;
            }
            jQuery.ajax({
                url:'/lawinfo/front/caseinfo/setcasefinish',
                data:{casinfoid:selectCaseinfoId},
                type:'POST',
                cache:false,
                success:function(data) {
                    self.afterSaveProgress();
                },
                error:function() {
                    alert('设置异常');
                }
            });
        });
    },
    init:function(){
        var self = this;
        jQuery('.caseinfo-progress-list').empty();
        jQuery('.add-form').empty();
        jQuery('#caseinfo-progress-ss-end-form  #exeajbh').val('');
        jQuery('#caseinfo-progress-ss-end-form  #exelawyers').val('');
        jQuery('#caseinfo-progress-ss-end-form  #exelawyerids').val('');
        self.clearSaveProgressAlert();
        if (selectCaseinfoId&&selectCaseinfoId>0) {
            self.findCaseProgressViews($.proxy(self.initCaseProgressView,self));
        }
        self.initEditPrivil();
    },
    initExeLawyerTree:function(callback) {
        var self = this;
        var treedata = null;
        jQuery.ajax({
            url:'/lawinfo/front/caseinfo/user/findlawyertree',
            type:'GET',
            cache:false,
            //async:false,
            success:function(data) {
                treedata = data;
            },
            error:function() {
                alert('获取律师信息异常');
            }
        }).done(function(){
            callback && callback(treedata);
        });
    },
    buildExeLawyerTree : function(treedata) {
        var self = this;
        exeLawyerTree = $('#caseinfo-exelawyer-tree').treeview({
            data: treedata,
            showCheckbox: true,
            onNodeSelected: function (event, node) {
                exeLawyerTreeSelectedNode = node;
            },
            onNodeUnselected: function (event, node) {
            },
            onNodeChecked: function (event, node) {
                self.setExeLawyer();
            },
            onNodeUnchecked: function (event, node) {
                self.setExeLawyer();
            }
        });
        exeLawyerTree.treeview('collapseAll');
    },
    findSelectedExeLawyerNodess:function(){
        return exeLawyerTree.treeview('getChecked');
    },
    setExeLawyer:function(){
        var self = this;
        var checkedNode = self.findSelectedExeLawyerNodess();
        if (checkedNode) {
            var userids = '';
            var users = '';
            for (var i=0;i<checkedNode.length;i++) {
                var nodetype = checkedNode[i].type;
                if (nodetype==1) {
                    userids+=checkedNode[i].userid;
                    userids+=',';
                    users+=checkedNode[i].text+"["+checkedNode[i].userid+"]";
                    users+=',';
                }
            }
            userids = userids.substring(0, userids.length - 1);
            users = users.substring(0, users.length - 1);
            $('#caseinfo-progress-ss-end-form #exelawyers').val(users);
            $('#caseinfo-progress-ss-end-form #exelawyerids').val(userids);
        }
    },
    showCaseProgressComments:function(node,caseInfo,formid){
        var self = this;
        var myform = jQuery('#'+formid);
        jQuery('.caseinfo-progress-list',myform).empty();
        jQuery('.add-form',myform).empty();
        var listHtml = '';
        var nodeid = node.id;
        var preprice = caseInfo.preprice;
        var sufprice = caseInfo.sufprice;
        var ystj = caseInfo.ystj;
        var estj = caseInfo.estj;
        var sfss = caseInfo.sfss;
        var ssajbh = caseInfo.ssajbh;
        //初期及后期律师费,一审及二审调解
        if (nodeid==400) {
            if (preprice&&preprice>0) {
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4"><label>初期律师费</label></div>';
                listHtml += '<div class="col-xs-4"><input id="commonfiled" class="form-control" type="text"  readonly value="'+preprice+'"/></div>';
                listHtml += '</div>';
                jQuery('.caseinfo-progress-list',myform).append(listHtml);
            }else{
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4"><label>初期律师费</label></div>';
                listHtml += '<div class="col-xs-4"><input id="commonfiled" class="form-control" type="text"/></div>';
                listHtml += '<div class="col-xs-2"><button type="button" class="btn btn-default save">添加</button></div>';
                listHtml += '</div>';
                jQuery('.add-form',myform).append(listHtml);
            }
        }else if (nodeid==4400) {
            if (sufprice&&sufprice>0) {
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4"><label>后期期律师费</label></div>';
                listHtml += '<div class="col-xs-4"><input id="commonfiled" class="form-control" type="text"  readonly value="'+sufprice+'"/></div>';
                listHtml += '</div>';
                jQuery('.caseinfo-progress-list',myform).append(listHtml);
            }else{
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4"><label>后期律师费</label></div>';
                listHtml += '<div class="col-xs-4"><input id="commonfiled" class="form-control" type="text"/></div>';
                listHtml += '<div class="col-xs-2"><button type="button" class="btn btn-default save">添加</button></div>';
                listHtml += '</div>';
                jQuery('.add-form',myform).append(listHtml);
            }
        }else if (nodeid==1600) {
            if (ystj&&ystj>0) {
                var selected1 = ystj==1?"selected":"";
                var selected2 = ystj==2?"selected":"";
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4"><label>一审是否调解</label></div>';
                listHtml += '<div class="col-xs-4">' +
                                '<select id="commonfiled" class="form-control"  disabled value="'+ystj+'">' +
                                    '<option value="1" '+selected1+'>未调解</option>' +
                                    '<option value="2" '+selected2+'>已调解</option>' +
                                '</select>' +
                            '</div>';
                listHtml += '</div>';
                jQuery('.caseinfo-progress-list',myform).append(listHtml);
            }else{
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4"><label>一审是否调解</label></div>';
                listHtml += '<div class="col-xs-4">' +
                    '<select id="commonfiled" class="form-control">' +
                    '<option value="1" >未调解</option>' +
                    '<option value="2" >已调解</option>' +
                    '</select>' +
                    '</div>';
                listHtml += '<div class="col-xs-2"><button type="button" class="btn btn-default save">添加</button></div>';
                listHtml += '</div>';
                jQuery('.add-form',myform).append(listHtml);
            }
        }else if (nodeid==3100) {
            if (estj&&estj>0) {
                var selected1 = estj==1?"selected":"";
                var selected2 = estj==2?"selected":"";
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4"><label>二审是否调解</label></div>';
                listHtml += '<div class="col-xs-4">' +
                    '<select id="commonfiled" class="form-control"  disabled value="'+estj+'">' +
                    '<option value="1" '+selected1+'>未调解</option>' +
                    '<option value="2" '+selected2+'>已调解</option>' +
                    '</select>' +
                    '</div>';
                listHtml += '</div>';
                jQuery('.caseinfo-progress-list',myform).append(listHtml);
            }else{
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4">二审是否调解</div>';
                listHtml += '<div class="col-xs-4">' +
                    '<select id="commonfiled" class="form-control">' +
                    '<option value="1">未调解</option>' +
                    '<option value="2">已调解</option>' +
                    '</select>' +
                    '</div>';
                listHtml += '<div class="col-xs-2"><button type="button" class="btn btn-default save">添加</button></div>';
                listHtml += '</div>';
                jQuery('.add-form',myform).append(listHtml);
            }
        }else if(nodeid==2100){
            if (sfss&&sfss>0) {
                var selected1 = sfss==1?"selected":"";
                var selected2 = sfss==2?"selected":"";
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4"><label>是否上诉</label></div>';
                listHtml += '<div class="col-xs-4">' +
                    '<select id="commonfiled" class="form-control"  disabled value="'+sfss+'">' +
                    '<option value="1" '+selected1+'>不上诉</option>' +
                    '<option value="2" '+selected2+'>上诉</option>' +
                    '</select>' +
                    '</div>';
                listHtml += '</div>';
                jQuery('.caseinfo-progress-list',myform).append(listHtml);
            }else{
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4">是否上诉</div>';
                listHtml += '<div class="col-xs-4">' +
                    '<select id="commonfiled" class="form-control">' +
                    '<option value="1">不上诉</option>' +
                    '<option value="2">上诉</option>' +
                    '</select>' +
                    '</div>';
                listHtml += '<div class="col-xs-2"><button type="button" class="btn btn-default save">添加</button></div>';
                listHtml += '</div>';
                jQuery('.add-form',myform).append(listHtml);
            }
        }else if(nodeid==701){
            if (ssajbh) {
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4"><label>诉讼案件编号</label></div>';
                listHtml += '<div class="col-xs-4"><input id="commonfiled" class="form-control" type="text"  readonly value="'+ssajbh+'"/></div>';
                listHtml += '</div>';
                jQuery('.caseinfo-progress-list',myform).append(listHtml);
            }else{
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4"><label>诉讼案件编号</label></div>';
                listHtml += '<div class="col-xs-4"><input id="commonfiled" class="form-control" type="text"/></div>';
                listHtml += '<div class="col-xs-2"><button type="button" class="btn btn-default save">添加</button></div>';
                listHtml += '</div>';
                jQuery('.add-form',myform).append(listHtml);
            }
        }/*else if(nodeid==3401||nodeid==4500){
            if (caseInfo.exelawyers) {
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-2"><label>执行案号</label></div>';
                listHtml += '<div class="col-xs-6">'+caseInfo.exeajbh+'</div>';
                listHtml += '</div>';
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-2"><label>执行律师</label></div>';
                listHtml += '<div class="col-xs-6">'+caseInfo.exelawyers+'</div>';
                listHtml += '</div>';
                jQuery('.add-form',myform).append(listHtml);
            }else{
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-2"><label>执行案号</label></div>';
                listHtml += '<div class="col-xs-6"><input id="exeajbh" class="form-control" type="text"/></div>';
                listHtml += '</div>';
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-2"><label>执行律师</label></div>';
                listHtml += '<div class="col-xs-6"><input id="exelawyers" class="form-control" type="text" readonly/><input id="exelawyerids" class="form-control" type="text" style="display: none"/> <div id="caseinfo-exelawyer-tree"></div>';
                listHtml += '</div>';
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-2"><button type="button" class="btn btn-default save">添加</button></div>';
                listHtml += '</div>';
                jQuery('.add-form',myform).append(listHtml);
                self.initExeLawyerTree($.proxy(self.buildExeLawyerTree,self));
            }
        }*/else{
            var comments = node.caseProgressCommentList;
            if (comments!=null) {
                listHtml += '<div class="row form-group">'+
                                '<div class="col-xs-4">&nbsp;</div>'+
                                '<div class="col-xs-3">内容</div>'+
                                '<div class="col-xs-3">下一步计划</div>'+
                                '</div>';
                for (var i=0;i<comments.length;i++) {
                    var comment = comments[i];
                    var index = i+1;
                    var str = index + "、" +comment.createtimestr;
                    listHtml += '<div class="row form-group">';
                    listHtml += '<div class="col-xs-4">'+str+'</div>';
                    listHtml += '<div class="col-xs-3"><textarea  class="form-control" rows="3" readonly>'+comment.comment+'</textarea></div>';
                    listHtml += '<div class="col-xs-3"><textarea  class="form-control" rows="3" readonly>'+comment.nexttask+'</textarea></div>';
                    /*listHtml += '<div class="col-xs-2">'+comment.optuserid+'</div>';
                     listHtml += '<div class="col-xs-3">'+comment.createtimestr+'</div>';*/
                    listHtml += '</div>';
                }
                listHtml += '<div style="height: 1px;border-bottom: 1px solid #ddd;margin-bottom: 20px"></div>';
            }
            var addHtml = '<div class="col-xs-5">'+
                                '<textarea id="comment" class="form-control" rows="3" placeholder="工作内容"></textarea>'+
                            '</div>'+
                            '<div class="col-xs-5">'+
                                '<textarea  id="nexttask" class="form-control" rows="3" placeholder="下一步计划"></textarea>'+
                            '</div>'+
                            '<div class="col-xs-2">'+
                                '<button type="button" class="btn btn-default save">添加</button>'+
                            '</div>';
            jQuery('.add-form',myform).append(addHtml);
            jQuery('.caseinfo-progress-list',myform).append(listHtml);
        }
        self.clearSaveProgressAlert();
        $('.save').on('click', $.proxy(self.saveProgressEvent,self));
    },
    saveProgressAlert:function(msg){
        jQuery('.error-label').empty();
        jQuery('.error-label').text(msg);
        jQuery('.has-error').show();
    },
    clearSaveProgressAlert : function(){
        jQuery('.error-label').empty();
        jQuery('.has-error').hide();
    },
    afterSaveProgress : function(){
        var self = this;
        self.init();
        jQuery('#comment').val('');
        jQuery('#nexttask').val('');
        jQuery('#progress-query-tab').click();
    },
    saveProgressEvent : function(e){
        var self = this;
        var myform = jQuery(e.target).parents('.progress-edit-form-class');
        if (caseProgressTreeSelectedNode!=null&&selectCaseinfoId!=null) {
            var nodeid = caseProgressTreeSelectedNode.id;
            var comment = null;
            var nexttask = null;
            //初期及后期律师费,一审及二审调解
            if (nodeid==400) {
                comment = jQuery('#commonfiled',myform).val();
                if (comment==null||comment.trim()=='') {
                    self.saveProgressAlert('初期律师费不能为空',myform);
                    return false;
                }else if (isNaN(comment)) {
                    self.saveProgressAlert('初期律师费必须是数字',myform);
                    return false;
                }
            }else if (nodeid==4400) {
                comment = jQuery('#commonfiled',myform).val();
                if (comment==null||comment.trim()=='') {
                    self.saveProgressAlert('后期律师费不能为空',myform);
                    return false;
                }else if (isNaN(comment)) {
                    self.saveProgressAlert('后期律师费必须是数字',myform);
                    return false;
                }
            }else if (nodeid==1600||nodeid==3100||nodeid==2100||nodeid==701) {
                comment = jQuery('#commonfiled',myform).val();
            }/*else if((nodeid==3401||nodeid==4500)){
                var exeajbh = jQuery('.add-form  #exeajbh').val();
                var exelawyers = jQuery('.add-form  #exelawyers').val();
                var exelawyerids = jQuery('.add-form  #exelawyerids').val();
                if (exeajbh==null||exeajbh.trim()=='') {
                    self.saveProgressAlert('执行案号不能为空',myform);
                    return false;
                }
                if (exelawyers==null||exelawyers.trim()=='') {
                    self.saveProgressAlert('执行律师不能为空',myform);
                    return false;
                }
                if (exelawyerids==null||exelawyerids.trim()=='') {
                    self.saveProgressAlert('执行律师不能为空',myform);
                    return false;
                }
                this.saveExecuteLawyers(exeajbh,exelawyers,exelawyerids,myform)
                return false;
            }*/else{
                comment = jQuery('#comment',myform).val();
                nexttask = jQuery('#nexttask',myform).val();
                if (comment==null||comment.trim()=='') {
                    self.saveProgressAlert('内容不能为空',myform);
                    return false;
                }
                if (nexttask==null||nexttask.trim()=='') {
                    self.saveProgressAlert('下一步计划不能为空',myform);
                    return false;
                }
            }
            jQuery.ajax({
                url:'/lawinfo/front/caseprogress/save',
                data:{caseinfoid:selectCaseinfoId,processnodeid:nodeid,comment:comment,nexttask:nexttask},
                type:'POST',
                cache:false,
                //async:false,
                success:function(data) {
                    self.saveProgressAlert('保存成功');
                    self.afterSaveProgress();
                },
                error:function() {
                    self.saveProgressAlert('保存案件进度信息异常',myform);
                }
            });
        }else{
            self.saveProgressAlert('保存异常，没有选择案件或者进度节点',myform);
            return false;
        }
    },
    saveExecuteLawyers:function(exeajbh,exelawyers,exelawyerids,myform){
        var self = this;
        jQuery.ajax({
            url:'/lawinfo/front/caseinfo/exelawyer/add',
            data:{id:selectCaseinfoId,exeajbh:exeajbh,exelawyers:exelawyers,exelawyerids:exelawyerids},
            type:'POST',
            cache:false,
            success:function(data) {
                self.saveProgressAlert('保存成功',myform);
                self.afterSaveProgress();
            },
            error:function() {
                self.saveProgressAlert('保存异常',myform);
            }
        });
    },
    initCaseProgressView : function(data){
        var self = this;
        if (data) {
            self.buildCaseProgressTable('progress-query',data.ssCaseProgressCommentList);
            self.buildCaseProgressTable('progress-execute-query',data.executeCaseProgressCommentList);
            if (data.ssCaseProgressTreeVoList) {
                self.buildCaseProgressTree(data.ssCaseProgressTreeVoList,data.caseInfo,'caseinfo-progress-tree','caseinfo-progress-form');
            }
            if (!data.ifsslawyer) {
                jQuery('#progress-progress-query-execute-tab').show();
                jQuery('#progress-edit-execute-tab').show();
                if (data.executeCaseProgressTreeVoList) {
                    self.buildCaseProgressTree(data.executeCaseProgressTreeVoList,data.caseInfo,'caseinfo-execute-progress-tree','caseinfo-execute-progress-form');
                }
            }else{
                jQuery('#progress-progress-query-execute-tab').hide();
                jQuery('#progress-edit-execute-tab').hide();
            }
            if (data.caseInfo) {
                var status = data.caseInfo.status;
                jQuery('#progress-ss-end-tab').show();
                jQuery('#progress-execute-end-tab').hide();
                jQuery('.status-progress').empty();
                if (status<100) {
                    jQuery('.status-progress').css('width','40%');
                    jQuery('.status-progress').append("当前案件处于诉讼中");
                }else if (status>=100&&status<9999) {
                    jQuery('#progress-ss-end-tab').hide();
                    if (!data.ifsslawyer) {
                        jQuery('#progress-execute-end-tab').show();
                    }else{
                        jQuery('#progress-execute-end-tab').hide();
                    }
                    jQuery('.status-progress').css('width','80%');
                    jQuery('.status-progress').append("当前案件处于执行中");
                }else if (status>=9999) {
                    jQuery('#progress-ss-end-tab').hide();
                    jQuery('#progress-execute-end-tab').hide();
                    jQuery('.status-progress').css('width','100%');
                    jQuery('.status-progress').append("当前案件已归档");
                }
                self.buildCaseinfoTable(data.caseInfo);
            }
        }
    },
    buildCommentVo:function(processnodeid,comment){
        var msg = "";
        if (processnodeid == 1600) {
            if (1==comment) {
                msg = "一审未调解";
            } else if (2==comment) {
                msg = "一审已调解";
            }
        }else if (processnodeid == 3100) {
            if (1==comment) {
                msg = "二审未调解";
            } else if (2==comment) {
                msg = "二审已调解";
            }
        }else if (processnodeid == 2100) {
            if (1==comment) {
                msg = "未上诉";
            } else if (2==comment) {
                msg = "已上诉";
            }
        }else if (processnodeid == 701&&comment!=null) {
            msg = "诉讼案件编号:"+comment;
        }else if (processnodeid == 400&&comment!=null) {
            msg = "初期律师费:"+comment;
        }else if (processnodeid == 4400&&comment!=null) {
            msg = "后期期律师费:"+comment;
        }else{
            msg = comment;
        }
        return msg;
    },
    buildCaseProgressTable:function(formid,data){
        var self = this;
        $('#'+formid+' tbody').empty();
        if (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                var o = data[i];
                html += "<tr>";
                html += "   <td>";
                html += (i+1);
                html += "   </td>";
                html += "   <td>";
                html += o.optuserid;
                html += "   </td>";
                html += "   <td>";
                html += o.createtimestr;
                html += "   </td>";
                html += "   <td>";
                html += '<textarea  class="form-control" rows="3" readonly>'+(o.comment==null?"":self.buildCommentVo(o.processnodeid,o.comment))+'</textarea>';
                html += "   </td>";
                html += "   <td>";
                html += '<textarea  class="form-control" rows="3" readonly>'+(o.nexttask==null?"":o.nexttask)+'</textarea>';
                html += "   </td>";
                html += "</tr>";
            }
            $('#'+formid+' tbody').append(html);
        }
    },
    buildCaseProgressTree : function(treedata,caseInfo,treeid,formid) {
        var self = this;
        caseProgressTree = $('#'+treeid).treeview({
            data: treedata,
            showCheckbox: false,
            onNodeSelected: function (event, node) {
                caseProgressTreeSelectedNode = node;
                if (node.parentprocessnodeid>0) {
                    self.showCaseProgressComments(node,caseInfo,formid);
                }else{
                    jQuery('.caseinfo-progress-list').empty();
                    jQuery('.add-form').empty();
                    self.clearSaveProgressAlert();
                }
            },
            onNodeUnselected: function (event, node) {
            }
        });
        caseProgressTree.treeview('collapseAll');
    },
    findCaseProgressViews:function(callback) {
        var self = this;
        var treedata = null;
        jQuery.ajax({
            url:'/lawinfo/front/caseprogress/findprogresstree',
            data:{caseinfoid:selectCaseinfoId},
            type:'GET',
            cache:false,
            //async:false,
            success:function(data) {
                treedata = data;
            },
            error:function() {
                alert('获取案件进度信息异常');
            }
        }).done(function(){
            callback && callback(treedata);
        });
    },
    buildCaseinfoTable:function(caseinfo){
        var self = this;
        if (caseinfo) {
            $('#ssls').val(caseinfo.sslawyers==null?"":caseinfo.sslawyers);
            caseinfo.casetype==1?$('#ajsfwj').attr('checked',true):$('#ajsfwj').attr('checked',false);
            $('#ajssjg').val(caseinfo.caseorgname==null?"":caseinfo.caseorgname);
            $('#jgllr').val(caseinfo.contacts==null?"":caseinfo.contacts);
            $('#zaiquanbj').val(caseinfo.zqbj==null?"":caseinfo.zqbj);
            if (caseinfo.zqdqr) {
                var date = new Date(caseinfo.zqdqr);
                var zaiquandqr = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
                $('#zaiquandqr').val(zaiquandqr);
            }else{
                $('#zaiquandqr').val('');
            }
            $('#dywjj').val(caseinfo.pawnvalue==null?"":caseinfo.pawnvalue);
            $('#lsfze').val(caseinfo.totalprice==null?"":caseinfo.totalprice);
            $('#slfy').val(caseinfo.court==null?"":caseinfo.court);
            $('#cbfg').val(caseinfo.judge==null?"":caseinfo.judge);
            $('#zwrxx').val(caseinfo.debtorinfo==null?"":caseinfo.debtorinfo);
            caseinfo.iscreditorrelated==1?$('#zwrsfgl').attr('checked',true):$('#zwrsfgl').attr('checked',false);
            $('#zwrcczk').val(caseinfo.debtorpropertyinfo==null?"":caseinfo.debtorpropertyinfo);
            $('#anyou').val(caseinfo.ay==null?"":caseinfo.ay);
            $('#dbrxx').val(caseinfo.guarantorinfo==null?"":caseinfo.guarantorinfo);
            caseinfo.isguarantorrelated==1?$('#dbrsfgl').attr('checked',true):$('#dbrsfgl').attr('checked',false);
            $('#dbfs').val(caseinfo.guaranteetype==null?"":caseinfo.guaranteetype);
            $('#dbrcczk').val(caseinfo.guarantorpropertyinfo==null?"":caseinfo.guarantorpropertyinfo);
            $('#dywxx').val(caseinfo.pawninfo==null?"":caseinfo.pawninfo);
            $('#ajcx').val(caseinfo.caseprocedure==null?"":caseinfo.caseprocedure);
            $('#zxls').val(caseinfo.exelawyers==null?"":caseinfo.exelawyers);
            $('#zxah').val(caseinfo.exeajbh==null?"":caseinfo.exeajbh);
        }
    }
}