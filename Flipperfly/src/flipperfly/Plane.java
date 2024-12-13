package fxml;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Plane extends GameObject {
    private final Image explosionImg = new Image(getClass().getResource("/Assets/explossion.png").toExternalForm());
    private final Image img;
    private boolean exploded = false;
    private int explosionSize = 500;

public Plane(int x, int y, Image img) {
        super(x, y, 54, 44);
        this.img = img;
    }

    public boolean isExploded() {
        return exploded;
    }

    public void explode() {
        exploded = true;
    }

    public void setExplosionSize(int size) {
        this.explosionSize = size;
    }
