import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Kia Kalani
 * <code>Execute</code> method is the class responsible for illustrating the
 * GUI to the user.
 * <p>Credits:</p>
 * Special thanks to https://github.com/dimitri/sudoku/blob/master/sudoku.txt for
 * providing the boards for this project.
 * @version 1.00
 */
public class Execute extends Application {
    /**
     * The stage of the program
     */
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        stage.setResizable(false);
        changeScene(new SolvePage(Solve.readBoard(Solve.randomDir())).getScene());
        primaryStage.show();
    }

    /**
     * This method would change the <code>Scene</code> of the
     * <code>Stage</code> of this project.
     *
     * @param scene the new scene for displaying contents.
     */
    public static void changeScene(Scene scene) {
        primaryStage.setScene(scene);
    }

    /**
     * The main method
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

}
