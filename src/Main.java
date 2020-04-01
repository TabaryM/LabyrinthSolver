import controller.LabyrintheModifier;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.Modele;
import vues.VueLabyrinthe;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Modele modele = new Modele(20, 20);
        primaryStage.setTitle("Le LABYRINTHE d'ses morts !");

        BorderPane root = new BorderPane();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("vues/labyrintheModifier.fxml"));
        loader.setControllerFactory(iC->new LabyrintheModifier(modele));
        Pane labyrintheModifier = loader.load();

        Pane labyrinthe = new VueLabyrinthe(modele);

        root.setCenter(labyrinthe);
        root.setRight(labyrintheModifier);

        primaryStage.setScene(new Scene(root, root.getPrefWidth(), root.getPrefHeight()));
        primaryStage.show();
    }

}
