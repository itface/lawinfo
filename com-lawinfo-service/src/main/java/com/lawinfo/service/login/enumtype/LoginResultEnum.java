package com.lawinfo.service.login.enumtype;

/**
 * Created by wangrongtao on 15/10/22.
 */
public enum LoginResultEnum {

    SUCCESS(0,"登录成功"),
    UID_PWD_EMPTY(-1,"用户名密码不能为空"),
    PWD_INCORRECT(-2,"密码错误"),
    PWD_NOT_EXIST(-3,"验证码不存在，请先获取验证码"),
    MAX_LOGIN_FAIL(-4,"超过最大失败次数"),
    LOGIN_EXCEPTION(-99,"登录异常");

    private int code;
    private String desc;

    LoginResultEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
