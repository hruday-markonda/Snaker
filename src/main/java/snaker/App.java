package snaker;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;
import java.util.HashMap;

public class App extends PApplet {
    // GAME SPECIFICATIONS
    public static final int WIDTH = 720;
    public static final int HEIGHT = 760;
    public static final int FPS = 60;
    //

    //GAME MANAGER IS LOADED
    private GameManager GameManager;
    private HashMap<String, PImage> allSprites = new HashMap<String, PImage>();
    private int frameCount = 0;
    private PFont gameFont;
    //

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        frameRate(FPS);
        // Load images during setup
        this.GameManager = new GameManager(this);
        this.gameFont = this.createFont("src/main/resources/04B_30__.TTF" ,21);

        this.allSprites.put("background", this.loadImage("src/main/resources/backgrounds/moon_background.jpeg"));
        this.allSprites.put("SnakeHead", this.loadImage("src/main/resources/snake/SnakeHead.png"));
        for(int i = 0; i < 5; i++){
            String imageSrc = "src/main/resources/snake/SnakeBody" + (i+1) + ".png";
            allSprites.put("SnakeBody" + (i+1), this.loadImage(imageSrc));
        }
        
        for(int i = 0; i < 3; i++){
            String imageSrc = "src/main/resources/tile/wall" + (i+1) + ".png";
            allSprites.put("wall"+(i+1), this.loadImage(imageSrc));
        }
            
    }

    public void draw() {
        //rect(0, 40, WIDTH, HEIGHT - 40);
        this.image(this.allSprites.get("background"), -250, -250);
        this.GameManager.updateMousePos(mouseX, mouseY);
        this.frameCount = (this.frameCount + 1) % 60;
        this.GameManager.draw(this);
    }

    public static void main(String[] args) { PApplet.main("snaker.App"); }

    public void keyReleased(){ 
        this.GameManager.keyHandler(keyCode); 
        if(this.GameManager.getScreenName() == "game"){
            this.GameManager.returnGameScreen().gameInput(this.GameManager.returnKeyInput(), this.GameManager);
        }
    }

    public static boolean checkBounds(int upX, int lowX, int upY, int lowY, int corX, int corY){
        if ((corX <= upX && corX >= lowX) && (corY <= upY && corY >= lowY)){ return true; }
        return false;
    }

    public void mouseReleased(){ this.GameManager.processMouseClick(); }

    public HashMap<String, PImage> getAllSprites(){ return this.allSprites; }

    public PFont returnFont(){ return this.gameFont; }

    public int getFrameCount(){ return this.frameCount; }

    public void resetGame(){ this.GameManager.resetGame(this); }
}
