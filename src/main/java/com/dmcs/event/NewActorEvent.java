package com.dmcs.event;

import com.dmcs.domain.Actor;
import com.dmcs.domain.Movie;
import org.springframework.context.ApplicationEvent;

/**
 * Created by chrustu on 17.06.2015.
 */
public class NewActorEvent extends ApplicationEvent {
    public NewActorEvent(Actor source) {
        super(source);
    }
}