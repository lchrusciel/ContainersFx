package com.dmcs.listener;

import com.dmcs.domain.Movie;
import com.dmcs.event.NewMovieEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by chrustu on 17.06.2015.
 */
@Component
public class AddMovieListener implements ApplicationListener<NewMovieEvent> {
    @Override
    public void onApplicationEvent(NewMovieEvent event) {
        Movie movie = (Movie)event.getSource();
        System.out.println("New Movie was created with title: " + movie.getTitle());
    }
}
