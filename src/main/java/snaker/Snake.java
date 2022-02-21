package snaker;

import processing.core.PApplet;
import processing.core.PImage;

//Snake is effectively a LinkedList
public class Snake{

    private Node snakeHead; //snakeHead acts more like a tail
    private Node snakeTail;
    private boolean isMoving;
    private boolean consumedFruit;
    private int snakeSize;
    private PImage headSprite;
    private PImage[] bodySpritesAll;
    private int spriteTrack;
    private PApplet curApp;

    public Snake(int startX, int startY, String initTraj, PApplet app){ //initTraj accepts left, right, up, down
        this.headSprite = app.loadImage("src/main/resources/snake/SnakeHead.png");
        this.bodySpritesAll = new PImage[5];
        for(int i = 0; i < 5; i++){
            String imageSrc = "src/main/resources/snake/SnakeBody" + (i+1) + ".png";
            bodySpritesAll[i] = app.loadImage(imageSrc);
        }
        
        this.curApp = app;
        this.spriteTrack = 0;
        this.snakeHead = new Node("head", initTraj, null, null, startX, startY, headSprite);
        this.snakeTail = snakeHead;
        this.consumedFruit = false;
        this.isMoving = true;
        this.snakeSize = 0;
    }

    public void updateSnakeNext(String newTraj){
        this.snakeHead.updateTrajectory(newTraj);
    }

    public void tick(){ //updates snake position
        if(this.isMoving == true){
            Node newTail = this.generateNewTail(); //potential new tail
            Node oldTailNext = this.snakeTail.getNextNode();

            Node cursor = this.snakeTail;
            while(cursor != null){
                switch(cursor.getNodeTraj()){
                    case "up":
                        cursor.updateNodeCorY(-1);
                        break;
                    case "down":
                        cursor.updateNodeCorY(1);
                        break;
                    case "left":
                        cursor.updateNodeCorX(-1);
                        break;
                    case "right":
                        cursor.updateNodeCorX(1);
                        break;
                }
                if(cursor.getNextNode() != null){
                    Node frontNode = cursor.getNextNode();
                    cursor.updateTrajectory(frontNode.getNodeTraj());
                }

                if(cursor.getNextNode() == null){ 
                    this.spriteTrack = (this.spriteTrack + 1) % 5;
                    break; 
                }
                cursor = cursor.getNextNode();
            }

            if(this.consumedFruit == true){ 
                if(this.snakeTail != null){
                    this.snakeTail.updatePrevNode(newTail);
                    if(oldTailNext != null){ oldTailNext.updatePrevNode(this.snakeTail); }
                }
                this.snakeTail = newTail;

                this.snakeSize += 1;
                this.consumedFruit = false;
            }
        }
    }
    
    public void draw(){ 
        Node cursor = this.snakeHead;
        while(cursor != null){
            this.curApp.image(cursor.getSprite(), cursor.getCorX(), cursor.getCorY());
            if(cursor.getPrevNode() != null){ cursor = cursor.getPrevNode(); } 
            else{ break; }
        }
    }

    public Node generateNewTail(){
        
        Node cursor = this.snakeHead;
        while(cursor.getPrevNode() != null){
            cursor = cursor.getPrevNode();
        }

        String oldTailTraj = cursor.getNodeTraj();
        int oldTailCorX = cursor.getCorX();
        int oldTailCorY = cursor.getCorY();
        Node newTail = new Node("body", oldTailTraj, cursor, null, oldTailCorX, oldTailCorY, this.bodySpritesAll[this.spriteTrack]);
        return newTail;
    }

    public void setMoving(boolean newValue){ this.isMoving = newValue; }

    public void changeHeadTraj(String newTraj){ this.snakeHead.updateTrajectory(newTraj); }

    public void setConsFruit(){ this.consumedFruit = true; }
}

class Node{

    private String nodeType; //accepts "head", "body"
    private String nodeTraj; //accepts "up", "down", "left", "right", "stop"
    private Node nextNode;
    private Node prevNode;
    private int corX;
    private int corY;
    private PImage sprite;

    public Node(String nodeType, String nodeTraj, Node nextNode, Node prevNode, int corX, int corY, PImage sprite){
        this.nodeType = nodeType;
        this.nodeTraj = nodeTraj;
        this.nextNode = nextNode;
        this.prevNode = prevNode;
        this.corX = corX;
        this.corY = corY;
        this.sprite = sprite;
    }

    public String getNodeTraj(){ return this.nodeTraj; }

    public int getCorX(){ return this.corX; }

    public int getCorY(){ return this.corY; }

    public Node getPrevNode(){ return this.prevNode; }

    public Node getNextNode(){ return this.nextNode; }

    public void updateNodeType(String nodeType){ this.nodeType = nodeType; }

    public void updateTrajectory(String newTraj){ this.nodeTraj = newTraj; }

    public void updateNodeCorX(int dispX){ this.corX = this.corX + dispX * 40; }

    public void updateNodeCorY(int dispY){ this.corY = this.corY + dispY * 40; }

    public void updateNextNode(Node nextNode){ this.nextNode = nextNode; }

    public void updatePrevNode(Node prevNode){ this.prevNode = prevNode; }

    public PImage getSprite(){ return this.sprite; }
}