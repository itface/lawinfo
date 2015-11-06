/**
 * Created by wangrongtao on 15/10/28.
 */
var selectCaseinfoId = null;
var caseinfo = {

    saveActionAlert:function(msg){
        jQuery('#caseinfo-modal .error-label').empty();
        jQuery('#caseinfo-modal .error-label').text(msg);
        jQuery('#caseinfo-modal .has-error').show();
    },
    initClickEvent:function(){
        jQuery('#caseinfo-list').unbind('click');
        jQuery('#caseinfo-modal .submit').unbind('click');
    },
    init:function(){
        var self = this;
        self.initClickEvent();
        jQuery('#caseinfo-list').on('click','.caseinfo-row', $.proxy(self.registCaseinfoTableRowEvent,this));
        jQuery('#caseinfo-modal .submit').on('click',function(e){
            var bankid = jQuery('#caseinfo-modal  #bankid').val();
            var bankname = $("#caseinfo-modal  #bankid").find("option:selected").text()
            var contact = jQuery('#caseinfo-modal  #contact').val();
            var contactphone = jQuery('#caseinfo-modal  #contactphone').val();
            var debtorinfo = jQuery('#caseinfo-modal  #debtorinfo').val();
            var iscreditorrelated = jQuery('#caseinfo-modal  #iscreditorrelated').attr("checked")?1:0;
            var debtorpropertyinfo = jQuery('#caseinfo-modal  #debtorpropertyinfo').val();
            var ay = jQuery('#caseinfo-modal  #ay').val();
            var zqbj = jQuery('#caseinfo-modal  #zqbj').val();
            var zqdqr = jQuery('#caseinfo-modal  #zqdqr').val();
            var guarantorinfo = jQuery('#caseinfo-modal  #guarantorinfo').val();
            var isguarantorrelated = jQuery('#caseinfo-modal  #isguarantorrelated').attr("checked")?1:0;
            var guaranteetype = jQuery('#caseinfo-modal  #guaranteetype').val();
            var guarantorpropertyinfo = jQuery('#caseinfo-modal  #guarantorpropertyinfo').val();
            var pawninfo = jQuery('#caseinfo-modal  #pawninfo').val();
            var pawnvalue = jQuery('#caseinfo-modal  #pawnvalue').val();
            var caseprocedure = jQuery('#caseinfo-modal  #caseprocedure').val();
            var court = jQuery('#caseinfo-modal  #court').val();
            var judge = jQuery('#caseinfo-modal  #judge').val();
            var exelawyerid = jQuery('#caseinfo-modal  #exelawyerid').val();
            var sslawyerid = jQuery('#caseinfo-modal  #sslawyerid').val();
            if (!bankid) {
                self.saveActionAlert('案件所属银行不能为空');
                return false;
            }
            if (!contact) {
                self.saveActionAlert('案件联系人不能为空');
                return false;
            }
            if (!contactphone) {
                self.saveActionAlert('案件联系人联系方式不能为空');
                return false;
            }
            if (!debtorinfo) {
                self.saveActionAlert('债务人信息不能为空');
                return false;
            }
            if (!debtorpropertyinfo) {
                self.saveActionAlert('债务人财产状况不能为空');
                return false;
            }
            if (!ay) {
                self.saveActionAlert('案由不能为空');
                return false;
            }
            if (!zqbj) {
                self.saveActionAlert('债权本金不能为空');
                return false;
            }
            if (!zqdqr) {
                self.saveActionAlert('债权到账日期不能为空');
                return false;
            }
            if (!guarantorinfo) {
                self.saveActionAlert('担保人信息不能为空');
                return false;
            }
            if (!guaranteetype) {
                self.saveActionAlert('担保方式不能为空');
                return false;
            }
            if (!pawninfo) {
                self.saveActionAlert('抵押物信息不能为空不能为空');
                return false;
            }
            if (!pawnvalue) {
                self.saveActionAlert('抵押物评估价值不能为空不能为空');
                return false;
            }
            if (!caseprocedure) {
                self.saveActionAlert('案件程序不能为空');
                return false;
            }
            if (!court) {
                self.saveActionAlert('受理法院不能为空');
                return false;
            }
            if (!judge) {
                self.saveActionAlert('承办法官不能为空');
                return false;
            }
            if (!exelawyerid) {
                self.saveActionAlert('执行律师ID不能为空');
                return false;
            }
            if (!sslawyerid) {
                self.saveActionAlert('诉讼律师ID不能为空');
                return false;
            }
            if (!totalprice) {
                self.saveActionAlert('律师费总额不能为空');
                return false;
            }
            var obj = {};
            obj.bankid = bankid;
            obj.bankname = bankname;
            obj.contact = contact;
            obj.contactphone = contactphone;
            obj.debtorinfo = debtorinfo;
            obj.iscreditorrelated = iscreditorrelated;
            obj.debtorpropertyinfo = debtorpropertyinfo;
            obj.ay = ay;
            obj.zqbj = zqbj;
            obj.zqdqr = zqdqr;
            obj.guarantorinfo = guarantorinfo;
            obj.isguarantorrelated = isguarantorrelated;
            obj.guaranteetype = guaranteetype;
            obj.guarantorpropertyinfo = guarantorpropertyinfo;
            obj.pawninfo = pawninfo;
            obj.pawnvalue = pawnvalue;
            obj.caseprocedure = caseprocedure;
            obj.court = court;
            obj.judge = judge;
            obj.exelawyerid = exelawyerid;
            obj.sslawyerid = sslawyerid;
            obj.totalprice = totalprice;
            console.debug(obj);
            jQuery.ajax({
                url:'/lawinfo/front/caseinfo/add',
                data:obj,
                type:'POST',
                success:function(data) {
                    if (data==1) {
                        self.showCaseinfoTable($.proxy(self.buildCaseinfoTable,this));
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
        self.showCaseinfoTable($.proxy(self.buildCaseinfoTable,this));
    },
    buildCaseinfoTable:function(data) {
        var self = this;
        if (data) {
            $('#caseinfo-list tbody').empty();
            var html = '';
            for(var i=0;i<data.length;i++) {
                var o = data[i];
                html+='<tr caseinfoid="'+ o.id+'" class="caseinfo-row">';
                html+=' <td>';
                html+='     '+ (i+1);
                html+=' </td>';
                html+=' <td>';
                html+='     '+ o.title;
                html+=' </td>';
                html+='</tr>';
            }
            $('#caseinfo-list tbody').append(html);
        }
    },
    registCaseinfoTableRowEvent:function(e){
        selectCaseinfoId = jQuery(e.currentTarget).attr('caseinfoid');
        jQuery('#caseinfo-progress-modal').modal({backdrop: 'static', keyboard: false}).modal('show');
    },
    showCaseinfoTable:function(callback){
        var self = this;
        var caseinfos = null;
        jQuery.ajax({
            url:'/lawinfo/front/caseinfo/find',
            data:{userid:userSelectedNode==null?null:userSelectedNode.userid},
            type:'GET',
            //async:false,
            success:function(data) {
                caseinfos = data;
            },
            error:function() {
                mainAlert('获取案件信息异常');
            }
        }).done(function(e){
            callback && callback(caseinfos);
        });
    }
}