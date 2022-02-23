package snaker;

import processing.core.PApplet;

public class MainMenu{

    private int frameCount;
    private PApplet curApp;
    private GameManager GameManager;
    private boolean tempTrack;

    public MainMenu(PApplet curApp, GameManager GameManager){
        this.frameCount = 0;
        this.curApp = curApp;
        this.GameManager = GameManager;
        this.tempTrack = true;
    }

    public void tick(){ 
        if(this.GameManager.returnTrackClick() == true){
            boolean playBound = this.GameManager.checkBounds(463, 253, 490, 230, this.GameManager.returnMouseX(), this.GameManager.returnMouseY());
            if(playBound == true){ 
                this.GameManager.resetGame(); 
                this.GameManager.changeScreen("game"); 
            }
            this.GameManager.processMouseClick();
            
        }
        this.frameCount = (this.frameCount + 1) % 60; 
    }

    public void draw(){
        curApp.fill(255);
        curApp.rect(180, 50, 360, 120, 40);
        curApp.fill(0);
        curApp.rect(190, 60, 340, 100, 30);

        if (this.frameCount == 0){ this.tempTrack ^= true; }
        if (this.tempTrack == false){ curApp.fill(Math.abs(30 - this.frameCount) * 255 / 30); } 
        else { curApp.fill(255); }
        
        curApp.textFont(this.GameManager.returnFont(), 55);
        curApp.text("SNAKER", 205, 130);

        curApp.fill(255);
        curApp.rect(180, 200, 360, 300, 40);
        curApp.fill(0);
        curApp.rect(190, 210, 340, 280, 30);
        
        if (this.tempTrack == false){ curApp.fill(Math.abs(30 - this.frameCount) * 255 / 30); } 
        else { curApp.fill(255); }
        curApp.textFont(this.GameManager.returnFont(), 55);
        boolean playBound = this.GameManager.checkBounds(463, 253, 490, 230, this.GameManager.returnMouseX(), this.GameManager.returnMouseY());

        if(playBound == true){ curApp.fill(58, 232, 238); }
        curApp.text("PRESS", 233, 297); 
        curApp.text("HERE", 200, 377); 
        curApp.text("TO", 422, 377); 
        curApp.text("PLAY", 260, 457); 
        
    }

}