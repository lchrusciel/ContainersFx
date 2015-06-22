package com.dmcs.service;

import com.dmcs.dao.MovieDAOInterface;
import com.dmcs.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Set<ConstraintViolation<Movie>> addMovie(Movie movie) {
        movieDao.addMovie(movie);
        return null;
    }

    public Movie receiveMovie(Integer movieId) {
        return movieDao.receiveMovie(movieId);
    }

    public List<Movie> receiveAll() {
        return movieDao.receiveAll();
    }
}
