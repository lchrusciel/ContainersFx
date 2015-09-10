package com.dmcs.dao;

import com.dmcs.domain.Actor;
import com.dmcs.domain.Movie;
import com.dmcs.event.NewActorEvent;
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
public class HibernateDAO implements MovieDAOInterface, ActorDAOInterface {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addOrUpdate(Movie movie) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(movie);
    }

    @Override
    public void delete(Movie movie) {
        Session session = sessionFactory.getCurrentSession();
        if (movie != null) {
            session.delete(movie);
        }
    }

    @Override
    public Movie receiveMovie(Integer movieId) {
        Session session = sessionFactory.getCurrentSession();
        return (Movie) session.get(Movie.class, movieId);
    }

    @Override
    public List<Movie> receiveMovies() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Movie.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public void addOrUpdate(Actor actor) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(actor);
    }

    @Override
    public void delete(Actor actor) {
        Session session = sessionFactory.getCurrentSession();
        if (actor != null) {
            session.delete(actor);
        }
    }

    @Override
    public Actor receiveActor(Integer actorId) {
        Session session = sessionFactory.getCurrentSession();
        return (Actor) session.get(Movie.class, actorId);
    }

    @Override
    public List<Actor> receiveActors() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Actor.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
}