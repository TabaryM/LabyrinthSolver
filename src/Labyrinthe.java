import labyrinthe.modele.Laby;

public class Labyrinthe /*extends Application*/ {

    public static void main(String[] args) {
        Laby laby = new Laby(10,10);
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
