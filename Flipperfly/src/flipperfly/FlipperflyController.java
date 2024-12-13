package flipperfly;

import java.util.ArrayList;

import Flipperfly.src.flipperfly.FlipperflyController.Plane;
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
    
    
    public void initialize() {

    }
    private void initializeGame() {

    }
    private void setupKeyListeners() {

    }
    abstract class GameObject {

    }
    class Plane extends GameObject {

    }
    class Building extends GameObject {
        
    }
}
