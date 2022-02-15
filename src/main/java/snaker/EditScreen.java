package snaker;

import processing.core.PApplet;

public class EditScreen{
    
    private PApplet curApp;
    private GameManager GameManager;
    private int frameCount;

    public EditScreen(PApplet curApp, GameManager GameManager){
        
        this.curApp = curApp;
        this.GameManager = GameManager;
        this.frameCount = 0;
    }

    public void tick(){
        this.frameCount = (this.frameCount + 1) % 60;
    }

    public void draw(){
        
    }

}