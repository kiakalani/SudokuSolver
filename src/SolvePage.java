import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * @author Kia Kalani
 * <code>SolvePage</code> class contains all of the components related to the game
 * itself.
 * <p>Important:</p>
 * This class is the only container of the GUI throughout the whole project and
 * for more information about the usage of this class see {@link Execute}
 * @version 1.00
 */
public class SolvePage extends Group {
    /**
     * This scene of the GUI.
     */
    private final Scene scene = new Scene(this,1024,768);
    /**
     * The CSS code containing the design of the buttons
     */
    private final static String btnStyle = "-fx-background-color: #000000;\n" +
            "-fx-text-fill: green;\n" +
            "-fx-alignment: CENTER;";
    /**
     * The object of all of the board contents.
     * @see BoardContents
     */
    private BoardContents boardContents;
    /**
     * A <code>Button</code> responsible for demonstarting the solved board
     * to the user.
     */
    private Button solve;

    /**
     * The constructor.
     * @param board the board of the game.
     */
    public SolvePage(int[][] board) {
        scene.setFill(Color.rgb(120,120,120));
        boardContents = new BoardContents(board,this);
        setSolveBtn();
        setNewBtn();
    }

    /**
     * This method initializes the solve button as well as setting the actions of
     * it. Furthermore, by pressing the button, the solved board would be displayed
     * to the user.
     */
    private void setSolveBtn() {
        solve = new Button("Solve this Board!");
        solve.relocate(350,600);
        solve.setStyle(btnStyle);
        getChildren().add(solve);
        solve.setPrefWidth(250);
        solve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Execute.changeScene(new SolvePage(Solve.getSolvedAnswer(boardContents.getBoard())).getScene());
            }
        });
    }

    /**
     * This method sets up a button for giving the player the option to choose a new board.
     */
    private void setNewBtn(){
        Button newBoard = new Button("Play a New Board");
        newBoard.setPrefWidth(250);
        newBoard.relocate(350,640);
        newBoard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Execute.changeScene(new SolvePage(Solve.readBoard(Solve.randomDir())).getScene());
            }
        });
        newBoard.setStyle(btnStyle);
        getChildren().add(newBoard);
    }
}
