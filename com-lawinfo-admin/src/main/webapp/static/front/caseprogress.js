/**
 * Created by wangrongtao on 15/10/28.
 */
var caseProgressTreeSelectedNode = null;
var caseProgressTree = null;
var caseprogress = {
    init:function(){
        var self = this;
        jQuery('#caseinfo-progress-form').hide();
        self.clearSaveProgressAlert();
        if (selectCaseinfoId&&selectCaseinfoId>0) {
            self.findCaseProgressViews($.proxy(self.initCaseProgressView,self));
        }
    },
    showCaseProgressComments:function(node){
        var self = this;
        jQuery('#caseinfo-progress-form').show();
        jQuery('#caseinfo-progress-list').empty();
        jQuery('#add-form').empty();
        var listHtml = '';
        var nodeid = node.id;
        //初期及后期律师费,一审及二审调解
        if (nodeid==400) {
            if (selectCasePreprice&&selectCasePreprice>0) {
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4"><label>初期律师费</label></div>';
                listHtml += '<div class="col-xs-4"><input id="commonfiled" class="form-control" type="text"  readonly value="'+selectCasePreprice+'"/></div>';
                listHtml += '</div>';
                jQuery('#caseinfo-progress-list').append(listHtml);
            }else{
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4"><label>初期律师费</label></div>';
                listHtml += '<div class="col-xs-4"><input id="commonfiled" class="form-control" type="text"/></div>';
                listHtml += '<div class="col-xs-2"><button type="button" class="btn btn-default save">添加</button></div>';
                listHtml += '</div>';
                jQuery('#add-form').append(listHtml);
            }
        }else if (nodeid==4400) {
            if (selectCaseSufprice&&selectCaseSufprice>0) {
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4"><label>后期期律师费</label></div>';
                listHtml += '<div class="col-xs-4"><input id="commonfiled" class="form-control" type="text"  readonly value="'+selectCaseSufprice+'"/></div>';
                listHtml += '</div>';
                jQuery('#caseinfo-progress-list').append(listHtml);
            }else{
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4"><label>后期律师费</label></div>';
                listHtml += '<div class="col-xs-4"><input id="commonfiled" class="form-control" type="text"/></div>';
                listHtml += '<div class="col-xs-2"><button type="button" class="btn btn-default save">添加</button></div>';
                listHtml += '</div>';
                jQuery('#add-form').append(listHtml);
            }
        }else if (nodeid==1600) {
            if (selectCaseYstj&&selectCaseYstj>0) {
                var selected1 = selectCaseYstj==1?"selected":"";
                var selected2 = selectCaseYstj==2?"selected":"";
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4"><label>一审是否调解</label></div>';
                listHtml += '<div class="col-xs-4">' +
                                '<select id="commonfiled" class="form-control"  disabled value="'+selectCaseYstj+'">' +
                                    '<option value="1" '+selected1+'>未调解</option>' +
                                    '<option value="2" '+selected2+'>已调解</option>' +
                                '</select>' +
                            '</div>';
                listHtml += '</div>';
                jQuery('#caseinfo-progress-list').append(listHtml);
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
                jQuery('#add-form').append(listHtml);
            }
        }else if (nodeid==3100) {
            if (selectCaseEstj&&selectCaseEstj>0) {
                var selected1 = selectCaseEstj==1?"selected":"";
                var selected2 = selectCaseEstj==2?"selected":"";
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4"><label>二审是否调解</label></div>';
                listHtml += '<div class="col-xs-4">' +
                    '<select id="commonfiled" class="form-control"  disabled value="'+selectCaseEstj+'">' +
                    '<option value="1" '+selected1+'>未调解</option>' +
                    '<option value="2" '+selected2+'>已调解</option>' +
                    '</select>' +
                    '</div>';
                listHtml += '</div>';
                jQuery('#caseinfo-progress-list').append(listHtml);
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
                jQuery('#add-form').append(listHtml);
            }
        }else if(nodeid==2100){
            if (selectCaseSfss&&selectCaseSfss>0) {
                var selected1 = selectCaseSfss==1?"selected":"";
                var selected2 = selectCaseSfss==2?"selected":"";
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4"><label>是否上诉</label></div>';
                listHtml += '<div class="col-xs-4">' +
                    '<select id="commonfiled" class="form-control"  disabled value="'+selectCaseSfss+'">' +
                    '<option value="1" '+selected1+'>不上诉</option>' +
                    '<option value="2" '+selected2+'>上诉</option>' +
                    '</select>' +
                    '</div>';
                listHtml += '</div>';
                jQuery('#caseinfo-progress-list').append(listHtml);
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
                jQuery('#add-form').append(listHtml);
            }
        }else if(nodeid==701){
            if (selectCaseSsajbh) {
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4"><label>诉讼案件编号</label></div>';
                listHtml += '<div class="col-xs-4"><input id="commonfiled" class="form-control" type="text"  readonly value="'+selectCaseSsajbh+'"/></div>';
                listHtml += '</div>';
                jQuery('#caseinfo-progress-list').append(listHtml);
            }else{
                listHtml += '<div class="row form-group">';
                listHtml += '<div class="col-xs-4"><label>诉讼案件编号</label></div>';
                listHtml += '<div class="col-xs-4"><input id="commonfiled" class="form-control" type="text"/></div>';
                listHtml += '<div class="col-xs-2"><button type="button" class="btn btn-default save">添加</button></div>';
                listHtml += '</div>';
                jQuery('#add-form').append(listHtml);
            }
        }else{
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
            jQuery('#add-form').append(addHtml);
            jQuery('#caseinfo-progress-list').append(listHtml);
        }
        self.clearSaveProgressAlert();
        $('#caseinfo-progress-modal .save').on('click', $.proxy(self.saveProgressEvent,self));
    },
    saveProgressAlert:function(msg){
        jQuery('#caseinfo-progress-modal .error-label').empty();
        jQuery('#caseinfo-progress-modal .error-label').text(msg);
        jQuery('#caseinfo-progress-modal .has-error').show();
    },
    clearSaveProgressAlert : function(msg){
        jQuery('#caseinfo-progress-modal .error-label').empty();
        jQuery('#caseinfo-progress-modal .has-error').hide();
    },
    afterSaveProgress : function(node,comment){
        var self = this;
        self.init();
        caseinfo.initCaseinfoTable();
        self.initCaseinfoData(node.id,comment);
        /*var selectedNode = caseProgressTree.treeview('search', [node.text]);
         caseProgressTree.treeview('selectNode', [ selectedNode,{}]);
         caseprogress.showCaseProgressComments(node);*/
        jQuery('#caseinfo-progress-form #comment').val('');
        jQuery('#caseinfo-progress-form #nexttask').val('');
    },
    initCaseinfoData:function(nodeid,value){
        if (nodeid==400) {
            selectCasePreprice = value;
        }else if (nodeid==4400) {
            selectCaseSufprice = value;
        }else if (nodeid==1600) {
            selectCaseYstj = value;
        }else if (nodeid==3100) {
            selectCaseEstj = value;
        }else if (nodeid==2100) {
            selectCaseSfss = value;
        }else if (nodeid==701) {
            selectCaseSsajbh = value;
        }
    },
    saveProgressEvent : function(e){
        var self = this;
        if (caseProgressTreeSelectedNode!=null&&selectCaseinfoId!=null) {
            var nodeid = caseProgressTreeSelectedNode.id;
            var comment = null;
            var nexttask = null;
            //初期及后期律师费,一审及二审调解
            if (nodeid==400) {
                comment = jQuery('#caseinfo-progress-form #commonfiled').val();
                if (comment==null||comment.trim()=='') {
                    self.saveProgressAlert('初期律师费不能为空');
                    return false;
                }else if (isNaN(comment)) {
                    self.saveProgressAlert('初期律师费必须是数字');
                    return false;
                }
            }else if (nodeid==4400) {
                comment = jQuery('#caseinfo-progress-form #commonfiled').val();
                if (comment==null||comment.trim()=='') {
                    self.saveProgressAlert('后期律师费不能为空');
                    return false;
                }else if (isNaN(comment)) {
                    self.saveProgressAlert('后期律师费必须是数字');
                    return false;
                }
            }else if (nodeid==1600) {
                comment = jQuery('#caseinfo-progress-form #commonfiled').val();
            }else if (nodeid==3100) {
                comment = jQuery('#caseinfo-progress-form #commonfiled').val();
            }else if (nodeid==2100) {
                comment = jQuery('#caseinfo-progress-form #commonfiled').val();
            }else if (nodeid==701) {
                comment = jQuery('#caseinfo-progress-form #commonfiled').val();
            }else{
                comment = jQuery('#caseinfo-progress-form #comment').val();
                nexttask = jQuery('#caseinfo-progress-form #nexttask').val();
                if (comment==null||comment.trim()=='') {
                    self.saveProgressAlert('内容不能为空');
                    return false;
                }
                if (nexttask==null||nexttask.trim()=='') {
                    self.saveProgressAlert('下一步计划不能为空');
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
                    self.afterSaveProgress(caseProgressTreeSelectedNode,comment);
                },
                error:function() {
                    self.saveProgressAlert('保存案件进度信息异常');
                }
            });
        }else{
            self.saveProgressAlert('保存异常，没有选择案件或者进度节点');
            return false;
        }
    },
    initCaseProgressView : function(data){
        var self = this;
        if (data) {
            if (data.caseProgressCommentList) {
                self.buildCaseProgressTable(data.caseProgressCommentList);
            }
            if (data.caseProgressTreeVoList) {
                self.buildCaseProgressTree(data.caseProgressTreeVoList);
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
    buildCaseProgressTable:function(data){
        var self = this;
        $('#progress-query tbody').empty();
        if (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                var o = data[i];
                html += "<tr>";
                html += "   <td>";
                html += (i+1);
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
            $('#progress-query tbody').append(html);
        }
    },
    buildCaseProgressTree : function(treedata) {
        var self = this;
        caseProgressTree = $('#caseinfo-progress-tree').treeview({
            data: treedata,
            showCheckbox: false,
            onNodeSelected: function (event, node) {
                caseProgressTreeSelectedNode = node;
                if (node.parentprocessnodeid>0) {
                    self.showCaseProgressComments(node);
                }else{
                    jQuery('#caseinfo-progress-form').hide();
                    self.clearSaveProgressAlert();
                }
            },
            onNodeUnselected: function (event, node) {
            }
        });
        //$('#progress-user-tree .check-icon').remove();
        //customOrgTree.treeview('expandAll');
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
                mainAlert('获取案件进度信息异常');
            }
        }).done(function(){
            callback && callback(treedata);
        });
    }
}