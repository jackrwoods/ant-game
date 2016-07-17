import java.io.*;
import java.util.ArrayList;

/**
 * Created by jack on 6/2/16.
 *
 */
public class MapWriter {
    private OutputStreamWriter writer;
    private BufferedWriter bufWriter;
    private File file;

    private String fileLoc, mapName;
    private int width, height; //width and height of map


    private int[][] tileType; //stores an integer which represents the tile's type. Anything greater than 0 is a hazard

    public MapWriter(String mapFileName, String mapName, int[][] tileType) {
        this.tileType = tileType;
        fileLoc = "./maps/" + mapFileName+".txt";
        file = new File(fileLoc);
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("New map file created successfully at " + fileLoc + ".");
            }
            writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            bufWriter = new BufferedWriter(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.mapName = mapName;
    }

    public void saveValues() {
        //load values into an array
        //Line 1: Map Name -
        //Line 2: Map Size – x,y
        //Line 3+: List of anything that isn't dirt (type,x/y)
        int count = 0;
        try {
            String writeData = "";
            for (int y = 0; y < tileType.length; y++) {
                for (int x = 0; x < tileType[0].length; x++) {
                    if (tileType[y][x] != 0) {
                        writeData+= tileType[y][x]+","+x+"/"+y+"\n";
                        count++;
                    }
                }
            }
            bufWriter.write(mapName+"\n"+tileType.length+","+tileType[0].length+"\n"+writeData);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if(bufWriter!=null)
                    bufWriter.close();
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter"+ex);
            }
        }
        System.out.println("Map "+mapName+" written successfully to "+fileLoc+" with x:"+tileType[0].length+" y:"+tileType.length+" and "+count+" entries.");
    }
}
