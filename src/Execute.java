import javafx.application.Application;
import javafx.stage.Stage;

public class Execute extends Application {
    private static Stage primaryStage;
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

    }

    public static void main(String[] args) {
        launch(args);
    }
}
