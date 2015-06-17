package main.java.com.dmcs;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.com.dmcs.domain.Movie;

/**
 * Main App class
 *
 * Created by chrustu on 14.06.2015.
 */
public class MainApp extends Application{
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Movie> movies = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

//        initRootLayout();
//        showPersonOverview();
    }

}
