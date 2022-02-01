package snaker;

import processing.core.PApplet;

public class MainMenu{

    public PApplet curApp;
    public int frameCount;
    public int option; //Option has value 1 for option 1, 2 for option 2, etc.

    //MAIN MENU CONSTRUCTOR
    public MainMenu(PApplet curApp) {
        this.frameCount = 0;
        this.curApp = curApp;
        this.option = 1;
    }

    public void tick() {
        this.frameCount = (this.frameCount + 1)%60;
    }

    public void draw() {
        curApp.background(57, 236, 255);
        curApp.fill(0, 0, 0);
    }

}