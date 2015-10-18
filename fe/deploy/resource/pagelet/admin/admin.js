define('pagelet/admin/admin.js', function(require, exports, module){ var departListData = {
                        list: [
                            {
                                id: "111",
                                name: "法律部门",
                                orgid: "100",
                                orgname: "组织",
                                des: "XXXXXXXXXXXXXXX"
                            },
                            {
                                id: "123",
                                name: "诉讼部门",
                                orgid: "1000",
                                orgname: "组织2",
                                des: "YYYYYYYYYYYYYY"
                            }
                        ]
                    };
var orgListData = {

                    status: 200,

                    list: [
                            {
                                id: "111",
                                name: "org1",
                                des: "XXXXXXXXXXXXXXX"
                            },
                            {
                                id: "123",
                                name: "org2",
                                des: "YYYYYYYYYYYYYY"
                            }
                        ]
                };

var priviligeList = {
    list: [
        {
            id: "111",
            name: "pri1",
            code: "1111",
            des: "XXXXXXXXXXXXXXX"
        },
        {
            id: "123",
            name: "pri2",
            code: "2222",
            des: "YYYYYYYYYYYYYY"
        }
    ]
};

var roleDataList = {
    list: [
        {
            id: "111",
            name: "roleName1",
            code: "1111",
            des: "mmmmmmmmmmmmm"
        },
        {
            id: "123",
            name: "roleName2",
            code: "2222",
            des: "nnnnnnnnnnnnnnnnnn"
        }
    ]
};

var userDataList = {
    list: [
        {
            id: "xxxx",
            name: "丁振赣",
            tel: 13309830393,
            org: "礼名律师事务所",
            role: "负责人"
        },
        {
            id: "xxxx",
            name: "丁振赣",
            tel: 13309830393,
            org: "礼名律师事务所",
            role: "负责人"
        },
        {
            id: "xxxx",
            name: "丁振赣",
            tel: 13309830393,
            org: "礼名律师事务所",
            role: "负责人"
        },
        {
            id: "xxxx",
            name: "丁振赣",
            tel: 13309830393,
            org: "礼名律师事务所",
            role: "负责人"
        },
        {
            id: "xxxx",
            name: "丁振赣",
            tel: 13309830393,
            org: "礼名律师事务所",
            role: "负责人"
        }
    ]
};


var template = function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='<div>'+
((__t=(a))==null?'':__t)+
'</div>';
}
return __p;
};
var organizationTpl = function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='';
 for(var i=0; i < list.length; i++){
__p+='\n<tr>\n  <td>'+
((__t=(i))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].name))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].description))==null?'':__t)+
'</td>\n</tr>\n';
}
__p+='';
}
return __p;
};
var departTpl = function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='';
 for(var i=0; i < list.length; i++){
__p+='\n<tr>\n  <td>'+
((__t=(i))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].name))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].orgname))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].description))==null?'':__t)+
'</td>\n</tr>\n';
}
__p+='';
}
return __p;
};
var dropOrganizationTpl = function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='';
 for(var i=0; i < list.length; i++){
__p+='\n  <li label="'+
((__t=(list[i].name))==null?'':__t)+
'" data-id="'+
((__t=(list[i].id))==null?'':__t)+
'"><a href="#">'+
((__t=(list[i].name ))==null?'':__t)+
'</a></li>\n';
}
__p+='';
}
return __p;
};
var priviligeTpl = function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='';
 for(var i=0; i < list.length; i++){
__p+='\n<tr>\n  <td>'+
((__t=(i))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].name))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].privilegeid))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].description))==null?'':__t)+
'</td>\n</tr>\n';
}
__p+='';
}
return __p;
};
var roleItemTpl = function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='';
 for(var i=0; i < list.length; i++){
__p+='\n<tr>\n  <td>'+
((__t=(i))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].name))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].roleid))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].description))==null?'':__t)+
'</td>\n</tr>\n';
}
__p+='';
}
return __p;
};
var previligeSelectorTpl = function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='';
 for(var i=0; i < list.length; i++){
__p+='\n<span >\n    <label>\n        <input type="checkbox" p-id="'+
((__t=(list[i].id))==null?'':__t)+
'"></input>'+
((__t=(list[i].name))==null?'':__t)+
'\n    </label>\n</span>\n';
}
__p+='';
}
return __p;
};
var userItemTpl = function(obj){
var __t,__p='',__j=Array.prototype.join,print=function(){__p+=__j.call(arguments,'');};
with(obj||{}){
__p+='';
 for(var i=0; i < list.length; i++){
__p+='\n    <tr>\n      <td>'+
((__t=(i))==null?'':__t)+
'</td>\n      <td>'+
((__t=(list[i].name))==null?'':__t)+
'</td>\n      <td>'+
((__t=(list[i].tel))==null?'':__t)+
'</td>\n      <td>'+
((__t=(list[i].org))==null?'':__t)+
'</td>å\n      <td>'+
((__t=(list[i].role))==null?'':__t)+
'</td>\n    </tr>\n';
}
__p+='';
}
return __p;
};

