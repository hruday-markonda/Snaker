package snaker;

import processing.core.PApplet;

public class GameManager{

    enum Directions{
        LEFT(37, "left"),
        UP(38, "up"),
        RIGHT(39, "right"),
        DOWN(40, "down");

        public final int unicode;
        public final String dir;

        Directions(int unicode, String dir) {
            this.unicode = unicode;
            this.dir = dir;
        }
    }

    public MainMenu MenuScreen;
    public int frameCount;
    public String screenName; //3 Possible values, 'main', 'game', 'edit'

    public GameManager(PApplet curApp){
        this.MenuScreen = new MainMenu(curApp);
        this.frameCount = 0;
        this.screenName = "main";
    }

    public void tick(){
        this.frameCount = (this.frameCount + 1)%60;
    }

    public void draw(){
        if(this.screenName.equals("main")){
            this.MenuScreen.tick();
            this.MenuScreen.draw();
        }
    }

    public void keyHandler(int inputKey){
        String direction = "";

        if (inputKey == Directions.LEFT.unicode) { direction = Directions.LEFT.dir; }
        else if (inputKey == Directions.UP.unicode) { direction = Directions.UP.dir; }
        else if (inputKey == Directions.DOWN.unicode) { direction = Directions.DOWN.dir; }
        else if (inputKey == Directions.RIGHT.unicode) { direction = Directions.RIGHT.dir; }
        System.out.println(direction);
    }

}