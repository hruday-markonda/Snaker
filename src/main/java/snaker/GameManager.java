package snaker;

import processing.core.PApplet;
import processing.core.PFont;

public class GameManager extends InputHandler{ //GameManager is also acts as the Menu Screen

    private MainMenu MainMenu;
    private GameScreen GameScreen;
    //private EditScreen EditScreen;
    private int frameCount;
    private String screenName; //3 Possible values, 'main', 'game', 'edit'
    

    public GameManager(App app){
        this.MainMenu = new MainMenu();
        this.GameScreen = new GameScreen(app);
        //this.EditScreen = new EditScreen(app);
        this.frameCount = 0;
        this.screenName = "main";
    }

    public void draw(App app){
        if(this.screenName.equals("main")){ this.MainMenu.tick(this, app); this.MainMenu.draw(this, app); }
        else if(this.screenName.equals("game")){ this.GameScreen.tick(); this.GameScreen.draw(app); }
    }

    public String getScreenName(){ return this.screenName; }

    public void changeScreen(String screenName){ this.screenName = screenName; }

    public GameScreen returnGameScreen(){ return this.GameScreen; }

    public void resetGame(App app){ this.GameScreen = new GameScreen(app); }
}