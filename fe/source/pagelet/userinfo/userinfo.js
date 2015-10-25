var userinfo = {
    init: function(){
        $("#record-nav").on("click", "li",function(e){
            e.preventDefault();
            $(this).parent().find(".active").removeClass("active");
            $(this).addClass("active");
        });

        $("#btn-statistic").on("click", function(){
            $("#statistic").show();
        });

        $(".list tbody").on("click", "tr", function(){
            $("#progress").modal({ keyboard: true }).modal("show");
        });


        $("#btn-case-add").on("click", function(){
            $("#case-add").modal({ keyboard: true }).modal("show");
        });
    }
};

module.exports = userinfo;