package com.dmcs.domain;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chrustu on 07.09.2015.
 */
public class ActorTest {

    @Test
    public void shouldHasId() throws Exception {
        Actor actor = new Actor();
        Integer value = new Integer(1);
        actor.setId(value);
        Assert.assertEquals(value, actor.getId());
    }
}