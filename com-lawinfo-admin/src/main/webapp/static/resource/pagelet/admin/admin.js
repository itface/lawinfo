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
                                orgtype: 1,
                                des: "XXXXXXXXXXXXXXX"
                            },
                            {
                                id: "123",
                                name: "org2",
                                orgtype: 2,
                                des: "YYYYYYYYYYYYYY"
                            },
                            {
                                id: "1233333",
                                name: "org3",
                                orgtype: 3,
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



var roleList = [
    {
        name: "团队负责人",
        roleid: 1,
    },
    {
        name: "执行部门负责人",
        roleid: 2,
    },
    {
        name: "诉讼部门负责人",
        roleid: 3,
    },
    {
        name: "主办律师",
        roleid: 4
    },
    {
        name: "总行",
        roleid: 5,
    },
    {
        name: "分、分支行",
        roleid: 6,
    },
    {
        name: "客户经理",
        roleid: 7,
    },
    {
        name: "非银负责人",
        roleid: 8
    },
    {
        name: "非银部门负责人",
        roleid: 9
    },
    {
        name: "非银业务员",
        roleid: 10
    }
];


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
((__t=(i+1))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].name))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].description))==null?'':__t)+
'</td>\n  <td><button class="btn btn-default btn-delete" data-id="'+
((__t=(list[i].id))==null?'':__t)+
'">删除</button></td>\n</tr>\n';
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
((__t=(i+1))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].name))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].orgname))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].description))==null?'':__t)+
'</td>\n  <td><button class="btn btn-default btn-delete" data-id="'+
((__t=(list[i].id))==null?'':__t)+
'">删除</button></td>\n</tr>\n';
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
'" data-type="'+
((__t=(list[i].orgtype))==null?'':__t)+
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
((__t=(i+1))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].name))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].privilegeid))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].description))==null?'':__t)+
'</td>\n  <td>\n    <button class="btn btn-default btn-delete" data-id="'+
((__t=(list[i].id))==null?'':__t)+
'">删除</button>\n  </td>\n</tr>\n';
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
((__t=(i+1))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].name))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].roleid))==null?'':__t)+
'</td>\n  <td>'+
((__t=(list[i].description))==null?'':__t)+
'</td>\n  <td><button class="btn btn-default btn-delete" data-id="'+
((__t=(list[i].id))==null?'':__t)+
'">删除</button></td>\n</tr>\n';
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
((__t=(i+1))==null?'':__t)+
'</td>\n      <td>'+
((__t=(list[i].name))==null?'':__t)+
'</td>\n      <td>'+
((__t=(list[i].userid))==null?'':__t)+
'</td>\n      <td>'+
((__t=(list[i].orgname))==null?'':__t)+
'</td>\n      <td>'+
((__t=(list[i].rolename))==null?'':__t)+
'</td>\n      <td>\n          <button class="btn btn-default btn-delete" data-id="'+
((__t=(list[i].id))==null?'':__t)+
'">删除</button>\n      </td>\n    </tr>\n';
}
__p+='';
}
return __p;
};

var _curSelectedOrgData
var _curSelectedOrgId = '';//部门的下啦菜单选择
var _addUserOrgId = '';
var _addUserDepartId = '';
var _addUserRoleId = '';
var _addUserRoleData;

var dropdownMenu = {};

