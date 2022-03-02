package snaker;

import processing.core.PApplet;
import processing.core.PImage;
import java.lang.Math;
import java.util.Arrays;

public class GameScreen{
    //Attributes
    private Snake gameSnake;
    private boolean gameDone = false;
    private int randomWallIndex = (int)Math.round((Math.random() * 2));
    //private double speedFrameBuffer = 60;

    public GameScreen(App app){

        int randomX = (int)(Math.random() * 14) * 40 + 80;
        int randomY = (int)(Math.random() * 14) * 40 + 80;
        int randomIndex = (int)Math.round((Math.random() * 3));
        String[] directions = {"right", "left", "up", "down"};
        String randomTraj = directions[randomIndex];
        this.gameSnake = new Snake(randomX, randomY, randomTraj, app);
    }

    public void tick(App app){
        if(this.gameSnake.snakeCollision() == true || this.gameSnake.getSnakeSize() == 256){
            this.gameSnake.setMoving(false);
            this.gameDone = true;
        }

        if (app.getFrameCount() == 59){
            this.gameSnake.tick(app);
        }
    }

    public void draw(App app){
        
        PImage wallSprite = app.getAllSprites().get("wall" + (this.randomWallIndex + 1));
        for(int i = 0; i < 18; i++){
            app.image(wallSprite, i*40, 0);
            app.image(wallSprite, i*40, 18*40);
        }

        for(int i = 0; i < 19; i++){
            app.image(wallSprite, 0, i*40);
            app.image(wallSprite, 17*40, i*40);
        }

        this.gameSnake.draw(app);

        if(this.gameDone == true){
            app.textFont(app.returnFont(), 35);
            if(this.gameSnake.getSnakeSize() != 256){ 
                app.fill(255, 0, 0);
                app.text("YOU LOST", 245, 307); 
            } 
            else{ 
                app.fill(0, 255, 0);
                app.text("YOU WON", 245, 307); 
            }
            app.text("PRESS Q", 245, 347); 
            app.text("TO QUIT", 245, 387);

        }
    }

    public void gameInput(String newKeyInput, GameManager GameManager, App app){

        if(Arrays.asList("up", "down", "left", "right").contains(newKeyInput)){ this.gameSnake.changeHeadTraj(newKeyInput, app); } 
        else if(newKeyInput == "enter"){ this.gameSnake.setConsFruit(); } 
        else if(newKeyInput == "Q"){ GameManager.changeScreen("main"); }
        GameManager.setKeyInput("");
    }
}