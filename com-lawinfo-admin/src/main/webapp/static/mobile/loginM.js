var login = {
    loginAlert:function(msg){
        jQuery('#loginForm .error-label').text(msg);
    },
    init: function(){
        var self = this;
        var $authCodeBtn = $("#authCodeBtn");
        $authCodeBtn.on("click", function(e){
            var _self = this;
            var tel = $("#loginForm").find("[name='tel']").val();
            if(tel==null||tel==''||!self.checkPhoneNo(tel)){
                self.loginAlert("请输入手机号");
                return;
            }
            $(_self).prop("disabled", true);
            $.ajax({
              url: "/login/sendsms?tel="+tel,
              dataType: "json",
                cache:false,
              success: function(data){
                  if(data.code == 0){
                      self.timeout();
                      self.loginAlert("验证码已发送");
                  }else{
                      $(_self).prop("disabled", false);
                      self.loginAlert(data.desc);
                  }
              },
              error: function(){
                $(_self).prop("disabled", false);
                  self.loginAlert("获取验证码失败");
              }
            })
        });

        $(".login").on("click", function(e){
            var tel = $("#loginForm").find("[name='tel']").val();
            var code = $("#loginForm").find("[name='code']").val();
            var wechatopenid = $("#wechatopenid").val();
            e.preventDefault();

            if(!tel){
                self.loginAlert("请输入手机号");
                return;
            }
            if(!code){
                e.preventDefault();
                self.loginAlert("请输入验证码");
            }
            
            $.ajax({
              method: "POST",
              url: "/login/mobile/dologin",
              data: {tel: tel, code: code,wechatopenid:wechatopenid},
                cache:false,
              dataType: "json",
            }).done(function(data){
              if(data.success){
                  location.replace(data.redirecturl);
              }else{
                  self.loginAlert(data.desc);
              }
            }).error(function(){
                self.loginAlert("未知错误");
            });
        });
    },
    checkPhoneNo:function(no){
        var pattern=/(^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$)|(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;
        if(pattern.test(no)) {
            return true;
        }else{
            return false;
        }
    },
    timeout: function(){
        var timeout = 60;

        var func = function(){
            timeout--;
            if (timeout==0) {
                $("#authCodeBtn").html("获取验证码");
            }else{
                $("#authCodeBtn").html("倒计时" + timeout + "秒");
            }
            if(timeout == 0){
               clearTimeout(self.time);
               $("#authCodeBtn").prop("disabled", false);
            }else{
               self.time = setTimeout(func, 1000);
            }
        };
        func();
    }
}