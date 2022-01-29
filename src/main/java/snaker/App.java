package snaker;

import processing.core.PApplet;

public class App extends PApplet {
    // GAME SPECIFICATIONS
    public static final int WIDTH = 720;
    public static final int HEIGHT = 760;
    public static final int FPS = 60;
    //

    //GAME MANAGER IS LOADED
    public GameManager GameManager;
    //

    public App() {
        this.GameManager = new GameManager(this);
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        frameRate(FPS);

        // Load images during setup
    }

    public void draw() {
        //rect(0, 40, WIDTH, HEIGHT - 40);
        this.GameManager.tick();
        this.GameManager.draw();
    }

    public static void main(String[] args) {
        PApplet.main("snaker.App");
    }
}
