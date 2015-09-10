package com.dmcs.service;

import com.dmcs.dao.ActorDAOInterface;
import com.dmcs.domain.Actor;
import com.dmcs.domain.Movie;
import com.dmcs.event.NewActorEvent;
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
public class ActorService {
    @Autowired
    private ActorDAOInterface actorDAO;

    @Autowired
    private ApplicationEventPublisher publisher;

    public Set<ConstraintViolation<Actor>> add(Actor actor) {
        this.publisher.publishEvent(new NewActorEvent(actor));
        actorDAO.addOrUpdate(actor);
        return null;
    }

    public Set<ConstraintViolation<Actor>> update(Actor actor) {
        actorDAO.addOrUpdate(actor);
        return null;
    }

    public void delete(Actor actor){
        actorDAO.delete(actor);
    }

    public Actor receive(Integer actorId) {
        return actorDAO.receiveActor(actorId);
    }

    public List<Actor> receiveAll() {
        return actorDAO.receiveActors();
    }
}
