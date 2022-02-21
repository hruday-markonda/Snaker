package snaker;

import processing.core.PApplet;
import processing.core.PImage;

public class App extends PApplet {
    // GAME SPECIFICATIONS
    public static final int WIDTH = 720;
    public static final int HEIGHT = 760;
    public static final int FPS = 60;
    //

    //GAME MANAGER IS LOADED
    private GameManager GameManager;
    private PImage background;
    //

    public App() {
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        frameRate(FPS);
        this.GameManager = new GameManager(this);
        this.background = this.loadImage("src/main/resources/backgrounds/moon_background.jpeg");
        // Load images during setup
    }

    public void draw() {
        //rect(0, 40, WIDTH, HEIGHT - 40);
        this.image(this.background, -250, -250);
        this.GameManager.updateMousePos(mouseX, mouseY);
        this.GameManager.tick();
        this.GameManager.draw();
    }

    public static void main(String[] args) { PApplet.main("snaker.App"); }

    public void keyReleased(){ 
        this.GameManager.keyHandler(keyCode); 
        if(this.GameManager.getScreenName() == "game"){
            this.GameManager.returnGameScreen().gameInput(this.GameManager.returnKeyInput());
        }
    }

    public void mouseReleased(){ this.GameManager.processMouseClick(); }
}
