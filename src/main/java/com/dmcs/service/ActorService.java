package com.dmcs.service;

import com.dmcs.dao.ActorDAOInterface;
import com.dmcs.domain.Actor;
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
public class ActorService {
    @Autowired
    private ActorDAOInterface actorDAO;

    public Set<ConstraintViolation<Actor>> addOrUpdate(Actor actor) {
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
