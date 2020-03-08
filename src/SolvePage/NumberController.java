package SolvePage;

import Solver.Solve;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Kia Kalani
 * @version 1.00
 * <code>NumberController</code> class is responsible for handling the events related to the game.
 * <p>Note:</p>
 * This class is also responsible for showing the mistakes that the player makes.
 * <p>Important:</p>
 * For further information about the usages of this class see {@link BoardContents}
 */
public class NumberController {
    /**
     * The row and column coordinates of the selected position.
     */
    private int[] selected;
    /**
     * The color for the modified part of the board.
     */
    private final Color colorModified = Color.rgb(40, 40, 40);
    /**
     * The color for showing the wrong answers.
     */
    private final Color errorColor = Color.rgb(254, 0, 0);
    /**
     * The color for the current selected item.
     */
    private final Color selectedColor = Color.BLUE;
    /**
     * The color behind everything by default.
     */
    private final Color defaultColor = Color.BLACK;
    /**
     * A copy of the board with the elements that the user changes.
     */
    private int[][] solvingBoard;

    /**
     * The constructor
     * @param nums refers to the grid of the images illustrating the numbers.
     * @param rects refers to the grid of the rectangles behind the images.
     * @param display refers to the Pane displaying the game.
     * @param board is the 2d Array of integers representing the board.
     */
    public NumberController(ImageView[][] nums, Rectangle[][] rects, Parent display, int[][] board) {
        solvingBoard = Solve.copy(board);
        selected = new int[2];
        selected[0] = -1;
        selected[1] = -1;
        controlImg(nums, rects, board);
        setKeyboardActions(display, nums, rects, board);
    }

    /**
     * This method sets up the events related to each image.
     * @param numbers refers to the grid of images showing the selected number to the user.
     * @param rectangles refers to the grid of rectangles behind the images.
     * @param board refers to the initial unsolved board.
     */
    private void controlImg(ImageView[][] numbers, Rectangle[][] rectangles, int[][] board) {
        for (int r = 0; r < numbers.length; r++) {
            for (int c = 0; c < numbers[0].length; c++) {
                final int row = r;
                final int column = c;
                numbers[r][c].setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        setDefaultColors(rectangles);
                        catchErrors(rectangles);
                        if (board[row][column] == 0) {
                            rectangles[row][column].setFill(selectedColor);
                            selected[0] = row;
                            selected[1] = column;
                        } else {
                            selected[0] = -1;
                            selected[1] = -1;
                        }
                    }
                });
            }
        }
    }

    /**
     * This method is responsible for setting all of the colors behind the images to the default color.
     * @param rectangles refers to the grid of the rectangles behind the image.
     */
    private void setDefaultColors(Rectangle[][] rectangles) {
        for (Rectangle[] rectanglelst : rectangles) {
            for (Rectangle rectangle : rectanglelst) {
                if (rectangle.getFill() != colorModified) {
                    rectangle.setFill(defaultColor);
                }
            }
        }

    }

    /**
     * This method is responsible for replacing the image according to the input number selected by the user.
     * @param display refers to the default pane displaying the game.
     * @param numbers refers to the grid of images illustrating the numbers.
     * @param rectangles refers to the grid of rectangles behind the images
     * @param board is the unsolved board
     */
    private void setKeyboardActions(Parent display, ImageView[][] numbers, Rectangle[][] rectangles, int[][] board) {
        display.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                try {
                    int typedNum = Integer.parseInt(keyEvent.getText());
                    if (selected[0] != -1 && board[selected[0]][selected[1]] == 0) {
                        numbers[selected[0]][selected[1]].setImage(new Image("SolvePage/Nums/" + typedNum + ".jpg"));
                        solvingBoard[selected[0]][selected[1]] = typedNum;
                        rectangles[selected[0]][selected[1]].setFill(colorModified);
                    }
                    catchErrors(rectangles);
                } catch (NumberFormatException err) {
                }
            }
        });
    }

    /**
     * This method shows the errors to the user
     * @param rectangles refers to the grid of rectangles behind the images.
     */
    private void catchErrors(Rectangle[][] rectangles) {
        catchHorizontalErrors(rectangles);
        catchVerticalErrors(rectangles);
        catchGridErrors(rectangles);
    }

    /**
     * This method catches horizontal errors
     * @param rectangles refers to the grid of rectangles behind the images.
     */
    private void catchHorizontalErrors(Rectangle[][] rectangles) {
        for (int r = 0; r < solvingBoard.length; r++) {
            for (int c = 0; c < solvingBoard[r].length; c++) {
                int curContent = solvingBoard[r][c];
                for (int c1 = c; c1 < solvingBoard.length; c1++) {
                    if (curContent == solvingBoard[r][c1] && c != c1 && curContent != 0) {
                        rectangles[r][c1].setFill(errorColor);
                        rectangles[r][c].setFill(errorColor);
                    }
                }

            }
        }
    }

    /**
     * This method catches vertical errors
     * @param rectangles refers to the rectangles behind the numbers.
     */
    private void catchVerticalErrors(Rectangle[][] rectangles) {
        for (int c = 0; c < solvingBoard[0].length; c++) {
            for (int r = 0; r < solvingBoard.length; r++) {
                int current = solvingBoard[r][c];
                for (int r1 = 0; r1 < solvingBoard.length; r1++) {
                    if (current == solvingBoard[r1][c] && r != r1 && current != 0) {
                        rectangles[r][c].setFill(errorColor);
                        rectangles[r1][c].setFill(errorColor);
                    }
                }
            }
        }
    }

    /**
     * This method catches the grid errors.
     * @param rectangles refers to the rectangle behind the images.
     */
    private void catchGridErrors(Rectangle[][] rectangles) {
        for (int r = 0; r < solvingBoard.length; r += 3) {
            for (int c = 0; c < solvingBoard[r].length; c += 3) {
                for (int r1 = 0; r1 < 3; r1++) {
                    for (int c1 = 0; c1 < 3; c1++) {
                        int num = solvingBoard[r + r1][c + c1];
                        for (int r2 = 0; r2 < 3; r2++) {
                            for (int c2 = 0; c2 < 3; c2++) {
                                if (num == solvingBoard[r + r2][c + c2] && r1 != r2 && c1 != c2 && num != 0) {
                                    rectangles[r + r1][c + c1].setFill(errorColor);
                                    rectangles[r + r2][c + c2].setFill(errorColor);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
