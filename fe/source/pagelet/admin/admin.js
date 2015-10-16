var template = __inline('/pagelet/admin/admin.tpl');

var user = {
    $pagePanell: $('.page-container'),
    $userPanell: $('#adduserpannel'),
    $rolePannel: $('#addrole'),
    $privilegePannel: $("#addprivilege"),
    $organizationPanel : $("#addorganization"), 
    $departPannel: $("#adddepartpannel"), 

    init: function(){

        var self = this;

        this.$pagePanell.on("click", 
                ".btn-add-user", 
                $.proxy(this.onAddUserClick, this));

        this.$pagePanell.on("click",".btn-addrole", $.proxy(this.onAddRoleClick, this));
        this.$pagePanell.on("click",".btn-addprivilege", $.proxy(this.onAddPrivilege, this));
        this.$pagePanell.on("click",".btn-organization", $.proxy(this.onAddOrganization, this));
        this.$pagePanell.on("click",".btn-depart", $.proxy(this.onAddDepart, this));


        this.$organizationPanel.on("click", ".modal-footer .submit", $.proxy(this.onAddOrgForm, this));
        this.$departPannel.on("click", ".modal-footer .submit", $.proxy(this.onAddDepartForm, this));
    },

    /*
     *[{
       id: "1",
       name: "xx",
       des: "xxx"
     }]
     */
    fetchOrganization: function(){
        $.ajax({
            url: "/api/fetch_organization",
            method: "get", 
            success: function(){
                
            },
            error: function(res){
                
            }
        });
    },

    onAddOrganization: function(e){
        this.$organizationPanel.modal({ keyboard: true }).modal("show");
    },

    onAddOrgForm: function(e){
        var self = this;

        e.preventDefault();
        $.ajax({
            url: "/api/add_organization",
            method: "post", 
            data: {
                name: self.$organizationPanel.find('input[name="orgName"]').val(),
                des: self.$organizationPanel.find('textarea').val(),
            },
            success: function(){
                console.log("add organization success");
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

        $.ajax({
            url: "/api/add_depart",
            method: "post", 
            data: {
                name: self.$departPannel.find('input[name="departName"]').val(),
                des: self.$departPannel.find('textarea').val(),
            },
            success: function(){
                console.log("add depart success");
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

module.exports = user;