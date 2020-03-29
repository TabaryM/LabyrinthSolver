import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.ModeleLabyrinthe;
import vues.VueLabyrinthe;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        ModeleLabyrinthe modele = new ModeleLabyrinthe(20, 20);
        modele.genereLabyrinthe("Prim");
        primaryStage.setTitle("Le LABYRINTHE d'ses morts !");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("vues/legende.fxml"));
        Pane legende = loader.load();


        BorderPane root = new BorderPane();

        Pane labyrinthe = new VueLabyrinthe(modele);
        //Pane controles = new VueControles(modele);

        root.setCenter(labyrinthe);
        root.setBottom(legende);

        primaryStage.setScene(new Scene(root, root.getPrefWidth(), root.getPrefHeight()));
        primaryStage.show();

    }

}
