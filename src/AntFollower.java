import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

/**
 * Created by jack on 6/24/16.
 *
 * The follower ant commanded by the leader ant
 *
 */
public class AntFollower extends Ant {

    private int tick, list, tarX, tarY;
    private Point finalPoint;

    public AntFollower(int x, int y, double speed, double dir, Pathfinder path, int list) { //For efficiency, maybe we can remove the pathfinder reference
        super(x, y, speed, dir, path);
        tick = 0;
        this.list = list;
        controllable = false;
    }

    @Override
    public void tick(GameContainer gc, StateBasedGame sbg) {
        tick++;
        if (tick >= 15 + list * 5) {
            super.tick(gc, sbg);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, int x, int y) { //x and y are the coordinates of the top left of the player's view
        //perform the transformation and draw the ant
        ant = new Rectangle(this.x - x, this.y - y, 5, 2);
        Transform t = Transform.createRotateTransform((float) (dir - Math.PI/2), ant.getCenterX(), ant.getCenterY());
        ant = ant.transform(t); //rotate the ant
        g.setColor(Color.white);
        g.draw(ant);
        g.fill(ant);
        g.setColor(Color.white); //white is the default color
    }

    @Override
    public void stop() {
        super.stop();
        Vector2f direction = new Vector2f((float) this.x, (float) this.y);
        float xDis = tarX - direction.x;
        float yDis = tarY - direction.y;
        dir = Math.atan2(yDis,  xDis) + Math.PI/2; //the direction in radians
    }

    @Override
    public void levelUp() {

    }

    //gives the ant a new target, and begins movement along the Leader's path
    public void move(ArrayList<Point> currentPath, Point finalPoint, int tarX, int tarY) {
        this.finalPoint = finalPoint;
        this.currentPath = currentPath;
        pathLoc = 0;
        updateWayPointCoord();
        tick = 0;
        moving = true;
        this.tarX = tarX;
        this.tarY = tarY;
    }

    @Override
    protected void updateWayPointCoord() {
        if (pathLoc < currentPath.size() - 2) {
            pathLoc++;
            wayPointX = (int) currentPath.get(pathLoc).getX() + 32 / 2;
            wayPointY = (int) currentPath.get(pathLoc).getY() + 32 / 2;
        } else if (pathLoc == currentPath.size() - 2) {
            pathLoc++;
            wayPointX = (int) finalPoint.getX();
            wayPointY = (int) finalPoint.getY();
        } else {
            if (moving) {
                stop();
            }
            pathLoc = 0;
        }
    }
}

