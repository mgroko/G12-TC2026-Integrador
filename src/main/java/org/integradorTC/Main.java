package org.integradorTC;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Main extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        // carga la escena principal
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/PantallaIDE.fxml"));
        scene = new Scene(fxmlLoader.load(), 925, 610);
        stage.setScene(scene);
        stage.show();
    }
        public static FXMLLoader setRoot(String fxml) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/" + fxml + ".fxml"));
            scene.setRoot(fxmlLoader.load());
            return fxmlLoader;
        }

        public static void main(String[] args) {
            launch();
        }

    }
