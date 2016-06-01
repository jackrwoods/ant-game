import org.newdawn.slick.geom.Rectangle;

/**
 * Created by jack on 6/1/16.
 *
 * DemoAnt class is used to demo pathfinding.
 */
public class DemoAnt extends Ant {

    public DemoAnt(int width, int height, int x, int y, double speed, double dir, Pathfinder path) {
        super(width, height, x, y, speed, dir, path);
        ant = new Rectangle(x, y, 30, 10);
    }

    @Override
    public void levelUp(){}
}
