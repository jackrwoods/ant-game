import java.util.ArrayList;

import org.newdawn.slick.geom.Point;

//Implements A* pathfinding for objects of type "Unit."
public class Pathfinder {
	
	private ArrayList<Point> path, openList, closedList;
	Point start, target;
	private Map map;
	
	public Pathfinder(Map map)
	{
		this.map = map;
		path = new ArrayList<Point>(); //the final path
		openList = new ArrayList<Point>(); //the "open list" for the A* algorithm
		closedList = new ArrayList<Point>(); //the "closed list" for the A* algorithm
	}
	
	public void reset() { //resets the lists
		path = new ArrayList<Point>(); //the final path
		openList = new ArrayList<Point>(); //the "open list" for the A* algorithm
		closedList = new ArrayList<Point>(); //the "closed list" for the A* algorithm
	}
	
	public ArrayList<Point> findPath(float startX, float startY, float endX, float endY, int movementScore) { //where x and y are the tileType locations of the current tile
		
		start = new Point(startX,startY); //the starting position
		target = new Point(endX,endY); //the target position
		
		if (startX == target.getX() && startY == target.getY()) {
			return path;
		} else {
			//iterates through all 8 possibilities and adds them to the list if they are not obstacles or equal to the starting position
			int lowestScoreTileX = 0, lowestScoreTileY = 0, lowestScore = -1, posX = 0, posY = 0;
			for (int x = -1; x < 2; x++) {
				for (int y = -1; y < 2; y++) {
					posX = (int) (startX + x);
					posY = (int) (startY + y);				
					if (!(map.getValue(posX, posY) > 63) && posX != startX && posY != startY) { //TODO CHANGE //if it is not an obstacle and isn't the starting position, add it to the list
						openList.add(new Point((float) posX, (float) posY));
					} else {
						closedList.add(new Point((float) posX, (float) posY));
					}
				}
			}
			for (int i = 0; i < openList.size(); i++) {
				posX = (int) openList.get(i).getX();
				posY = (int) openList.get(i).getY();
				int distanceScore = (int) (Math.abs(posX - target.getX()) + Math.abs(posY - target.getY())); //the city block distance score, regardless of obstacles
				int movement = movementScore;
				int score = movement + distanceScore;
				if (lowestScore == -1 || score < lowestScore) {
					lowestScore = score;
					lowestScoreTileX = (int) startX;
					lowestScoreTileY = (int) startY;
				}
			}
			path.add(new Point((float) lowestScoreTileX, (float) lowestScoreTileY));
			path.addAll(findPath((float) lowestScoreTileX, (float) lowestScoreTileY, endX, endY, movementScore++));
			return path;
		}
			
	}
}
