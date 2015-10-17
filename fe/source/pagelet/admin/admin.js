var departListData = {
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


var template = __inline('/pagelet/admin/admin.tpl');
var organizationTpl = __inline("/pagelet/admin/organization.tpl");
var departTpl = __inline("/pagelet/admin/depart_list.tpl");
var dropOrganizationTpl = __inline("/pagelet/admin/org_dropdown.tpl");
var priviligeTpl = __inline("/pagelet/admin/previlige.tpl");
var roleItemTpl = __inline("/pagelet/admin/roleitem.tpl");
var previligeSelectorTpl = __inline("/pagelet/admin/previlige_selector.tpl");
var userItemTpl = __inline("/pagelet/admin/useritem.tpl");

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
            url: "/api/fetch_user_list",
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
            url: "/api/fetch_role_list",
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
            url: "/api/fetch_organization",
            method: "get", 
            success: function(res){
                render(res);
            },
            error: function(res){
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
            url: "/api/fetch_depart_list",
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
            url: "/api/fetch_previlige_list",
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
            url: "/api/add_role",
            method: "post", 
            data: {
                name: name,
                code: code,
                des: des,
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
            url: "/api/add_privilege",
            method: "post", 
            data: {
                name: name,
                code: code,
                des: des,
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

    onAddOrganization: function(e){
        this.$organizationPanel.modal({ keyboard: true }).modal("show");
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
            url: "/api/add_organization",
            method: "post", 
            data: {
                name: name,
                type: type,
                des: des,
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
        }

        $.ajax({
            url: "/api/add_depart",
            method: "post", 
            data: {
                name: name,
                des: des,
                orgId: _curSelectedOrgId
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