dropdownMenu.register = function(id, selectedHandler){
    var $dropdown = $("#"+id);
    $dropdown.off("click");
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
            var orgtype = $(this).attr("data-type");

            _addUserOrgId = dataId;
            self.findDepartByOrg(dataId);
            self.findRole(orgtype);
        });


        dropdownMenu.register("roleMenuList", function(){
            var dataId = $(this).attr("data-id");
            _addUserRoleId = dataId;
            if(dataId == 1 || dataId == 5 || dataId == 8){
                $("#row-depart-add").hide();
            }else{
                $("#row-depart-add").show();
            }
        });

        

        this.$pagePanell.on("click", ".btn-add-user", $.proxy(this.onAddUserClick, this));
        this.$pagePanell.on("click",".btn-addrole", $.proxy(this.onAddRoleClick, this));
        this.$pagePanell.on("click",".btn-addprivilege", $.proxy(this.onAddPrivilege, this));
        this.$pagePanell.on("click",".btn-organization", $.proxy(this.onAddOrganization, this));
        this.$pagePanell.on("click",".btn-depart", $.proxy(this.onAddDepart, this));


        this.$organizationPanel.on("click", ".modal-footer .submit", $.proxy(this.onAddOrgForm, this));
        this.$departPannel.on("click", ".modal-footer .submit", $.proxy(this.onAddDepartForm, this));
        this.$privilegePannel.on("click", ".modal-footer .submit", $.proxy(this.onAddPrivilegeForm, this));
        this.$userPanell.on("click", ".modal-footer .submit", $.proxy(this.onAddUserForm, this));
        this.$rolePannel.on("click", ".modal-footer .submit", $.proxy(this.onaddRoleForm, this));

        this.fetchUserList();
        this.fetRoleList();
        this.fetchOrganization();
        this.fetchDepartList();
        this.fetchPrivilegeList();

        this.initDeleteBtn();
    },

    initDeleteBtn: function(){
        var self = this;

        var deleteData = function(url, callback){
            $.ajax({
                url: url,
                method: "get"
            }).done(function(){
                callback && callback();
            });
        };

        $("#userlistcontent").on("click", ".btn-delete", function(){
            var id = $(this).attr("data-id");
            deleteData("/admin/user/remove/"+id, function(){
                self.fetchUserList();
            });
        });
        $("#roleListContent").on("click", ".btn-delete", function(){
            var id = $(this).attr("data-id");
            deleteData("/admin/role/remove/"+id, function(){
                self.fetRoleList();
            });
        });
        $("#privilegeListContent").on("click", ".btn-delete", function(){
            var id = $(this).attr("data-id");
            deleteData("/admin/privilege/remove/"+id, function(){
                self.fetchPrivilegeList();
            });
        });
        $("#orgList").on("click", ".btn-delete", function(){
            var id = $(this).attr("data-id");
            deleteData("/admin/orginfo/remove/"+id, function(){
                self.fetchOrganization();
            });
        });
        $("#departListContent").on("click", ".btn-delete", function(){
            var id = $(this).attr("data-id");
            deleteData("/admin/dept/remove/"+id, function(){
                self.fetchDepartList();
            });
        });
    },

    findRole: function(type){
        var dataList = [];

      
        for(var i=0; i < roleList.length; i++){
            if(type == 1){
                if(roleList[i].roleid == 1 || 
                    roleList[i].roleid == 2 || 
                    roleList[i].roleid == 3 || 
                    roleList[i].roleid == 4){
                    dataList.push(roleList[i]);
                }
            }else if(type == 2){
                if(roleList[i].roleid == 5 || 
                    roleList[i].roleid == 6 || 
                    roleList[i].roleid == 7){
                    dataList.push(roleList[i]);
                }
            }else if(type == 3){
                if(roleList[i].roleid == 8 || 
                    roleList[i].roleid == 9 || 
                    roleList[i].roleid == 10){
                    dataList.push(roleList[i]);
                }
            }
        }

        var tpl = '';
        for(var i=0; i < dataList.length; i++){
            tpl += '<li label="'+dataList[i].name+'" data-id="' 
                +dataList[i].roleid+ '""><a href="#">' +dataList[i].name+ '</a></li>';
        }
        $("#roleMenuList").html(tpl);

        _addUserRoleId = dataList && dataList.length > 0 ? dataList[0].roleid : "";

        if(_addUserRoleId){
            _addUserRoleData = dataList[0];
            $("#roleMenuList").parent().find(".lb").text(dataList[0].name);
        }
        if(_addUserRoleId == 1 || _addUserRoleId == 5 || _addUserRoleId == 8){
            $("#row-depart-add").hide();
        }else{
            $("#row-depart-add").show();
        }
    },

    findDepartByOrg: function( orgId ){
        $.ajax({
            url: "/admin/dept/findbyorgid/"+orgId,
            method: "get", 
            success: function(dataList){
                _addUserDepartId = dataList && dataList.length> 0 ? dataList[0].id : "";
                $("#departMenuList").parent().find(".lb").text(_addUserDepartId?dataList[0].name: "");

                var tpl = '';
                for(var i=0; i < dataList.length; i++){
                    tpl += '<li label="'+dataList[i].name+'" data-id="' +dataList[i].id+ '""><a href="#">' +dataList[i].name+ '</a></li>';
                }
                $("#departMenuList").html(tpl);
                dropdownMenu.register("departMenuList");
            },
            error: function(res){
                
                dataList = [{id: "xx", name: "xxxnnmame"}, {id: "xx", name: "yyyyyyy"}];
                _addUserDepartId = dataList && dataList.length> 0 ? dataList[0].id : "";
                $("#departMenuList").parent().find(".lb").text(_addUserDepartId?dataList[0].name: "");

                var tpl = '';
                for(var i=0; i < dataList.length; i++){
                    tpl += '<li label="'+dataList[i].name+'" data-id="' +dataList[i].id+ '""><a href="#">' +dataList[i].name+ '</a></li>';
                }
                $("#departMenuList").html(tpl);
                dropdownMenu.register("departMenuList");
                
            }
        });
    },

    fetchUserList: function(){
        var render = function( data ){
            var tpl = userItemTpl( data );
            $("#userlistcontent").html(tpl);
        };

        $.ajax({
            url: "/admin/user/find",
            method: "get", 
            success: function(datalist){
                var tplData = {
                    list: datalist
                };
                render(tplData);
            },
            error: function(res){
                render(userDataList);
            }
        });
    },

    fetRoleList: function(){
        return;
        var render = function( data ){
            var tpl = roleItemTpl( data );
            $("#roleListContent").html(tpl);
            var dataList = data.list;

            //dataList = [{id: "xx", name: "xxxnnmame"}];
            var tpl = '';
            for(var i=0; i < dataList.length; i++){
                tpl += '<li label="'+dataList[i].name+'" data-id="' +dataList[i].id+ '""><a href="#">' +dataList[i].name+ '</a></li>';
            }
            //$("#roleMenuList").html(tpl);

            _addUserRoleId = dataList && dataList.length > 0 ? dataList[0].id : "";
            if(_addUserRoleId){
                //$("#roleMenuList").parent().find(".lb").text(dataList[0].name);
            }
        };

        $.ajax({
            url: "/admin/role/find",
            method: "get", 
            success: function(datalist){
                var tplData = {
                    list: datalist
                };
                render(tplData);
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
        var self = this;
        var render = function( data ){
            var tpl = organizationTpl( data );
            var tpl_drop = dropOrganizationTpl( data );

            $("#orgList").html(tpl);
            $("#orgdroplist").html(tpl_drop);
            $("#userOrgMenu").html(tpl_drop);

            if(data.list && data.list.length != 0){
                _curSelectedOrgData = data.list[0];
                _curSelectedOrgId = data.list[0].id;
                _addUserOrgId = _curSelectedOrgId;
                _addUserOrgId && self.findDepartByOrg(_addUserOrgId);

                $("#orgdroplist").parent().find(".lb").html(data.list[0].name);
                $("#userOrgMenu").parent().find(".lb").html(data.list[0].name);

                self.findRole(_curSelectedOrgData.orgtype);
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
            success: function(datalist){
                var tplData = {
                    list: datalist
                };
                render(tplData);
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
            success: function(datalist){
                var tplData = {
                    list: datalist
                };
                render(tplData);
            },
            error: function(res){
                render(priviligeList);
            }
        });
    },

    onaddRoleForm: function(e){
        var self = this;
        e.preventDefault();
        var $pp = this.$rolePannel;

        var pgs = [];
        var name = $pp.find('input[name="roleName"]').val();
        var code = $pp.find('input[name="roleCode"]').val();
        var $privileges = $pp.find('.privileges input:checked');
        $privileges.each(function(){
            pgs.push($(this).attr("p-id"));
        });
        pgs = pgs.join(",");

        var des = $pp.find('textarea').val();

        $.ajax({
            url: "/admin/role/add",
            method: "post", 
            data: {
                name: name,
                roleid: code,
                description: des,
                privilegeids: pgs
            }
        }).done(function(res){
            self.fetRoleList(res);
        }).error(function(res){
            alert(res.status);
        }).always(function(){
            self.$rolePannel.modal({ keyboard: true }).modal("hide");
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
        }).always(function(){
            self.$privilegePannel.modal({ keyboard: true }).modal("hide");
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
        }).always(function(){
            self.$organizationPanel.modal({ keyboard: true }).modal("hide");
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
        }).always(function(){
            self.$departPannel.modal({ keyboard: true }).modal("hide");
        });
        return false;
    },

    onAddUserForm: function(e){
        var self = this;

        e.preventDefault();

        var userid = this.$userPanell.find('[name="tel"]').val();
        var name = this.$userPanell.find('[name="username"]').val();

        $.ajax({
            url: "/admin/user/add",
            method: "post", 
            data: {
                name: name,
                userid: userid,
                orgid: _addUserOrgId,
                deptid: (_addUserRoleId == 1 || _addUserRoleId == 5 || _addUserRoleId == 8)?"": _addUserDepartId,
                roleids: _addUserRoleId
            },
            success: function(){
                self.fetchUserList();
            },
            error: function(res){
                alert(res.status);
            }
        }).always(function(){
            self.$userPanell.modal({ keyboard: true }).modal("hide");
        });
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