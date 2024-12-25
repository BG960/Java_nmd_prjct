import processing.core.PApplet;

public class Main extends PApplet {

    private float[] xPositions, yPositions;
    private float[] speedX, speedY;
    private int numBalls = 5;
    private float ballSize = 50;
    private int[] ballColors;

    @Override
    public void settings() {
        fullScreen();
    }

    @Override
    public void setup() {
        xPositions = new float[numBalls];
        yPositions = new float[numBalls];
        speedX = new float[numBalls];
        speedY = new float[numBalls];
        ballColors = new int[numBalls];


        for (int i = 0; i < numBalls; i++) {
            xPositions[i] = width / 2;
            yPositions[i] = height / 2;
            speedX[i] = random(3, 7);
            speedY[i] = random(3, 7);
            ballColors[i] = color(random(255), random(255), random(255));
        }
    }

    @Override
    public void draw() {
        background(0);


        for (int i = 0; i < numBalls; i++) {
            fill(ballColors[i]);
            circle(xPositions[i], yPositions[i], ballSize);


            xPositions[i] += speedX[i];
            yPositions[i] += speedY[i];


            if (xPositions[i] >= width || xPositions[i] <= 0) {
                speedX[i] = -speedX[i];
                ballColors[i] = color(random(255), random(255), random(255));
            }
            if (yPositions[i] >= height || yPositions[i] <= 0) {
                speedY[i] = -speedY[i];
                ballColors[i] = color(random(255), random(255), random(255));
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main(Main.class.getName());
    }
}
