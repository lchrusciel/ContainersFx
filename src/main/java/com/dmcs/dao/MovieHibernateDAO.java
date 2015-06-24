package com.dmcs.dao;

import com.dmcs.domain.Movie;
import com.dmcs.event.NewMovieEvent;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * Created by chrustu on 17.06.2015.
 */
@Transactional
@Repository
@Profile("hibernate")
public class MovieHibernateDAO implements MovieDAOInterface {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ApplicationEventPublisher publisher;

    public void addOrUpdateMovie(Movie movie) {
        this.publisher.publishEvent(new NewMovieEvent(movie));
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(movie);
    }

    public void deleteMovie(Movie movie) {
        Session session = sessionFactory.getCurrentSession();
        if (movie != null) {
            session.delete(movie);
        }
    }

    public Movie receiveMovie(Integer movieId) {
        Session session = sessionFactory.getCurrentSession();
        return (Movie) session.get(Movie.class, movieId);
    }

    public List<Movie> receiveAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Movie.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
}