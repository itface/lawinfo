package com.lawinfo.service.sms;

import com.lawinfo.service.sms.enumtype.EnSendSmsResultEnum;

/**
 * Created by wangrongtao on 15/10/22.
 */
public interface EnSendSmsService {
    public EnSendSmsResultEnum sendSms(String phoneno);
}
