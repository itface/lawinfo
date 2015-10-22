package com.lawinfo.service.sms.enumtype;

/**
 * Created by wangrongtao on 15/10/22.
 */
public enum  EnSendSmsResultEnum {

    SUCCESS(0,"发送成功"),
    PHONENO_TOO_MUCH(-1,"一次发送的手机号码过多"),
    USERNAME_WRONG(-2,"登录账户错误"),
    PWD_WRONG(-3,"密码错误"),
    BALANCE_NOT_ENOUGH(-4,"余额不足"),
    TIMEOUT(-5,"超时[注意检查服务器系统时间]"),
    CODE_INCORRECT(-6,"code参数不合法"),
    NOT_GET(-7,"用成POST了，正确应该是GET"),
    USERNAME_EMPTY(-8,"username参数丢失"),
    PWD_EMPTY(-9,"pwd参数丢失"),
    MSG_INCORRECT(-10,"msg参数丢失 或者 msg为空信息 或 msg 编码不对"),
    MOBILES_EMPTY(-11,"mobiles参数丢失"),
    DT_EMPTY(-12,"dt参数丢失"),
    CONTENT_INCORRECT(-13,"一次下发短信超过了400个字"),
    MOBILES_INCORRECT(-14,"mobiles参数不对"),
    IP_INCORRECT(-15,"IP鉴权失败"),
    INVALID_SEND_SMS(-16,"获取验证码太频繁"),
    SEND_EXCEPTION(-100,"其他错误");

    private int code;
    private String desc;

    EnSendSmsResultEnum(int code, String desc) {
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

    public static EnSendSmsResultEnum findByCode(int code) {
        EnSendSmsResultEnum[] enSendSmsResultEnums = EnSendSmsResultEnum.values();
        for (int i = 0; i < enSendSmsResultEnums.length; i++) {
            if (code == enSendSmsResultEnums[i].code) {
                return enSendSmsResultEnums[i];
            }
        }
        return null;
    }
}
