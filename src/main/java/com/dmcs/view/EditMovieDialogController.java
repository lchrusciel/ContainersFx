package com.dmcs.view;

import com.dmcs.domain.Movie;
import com.dmcs.service.MovieService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.ConstraintViolation;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by chrustu on 23.06.2015.
 */
@Controller
public class EditMovieDialogController {
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField directorTextField;
    @FXML
    private TextField productionYearTextField;

    @Autowired
    private MovieService movieService;

    private Stage stage;
    private Movie movie;

    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        titleTextField.setText(movie.getTitle());
        directorTextField.setText(movie.getDirector());
        productionYearTextField.setText(movie.getProductionYear());
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    public void handleCancel() {
        stage.close();
    }

    public void handleOk() {
        if (null == this.movie) {
            this.movie = new Movie();
        }

        this.movie.setTitle(titleTextField.getText());
        this.movie.setDirector(directorTextField.getText());
        this.movie.setProductionYear(productionYearTextField.getText());
        Set<ConstraintViolation<Movie>> errors;

        if (null == (errors = movieService.addOrUpdate(this.movie))) {
            stage.close();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid fields");
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(printValidationResult(errors));

        alert.showAndWait();
    }

    public Movie getMovie() {
        return this.movie;
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
