package com.dmcs.domain;

import javafx.beans.property.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Actor model class.
 *
 * Created by chrustu on 14.06.2015.
 */
@Entity
public class Actor {

    private IntegerProperty id = new SimpleIntegerProperty(0);
    private StringProperty firstName = new SimpleStringProperty("firstName");
    private StringProperty lastName = new SimpleStringProperty("lastName");
    private ObjectProperty<Movie> movie = new SimpleObjectProperty<Movie>();

    @Id
    @GeneratedValue
    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    @NotNull
    @Size(min=2,max=30)
    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    @NotNull
    @Size(min=2,max=30)
    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    @ManyToOne
    public Movie getMovie() {
        return movie.get();
    }

    public void setMovie(Movie movie) {
        this.movie.set(movie);
    }

    public ObjectProperty<Movie> movieProperty() {
        return movie;
    }

}
