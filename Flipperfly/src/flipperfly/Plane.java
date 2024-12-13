package fxml;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Plane extends GameObject {
    private final Image explosionImg = new Image(getClass().getResource("/Assets/explossion.png").toExternalForm());
    private final Image img;
    private boolean exploded = false;
    private int explosionSize = 500;
