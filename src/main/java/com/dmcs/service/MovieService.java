package com.dmcs.service;

import com.dmcs.dao.MovieDAOInterface;
import com.dmcs.domain.Movie;
import com.dmcs.event.NewMovieEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

/**
 * Created by chrustu on 17.06.2015.
 */
@Service
public class MovieService {
    @Autowired
    private MovieDAOInterface movieDao;

    @Autowired
    private ApplicationEventPublisher publisher;

    public Set<ConstraintViolation<Movie>> add(Movie movie) {
        this.publisher.publishEvent(new NewMovieEvent(movie));
        movieDao.addOrUpdate(movie);
        return null;
    }

    public Set<ConstraintViolation<Movie>> update(Movie movie) {
        movieDao.addOrUpdate(movie);
        return null;
    }

    public void delete(Movie movie){
        movieDao.delete(movie);
    }

    public Movie receive(Integer movieId) {
        return movieDao.receiveMovie(movieId);
    }

    public List<Movie> receiveAll() {
        return movieDao.receiveMovies();
    }
}
