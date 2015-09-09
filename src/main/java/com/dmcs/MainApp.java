package com.dmcs;

import com.dmcs.config.Config;
import com.dmcs.domain.Actor;
import com.dmcs.domain.Movie;
import com.dmcs.service.ActorService;
import com.dmcs.service.MovieService;
import com.dmcs.view.OverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Main App class
 *
 * Created by chrustu on 14.06.2015.
 */
public class MainApp extends Application{
    private Stage primaryStage;
    private BorderPane rootLayout;
    private static AnnotationConfigApplicationContext applicationContext;
    private ObservableList<Movie> movies = FXCollections.observableArrayList();

    public static void main(String[] args) {
        Set<ConstraintViolation<Movie>> validationResult;

        applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.getEnvironment().setActiveProfiles("hibernate");
        applicationContext.register(Config.class);
        applicationContext.refresh();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MovieApp");

        initRootLayout();
        showMovieOverview();
    }

    /**
     * Initialize the root layout.
     */
    public void initRootLayout() {
        try {
            //Load root layout from fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            //Show the scane containing root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showMovieOverview() {
        try {
            //Load persons overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Overview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            OverviewController controller = loader.getController();
            controller.setMovieService(applicationContext.getBean(MovieService.class));
            controller.setActorService(applicationContext.getBean(ActorService.class));

            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Movie> getMovies(){
        return movies;
    }

    /**
     * Returns the main stage.
     * @return Stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
