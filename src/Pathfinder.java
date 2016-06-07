import java.util.ArrayList;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.util.pathfinding.AStarHeuristic;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;

//Implements A* pathfinding for objects of type "Unit."
public class Pathfinder {

    private AStarPathFinder pathFinder;
	private ArrayList<Point> path;
    private Map map;
    private AStarHeuristic heuristic;
	
	public Pathfinder(Map map)
	{
        heuristic = new CustomHeuristic();
        pathFinder = new AStarPathFinder(map, 1000000, false, heuristic);
		this.map = map;
		path = new ArrayList<Point>(); //the final path
	}
	
	public void reset() { //resets the lists
		path = new ArrayList<Point>(); //the final path
	}
	
	public ArrayList<Point> findPath(float startX, float startY, float endX, float endY) { //where x and y are the tileType locations of the current tile
        reset();
        Path path = pathFinder.findPath(null, (int)startX, (int)startY, (int)endX, (int)endY);
        int length = path.getLength();
        System.out.println("Found path of length: " + length + ".");

        for (int i = 0; i < length; i++) {
            this.path.add(new Point(path.getX(i) * 32, path.getY(i) * 32));
		}
        return this.path;
	}

    public Map getMap() {
        return map;
    }
}
