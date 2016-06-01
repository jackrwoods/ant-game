import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

/**
 * Created by jack on 5/31/16.
 *
 * Ant Class is an Abstract Class for all Ants
 */
public abstract class Ant extends GameObject {

    protected int x, y, type, width, height, tarX, tarY, relX, relY, wayPointX, wayPointY; //x and y are the coordinates of the ant on the map, tarX and tarY are the target XY coordinates
    protected double xVel, yVel, dir, hp, speed; //dir is a direction in degrees
    protected Shape ant;
    protected boolean moving, alive;
    protected Pathfinder path;
    protected ArrayList<Point> currentPath;

    public Ant(int width, int height, int x, int y, double speed, double dir, int type, Pathfinder path) {
        super(ID.Ant);
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.speed = speed;
        xVel  = 0;
        yVel = 0;
        tarX = -1;
        tarY = -1;
        moving = false;
        this.path = path;
        currentPath = new ArrayList<Point>();
        hp = 100.0;
        alive = true;
    }

    public void setXVel(double xVel)
    {
        this.xVel = xVel;
    }
    public void setYVel(double yVel)
    {
        this.yVel = yVel;
    }

    public Shape getShape()
    {
        return ant;
    }

    public void tick(GameContainer gc, StateBasedGame sbg) {
        //set velocities
        if (moving) {
            xVel += Math.sin(dir)*speed;
            yVel += Math.cos(dir)*speed;
        } else {
            xVel = 0;
            yVel = 0;
        }

        //check to make sure the ant doesn't pass the target
        //round the velocities to make them integers
        if (tarX - x < xVel) {
            xVel = tarX - x;
        } else {
            this.x += Math.round(xVel);
        }
        if (tarY - x < yVel) {
            yVel = tarY - y;
        } else {

            this.y += -1 * Math.round(yVel);
        }

        //adjust the coordinates to correctly locate the ant in the player's view
        relX = this.x - x;
        relY = this.y - y;

        //set the coordinates
        ant.setX((float)x);
        ant.setY((float)y);

        //calculate the direction of the ant
        if (moving) {
            if (tarX == x && tarY == y) {
                moving = false;
                dir = 0;
            } else if (wayPointX == x && wayPointY == y){
                updateWayPointCoord();
                Vector2f direction = new Vector2f((float) this.x, (float) this.y);
                float xDis = tarX - direction.x;
                float yDis = tarY - direction.y;
                dir = Math.atan2(yDis,  xDis) + Math.PI/2; //the direction in degrees
            } else {
                Vector2f direction = new Vector2f((float) this.x, (float) this.y);
                float xDis = tarX - direction.x;
                float yDis = tarY - direction.y;
                dir = Math.atan2(yDis,  xDis) + Math.PI/2; //the direction in degrees
            }
        } else {
            dir = 0; //the ant faces straight upward if it isn't moving
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, int x, int y) { //x and y are the coordinates of the top left of the player's view
        //perform the transformation and draw the ant
        Transform t = Transform.createRotateTransform((float) dir, ant.getCenterX(), ant.getCenterY());
        ant = ant.transform(t); //rotate the ant
        g.draw(ant);
        g.setColor(Color.white); //white is the default color
    }

    //gives the ant a new target, and begins movement
    public void move (int tarX, int tarY) {
        float tileX = Math.round(x / 32);
        float tileY = Math.round(y / 32);
        float endTileX = Math.round(tarX/32);
        float endTileY = Math.round(tarY/32);
        currentPath = path.findPath((float) tileX, (float) tileY, endTileX, endTileY, 1); //1 is the movementScore
        moving = true;
        wayPointX = (int) currentPath.get(0).getX();
        wayPointY = (int) currentPath.get(0).getY();
    }

    private void updateWayPointCoord() {
        currentPath.remove(0);
        wayPointX = (int) currentPath.get(0).getX();
        wayPointY = (int) currentPath.get(0).getY();
    }

    public void stop() {
        moving = false;
        xVel = 0;
        yVel = 0;
    }

    public void damage(double amt) {
        hp = hp - amt;
        if (hp <= 0) {
            alive = false;
        }
    }

    public void heal(double amt) {
        hp = hp + amt;
        if (hp > 100) {
            hp = 100.0;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public abstract void levelUp();
}