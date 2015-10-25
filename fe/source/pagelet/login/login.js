 [
   {
      id: "1",
      index: "1",
      name: "22",
      level: "1"
   },
   {
      id: "2",
      index: "2",
      name: "333",
      level: "1",
   },
   {
      id: "3",
      index: 3,
      name: "444",
      level : 1,
      level_name: "iiii",
      conent: ""
   },
   {
       id: "4", 
       index: 4,
       name: "55",
       level: 1,
       level_name: "iiii"
   }
]

var login = {
    init: function(){
        var self = this;
        var $authCodeBtn = $("#authCodeBtn");
        $authCodeBtn.on("click", function(e){
            $(this).prop("disabled", true);
            self.timeout();
        });

        $(".login").on("click", function(e){
            var tel = $("#loginForm").find("[name='tel']").val();
            var code = $("#loginForm").find("[name='code']").val();

            e.preventDefault();

            if(!tel){
                alert("请输入手机号");
                return;
            }
            if(!code){
                e.preventDefault();
                alert("请输入验证码");
            }
            
            $.ajax({
              method: "POST",
              url: "/login/dologin",
              data: {tel: tel, code: code}
            }).done(function(data){
              if(data.code == 0){
                if(data.type == 1){
                  location.replace("/admin/index");
                }else {
                  location.replace("/lawinfo");
                }
              }else{
                alert(data.error);
              }
            }).error(function(){
              alert("未知错误");
            });
        });
    },
    timeout: function(){
        var timeout = 60;

        var func = function(){
            timeout--;
            $("#authCodeBtn").html("倒计时" + timeout + "秒");
            if(timeout == 0){
               clearTimeout(self.time);
               $("#authCodeBtn").prop("disabled", false);
            }else{
               self.time = setTimeout(func, 1000);
            }
        };
        func();
    }
};  


module.exports = login;