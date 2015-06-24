package com.dmcs.view;

import com.dmcs.domain.Actor;
import com.dmcs.domain.Movie;
import com.dmcs.service.MovieService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by chrustu on 24.06.2015.
 */
public class EditActorDialogController {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;

    private Stage stage;
    private Actor actor;
    private Movie movie;

    @Autowired
    private MovieService movieService;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    public void handleCancel() {
        stage.close();
    }

    public void handleOk() {
        Set<ConstraintViolation<Movie>> errors;

        this.actor = new Actor();

        this.actor.setFirstName(firstName.getText());
        this.actor.setLastName(lastName.getText());
        this.actor.setMovie(movie);

        movie.getActors().add(actor);

        if (null == (errors = movieService.addOrUpdateMovie(movie))) {
            stage.close();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid fields");
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(printValidationResult(errors));

        alert.showAndWait();
    }

    private static String printValidationResult(Set<ConstraintViolation<Movie>> validationResult) {
        StringBuilder message = new StringBuilder();
        for (Iterator iterator = validationResult.iterator(); iterator
                .hasNext();) {
            ConstraintViolation<Movie> constraintViolation = (ConstraintViolation<Movie>) iterator
                    .next();
            message.append("Invalid value: "
                    + constraintViolation.getPropertyPath() + " message: " + constraintViolation.getMessage() + "\n");
        }
        return message.toString();
    }
}
