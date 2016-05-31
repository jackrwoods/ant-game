import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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
		try {
			int rowWidthSize = (int) Math.sqrt(mapInput.available());
			for (int x = 0; x < rowWidthSize; x++) {
				for (int y = 0; y < rowWidthSize; y++) {
					tileType[y][x] = mapInput.read();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void saveValues() {
		try {
			int rowWidthSize = (int) Math.sqrt(mapInput.available());
			for (int x = 0; x < rowWidthSize; x++) {
				for (int y = 0; y < rowWidthSize; y++) {
					tileType[y][x] = mapInput.read();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getValue(int x, int y) { //returns the value at a square
		return tileType[y][x];
	}

}
