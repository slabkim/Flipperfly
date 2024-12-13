package fxml;

import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {
    int x, y, width, height;

GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void draw(GraphicsContext gc);
}
