import org.newdawn.slick.util.pathfinding.AStarHeuristic;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

/**
 * A heuristic that drives the search based on the Manhattan distance
 * between the current location and the target
 *
 * @author Kevin Glass
 */
public class CustomHeuristic implements AStarHeuristic {

    /**
     * Create a new heuristic
     *
     * @param minimumCost The minimum movement cost from any one square to the next
     */
    public CustomHeuristic() {
    }

    /**
     * @see AStarHeuristic#getCost(TileBasedMap, Mover, int, int, int, int)
     */
    public float getCost(TileBasedMap map, Mover mover, int x, int y, int tx, int ty) {
        return (Math.abs(x-tx) + Math.abs(y-ty));
    }

}