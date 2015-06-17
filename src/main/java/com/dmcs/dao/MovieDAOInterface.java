package main.java.com.dmcs.dao;

import main.java.com.dmcs.domain.Movie;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by chrustu on 17.06.2015.
 */
@Component
public interface MovieDAOInterface {
    public abstract void addMovie(Movie movie);
    public abstract Movie receiveMovie(Integer movieId);
    public abstract List<Movie> receiveAll();
}