var _curSelectedOrgId = '';
var _addUserOrgId = '';

var dropdownMenu = {};

dropdownMenu.register = function(id, selectedHandler){
    var $dropdown = $("#"+id);
    $dropdown.on("click", "li", function(e){
        e.preventDefault();
        $dropdown.parent().find(".dropdown-toggle .lb").text($(this).attr("label"));
        selectedHandler && selectedHandler.apply(this, arguments);
    });;
};

var admin = {
    $pagePanell: $('.page-container'),
    $userPanell: $('#adduserpannel'),
    $rolePannel: $('#addrole'),
    $privilegePannel: $("#addprivilege"),
    $organizationPanel : $("#addorganization"), 
    $departPannel: $("#adddepartpannel"), 

    init: function(){

        var self = this;

        dropdownMenu.register("orgdroplist", function(){
            var dataId = $(this).attr("data-id");
            var label = $(this).attr("label");

            _curSelectedOrgId = dataId;
            _curSelectedOrgName = label;
        });

        dropdownMenu.register("userOrgMenu", function(){
            var dataId = $(this).attr("data-id");
            var label = $(this).attr("label");

            _addUserOrgId = dataId;
        });

        

        this.$pagePanell.on("click", ".btn-add-user", $.proxy(this.onAddUserClick, this));
        this.$pagePanell.on("click",".btn-addrole", $.proxy(this.onAddRoleClick, this));
        this.$pagePanell.on("click",".btn-addprivilege", $.proxy(this.onAddPrivilege, this));
        this.$pagePanell.on("click",".btn-organization", $.proxy(this.onAddOrganization, this));
        this.$pagePanell.on("click",".btn-depart", $.proxy(this.onAddDepart, this));


        this.$organizationPanel.on("click", ".modal-footer .submit", $.proxy(this.onAddOrgForm, this));
        this.$departPannel.on("click", ".modal-footer .submit", $.proxy(this.onAddDepartForm, this));
        this.$privilegePannel.on("click", ".modal-footer .submit", $.proxy(this.onAddPrivilegeForm, this));
        this.$privilegePannel.on("click", ".modal-footer .submit", $.proxy(this.onAddPrivilegeForm, this));
        this.$rolePannel.on("click", ".modal-footer .submit", $.proxy(this.onaddRoleForm, this));

        this.fetchUserList();
        this.fetRoleList();
        this.fetchOrganization();
        this.fetchDepartList();
        this.fetchPrivilegeList();
    },

    fetchUserList: function(){
        var render = function( data ){
            var tpl = userItemTpl( data );
            $("#userlistcontent").html(tpl);
        };

        $.ajax({
            url: "/admin/user/find",
            method: "get", 
            success: function(res){
                render(res);
            },
            error: function(res){
                render(userDataList);
            }
        });
    },

    fetRoleList: function(){
        var render = function( data ){
            var tpl = roleItemTpl( data );
            $("#roleListContent").html(tpl);
        };

        $.ajax({
            url: "/admin/role/find",
            method: "get", 
            success: function(res){
                render(res);
            },
            error: function(res){
                render(roleDataList);
            }
        });
    },

    /*
     *获取机构列表
     *[{
       id: "1",
       name: "xx",
       des: "xxx"
     }]
     */
    fetchOrganization: function(){
        var render = function( data ){
            var tpl = organizationTpl( data );
            var tpl_drop = dropOrganizationTpl( data );
            $("#orgList").html(tpl);
            $("#orgdroplist").html(tpl_drop);
            $("#userOrgMenu").html(tpl_drop);

            if(data.list && data.list.length != 0){
                _curSelectedOrgId = data.list[0].id;
                _addUserOrgId = _curSelectedOrgId;

                $("#orgdroplist").parent().find(".lb").html(data.list[0].name);
                $("#userOrgMenu").parent().find(".lb").html(data.list[0].name);
            }
        };

        $.ajax({
            url: "/admin/orginfo/find",
            method: "get", 
            success: function(dataList){
                var tplData =  {
                    list: dataList
                };
                render(tplData);
            },
            error: function(){
                render(orgListData);
            }
        });
    },

    /*
     *获取部门列表
     *  
     */
    fetchDepartList: function(){
        var render = function( data ){
            var tpl = departTpl( data );
            $("#departListContent").html(tpl);
        };

        $.ajax({
            url: "/admin/dept/find",
            method: "get", 
            success: function(res){
                render(res);
            },
            error: function(res){
                render(departListData);
            }
        });
    },  

    fetchPrivilegeList: function(){
        var render = function( data ){
            var tpl = priviligeTpl( data );
            var selectorTpl = previligeSelectorTpl(data);
            $("#privilegeListContent").html(tpl);
            $("#privilegeList").html(selectorTpl);
        };

        $.ajax({
            url: "/admin/privilege/find",
            method: "get", 
            success: function(res){
                render(res);
            },
            error: function(res){
                render(priviligeList);
            }
        });
    },

    onaddRoleForm: function(e){
        e.preventDefault();
        var $pp = this.$rolePannel;

        var pgs = '';
        var name = $pp.find('input[name="roleName"]').val();
        var code = $pp.find('input[name="roleCode"]').val();
        var $privileges = $pp.find('.privileges input:checked');
        $privileges.each(function(){
            pgs += ","+$(this).attr("p-id");
        });

        var des = $pp.find('textarea').val();

        $.ajax({
            url: "/admin/role/add",
            method: "post", 
            data: {
                name: name,
                roleid: code,
                description: des,
                pgs: pgs
            }
        }).done(function(res){
            self.fetRoleList(res);
        }).error(function(res){
            alert(res.status);
        });
    },

    onAddPrivilegeForm: function(e){
        var self = this;

        e.preventDefault();
        var $pp = this.$privilegePannel;
        var name = $pp.find('input[name="preName"]').val();
        var code = $pp.find('input[name="precode"]').val();
        var des = $pp.find('textarea').val();

        if(!name || !code || !des){
            alert("请填写完全");
            return;
        }

        $.ajax({
            url: "/admin/privilege/add",
            method: "post", 
            data: {
                name: name,
                privilegeid: code,
                description: des,
            },
            success: function(){
                console.log("add_privilege success");
                self.fetchPrivilegeList();
            },
            error: function(res){
                alert(res.status);
            }
        });
    },

    onAddOrgForm: function(e){
        var self = this;

        e.preventDefault();

        var name = this.$organizationPanel.find('input[name="orgName"]').val();
        var type = this.$organizationPanel.find('[name="orgType"]:checked').val();
        var des = this.$organizationPanel.find('textarea').val();

        if(!name || !des){
            alert("填入内容不能有空");
            return;
        }

        $.ajax({
            url: "/admin/orginfo/add",
            method: "post", 
            data: {
                name: name,
                orgtype: type,
                description: des,
            },
            success: function(){
                console.log("add organization success");
                self.fetchOrganization();
            },
            error: function(res){
                alert(res.status);
            }
        });
        return false;
    },

    onAddDepartForm: function(e){
        var self = this;

        e.preventDefault();

        var name = this.$departPannel.find('input[name="departName"]').val();
        var des = this.$departPannel.find('textarea').val();

        if(!name || !des || !_curSelectedOrgId){
            alert("有空填项");
            return;
        };

        $.ajax({
            url: "/admin/dept/add",
            method: "post", 
            data: {
                name: name,
                description: des,
                orgid: _curSelectedOrgId
            },
            success: function(){
                console.log("add depart success");
                self.fetchDepartList();
            },
            error: function(res){
                alert(res.status);
            }
        });
        return false;
    },

    onAddDepartPannel: function(){

    },

    onAddOrganization: function(e){
        this.$organizationPanel.modal({ keyboard: true }).modal("show");
    },

    onAddUserClick : function(){
        this.$userPanell.modal({ keyboard: true }).modal("show");
    },
    onAddRoleClick: function(){
        this.$rolePannel.modal({ keyboard: true }).modal("show");
    },
    onAddPrivilege: function(){
        this.$privilegePannel.modal({ keyboard: true }).modal("show");
    },
    onAddDepart: function(){
        this.$departPannel.modal({ keyboard: true }).modal("show");
    }
};

module.exports = admin; 
});