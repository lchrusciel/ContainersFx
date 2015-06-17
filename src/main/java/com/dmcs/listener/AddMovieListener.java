package main.java.com.dmcs.listener;

import main.java.com.dmcs.domain.Movie;
import main.java.com.dmcs.event.NewMovieEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by chrustu on 17.06.2015.
 */
public class AddMovieListener implements ApplicationListener<NewMovieEvent> {
    @Override
    public void onApplicationEvent(NewMovieEvent event) {
        Movie movie = (Movie)event.getSource();
        System.out.println("New Movie was created with title: " + movie.getTitle());
    }
}
