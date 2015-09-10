package com.dmcs.view;

import com.dmcs.domain.Actor;
import com.dmcs.domain.Movie;
import com.dmcs.service.ActorService;
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
import java.util.ArrayList;
import java.util.List;

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
    private ActorService actorService;

    static private final String EDIT = "edit";
    static private final String DELETE = "delete";

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

    public void setActorService(ActorService actorService) {
        this.actorService = actorService;
    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param movie the movie or null
     */
    private void showMovieDetails(Movie movie) {
        if (movie != null) {
            List<Actor> actors;
            // Fill the labels with info from the person object.
            titleLabel.setText(movie.getTitle());
            directorLabel.setText(movie.getDirector());
            productionYearLabel.setText(movie.getProductionYear());

            if (null == (actors = movie.getActors())) {
                actors = new ArrayList<Actor>();
            }
            actorsTable.setItems(FXCollections.observableArrayList(actors));
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
    private void handleNewMovie() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OverviewController.class.getResource("EditMovieDialog.fxml"));
            AnchorPane newMovie = (AnchorPane) loader.load();
            Scene scene = new Scene(newMovie);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Create new movie");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);

            EditMovieDialogController controller = loader.getController();
            controller.setMovieService(movieService);
            controller.setStage(dialogStage);

            dialogStage.showAndWait();
            updateView(controller.getMovie());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when he user clicks on the new button
     */
    @FXML
    private void handlEditMovie() {
        try {
            if (null == movieTable.getSelectionModel().getSelectedItem()) {
                noChoosenMovie(EDIT);
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
            updateView(controller.getMovie());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when he user clicks on the delete button
     */
    @FXML
    private void handleDeleteMovie() {
        Movie movie;

        if (null == (movie = movieTable.getSelectionModel().getSelectedItem())) {
            noChoosenMovie(DELETE);
            return;
        }

        movieService.delete(movie);
        movieTable.setItems(FXCollections.observableArrayList(movieService.receiveAll()));
    }

    /**
     * Called when he user clicks on the new actor button
     */
    @FXML
    private void handleNewActor() {
        try {
            if (null == movieTable.getSelectionModel().getSelectedItem()) {
                noChoosenMovie(EDIT);
                return;
            }

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OverviewController.class.getResource("EditActorDialog.fxml"));
            AnchorPane newActor = (AnchorPane) loader.load();
            Scene scene = new Scene(newActor);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Create new actor");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(scene);

            EditActorDialogController controller = loader.getController();
            controller.setActorService(actorService);
            controller.setStage(dialogStage);
            controller.setMovie(movieTable.getSelectionModel().getSelectedItem());

            dialogStage.showAndWait();
            updateViewWithActor(controller.getMovie(), controller.getActor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEditActor() {
        try {
            if (null == actorsTable.getSelectionModel().getSelectedItem()) {
                noChoosenActor(EDIT);
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
            controller.setActorService(actorService);
            controller.setStage(dialogStage);
            controller.setMovie(movieTable.getSelectionModel().getSelectedItem());
            controller.setActor(actorsTable.getSelectionModel().getSelectedItem());

            dialogStage.showAndWait();
            updateViewWithActor(controller.getMovie(), controller.getActor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when he user clicks on the delete button
     */
    @FXML
    private void handleDeleteActor() {
        Actor actor;

        if (null == (actor = actorsTable.getSelectionModel().getSelectedItem())) {
            noChoosenActor(DELETE);
            return;
        }

        actorService.delete(actor);
        updateView(movieTable.getSelectionModel().getSelectedItem());
    }

    private void updateView(Movie movie) {
        movieTable.getItems().clear();
        movieTable.getSelectionModel().clearSelection();
        movieTable.setItems(FXCollections.observableArrayList(movieService.receiveAll()));

        if (null != movie) {
            movie = movieService.receive(movie.getId());
            movieTable.getSelectionModel().select(movie);
        }
    }

    private void updateViewWithActor(Movie movie, Actor actor) {
        updateView(movie);
        actorsTable.getSelectionModel().clearSelection();
        actorsTable.getSelectionModel().select(actor);
    }

    private void noChoosenMovie(String action) {
        renderWarning("movie", action);
    }
    private void noChoosenActor(String action) {
        renderWarning("actor", action);
    }

    private void renderWarning(String subject, String action) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Choose " + subject + "!");
        alert.setHeaderText("You did not choose the " + subject + ".");
        alert.setContentText("You have to choose the " + subject + " before you will be able to " + action + " it.");
        alert.showAndWait();
    }
}
