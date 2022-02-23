package snaker;

import processing.core.PApplet;
import processing.core.PImage;
import java.lang.Math;

public class GameScreen{

    private PApplet curApp;
    private GameManager GameManager;
    private int frameCount;
    private Snake gameSnake;

    public GameScreen(PApplet curApp, GameManager GameManager){
        this.curApp = curApp;
        this.GameManager = GameManager;
        this.frameCount = 0;
        int randomX = (int)((Math.random() * 16) + 1) * 40 + 40;
        int randomY = (int)((Math.random() * 17) + 1) * 40 + 40;
        int randomIndex = (int)(Math.random() * 2);
        String[] directions = {"right", "left", "up", "down"};
        String randomTraj = directions[randomIndex];
        this.gameSnake = new Snake(randomX, randomY, randomTraj, curApp);
    }

    public void tick(){
        this.frameCount = (this.frameCount + 1) % 60;
    }

    public void draw(){
        
        PImage wallSprite = this.curApp.loadImage("src/main/resources/tile/wall3.png");
        for(int i = 0; i < 18; i++){
            this.curApp.image(wallSprite, i*40, 0);
            this.curApp.image(wallSprite, i*40, 17*40);
        }

        for(int i = 0; i < 18; i++){
            this.curApp.image(wallSprite, 0, i*40);
            this.curApp.image(wallSprite, 17*40, i*40);
        }
        
        if (this.frameCount == 59){
            this.gameSnake.tick();
        }
        this.gameSnake.draw();
    }

    public void gameInput(String newKeyInput){
        if(newKeyInput != "enter"){
            this.gameSnake.changeHeadTraj(newKeyInput);
        } else{
            this.gameSnake.setConsFruit();
        }
        GameManager.setKeyInput("");
    }
}