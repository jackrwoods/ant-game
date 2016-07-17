import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jack on 6/9/16.
 *
 * Leader of a group of military ants.
 *
 */
public class AntLeader extends Ant{

    private int[][] locations;
    private ArrayList<AntFollower> soldiers;
    private double[] dirList;
    private Shape ant;
    private int topX, topY, centerX, centerY, ticks;

    public AntLeader(int x, int y, double speed, double dir, Pathfinder path) {
        super(x, y, speed, dir, path);
        super.move(tarX, tarY);
        centerX = x;
        centerY = y;
        ant = new Rectangle(this.x - x, this.y - y, 10, 10);
        soldiers = new ArrayList<AntFollower>();
        topX = 0;
        topY = 0;
        generateLocations();
        createSoldier();
        dirList = new double[200];
        ant = new Rectangle(this.x - x, this.y - y, 10, 10);
    }

    public AntLeader(int x, int y, double speed, double dir, Pathfinder path, int numSoldiers) {
        super(x, y, speed, dir, path);
        centerX = x;
        centerY = y;
        ant = new Rectangle(this.x - x, this.y - y, 10, 10);
        soldiers = new ArrayList<AntFollower>();
        topX = 0;
        topY = 0;
        generateLocations();
        for (int i = 0; i < numSoldiers; i++) {
            createSoldier();
        }
        dirList = new double[200];
    }

    @Override
    public void setXVel(double xVel) {
        super.setXVel(xVel);
    }

    @Override
    public void setYVel(double yVel) {
        super.setYVel(yVel);
    }

    @Override
    public Shape getShape() {
        return super.getShape();
    }

    public int getNumSoldiers() {
        return soldiers.size();
    }

    @Override
    public void tick(GameContainer gc, StateBasedGame sbg) {
        //Tick this ant like any other ant
        super.tick(gc, sbg);

        //Tick soldiers
        for (int i = 0; i < soldiers.size(); i++) {
            soldiers.get(i).tick(gc, sbg);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, int x, int y) {
        super.render(gc, sbg, g, x, y);
        ant = super.ant;
        /*for(int i = 0; i < soldiers.size(); i++) {
            soldiers.get(i).render(gc, sbg, g, x, y);
        }*/
        topX = x;
        topY = y;
    }

    @Override
    public void move(int tarX, int tarY) {
        super.move(tarX, tarY);
        centerX = tarX;
        centerY = tarY;
        generateLocations();
        for (int i = 0; i < soldiers.size(); i++) {
            soldiers.get(i).move(currentPath, new Point(locations[i][0], locations[i][1]), tarX, tarY);
        }
    }

    @Override
    public void stop() {
        super.stop();
        generateLocations();
    }

    @Override
    public void damage(int amt) {
        super.damage(amt);
    }

    @Override
    public void heal(int amt) {
        super.heal(amt);
    }

    @Override
    public boolean isAlive() {
        return super.isAlive();
    }

    //levels up the leader and by increasing its speed, etc
    @Override
    public void levelUp() {

    }

    //Levels up an individual ant by increasing its atk
    public void levelUp(int antNum) {

    }

    @Override
    public boolean isOwner(int team) {
        return super.isOwner(team);
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

    //creates and initializes a soldier ant
    public void createSoldier() {
        soldiers.add(new AntFollower(locations[soldiers.size()][0], locations[soldiers.size()][1], speed, 0, path, soldiers.size()));
    }

    private void generateLocations() {
        locations = new int[200][2];
        int i = 0;
        int posX = (int) (Math.round(100 * Math.cos(dir + Math.PI)) + centerX);
        int posY = (int) (Math.round(100 * Math.sin(dir + Math.PI)) + centerY);

        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 10; y++) {
                int depth = 10 * y + 30;
                int width = x * 10;
                double angle = Math.atan((double)depth/width) + dir;
                int length = (int) Math.round(Math.sqrt(Math.pow(width,2) + Math.pow(depth,2)));
                locations[i][0] = (int) (Math.round(length * Math.cos(angle)) + posX);
                locations[i][1] = (int) (Math.round(length * Math.sin(angle)) + posY);
                i++;
            }
        }
    }

    public ArrayList<Ant> getList(int x, int y, int width, int height) {
        ArrayList<Ant> temp = new ArrayList<Ant>();
        for(int i = 0; i < soldiers.size(); i++) {
            Ant tempAnt = soldiers.get(i);
            if (tempAnt.getX() >= x && tempAnt.getY() >= y && tempAnt.getX() <= width + x && tempAnt.getY() <= height + y) {
                temp.add(tempAnt);
            }
        }
        if (getX() >= x && getY() >= y && getX() <= width + x && getY() <= height + y) {
            temp.add(this);
        }
        return temp;
    }
}
