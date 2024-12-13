package fxml;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Building extends GameObject {
    public static final int BUILDING_HEIGHT = 532;
    private final Image img;
    boolean passed = false;
    
public Building(int x, int y, Image img) {
        super(x, y, 84, BUILDING_HEIGHT);
        this.img = img;
    }
