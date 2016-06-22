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
 *
 */
public abstract class Ant extends GameObject {

    protected int x, y, type, width, height, tarX, tarY, wayPointX, wayPointY, team, hp, maxHP; //x and y are the coordinates of the ant on the map, tarX and tarY are the target XY coordinates
    protected double xVel, yVel, dir, speed; //dir is a direction in degrees
    protected Shape ant;
    protected boolean moving, alive;
    protected Pathfinder path;
    protected ArrayList<Point> currentPath;

    public Ant(int x, int y, double speed, double dir, Pathfinder path) {
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
        hp = 100;
        maxHP = 100;
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

        //calculate the direction of the ant
        if (wayPointX == x && wayPointY == y) {
            updateWayPointCoord();
        }
        if (moving) {
            Vector2f direction = new Vector2f((float) this.x, (float) this.y);
            float xDis = wayPointX - direction.x;
            float yDis = wayPointY - direction.y;
            dir = Math.atan2(yDis,  xDis) + Math.PI/2; //the direction in radians
        }

        //set velocities
        xVel = Math.sin(dir)*speed;
        yVel = -1 * Math.cos(dir)*speed;
        if (moving) {
            //if the ant is going to miss its target
            //if ((x < wayPointX && y < wayPointY && x + xVel > wayPointX && y + yVel > wayPointY) || (x > wayPointX && y < wayPointY && x + xVel < wayPointX && y + yVel > wayPointY) || (x < wayPointX && y > wayPointY && x + xVel > wayPointX && y + yVel < wayPointY) || (x > wayPointX && y > wayPointY && x + xVel < wayPointX && y + yVel < wayPointY) || (x > wayPointX && y == wayPointY && x + xVel < wayPointX && y + yVel == wayPointY) || (x < wayPointX && y == wayPointY && x + xVel > wayPointX && y + yVel == wayPointY) || (x == wayPointX && y > wayPointY && x + xVel == wayPointX && y + yVel < wayPointY) || (x == wayPointX && y < wayPointY && x + xVel == wayPointX && y + yVel > wayPointY)) {
              if (Math.abs(x-wayPointX) <= 2 && Math.abs(y - wayPointY) <= 2) {
                x = wayPointX;
                y = wayPointY;
            } else {
                xVel = Math.round(xVel);
                yVel = Math.round(yVel);
                x += xVel;
                y += yVel;
            }
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, int x, int y) { //x and y are the coordinates of the top left of the player's view
        //perform the transformation and draw the ant
        ant = new Rectangle(this.x - x, this.y - y, 10, 10);
        Transform t = Transform.createRotateTransform((float) (dir - Math.PI/2), ant.getCenterX(), ant.getCenterY());
        ant = ant.transform(t); //rotate the ant
        g.setColor(Color.white);
        g.draw(ant);
        g.fill(ant);
        g.setColor(Color.white); //white is the default color
    }

    //gives the ant a new target, and begins movement
    public void move (int tarX, int tarY) {
        float tileX = x / 32;
        float tileY = y / 32;
        float endTileX = tarX/32;
        float endTileY = tarY/32;
        this.tarX = tarX;
        this.tarY = tarY;
        currentPath = path.findPath(tileX, tileY, endTileX, endTileY);
        moving = true;
        wayPointX = (int) currentPath.get(1).getX() + 32/2;
        wayPointY = (int) currentPath.get(1).getY() + 32/2;
        for (int i = 1; i < currentPath.size(); i++) {
            path.getMap().pathTile((int) currentPath.get(i).getX(), (int) currentPath.get(i).getY());
        }
    }

    private void updateWayPointCoord() {
        if (currentPath.size() > 1) {
            currentPath.remove(0);
            wayPointX = (int) currentPath.get(0).getX() + 32/2;
            wayPointY = (int) currentPath.get(0).getY() + 32/2;
        } else {
            if(moving) {
                stop();
            }
        }
    }

    public void stop() {
        moving = false;
        xVel = 0;
        yVel = 0;
    }

    public void damage(int amt) {
        hp = hp - amt;
        if (hp <= 0) {
            alive = false;
        }
    }

    public void heal(int amt) {
        hp = hp + amt;
        if (hp > maxHP) {
            hp = 100;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public abstract void levelUp();

    public boolean isOwner (int team) { //returns true if the team number supplied matches this ant's team number
        if (this.team == team) {
            return true;
        } else {
            return false;
        }
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}