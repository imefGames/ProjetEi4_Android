package istia.ei4.ProjetISTIA;

import android.graphics.Color;

/**
 * Created by Pierre on 25/02/2015.
 */
public class GamePiece implements IGameObject {



    private int x                   = 0;
    private int y                   = 0;
    private int xObjective          = 0;
    private int yObjective          = 0;
    private int xGrid               = 400;
    private int yGrid               = 500;
    private int xDraw               = 0;
    private int yDraw               = 0;
    private float widthCell         = 16;
    private float heightCell        = 16;
    private int radius              = 32;
    private int color               = Color.RED;
    private boolean inMouvement     = false;
    private int deltaX              = 0;
    private int deltaY              = 0;
    private int deltaValue          = 2;
    private boolean testSiGagne     = true;

    private int image               = 0;

    public void setY(int y) {
        this.y = y;
        deltaY = 0;
    }

    public void setX(int x) {
        this.x = x;
        deltaX = 0;
    }
    public boolean isInMouvement() {
        return inMouvement;
    }

    public int getColor() {
        return color;
    }

    public int getxObjective() {
        return xObjective;
    }

    public int getyObjective() {
        return yObjective;
    }

    public void setyObjective(int yObjective) {
        this.yObjective = yObjective;
    }

    public void setxObjective(int xObjective) {
        this.xObjective = xObjective;
    }
    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
    public GamePiece(int x, int y, int color){
        this.x = x;
        this.y = y;
        this.xObjective = x;
        this.yObjective = y;
        this.color = color;

        switch(color)
        {
            case Color.RED:
                image = R.drawable.rr;
                break;
            case Color.YELLOW:
                image = R.drawable.rj;
                break;
            case Color.BLUE:
                image = R.drawable.rb;
                break;
            case Color.GREEN:
                image = R.drawable.rv;
                break;
            default:
                image = 0;
                break;
        }

    }

    public void setGridDimensions(int xGrid, int yGrid, float cellSize){
        this.xGrid = xGrid;
        this.yGrid = yGrid;
        this.widthCell = this.heightCell = cellSize;
        this.radius = (int) (cellSize / 2);

    }

    @Override
    public void create(){
    }

    @Override
    public void load(RenderManager renderManager){
    }

    @Override
    public void draw(RenderManager renderManager){
        //renderManager.setColor(this.color);
        //afficher le pion

        xDraw = (int)(this.xGrid+((this.x+((float)deltaX)/10)+0.5f)*this.widthCell);
        yDraw = (int)(this.yGrid+((this.y+((float)deltaY)/10)+0.5f)*this.heightCell);
        renderManager.drawCircle(xDraw, yDraw, this.radius);


        renderManager.drawImage(xDraw-this.radius, yDraw-this.radius, xDraw+this.radius, yDraw+this.radius, this.image);
    }

    @Override
    public void update(GameManager gameManager){
        //si le pion n'est pas en mouvement, ...
        if((this.x == this.xObjective) && (this.y == this.yObjective) && (deltaX == 0) && (deltaY == 0)){

//            System.out.println(" GamePiece "+color + " x = "+ x + " y = " + y + " xObj = "+xObjective+ " yObj = "+yObjective + " deltaX = "+deltaX + " deltaY = "+deltaY);
            if(inMouvement)
            {
                ((GridGameScreen)(gameManager.getCurrentScreen())).doMovesInMemory();
            }

            inMouvement = false;

            if(testSiGagne)
            {
                ((GridGameScreen)(gameManager.getCurrentScreen())).gagne(this);
                testSiGagne = false;
            }
//            inMouvement = false;
            //si il y a une entrée utilisateur, ...
            InputManager inputManager = gameManager.getInputManager();
            if(inputManager.eventHasOccurred()){
                int xTouch, yTouch, dx, dy;
                xTouch = (int)inputManager.getTouchX();
                yTouch = (int)inputManager.getTouchY();
                dx = xTouch - this.xDraw;
                dy = yTouch - this.yDraw;
                //si l'utilisateur a touché le pion, ...
                if(dx*dx + dy*dy <= this.radius*this.radius && inputManager.downOccurred()){

                    //afficher l'interface de mouvement
                    ((GridGameScreen)(gameManager.getCurrentScreen())).activateInterface(this, xDraw, yDraw);
                }
            }


        }else{ //sinon (si le pion doit bouger),

            inMouvement = true;
            testSiGagne = true;

            if(this.x < this.xObjective)
                deltaX +=deltaValue;
            if(this.x > this.xObjective)
                deltaX -=deltaValue;

            if(this.y < this.yObjective)
                deltaY+=deltaValue;
            if(this.y > this.yObjective)
                deltaY-=deltaValue;


            if(deltaX > 9) {
                this.x += 1;
                deltaX = 0;
            }
            if(deltaX < -9) {
                this.x -= 1;
                deltaX = 0;
            }
            if(deltaY > 9) {
                this.y += 1;
                deltaY = 0;
            }
            if(deltaY < -9) {
                this.y -= 1;
                deltaY = 0;
            }
        }
    }

    @Override
    public void destroy(){
    }

}
