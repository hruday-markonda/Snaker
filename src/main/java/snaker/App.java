package snaker;

import processing.core.PApplet;

public class App extends PApplet {

    public static final int WIDTH = 720;
    public static final int HEIGHT = 760;

    public static final int FPS = 60;

    public App() {
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        frameRate(FPS);

        // Load images during setup
    }

    public void draw() {
        background(57, 236, 255);
        fill(0, 0, 0);
        rect(0, 40, WIDTH, HEIGHT - 40);
    }

    public static void main(String[] args) {
        PApplet.main("snaker.App");
    }
}
