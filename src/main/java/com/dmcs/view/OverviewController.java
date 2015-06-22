package com.dmcs.view;

import com.dmcs.MainApp;
import com.dmcs.domain.Movie;
import com.dmcs.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by chrustu on 19.06.2015.
 */
public class OverviewController {
    @FXML
    private TableView<Movie> movieTable;
    @FXML
    private TableColumn<Movie, String> titleColumn;
    @FXML
    private TableColumn<Movie, String> directorColumn;

    @FXML
    private Label titleLabel;
    @FXML
    private Label directorLabel;
    @FXML
    private Label productionYearLabel;

    private MainApp mainApp;

    @Autowired
    private DateUtil dateUtil;

    @FXML
    private void initialize() {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        directorColumn.setCellValueFactory(cellData -> cellData.getValue().directorProperty());

        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        movieTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        movieTable.setItems(mainApp.getMovies());
    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param movie the person or null
     */
    private void showPersonDetails(Movie movie) {
        if (movie != null) {
            // Fill the labels with info from the person object.
            titleLabel.setText(movie.getTitle());
            directorLabel.setText(movie.getDirector());
            productionYearLabel.setText(dateUtil.format(movie.getProductionYear()));
        } else {
            // Person is null, remove all the text.
            titleLabel.setText("");
            directorLabel.setText("");
            productionYearLabel.setText("");
        }
    }
}
