package snaker;

public class InputHandler{

    enum Inputs{
        LEFT(37, "left"),
        UP(38, "up"),
        RIGHT(39, "right"),
        DOWN(40, "down"),
        ENTER(10, "enter"),
        Q(81, "Q"),
        D(68, "D");

        public final int unicode;
        public final String dir;

        Inputs(int unicode, String dir) {
            this.unicode = unicode;
            this.dir = dir;
        }
    }

    protected String keyInput = "";
    protected int mouseX = 0;
    protected int mouseY = 0;
    protected boolean trackClick = false;

    public void keyHandler(int inputKey){
        if (inputKey == Inputs.LEFT.unicode) { this.keyInput = Inputs.LEFT.dir; }
        else if (inputKey == Inputs.UP.unicode) { this.keyInput = Inputs.UP.dir; }
        else if (inputKey == Inputs.DOWN.unicode) { this.keyInput = Inputs.DOWN.dir; }
        else if (inputKey == Inputs.RIGHT.unicode) { this.keyInput = Inputs.RIGHT.dir; }
        else if (inputKey == Inputs.ENTER.unicode) { this.keyInput = Inputs.ENTER.dir; }
        else if (inputKey == Inputs.Q.unicode) { this.keyInput = Inputs.Q.dir; }
        else if (inputKey == Inputs.D.unicode) { this.keyInput = Inputs.D.dir; }

        System.out.println(this.keyInput);
        
    }

    public void updateMousePos(int mouseX, int mouseY){ this.mouseX = mouseX; this.mouseY = mouseY; }

    public void processMouseClick(){ this.trackClick ^= true; }

    public boolean returnTrackClick(){ return this.trackClick; }

    public void setKeyInput(String newKeyInput){ this.keyInput = newKeyInput; }
    
    public String returnKeyInput(){ return this.keyInput; }

    public int returnMouseX(){ return this.mouseX; }
    
    public int returnMouseY(){ return this.mouseY; }
}