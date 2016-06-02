import java.io.*;
import java.util.ArrayList;

/**
 * Created by jack on 6/2/16.
 *
 */
public class MapLoader {
    private InputStreamReader reader;
    private BufferedReader bufReader;
    ArrayList<String> file;

    private String fileLoc, mapName;
    private int width, height; //width and height of map


    private int[][] tileType; //stores an integer which represents the tile's type. Anything greater than 0 is a hazard

    public MapLoader(String mapFileName) {
        fileLoc = "./maps/" + mapFileName+".txt";
        try {
            reader = new InputStreamReader(new FileInputStream(new File(fileLoc)), "UTF-8");
            bufReader = new BufferedReader(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        file = new ArrayList<String>();
        loadValues();
    }

    private void loadValues() {
        //load values into an array
        //Line 1: Map Name -
        //Line 2: Map Size – x,y
        //Line 3+: List of anything that isn't dirt (type,x/y)
        boolean read = true;
        try {
            while (read) {
                try {
                    String line = bufReader.readLine();
                    file.add(line);
                } catch (NullPointerException e) {
                    read = false;
                    bufReader.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer converter = new Integer(0); //used to convert strings to integers
        mapName = file.get(0);
        width = converter.getInteger(file.get(1).substring(0,file.get(1).indexOf(','))); //get first number and convert it to an integer
        height = converter.getInteger(file.get(1).substring(file.get(1).indexOf(',') + 1)); //get second number and convert it to an integer
        tileType = new int[height][width];
        for (int i = 2; i < file.size(); i++) {
            int type = converter.getInteger(file.get(1).substring(0,file.get(1).indexOf(',')));
            int x = converter.getInteger(file.get(1).substring(file.get(1).indexOf(',') + 1, file.get(1).indexOf('/')));
            int y = converter.getInteger(file.get(1).substring(file.get(1).indexOf('/') + 1));
            tileType[y][x] = type;
        }
        System.out.println("Map "+mapName+" loaded successfully from "+fileLoc+" with x:"+width+" y:"+height+" and "+(int) (file.size() - 2)+" entries.");
    }

    public String getName() {
        return mapName;
    }

    public int[] getSize() {
        return new int{width,height};
    }

    public int[][] getArray() {
        return tileType;
    }
}
