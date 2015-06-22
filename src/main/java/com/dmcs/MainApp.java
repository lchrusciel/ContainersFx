package com.dmcs;

import com.dmcs.config.Config;
import com.dmcs.domain.Actor;
import com.dmcs.domain.Movie;
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
import java.time.LocalDate;
import java.time.LocalTime;
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
    private ObservableList<Movie> movies = FXCollections.observableArrayList();

    public static void main(String[] args) {
        Set<ConstraintViolation<Movie>> validationResult;

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.getEnvironment().setActiveProfiles("hibernate");
        applicationContext.register(Config.class);
        applicationContext.refresh();


        MovieService movieService = applicationContext.getBean(MovieService.class);
        Movie movie1 = new Movie();
        movie1.setDirector("Jackson");
        movie1.setProductionYear(LocalDate.of(1999, 2, 21));
        movie1.setTitle("Hobbit");

        Actor actor1 = new Actor();
        actor1.setFirstName("John");
        actor1.setLastName("Doe");
        actor1.setMovie(movie1);

        Actor actor2 = new Actor();
        actor2.setFirstName("Jenny");
        actor2.setLastName("Doe");
        actor2.setMovie(movie1);

        Actor actor3 = new Actor();
        actor3.setFirstName("Jack");
        actor3.setLastName("Doe");
        actor3.setMovie(movie1);

        List<Actor> actorsList = new ArrayList<Actor>();
        actorsList.add(actor1);
        actorsList.add(actor2);
        actorsList.add(actor3);

        movie1.setActors(actorsList);
        movieService.addMovie(movie1);
        System.out.println(movieService.receiveAll());

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
            controller.setMainApp(this);

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
