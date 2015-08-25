package com.dmcs.view;

import com.dmcs.domain.Actor;
import com.dmcs.domain.Movie;
import com.dmcs.service.MovieService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * Created by chrustu on 19.06.2015.
 */
@Controller
public class OverviewController {
    @FXML
    private TableView<Movie> movieTable;
    @FXML
    private TableColumn<Movie, String> titleColumn;
    @FXML
    private TableColumn<Movie, String> directorColumn;

    @FXML
    private TableView<Actor> actorsTable;
    @FXML
    private TableColumn<Actor, String> firstNameColumn;
    @FXML
    private TableColumn<Actor, String> lastNameColumn;

    @FXML
    private Label titleLabel;
    @FXML
    private Label directorLabel;
    @FXML
    private Label productionYearLabel;

//    @Autowired
    private MovieService movieService;

    @FXML
    private void initialize() {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        directorColumn.setCellValueFactory(cellData -> cellData.getValue().directorProperty());

        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // Clear movie details.
        showMovieDetails(null);

        // Listen for selection changes and show the person details when changed.
        movieTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showMovieDetails(newValue));
    }

    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
        movieTable.setItems(FXCollections.observableArrayList(movieService.receiveAll()));
    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param movie the person or null
     */
    private void showMovieDetails(Movie movie) {
        if (movie != null) {
            // Fill the labels with info from the person object.
            titleLabel.setText(movie.getTitle());
            directorLabel.setText(movie.getDirector());
            productionYearLabel.setText(movie.getProductionYear());

            actorsTable.setItems(FXCollections.observableArrayList(movie.getActors()));
        } else {
            // Person is null, remove all the text.
            titleLabel.setText("");
            directorLabel.setText("");
            productionYearLabel.setText("");
        }
    }

    /**
     * Called when he user clicks on the new button
     */
    @FXML
    private void handlEditMovie() {
        try {
            if (null == movieTable.getSelectionModel().getSelectedItem()) {
                noChoosenMovie();
                return;
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OverviewController.class.getResource("EditMovieDialog.fxml"));
            AnchorPane newMovie = (AnchorPane) loader.load();
            Scene scene = new Scene(newMovie);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit movie");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);

            EditMovieDialogController controller = loader.getController();
            controller.setMovieService(movieService);
            controller.setStage(dialogStage);
            controller.setMovie(movieTable.getSelectionModel().getSelectedItem());

            dialogStage.showAndWait();
            movieTable.getItems().clear();
            movieTable.setItems(FXCollections.observableArrayList(movieService.receiveAll()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when he user clicks on the new actor button
     */
    @FXML
    private void handleNewActor() {
        try {
            if (null == movieTable.getSelectionModel().getSelectedItem()) {
                noChoosenMovie();
                return;
            }

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OverviewController.class.getResource("EditActorDialog.fxml"));
            AnchorPane newActor = (AnchorPane) loader.load();
            Scene scene = new Scene(newActor);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit actor");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);

            EditActorDialogController controller = loader.getController();
            controller.setMovieService(movieService);
            controller.setStage(dialogStage);
            controller.setMovie(movieTable.getSelectionModel().getSelectedItem());

            dialogStage.showAndWait();
            movieTable.setItems(FXCollections.observableArrayList(movieService.receiveAll()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when he user clicks on the new button
     */
    @FXML
    private void handleNewMovie() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OverviewController.class.getResource("EditMovieDialog.fxml"));
            AnchorPane newMovie = (AnchorPane) loader.load();
            Scene scene = new Scene(newMovie);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit movie");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);

            EditMovieDialogController controller = loader.getController();
            controller.setMovieService(movieService);
            controller.setStage(dialogStage);

            dialogStage.showAndWait();
            movieTable.setItems(FXCollections.observableArrayList(movieService.receiveAll()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void noChoosenMovie() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Choose movie!");
        alert.setHeaderText("You did not choose the movie.");
        alert.setContentText("You have to choose the movie before you will be able to add actor to it.");
        alert.showAndWait();
    }

    /**
     * Called when he user clicks on the delete button
     */
    @FXML
    private void handleDeleteMovie() {
        Movie movie = movieTable.getSelectionModel().getSelectedItem();
        movieService.delete(movie);
        movieTable.setItems(FXCollections.observableArrayList(movieService.receiveAll()));
    }
}
