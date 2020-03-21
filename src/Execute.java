import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Execute extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        stage.setResizable(false);
        changeScene(new SolvePage(Solve.readBoard(Solve.randomDir())).getScene());
        primaryStage.show();
    }

    public static void changeScene(Scene scene) {
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
