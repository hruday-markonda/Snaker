package snaker;

import processing.core.PApplet;
import processing.core.PImage;
import java.lang.Math;
import java.util.Arrays;

public class GameScreen{
    //Attributes
    private PApplet curApp;
    private GameManager GameManager;
    private int frameCount;
    private Snake gameSnake;
    private boolean gameDone;

    public GameScreen(PApplet curApp, GameManager GameManager){
        this.curApp = curApp;
        this.GameManager = GameManager;
        this.frameCount = 0;
        int randomX = (int)(Math.random() * 16) * 40 + 40;
        int randomY = (int)(Math.random() * 16) * 40 + 40;
        int randomIndex = (int)Math.round((Math.random() * 3));
        String[] directions = {"right", "left", "up", "down"};
        String randomTraj = directions[randomIndex];
        this.gameSnake = new Snake(randomX, randomY, randomTraj, curApp);
        this.gameDone = false;
    }

    public void tick(){
        if(this.gameSnake.snakeCollision() == true || this.gameSnake.getSnakeSize() == 256){
            this.gameSnake.setMoving(false);
            this.gameDone = true;
        }
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

        if(this.gameDone == true){
            curApp.textFont(this.GameManager.returnFont(), 35);
            if(this.gameSnake.getSnakeSize() != 256){ 
                curApp.fill(255, 0, 0);
                curApp.text("YOU LOST", 245, 307); 
            } 
            else{ 
                curApp.fill(0, 255, 0);
                curApp.text("YOU WON", 245, 307); 
            }
            curApp.text("PRESS Q", 245, 347); 
            curApp.text("TO QUIT", 245, 387);

        }


        if (this.frameCount == 59){
            this.gameSnake.tick();
        }
        this.gameSnake.draw();
    }

    public void gameInput(String newKeyInput){

        if(Arrays.asList("up", "down", "left", "right").contains(newKeyInput)){ this.gameSnake.changeHeadTraj(newKeyInput); } 
        else if(newKeyInput == "enter"){ this.gameSnake.setConsFruit(); } 
        else if(newKeyInput == "Q"){ this.GameManager.changeScreen("main"); }
        this.GameManager.setKeyInput("");
    }
}