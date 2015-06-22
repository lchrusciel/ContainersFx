package com.dmcs.event;

import com.dmcs.domain.Movie;
import org.springframework.context.ApplicationEvent;

/**
 * Created by chrustu on 17.06.2015.
 */
public class NewMovieEvent extends ApplicationEvent {
    public NewMovieEvent(Movie source) {
        super(source);
    }
}