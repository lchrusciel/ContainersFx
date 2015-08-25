package com.dmcs.dao;

import com.dmcs.domain.Actor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by chrustu on 07.07.2015.
 */
@Component
public interface ActorDAOInterface {
    void addOrUpdate(Actor actor);

    void delete(Actor actor);

    Actor receiveActor(Integer actorId);

    List<Actor> receiveActors();
}
