import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.pathfinding.TileBasedMap;
import org.newdawn.slick.util.pathfinding.PathFindingContext;

import java.io.*;

import java.util.ArrayList;

//This stores all of the map data (ie: what tile should be rendered where)
public class Map implements TileBasedMap{

	private int width, height; //map width and height, not screen res
	private int[][] tileType; //stores an integer which represents the tile's type. Anything greater than 0 is a hazard
	private String fileName;
	private OutputStreamWriter writer;
	private BufferedWriter bufWriter;
	MapLoader load;
    ArrayList<Tile> renderList;

	public Map(float width, float height, String fileName) { //resolution for rendering, the file name
		this.width = (int) width;
		this.height = (int) height;
		this.fileName = fileName; //stored in the ./maps directory
		load = new MapLoader(fileName);
        tileType = load.getArray();
        createRenderList(); //call this method when the player pans view
	}

    private void createRenderList() {
        renderList = new ArrayList<Tile>();
        int[] mapSize = load.getSize();
        int mapH = mapSize[1], mapW = mapSize[0];
        for (int forX = 0; forX < mapW; forX++) {
            for (int forY = 0; forY < mapH; forY++) {
                if (tileType[forY][forX] != 0)
                {
                    renderList.add(new Tile(forX * 32, forY * 32, tileType[forY][forX]));
                }
            }
        }
    }

	public int getValue(int x, int y) { //returns the value at a square
		return tileType[y][x];
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g, int x, int y) { //x and y are the coordinates of the otop left corner of the player's view
        for (int i = 0; i < renderList.size(); i++) {
            renderList.get(i).render(gc, sbg, g, x, y);
        }
    }

    @Override
    public boolean blocked(PathFindingContext ctx, int x, int y) {
        return tileType[y][x] >= 1; //change to 100
    }

    @Override
    public float getCost(PathFindingContext ctx, int x, int y) {
        return 1.0f;
    }

    @Override
    public int getHeightInTiles() {
        return height;
    }

    @Override
    public int getWidthInTiles() {
        return width;
    }

    @Override
    public void pathFinderVisited(int x, int y) {}

    public void pathTile(int x, int y) {
        renderList.add(new Tile(x,y,2));
    }
}
