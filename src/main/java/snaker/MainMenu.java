package snaker;

import processing.core.PApplet;

public class MainMenu{

    private boolean tempTrack;

    public MainMenu(){
        this.tempTrack = true;
    }

    public void tick(GameManager GameManager, App app){ 
        if(GameManager.returnTrackClick() == true){
            boolean playBound = App.checkBounds(463, 253, 490, 230, GameManager.returnMouseX(), GameManager.returnMouseY());
            if(playBound == true){ 
                app.resetGame(); 
                GameManager.changeScreen("game"); 
            }
            GameManager.processMouseClick();
            
        }
    }

    public void draw(GameManager GameManager, App app){
        app.fill(255);
        app.rect(180, 50, 360, 120, 40);
        app.fill(0);
        app.rect(190, 60, 340, 100, 30);

        if (app.getFrameCount() == 0){ this.tempTrack ^= true; }
        if (this.tempTrack == false){ app.fill(Math.abs(30 - app.getFrameCount()) * 255 / 30); } 
        else { app.fill(255); }
        
        app.textFont(app.returnFont(), 55);
        app.text("SNAKER", 205, 130);

        app.fill(255);
        app.rect(180, 200, 360, 300, 40);
        app.fill(0);
        app.rect(190, 210, 340, 280, 30);
        
        if (this.tempTrack == false){ app.fill(Math.abs(30 - app.getFrameCount()) * 255 / 30); } 
        else { app.fill(255); }
        app.textFont(app.returnFont(), 55);
        boolean playBound = App.checkBounds(463, 253, 490, 230, GameManager.returnMouseX(), GameManager.returnMouseY());

        if(playBound == true){ app.fill(58, 232, 238); }
        app.text("PRESS", 233, 297); 
        app.text("HERE", 200, 377); 
        app.text("TO", 422, 377); 
        app.text("PLAY", 260, 457); 
        
    }

}