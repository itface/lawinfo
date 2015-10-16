var template = __inline('/pagelet/admin/admin.tpl');
var organizationTpl = __inline("/pagelet/admin/organization.tpl");
var departTpl = __inline("/pagelet/admin/depart_list.tpl");
var dropOrganizationTpl = __inline("/pagelet/admin/org_dropdown.tpl");

var _curSelectedOrgId = '';


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

        this.$pagePanell.on("click", 
                ".btn-add-user", 
                $.proxy(this.onAddUserClick, this));

        this.$pagePanell.on("click",".btn-addrole", $.proxy(this.onAddRoleClick, this));
        this.$pagePanell.on("click",".btn-addprivilege", $.proxy(this.onAddPrivilege, this));
        this.$pagePanell.on("click",".btn-organization", $.proxy(this.onAddOrganization, this));
        this.$pagePanell.on("click",".btn-depart", $.proxy(this.onAddDepart, this));


        this.$organizationPanel.on("click", ".modal-footer .submit", $.proxy(this.onAddOrgForm, this));
        this.$departPannel.on("click", ".modal-footer .submit", $.proxy(this.onAddDepartForm, this));

        this.fetchOrganization();
        this.fetchDepartList();
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

            if(data.list && data.list.length != 0){
                _curSelectedOrgId = data.list[0].id;
                _curSelectedOrgName = data.list[0].name;
                $("#orgdroplist").parent().find(".lb").html(_curSelectedOrgName);
            }
        };

        $.ajax({
            url: "/api/fetch_organization",
            method: "get", 
            success: function(res){
                render(res);
            },
            error: function(res){
                render({
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
                });
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
                render({
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
                });
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