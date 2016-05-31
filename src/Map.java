import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;

import java.util.ArrayList;

//This stores all of the map data (ie: what tile should be rendered where)
public class Map {
	
	private int width, height; //map width and height, not screen res
	private int[][] tileType; //stores an integer which represents the tile's type. Anything greater than 0 is a hazard
	private String fileName;
	private InputStream mapInput;
	
	public Map(float width, float height, String fileName) { //resolution for rendering, the file name
		this.width = (int) width;
		this.height = (int) height;
		this.fileName = fileName; //stored in the ./maps directory
		try {
			mapInput = new FileInputStream("./maps/"+fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		loadValues();
	}
	
	private void loadValues() {
		//load values into an array
		try {
			int rowWidthSize = (int) Math.sqrt(mapInput.available());
			width = rowWidthSize;
			height = rowWidthSize;
			for (int x = 0; x < rowWidthSize; x++) {
				for (int y = 0; y < rowWidthSize; y++) {
					tileType[y][x] = mapInput.read();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//create tile objects and load into arrayList
	}
	
	private void saveValues() {
		try {
			OutputStream mapOutput = new FileOutputStream("./maps/"+fileName);
			for (int x = 0; x < tileType.length; x++) {
				for (int y = 0; y < tileType[].length; y++) {
					mapOutput.write(tileType[y][x]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getValue(int x, int y) { //returns the value at a square
		return tileType[y][x];
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g, int x, int y) { //x and y are the coordinates of the otop left corner of the player's view
		ArrayList<Tile> renderList = new ArrayList<Tile>();
		for (int x = 0; x < width; x++)  {
			for (int y = 0; y < height; y++) {
				renderList.add(new Tile(x, y, tileType[y][x]));
			}
		}
		for (int i = 0; i < renderList.size(); i++) {
			renderList.get(i).render(gc, sbg, g, x, y);
		}
	}
