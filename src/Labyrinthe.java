import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import labyrinthe.modele.Laby;
import labyrinthe.modele.Point;

import java.io.IOException;

public class Labyrinthe /*extends Application*/ {

    public static void main(String[] args) {
        Laby laby = new Laby(new Point(10,10));
        System.out.println(laby);
        //launch(args);

    }
/*
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("labyrinthe/vue/sample.fxml"));
        primaryStage.setTitle("Labyrinthe");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }*/
}
