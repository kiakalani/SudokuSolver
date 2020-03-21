import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class SolvePage extends Group {
    private final Scene scene = new Scene(this,1024,768);
    private final static String btnStyle = "-fx-background-color: #000000;\n" +
            "-fx-text-fill: green;\n" +
            "-fx-alignment: CENTER;";
    private BoardContents boardContents;
    private Button solve;
    public SolvePage(String directory) {
        scene.setFill(Color.rgb(120,120,120));
        boardContents = new BoardContents(Solve.readBoard(directory),this);
        setSolveBtn();
    }

    public SolvePage(int[][] board) {
        scene.setFill(Color.rgb(120,120,120));
        boardContents = new BoardContents(board,this);
        setSolveBtn();
        setNewBtn();
    }
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
