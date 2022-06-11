package snaker;

import processing.core.PApplet;
import processing.core.PImage;

//Snake is effectively a LinkedList
public class Snake{

    private Node snakeHead; //snakeHead acts more like a tail
    private Node snakeTail;
    private boolean isMoving = true;
    private boolean consumedFruit = false;
    private boolean consumedChilli = false;
    private int snakeSize = 1;
    private int spriteTrack = 0;

    public Snake(int startX, int startY, String initTraj, App app){ //initTraj accepts left, right, up, down
        
        this.snakeHead = new Node("head", initTraj, null, null, startX, startY, app.getAllSprites().get("SnakeHead"));
        this.snakeTail = snakeHead;
        // this.isMoving = true;
    }

    public void tick(App app){ //updates snake position
        if(this.isMoving == true){
            Node newTail = this.generateNewTail(app); //potential new tail
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
                this.spriteTrack = (this.spriteTrack + 1) % 5;
                this.snakeSize += 1;
                this.consumedFruit = false;
            }

            else if(this.consumedChilli == true){ 
                // if(this.snakeTail != null){
                //     this.snakeTail.updatePrevNode(newTail);
                //     if(oldTailNext != null){ oldTailNext.updatePrevNode(this.snakeTail); }
                // }
                if(oldTailNext != null){ oldTailNext.updatePrevNode(null); }
                this.snakeTail = oldTailNext;
                this.snakeSize -= 1;
                this.consumedChilli = false;
            }
        }
    }
    
    public void draw(App app){ 
        Node cursor = this.snakeHead;
        while(cursor != null){
            cursor.draw(app);
            if(cursor.getPrevNode() != null){ cursor = cursor.getPrevNode(); } 
            else{ break; }
        }
    }

    public Node generateNewTail(App app){
        
        Node cursor = this.snakeHead;
        while(cursor.getPrevNode() != null){
            cursor = cursor.getPrevNode();
        }

        String oldTailTraj = cursor.getNodeTraj();
        int oldTailCorX = cursor.getCorX();
        int oldTailCorY = cursor.getCorY();
        Node newTail = new Node("body", oldTailTraj, cursor, null, oldTailCorX, oldTailCorY, app.getAllSprites().get("SnakeBody" + (this.spriteTrack + 1)));
        return newTail;
    }

    public boolean snakeCollision(){
        if(this.snakeHead.getCorX() < 40 || this.snakeHead.getCorX() > 640 || this.snakeHead.getCorY() < 40 || this.snakeHead.getCorY() > 680){
            return true;
        }
        
        if(this.snakeSize > 3){
            Node cursor = this.snakeHead.getPrevNode();
            while(cursor != null){
                if((cursor.getCorX() == this.snakeHead.getCorX()) && (cursor.getCorY() == this.snakeHead.getCorY())){
                    return true;
                }
                cursor = cursor.getPrevNode();
            }
            return false; 
        }

        return false;
    }

    public int getSnakeSize(){ return this.snakeSize; }

    public void setMoving(boolean newValue){ this.isMoving = newValue; }

    public void changeHeadTraj(String newTraj, App app){ 
        String curHeadTraj = this.snakeHead.getNodeTraj();
        String badTurn = app.getBadTurns().get(curHeadTraj);

        if(newTraj.equals(badTurn) && this.snakeSize > 1){ return; }
        this.snakeHead.updateTrajectory(newTraj); 
    }

    public void consItem(boolean isChilli){ 
        if(isChilli == false){
            this.consumedFruit = true;
        }

        else{
            this.consumedChilli = true;
        }
    }
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

    public void draw(App app){
        app.image(this.sprite, this.corX, this.corY);
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