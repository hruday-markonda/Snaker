package snaker;

public class MainMenu{

    public int frameCount;

    //MAIN MENU CONSTRUCTOR
    public MainMenu() {
        this.frameCount = 0;
    }

    public void tick() {
        this.frameCount = (this.frameCount + 1)%60;
    }

    public void draw() {
        background(57, 236, 255);
        fill(0, 0, 0);
    }

}