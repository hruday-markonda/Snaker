package snaker;

import processing.core.PApplet;

public class MainMenu{

    public PApplet curApp;
    public int frameCount;

    //MAIN MENU CONSTRUCTOR
    public MainMenu(PApplet curApp) {
        this.frameCount = 0;
        this.curApp = curApp;
    }

    public void tick() {
        this.frameCount = (this.frameCount + 1)%60;
    }

    public void draw() {
        curApp.background(57, 236, 255);
        curApp.fill(0, 0, 0);
    }

}