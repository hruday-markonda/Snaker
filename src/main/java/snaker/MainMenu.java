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
            String newScreen = "";
            boolean playBound = this.GameManager.checkBounds(463, 253, 340, 260, this.GameManager.returnMouseX(), this.GameManager.returnMouseY());
            boolean editBound = this.GameManager.checkBounds(463, 253, 450, 370, this.GameManager.returnMouseX(), this.GameManager.returnMouseY());
            if(playBound == true){ newScreen = "game"; }
            if(editBound == true){ newScreen = "edit"; }
            this.GameManager.processMouseClick();
            this.GameManager.changeScreen(newScreen);
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
        
        //curApp.fill(160);
        //curApp.rect(253, 260, 210, 80);
        //curApp.rect(253, 370, 210, 80);
        curApp.fill(255);
        curApp.textFont(this.GameManager.returnFont(), 55);
        boolean playBound = this.GameManager.checkBounds(463, 253, 340, 260, this.GameManager.returnMouseX(), this.GameManager.returnMouseY());
        boolean editBound = this.GameManager.checkBounds(463, 253, 450, 370, this.GameManager.returnMouseX(), this.GameManager.returnMouseY());

        if(playBound == false){ 
            curApp.text("PLAY", 260, 320); 
        } else { 
            curApp.fill(58, 232, 238);
            curApp.text("PLAY", 260, 320); 
        }

        if(editBound == false){
            curApp.fill(255);
            curApp.text("EDIT", 265, 430);
        } else {
            curApp.fill(58, 232, 238);
            curApp.text("EDIT", 265, 430);
        }
        
    }

}