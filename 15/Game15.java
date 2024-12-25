import processing.core.PApplet;

public class Game15 extends PApplet {

    private int[][] gameField;
    private float x;
    private float y;
    private float extent;
    private int moveCount;

    @Override
    public void settings() {
        fullScreen();
    }

    @Override
    public void setup() {
        gameField = new int[4][4];
        moveCount = 0;
        initGameField();
        x = width / 3f;
        y = 200f;
        extent = (width / 3f) / 4;
    }

    private void initGameField() {
        int count = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gameField[i][j] = count++;
            }
        }
        gameField[3][3] = 0; // Пустая клетка
        shuffleGameField();
    }

    private void shuffleGameField() {
        for (int i = 0; i < 100; i++) {
            int randomMove = (int) random(4);
            switch (randomMove) {
                case 0 -> up();
                case 1 -> down();
                case 2 -> left();
                case 3 -> right();
            }
        }
    }

    @Override
    public void draw() {
        drawBackground();
        drawGrid();
        drawTiles();
        drawInfoPanel();
    }

    private void drawBackground() {
        background(30, 30, 50); // Тёмный градиентный фон
    }

    private void drawGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                fill(50, 50, 80); // Цвет клеток
                stroke(100, 100, 150); // Цвет рамок
                strokeWeight(3);
                rect(x + extent * j, y + extent * i, extent, extent, 10); // Закруглённые углы
            }
        }
    }

    private void drawTiles() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameField[i][j] != 0) {
                    fill(200, 200, 80); // Цвет плитки
                    stroke(255, 200, 100); // Цвет рамки плитки
                    strokeWeight(5);
                    rect(x + extent * j, y + extent * i, extent, extent, 15);

                    // Тень плитки
                    fill(0, 0, 0, 50);
                    noStroke();
                    rect(x + extent * j + 5, y + extent * i + 5, extent - 10, extent - 10, 15);

                    // Текст внутри плитки
                    fill(50);
                    textSize(40);
                    textAlign(CENTER, CENTER);
                    text(gameField[i][j], x + extent * j + extent / 2, y + extent * i + extent / 2);
                }
            }
        }
    }

    private void drawInfoPanel() {
        fill(255, 255, 255);
        textSize(30);
        textAlign(LEFT, CENTER);
        text("Moves: " + moveCount, 20, 50);

        // Кнопка для перезапуска
        fill(255, 0, 0);
        rect(width - 150, 20, 120, 50, 10);
        fill(255);
        textAlign(CENTER, CENTER);
        text("Restart", width - 90, 45);
    }

    @Override
    public void mousePressed() {
        if (mouseX >= width - 150 && mouseX <= width - 30 && mouseY >= 20 && mouseY <= 70) {
            initGameField();
            moveCount = 0;
        }
    }

    @Override
    public void keyPressed() {
        boolean moved = false;
        switch (keyCode) {
            case UP -> moved = up();
            case DOWN -> moved = down();
            case LEFT -> moved = left();
            case RIGHT -> moved = right();
            default -> showInvalidKeyMessage();
        }
        if (moved) moveCount++;
        if (checkWin()) {
            fill(0, 255, 0);
            textSize(100);
            textAlign(CENTER, CENTER);
            text("You Win!", width / 2f, height / 2f);
            noLoop(); // Остановить игру
        }
    }

    private void showInvalidKeyMessage() {
        fill(255, 0, 0);
        textSize(40);
        textAlign(CENTER, CENTER);
        text("Invalid key!", width / 2f, height - 100);
    }

    private boolean up() {
        return moveTile(-1, 0);
    }

    private boolean down() {
        return moveTile(1, 0);
    }

    private boolean left() {
        return moveTile(0, -1);
    }

    private boolean right() {
        return moveTile(0, 1);
    }

    private boolean moveTile(int rowOffset, int colOffset) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameField[i][j] == 0) {
                    int newRow = i + rowOffset;
                    int newCol = j + colOffset;
                    if (newRow >= 0 && newRow < 4 && newCol >= 0 && newCol < 4) {
                        gameField[i][j] = gameField[newRow][newCol];
                        gameField[newRow][newCol] = 0;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkWin() {
        int count = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 3 && j == 3) {
                    return gameField[i][j] == 0;
                }
                if (gameField[i][j] != count++) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PApplet.main("Game15");
    }
}

//import processing.core.PApplet;
//
//public class Game15 extends PApplet {
//
//    private int[][] gameField;
//    private float x;
//    private float y;
//    private float extent;
//
//    @Override
//    public void settings() {
//        fullScreen();
//    }
//
//    @Override
//    public void setup() {
//        gameField = new int[4][4];
//        initGameField();
//        x = width / 3f;
//        y = 200f;
//        extent = (width / 3f) / 4;
//    }
//
//    private void initGameField() {
//        int count = 1;
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                gameField[i][j] = count++;
//            }
//        }
//        gameField[3][3] = 0; // Пустая клетка
//        shuffleGameField();
//    }
//
//    private void shuffleGameField() {
//        for (int i = 0; i < 100; i++) {
//            int randomMove = (int) random(4);
//            switch (randomMove) {
//                case 0 -> up();
//                case 1 -> down();
//                case 2 -> left();
//                case 3 -> right();
//            }
//        }
//    }
//
//    @Override
//    public void draw() {
//        background(0);
//        drawGrid();
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                if (gameField[i][j] != 0) {
//                    fill(255, 255, 0);
//                    textSize(40);
//                    textAlign(CENTER, CENTER);
//                    text(gameField[i][j], x + extent * j + extent / 2f, y + extent * i + extent / 2f);
//                }
//            }
//        }
//    }
//
//    private void drawGrid() {
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                fill(100, 0, 255);
//                square(x + extent * j, y + extent * i, extent);
//            }
//        }
//    }
//
//    @Override
//    public void keyPressed() {
//        switch (keyCode) {
//            case UP -> up();
//            case DOWN -> down();
//            case LEFT -> left();
//            case RIGHT -> right();
//            default -> showInvalidKeyMessage();
//        }
//        if (checkWin()) {
//            fill(0, 255, 0);
//            textSize(100);
//            textAlign(CENTER, CENTER);
//            text("You Win!", width / 2f, height / 2f);
//            noLoop(); // Остановить игру
//        }
//    }
//
//    private void showInvalidKeyMessage() {
//        fill(255, 0, 0);
//        textSize(40);
//        textAlign(CENTER, CENTER);
//        text("Invalid key!", width / 2f, height - 100);
//    }
//
//    private void up() {
//        moveTile(-1, 0);
//    }
//
//    private void down() {
//        moveTile(1, 0);
//    }
//
//    private void left() {
//        moveTile(0, -1);
//    }
//
//    private void right() {
//        moveTile(0, 1);
//    }
//
//    private void moveTile(int rowOffset, int colOffset) {
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                if (gameField[i][j] == 0) {
//                    int newRow = i + rowOffset;
//                    int newCol = j + colOffset;
//                    if (newRow >= 0 && newRow < 4 && newCol >= 0 && newCol < 4) {
//                        gameField[i][j] = gameField[newRow][newCol];
//                        gameField[newRow][newCol] = 0;
//                        return;
//                    }
//                }
//            }
//        }
//    }
//
//    private boolean checkWin() {
//        int count = 1;
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                if (i == 3 && j == 3) {
//                    return gameField[i][j] == 0;
//                }
//                if (gameField[i][j] != count++) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    public static void main(String[] args) {
//        PApplet.main("Game15");
//    }
//}
