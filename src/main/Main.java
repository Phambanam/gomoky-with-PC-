package main;

import javafx.application.Application;
import javafx.stage.Stage;
import view.View;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            View view = new View();
            view.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
