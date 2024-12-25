import java.util.Random;

public class LogicFor2048 {

    private int[][] gameBoard;
    private int count;
    private int chance;
    public LogicFor2048() {
        gameBoard = new int[4][4];
        gameBoard[0][3] = 2;
        gameBoard[0][2] = 2;
        gameBoard[0][1] = 2;
        count = 0;
        chance = 4;
    }

    public boolean moveLeft() {
        boolean moved = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j > 4; j++) {
                for (int k = j + 1; k < 4; k++) {
                    if (gameBoard[i][j] == 0 && gameBoard[i][k] != 0) {
                        gameBoard[i][j] = gameBoard[i][k];
                        gameBoard[i][k] = 0;
                        moved = true;
                    } else if (gameBoard[i][j] != 0) break;
                }
            }

            for (int j = 3; j > 0; j--) {
                if (gameBoard[i][j] == gameBoard[i][j - 1] && gameBoard[i][j] != 0) {
                    gameBoard[i][j] *= 2;
                    gameBoard[i][j - 1] = 0;
                    moved = true;
                }
            }

            for (int j = 0; j < 4; j++) {
                for (int k = j + 1; k < 4; k++) {
                    if (gameBoard[i][j] == 0 && gameBoard[i][k] != 0) {
                        gameBoard[i][j] = gameBoard[i][k];
                        gameBoard[i][k] = 0;
                        moved = true;
                    } else if (gameBoard[i][j] != 0) break;
                }
            }
        }
        return moved;
    }

    public boolean moveRight() {
        boolean moved = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j > 0; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    if (gameBoard[i][j] == 0 && gameBoard[i][k] != 0) {
                        gameBoard[i][j] = gameBoard[i][k];
                        gameBoard[i][k] = 0;
                        moved = true;
                    } else if (gameBoard[i][j] != 0) break;
                }
            }

            for (int j = 3; j > 0; j--) {
                if (gameBoard[i][j] == gameBoard[i][j - 1] && gameBoard[i][j] != 0) {
                    gameBoard[i][j] *= 2;
                    gameBoard[i][j - 1] = 0;
                    moved = true;
                }
            }

            for (int j = 3; j > 0; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    if (gameBoard[i][j] == 0 && gameBoard[i][k] != 0) {
                        gameBoard[i][j] = gameBoard[i][k];
                        gameBoard[i][k] = 0;
                        moved = true;
                    } else if (gameBoard[i][j] != 0) break;
                }
            }
        }
        return moved;
    }


    public boolean moveUp() {
        boolean moved = false;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                for (int k = i + 1; k < 4; k++) {
                    if (gameBoard[i][j] == 0 && gameBoard[k][j] != 0) {
                        gameBoard[i][j] = gameBoard[k][j];
                        gameBoard[k][j] = 0;
                        moved = true;
                    } else if (gameBoard[i][j] != 0) break;
                }
            }

            for (int i = 0; i < 3; i++) {
                if (gameBoard[i][j] == gameBoard[i + 1][j] && gameBoard[i][j] != 0) {
                    gameBoard[i][j] *= 2;
                    gameBoard[i + 1][j] = 0;
                    moved = true;
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int k = i + 1; k < 4; k++) {
                    if (gameBoard[i][j] == 0 && gameBoard[k][j] != 0) {
                        gameBoard[i][j] = gameBoard[k][j];
                        gameBoard[k][j] = 0;
                        moved = true;
                    } else if (gameBoard[i][j] != 0) break;
                }
            }
        }
        return moved;
    }



    public boolean moveDown() {
        boolean moved = false;
        for (int j = 0; j < 4; j++) {
            for (int i = 3; i >= 0; i--) {
                for (int k = i - 1; k >= 0; k--) {
                    if (gameBoard[i][j] == 0 && gameBoard[k][j] != 0) {
                        gameBoard[i][j] = gameBoard[k][j];
                        gameBoard[k][j] = 0;
                        moved = true;
                    } else if (gameBoard[i][j] != 0) break;
                }
            }

            for (int i = 3; i > 0; i--) {
                if (gameBoard[i][j] == gameBoard[i - 1][j] && gameBoard[i][j] != 0) {
                    gameBoard[i][j] *= 2;
                    gameBoard[i - 1][j] = 0;
                    moved = true;
                }
            }

            for (int i = 3; i >= 0; i--) {
                for (int k = i - 1; k >= 0; k--) {
                    if (gameBoard[i][j] == 0 && gameBoard[k][j] != 0) {
                        gameBoard[i][j] = gameBoard[k][j];
                        gameBoard[k][j] = 0;
                        moved = true;
                    } else if (gameBoard[i][j] != 0) break;
                }
            }
        }
        return moved;
    }


    public void printGameBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(" " + gameBoard[i][j]);
            }
            System.out.println();
        }
    }

    public void randomNumber() {
        Random rnd = new Random();
        boolean emptyFound = false;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameBoard[i][j] == 0) {
                    emptyFound = true;
                    break;
                }
            }
            if (emptyFound) break;
        }

        if (!emptyFound) return;

        while (true) {
            int x = rnd.nextInt(4);
            int y = rnd.nextInt(4);

          if (gameBoard[x][y] == 0) {
                gameBoard[x][y] = 2;
                break;
            }
        }
    }

    public boolean isGameOver() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameBoard[i][j] == 0) return false; // Есть пустая клетка
                if (i < 3 && gameBoard[i][j] == gameBoard[i + 1][j]) return false; // Возможное объединение вниз
                if (j < 3 && gameBoard[i][j] == gameBoard[i][j + 1]) return false; // Возможное объединение вправо
            }
        }
        return true; // Ходов больше нет
    }





    public int[][] getGameBoard() {
        return gameBoard;
    }

    public int getCoordinates(int x, int y){
        return gameBoard[x][y];
    }

    public void setGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }


}