package com.lawinfo.admin.system.event;

import com.lawinfo.domain.org.User;
import org.springframework.context.ApplicationEvent;

/**
 * Created by wangrongtao on 15/10/17.
 */
public class UserEvent extends ApplicationEvent {
    private User user;
    public UserEvent(Object source) {
        super(source);
    }

    public UserEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
