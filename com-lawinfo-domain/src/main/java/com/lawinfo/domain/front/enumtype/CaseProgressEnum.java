package com.lawinfo.domain.front.enumtype;

/**
 * Created by wangrongtao on 15/11/11.
 */
public enum CaseProgressEnum {

    LEVEL_1(100,"合约恰谈",100,0),
    LEVEL_1_STEP_1(200,"银行委托恰谈",200,100),
    LEVEL_1_STEP_2(300,"合同已签订",300,100),
    LEVEL_1_STEP_3(400,"初期律师费已支出",400,100),
    LEVEL_1_STEP_4(500,"诉前财产保全",500,100),

    LEVEL_2(600,"一审立案",600,0),
    LEVEL_2_STEP_1(700,"立案",700,600),
    LEVEL_2_STEP_2(800,"财产保全",800,600),
    LEVEL_2_STEP_3(900,"文书送达",900,600),
    LEVEL_2_STEP_4(1000,"确定开庭日",1000,600),

    LEVEL_3(1100,"一审开庭",1100,0),
    LEVEL_3_STEP_1(1200,"已开庭",1200,1100),
    LEVEL_3_STEP_2(1300,"代理文书提交",1300,1100),
    LEVEL_3_STEP_3(1400,"判决书跟进",1400,1100),

    LEVEL_4(1500,"一审调解",1500,0),
    LEVEL_4_STEP_1(1600,"是否调解",1600,1500),

    LEVEL_5_STEP_1(1700,"一审判决",1700,0),
    LEVEL_5_STEP_2(1800,"法院已出具判决书",1800,1700),
    LEVEL_5_STEP_3(1900,"判决送达",1900,1700),


    LEVEL_6_STEP_1(2000,"上诉期",2000,0),
    LEVEL_6_STEP_2(2100,"是否上诉",2100,2000),

    LEVEL_7_STEP_1(2200,"二审立案",2200,0),
    LEVEL_7_STEP_2(2300,"立案",2300,2200),
    LEVEL_7_STEP_3(2400,"文书送达",2400,2200),
    LEVEL_7_STEP_4(2500,"确定开庭日",2500,2200),

    LEVEL_8_STEP_1(2600,"二审开庭",2600,0),
    LEVEL_8_STEP_2(2700,"已开庭",2700,2600),
    LEVEL_8_STEP_3(2800,"代理文书提交",2800,2600),
    LEVEL_8_STEP_4(2900,"判决书跟进",2900,2600),

    LEVEL_9_STEP_1(3000,"二审调解",3000,0),
    LEVEL_9_STEP_2(3100,"是否调解",3100,3000),

    LEVEL_10_STEP_1(3200,"二审判决",3200,0),
    LEVEL_10_STEP_2(3300,"法院已出具判决书",3300,3200),
    LEVEL_10_STEP_3(3400,"判决书送达",3400,3200),

    LEVEL_11_STEP_1(3500,"执行阶段",3500,0),
    LEVEL_11_STEP_2(3600,"已申请执行立案",3600,3500),
    LEVEL_11_STEP_3(3700,"已确定法官",3700,3500),
    LEVEL_11_STEP_4(3800,"执行文书送达",3800,3500),
    LEVEL_11_STEP_5(3900,"财产拍卖",3900,3500),
    LEVEL_11_STEP_6(4000,"结案",4000,3500),

    LEVEL_12_STEP_1(4100,"律师费结算",4100,0),
    LEVEL_12_STEP_2(4200,"提交律师费结算",4200,4100),
    LEVEL_12_STEP_3(4300,"开出发票",4300,4100),
    LEVEL_12_STEP_4(4400,"律师费到帐",4400,4100);

    private int id;
    private String name;
    private int index;
    private int parentid;

    CaseProgressEnum(int id, String name, int index, int parentid) {
        this.id = id;
        this.name = name;
        this.index = index;
        this.parentid = parentid;
    }
}
