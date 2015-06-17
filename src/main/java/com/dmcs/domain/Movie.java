package main.java.com.dmcs.domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

/**
 * Movie model class.
 *
 * Created by chrustu on 14.06.2015.
 */
public class Movie {

    private IntegerProperty id;
    private StringProperty title;
    private StringProperty director;
    private ObjectProperty<LocalDate> productionYear;
    private ObjectProperty<List<Actor>> actors;

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
