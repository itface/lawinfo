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

        $("#loginForm").on("submit", function(e){
            var tel = $(this).find("[name='tel']").val();
            var code = $(this).find("[name='code']").val();

            if(!tel){
                e.preventDefault();
                alert("请输入手机号");
                return;
            }
            if(!code){
                e.preventDefault();
                alert("请输入验证码");
            }
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