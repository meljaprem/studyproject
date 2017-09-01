package com.prem.studyproject.listeners.events;

import com.prem.studyproject.domain.model.User;
import org.springframework.context.ApplicationEvent;

public class RegistrationUserEvent extends ApplicationEvent {

    private User user;

    public RegistrationUserEvent(User user, Object source) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
