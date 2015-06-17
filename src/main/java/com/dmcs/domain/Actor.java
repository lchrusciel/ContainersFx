package main.java.com.dmcs.domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

/**
 * Actor model class.
 *
 * Created by chrustu on 14.06.2015.
 */
public class Actor {

    private IntegerProperty id;
    private StringProperty firstName;
    private StringProperty lastName;
    private ObjectProperty<Movie> movie;

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

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
