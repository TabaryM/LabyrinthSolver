import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.Modele;
import modele.ModeleLabyrinthe;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        ModeleLabyrinthe modele = new ModeleLabyrinthe(20, 20);


        FXMLLoader loader = new FXMLLoader();
        URL path = getClass().getResource("vues/root.fxml");
        loader.setLocation(path);
        Pane root = loader.load();

        loadComposantsApplication(root, modele);
        primaryStage.setTitle("Le LABYRINTHE d'ses morts !");

        primaryStage.setScene(new Scene(root, 1000, 750));
        primaryStage.show();
    }


    private void loadComposantsApplication(Pane root, Modele modele) throws IOException {
        // On charge tout les élément graphiques sur la racine

    }
}
