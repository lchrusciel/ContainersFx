package com.dmcs.dao;

import com.dmcs.domain.Movie;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by chrustu on 17.06.2015.
 */
@Component
public interface MovieDAOInterface {
    void addOrUpdate(Movie movie);
    void delete(Movie id);
    Movie receiveMovie(Integer movieId);
    List<Movie> receiveMovies();
}
