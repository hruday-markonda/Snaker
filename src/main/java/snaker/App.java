package snaker;

import processing.core.PApplet;

public class App extends PApplet {
    // GAME SPECIFICATIONS
    public static final int WIDTH = 720;
    public static final int HEIGHT = 760;
    public static final int FPS = 60;
    //

    //MAIN MENU IS LOADED
    public MainMenu MenuScreen;
    //

    public App() {
        this.MenuScreen = new MainMenu(this);
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
        this.MenuScreen.tick()
    }

    public static void main(String[] args) {
        PApplet.main("snaker.App");
    }
}
