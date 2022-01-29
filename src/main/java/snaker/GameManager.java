package snaker;

public class GameManager{

    public MainMenu MainScreen;
    public int frameCount;

    public GameManager(PApplet curApp){
        this.MenuScreen = new MainMenu(curApp);
        this.frameCount = 0;
    }

    public void tick(){
        this.frameCount = (this.frameCount + 1)%60;
    }

    public void draw(){

    }

}