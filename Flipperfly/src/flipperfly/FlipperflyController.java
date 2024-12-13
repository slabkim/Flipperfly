package flipperfly;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class FlipperflyController {
    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private final Image planeImg = new Image(getClass().getResource("/Assets/pesawat.png").toExternalForm());
    private final Image topBuilding = new Image(getClass().getResource("/Assets/topgedung.png").toExternalForm());
    private final Image bottomBuilding = new Image(getClass().getResource("/Assets/bottomgedung .png").toExternalForm());
    private final Image explosionImg = new Image(getClass().getResource("/Assets/explossion.png").toExternalForm());
    private final Image gameOverImg = new Image(getClass().getResource("/Assets/gameover.png").toExternalForm());
    public  Media backsound = new Media(getClass().getResource("/Assets/Backsound.mp3").toExternalForm());
    
    private Plane plane;
    private ArrayList<Building> buildings = new ArrayList<>();
    private boolean gameOver;
    private double score;
    private MediaPlayer backgroundMusicPlayer;

    private int velocityY;
    private final int velocityX = -10;

    private AnimationTimer gameLoop;
    private boolean spaceHeld = false;
    private boolean gameStarted = false;
    public static int globalScore = 0; 

    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        initializeGame();
        setupKeyListeners();
        startGameLoop();
        backgroundMusicPlayer = new MediaPlayer(backsound);
        backgroundMusicPlayer.setOnEndOfMedia(() -> backgroundMusicPlayer.seek(Duration.ZERO));
        backgroundMusicPlayer.setVolume(0.5);
        backgroundMusicPlayer.play();
    }
    private void initializeGame() {
        plane = new Plane((int) canvas.getWidth() / 8, (int) canvas.getHeight() / 2, planeImg);
        buildings.clear();
        score = 0;
        gameOver = false;
        velocityY = 0;
        globalScore = 0;
        gameStarted = false;
    }
    private void setupKeyListeners() {

    }
    private void updateVelocity(){

    }
    private void startGameLoop(){

    }
    private void move(){
        if (!gameStarted) {
            return;
        }

        updateVelocity();
        plane.y += velocityY;
        plane.y = Math.max(plane.y, 0);

        for (Building building : buildings) {
            building.x += velocityX;

            if (!building.passed && plane.x > building.x + building.width) {
                score += 0.5;
                globalScore++;
                building.passed = true;
            }

            if (collision(plane, building)) {
                plane.explode();
                plane.setExplosionSize(500);
                gameOver = true;
            }
        }

        buildings.removeIf(building -> building.x + building.width < 0);

        if (plane.y > canvas.getHeight()) {
            gameOver = true;
        }
    }
    private void render(){

    }
    private void placeBuildings(){

    }
    private boolean collision(GameObject obj1, GameObject obj2){
        return obj1.x < obj2.x + obj2.width &&
        obj1.x + obj1.width > obj2.x &&
        obj1.y < obj2.y + obj2.height &&
        obj1.y + obj1.height > obj2.y;
    }
}
