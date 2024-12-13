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
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                if (!gameStarted) {
                    gameStarted = true;
                } else if (!gameOver) {
                    spaceHeld = true;
                }

                if (gameOver) {
                    initializeGame();
                }
            }
        });

        canvas.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                spaceHeld = false;
            }
        });
    }

    private void updateVelocity() {
        if (spaceHeld) {
            velocityY = Math.max(velocityY - 1, -15);
        } else {
            velocityY = Math.min(velocityY + 1, 15);
        }
    }

    private void startGameLoop() {
        gameLoop = new AnimationTimer() {
            private long lastBuildingTime = 0;

            @Override
            public void handle(long now) {
                if (!gameOver) {
                    move();
                    render();

                    if (now - lastBuildingTime > 1_500_000_000) {
                        placeBuildings();
                        lastBuildingTime = now;
                    }
                }
            }
        };
        gameLoop.start();
    }

    private void move() {
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
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        plane.draw(gc);

        for (Building building : buildings) {
            building.draw(gc);
        }

        gc.setFont(new Font("Arial", 20));
        gc.fillText("Score: " + (int) score, 10, 35);

        if (!gameStarted) {
            gc.setFont(new Font("Arial", 30));
            gc.fillText("Press SPACE to Start", canvas.getWidth() / 2 - 120, canvas.getHeight() / 2);
        }

        if (gameOver) {
            double imgWidth = 700;
            double imgHeight = 700;

            double imgX = (canvas.getWidth() - imgWidth) / 2;
            double imgY = (canvas.getHeight() - imgHeight) / 2;

            gc.drawImage(gameOverImg, imgX, imgY, imgWidth, imgHeight);
        }
    }
    private void placeBuildings(){
        int openingSpace = (int) canvas.getHeight() / 4;
        int randomBuildingY = (int) (-Building.BUILDING_HEIGHT / 4 - Math.random() * (Building.BUILDING_HEIGHT / 2));

        buildings.add(new Building((int) canvas.getWidth(), randomBuildingY, topBuilding));
        buildings.add(new Building((int) canvas.getWidth(), randomBuildingY + Building.BUILDING_HEIGHT + openingSpace, bottomBuilding));
    }
    
    private boolean collision(GameObject obj1, GameObject obj2){
        return obj1.x < obj2.x + obj2.width &&
        obj1.x + obj1.width > obj2.x &&
        obj1.y < obj2.y + obj2.height &&
        obj1.y + obj1.height > obj2.y;
    }
}
