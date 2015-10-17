package com.lawinfo.admin.system.event;

import com.lawinfo.domain.org.User;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * Created by wangrongtao on 15/10/17.
 */
//@Service
public class UserPublisher implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void saveUser() {
        UserEvent userEvent = new UserEvent(this);
        applicationEventPublisher.publishEvent(userEvent);
    }
}
