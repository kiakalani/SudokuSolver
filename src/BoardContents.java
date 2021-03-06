import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Kia Kalani
 * @version 1.00
 * <code>BoardContents</code> is the container of the objects related to the game.
 * <p>Note:</p>
 * For further information about the usages of this class see {@link SolvePage}
 */
public class BoardContents {
    /**
     * The grid of images for displaying the board
     */
    private final ImageView[][] numbers = new ImageView[9][9];
    /**
     * The grid of rectangles for showing behind the board.
     */
    private final Rectangle[][] rectangles = new Rectangle[9][9];
    /**
     * A gridpane containing all of the game related elements in itself.
     */
    private GridPane boardContents;
    /**
     * The controller of all the game related objects.
     */
    private NumberController numberController;
    /**
     * The board of the game as a grid.
     */
    private int[][] board;

    /**
     * The constructor
     *
     * @param board   the initial given board
     * @param display the pane showing the contents.
     */
    public BoardContents(int[][] board, Group display) {
        setBoardContents(display);
        setRectangles();
        setNumbers(board);
        this.board = board;
        numberController = new NumberController(numbers, rectangles, display, board);
    }

    /**
     * The getter of the board
     *
     * @return the contents of the board
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * This method initializes the gridpane with the row size of 9 and column size of 9.
     *
     * @param display is the default pane displaying the game contents.
     */
    private void setBoardContents(Group display) {
        boardContents = new GridPane();
        for (int r = 0; r < 9; r++) {
            boardContents.addRow(r);
            boardContents.addColumn(r);
        }
        boardContents.relocate(250, 50);
        display.getChildren().add(boardContents);
    }

    /**
     * This method initializes all of the numbers according to the data from the board.
     *
     * @param board is the sudoku board.
     */
    private void setNumbers(int[][] board) {
        for (int r = 0; r < numbers.length; r++) {
            for (int c = 0; c < numbers[r].length; c++) {
                numbers[r][c] = new ImageView("Nums/" + board[r][c] + ".jpg");
                numbers[r][c].setFitWidth(50);
                numbers[r][c].setFitHeight(50);
                boardContents.add(numbers[r][c], c, r);
            }
        }
    }

    /**
     * This method initializes all of the rectangles.
     */
    private void setRectangles() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                rectangles[r][c] = new Rectangle(50, 50, Color.BLACK);
                boardContents.add(rectangles[r][c], c, r);
            }
        }
    }
}
