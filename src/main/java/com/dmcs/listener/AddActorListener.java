package com.dmcs.listener;

import com.dmcs.domain.Actor;
import com.dmcs.domain.Movie;
import com.dmcs.event.NewActorEvent;
import com.dmcs.event.NewMovieEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by chrustu on 7.07.2015.
 */
@Component
public class AddActorListener implements ApplicationListener<NewActorEvent> {
    @Override
    public void onApplicationEvent(NewActorEvent event) {
        Actor actor = (Actor)event.getSource();
        System.out.printf("Actor with name %s was created.", actor.getFirstName() + " " + actor.getLastName());
    }
}
