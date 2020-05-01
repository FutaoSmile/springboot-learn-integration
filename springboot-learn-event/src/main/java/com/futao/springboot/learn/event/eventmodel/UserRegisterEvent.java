package com.futao.springboot.learn.event.eventmodel;

import com.futao.springboot.learn.event.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

/**
 * @author futao
 * @date 2020/5/1
 */
@Slf4j
public class UserRegisterEvent extends ApplicationEvent {

    private User source;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UserRegisterEvent(User source) {
        super(source);
        this.source = source;
        log.info("事件发生..");
    }

    @Override
    public User getSource() {
        return source;
    }
}
