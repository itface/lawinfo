package com.lawinfo.service.sms.impl;

/**
 * Created by wangrongtao on 15/10/22.
 */
public class EnSendSmsServiceImpl{
/*
    private static Logger logger = LoggerFactory.getLogger(EnSendSmsServiceImpl.class);

    private  String address;
    private  String userid;
    private  String pwd;
    private  long expiretime;
    private static final String PREFIX = "【剪刀手科技】";
    @Resource
    private SmsService smsService;
    @Resource
    private UserService userService;

    *//*
    * 如uid是：test，登录密码是：123123
    pwd=md5(123123test),即
    pwd=b9887c5ebb23ebb294acab183ecf0769

    线生成地址：http://www.sms.cn/password
    *//*
    @Override
    public EnSendSmsResultEnum sendSms(String phoneno) {
        try {
            if (!StringUtils.isEmpty(phoneno)&&isMobileNO(phoneno)) {
                Long lastInvalidSendSmsTime = InvalidSendSmsCache.cache.get(phoneno,new Callable<Long>(){
                    @Override
                    public Long call() throws Exception {
                        return 1l;
                    }
                });
                if (lastInvalidSendSmsTime!=null&&lastInvalidSendSmsTime==1l) {
                    //把发送过验证码的放到缓存中，不管是不是正确用户，都是禁止频繁获取验证码
                    InvalidSendSmsCache.cache.put(phoneno,new Date().getTime());
                    UserQuery userQuery = new UserQuery();
                    userQuery.setUserid(phoneno);
                    List<User> users = userService.findList(userQuery);
                    User user = CollectionUtils.isEmpty(users) ? null : users.get(0);
                    if (user != null) {
                        String code = getRandomStr();
                        String smsContent = getSmsContent(code);
                        String result = send(smsContent,phoneno);
                        int resultCode = Integer.parseInt(result);
                        EnSendSmsResultEnum enSendSmsResultEnum = EnSendSmsResultEnum.findByCode(resultCode);
                        if (enSendSmsResultEnum == null) {
                            return EnSendSmsResultEnum.SEND_EXCEPTION;
                        }else if(enSendSmsResultEnum == EnSendSmsResultEnum.SUCCESS) {
                            long now = new Date().getTime()+expiretime;
                            String nowStr =  DateUtils.formatDatetime(new Date((now)));
                            Sms sms = new Sms();
                            sms.setPhoneno(phoneno);
                            sms.setExpiretime(now);
                            sms.setExpiretimestr(nowStr);
                            sms.setPwd(code);
                            smsService.save(sms);
                        }
                        return enSendSmsResultEnum;
                    }else{
                        return EnSendSmsResultEnum.USERNAME_WRONG;
                    }
                }else{
                    return EnSendSmsResultEnum.INVALID_SEND_SMS;
                }
            }else{
                return EnSendSmsResultEnum.USERNAME_EMPTY;
            }
        } catch (Exception e) {
            logger.error("sendSms exception,phoneno:"+phoneno,e);
            return EnSendSmsResultEnum.SEND_EXCEPTION;
        }

    }
    public String send(String msgContent, String mobile){
        BufferedReader in = null;
        HttpURLConnection connection = null;
        String sendResult = null;
        try {
            long dt = new Date().getTime()/1000;
            String password = StrUtils.MD5(userid + pwd + dt);
            String param =
                    "?username=" + userid +
                            "&pwd=" + password +
                            "&mobiles=" + mobile +
                            "&msg=" + URLEncoder.encode(msgContent, "UTF-8") +
                            "&dt=" + dt;
            StringBuilder sb = new StringBuilder(address).append(param);//.append(URLEncoder.encode(param,"utf-8"));
            URL url = new URL(sb.toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            sendResult = in.readLine();
            logger.info("send result is "+sendResult+",mobile is "+mobile);
        } catch (IOException e) {
            logger.error("send exception,mobile:"+mobile,e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("send exception,close BufferedReader exception,mobile:"+mobile,e);
                }
            }
            if (connection!=null) {
                try {
                    connection.disconnect();
                } catch (Exception e) {
                    logger.error("send exception,disconnect connection exception,mobile:"+mobile,e);
                }
            }
        }
        return  sendResult;
    }

    public static boolean isMobileNO(String mobiles){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(170))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
    private String getSmsContent(String code) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX).append("您的登录验证码是:").append(code).append(",验证码有效期是").append(expiretime/(1000*60)).append("分钟.");
        return sb.toString();
    }
    private String getRandomStr(){
        int num =  (int)((Math.random()*9+1)*100000);
        return String.valueOf(num);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public long getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(long expiretime) {
        this.expiretime = expiretime;
    }*/
}
