package com.dmcs.domain;

import javafx.beans.property.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Movie model class.
 *
 * Created by chrustu on 14.06.2015.
 */
@Entity
public class Movie {

    private IntegerProperty id = new SimpleIntegerProperty(0);
    private StringProperty title = new SimpleStringProperty("title");
    private StringProperty director = new SimpleStringProperty("director");
    private ObjectProperty<LocalDate> productionYear = new SimpleObjectProperty<LocalDate>();
    private ObjectProperty<List<Actor>> actors = new SimpleObjectProperty<List<Actor>>();

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

    @NotBlank
    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    @NotBlank
    public String getDirector() {
        return director.get();
    }

    public void setDirector(String director) {
        this.director.set(director);
    }

    public StringProperty directorProperty() {
        return director;
    }

    @NotBlank
    public LocalDate getProductionYear() {
        return productionYear.get();
    }

    public void setProductionYear(LocalDate productionYear) {
        this.productionYear.set(productionYear);
    }

    @OneToMany(mappedBy="movie")
    @Cascade(value= CascadeType.ALL)
    public ObjectProperty<LocalDate> productionYearProperty() {
        return productionYear;
    }

    public List<Actor> getActors() {
        return actors.get();
    }

    public void setActors(List<Actor> actors) {
        this.actors.set(actors);
    }

    public ObjectProperty<List<Actor>> actorsProperty() {
        return actors;
    }
}
