package snaker;

import processing.core.PApplet;
import processing.core.PFont;

public class GameManager extends InputHandler{ //GameManager is also acts as the Menu Screen

    // enum Inputs{
    //     LEFT(37, "left"),
    //     UP(38, "up"),
    //     RIGHT(39, "right"),
    //     DOWN(40, "down"),
    //     ENTER(10, "enter");

    //     public final int unicode;
    //     public final String dir;

    //     Inputs(int unicode, String dir) {
    //         this.unicode = unicode;
    //         this.dir = dir;
    //     }
    // }

    private MainMenu MainMenu;
    private GameScreen GameScreen;
    private EditScreen EditScreen;
    private PApplet curApp;
    private int frameCount;
    private String screenName; //3 Possible values, 'main', 'game', 'edit'
    private PFont gameFont;

    public GameManager(PApplet curApp){
        this.MainMenu = new MainMenu(curApp, this);
        this.GameScreen = new GameScreen(curApp, this);
        this.EditScreen = new EditScreen(curApp, this);
        this.curApp = curApp;
        this.frameCount = 0;
        this.screenName = "main";
        this.gameFont = curApp.createFont("src/main/resources/04B_30__.TTF" ,21);
    }

    public void tick(){
        this.frameCount = (this.frameCount + 1) % 60;
    }

    public void draw(){
        if(this.screenName.equals("main")){ this.MainMenu.tick(); this.MainMenu.draw(); }
        else if(this.screenName.equals("game")){ this.GameScreen.tick(); this.GameScreen.draw(); }
        else if(this.screenName.equals("edit")){ this.EditScreen.tick(); this.EditScreen.draw(); }
        //else if(this.screenName.equals("game")){ System.out.println("game screen :)"); }
        //else if(this.screenName.equals("edit")){ System.out.println("edit screen :)"); }
    }

    public PFont returnFont(){ return this.gameFont; }

    public boolean checkBounds(int upX, int lowX, int upY, int lowY, int corX, int corY){
        if ((corX <= upX && corX >= lowX) && (corY <= upY && corY >= lowY)){ return true; }
        return false;
    }

    public String getScreenName(){ return this.screenName; }

    public void changeScreen(String screenName){ this.screenName = screenName; }

    public void resetGame(){ this.GameScreen = new GameScreen(curApp, this); }

    public GameScreen returnGameScreen(){ return this.GameScreen; }
}