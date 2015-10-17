package com.lawinfo.admin.system.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * Created by wangrongtao on 15/10/17.
 */
//@Service
public class UserContextListener implements ApplicationListener<UserEvent> {
    @Override
    public void onApplicationEvent(UserEvent userEvent) {

    }
}
