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
    private ArrayList<int[]> soldiers;
    private double[] dirList;
    private Shape ant;
    private boolean previous;
    private int topX, topY, centerX, centerY;

    public AntLeader(int x, int y, double speed, double dir, Pathfinder path) {
        super(x, y, speed, dir, path);
        ant = new Rectangle(this.x - x, this.y - y, 10, 10);
        soldiers = new ArrayList<int[]>();
        topX = 0;
        topY = 0;
        generateLocations();
        createSoldier();
        dirList = new double[200];
        ant = new Rectangle(this.x - x, this.y - y, 10, 10);
    }

    public AntLeader(int x, int y, double speed, double dir, Pathfinder path, int numSoldiers) {
        super(x, y, speed, dir, path);
        ant = new Rectangle(this.x - x, this.y - y, 10, 10);
        soldiers = new ArrayList<int[]>();
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
        //for (int i = 0; i < 8; i++) {
            //System.out.print(soldiers.get(0)[i]+",");
        //}
        //System.out.print("\n");

        //Tick soldiers
        for (int i = 0; i < soldiers.size(); i++) {
            //Array: x, y, tarX, tarY, hp, atk, def

            //set the ant's target
            soldiers.get(i)[2] = 1;
            soldiers.get(i)[3] = locations[i][0];
            soldiers.get(i)[4] = locations[i][1];



            //if the ant is moving
            if (soldiers.get(i)[2] == 1) {
                Vector2f direction = new Vector2f((float) soldiers.get(i)[0], (float) soldiers.get(i)[1]);
                float xDis = soldiers.get(i)[3] - direction.x;
                float yDis = soldiers.get(i)[4] - direction.y;
                dirList[i] = Math.atan2(yDis, xDis) + Math.PI / 2; //the direction in radians

                //set velocities
                double tempXVel = Math.sin(dirList[i]) * speed - 0.3;
                double tempYVel = -1 * Math.cos(dirList[i]) * speed - 0.3;
                //if the ant is going to miss its target
                //if ((x < wayPointX && y < wayPointY && x + xVel > wayPointX && y + yVel > wayPointY) || (x > wayPointX && y < wayPointY && x + xVel < wayPointX && y + yVel > wayPointY) || (x < wayPointX && y > wayPointY && x + xVel > wayPointX && y + yVel < wayPointY) || (x > wayPointX && y > wayPointY && x + xVel < wayPointX && y + yVel < wayPointY) || (x > wayPointX && y == wayPointY && x + xVel < wayPointX && y + yVel == wayPointY) || (x < wayPointX && y == wayPointY && x + xVel > wayPointX && y + yVel == wayPointY) || (x == wayPointX && y > wayPointY && x + xVel == wayPointX && y + yVel < wayPointY) || (x == wayPointX && y < wayPointY && x + xVel == wayPointX && y + yVel > wayPointY)) {
                if (Math.abs(soldiers.get(i)[0] - soldiers.get(i)[3]) <= 2 && Math.abs(soldiers.get(i)[1] - soldiers.get(i)[4]) <= 2) {
                    soldiers.get(i)[0] = soldiers.get(i)[3];
                    soldiers.get(i)[1] = soldiers.get(i)[4];
                } else {
                    tempXVel = Math.round(tempXVel);
                    tempYVel = Math.round(tempYVel);
                    soldiers.get(i)[0] += tempXVel;
                    soldiers.get(i)[1] += tempYVel;
                }
            }
        }

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, int x, int y) {
        super.render(gc, sbg, g, x, y);
        ant = super.ant;
        for(int i = 0; i < soldiers.size(); i++) {
            Shape tempAnt = new Rectangle(soldiers.get(i)[0] - x, soldiers.get(i)[1] - y, 2, 5);
            Transform temp = Transform.createRotateTransform((float) (dirList[i]), tempAnt.getCenterX(), tempAnt.getCenterY());//- Math.PI/2
            tempAnt = tempAnt.transform(temp); //rotate the ant
            g.setColor(Color.white);
            g.draw(tempAnt);
            g.fill(tempAnt);
            g.setColor(Color.white); //white is the default color
        }
        topX = x;
        topY = y;
        centerX = (int) ant.getCenterX();
        centerY = (int) ant.getCenterY();
    }

    @Override
    public void move(int tarX, int tarY) {
        super.move(tarX, tarY);
        previous = true;
        for (int i = 1; i < soldiers.size(); i++) {
            soldiers.get(i)[2] = 1;
            soldiers.get(i)[3] = soldiers.get(i-1)[0];
            soldiers.get(i)[4] = soldiers.get(i-1)[1];
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
        int[] temp = {locations[soldiers.size()][0], locations[soldiers.size()][1], 0, -1, -1, 100, 10, 0, 0, 0};
        soldiers.add(temp);
    }

    private void generateLocations() {
        locations = new int[200][2];
        int i = 0;
        int posX = (int) (Math.round(100 * Math.cos(dir + Math.PI)) + centerX + topX);
        int posY = (int) (Math.round(100 * Math.sin(dir + Math.PI)) + centerY + topY);

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
        System.out.println("Done.");
    }
}
