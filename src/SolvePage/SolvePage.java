package SolvePage;

import Solver.Solve;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class SolvePage extends StackPane {
    private final Scene scene = new Scene(this,1024,768);
    private final BoardContents boardContents = new BoardContents(Solve.readBoard("C:\\Users\\Kia\\IdeaProjects\\Sudoku\\SudokuTables\\14.txt"),this);
    public SolvePage() {

    }
}
