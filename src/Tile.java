import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by jack on 5/31/16.
 *
 * Represents a tile to be rendered on the GameplayStateMap
 *
 * Tiles are 32X32 size
 *
 * TODO: implement tiles from sprites LATER
 *
 */
public class Tile extends GameObject {

    private int x, y, type; //x and y are the coordinates of the tile on the map
    private Shape tile;
    public Tile(int x, int y, int type) {
        super(ID.Tile);
        this.x = x;
        this.y = y;
        this.type = type;
        tile = new Rectangle((float) x, (float) y, 128, 128); //change to 32X32
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, int x, int y) { //x and y are the coordinates of the top left of the player's view
        switch(type)
        {
            case 0:
            {
                g.setColor(Color.darkGray); //TODO: change color to brown using RGB and choose other types/colors for different tiles
            }
            break;
            case 1:
            {
                g.setColor(Color.cyan); //implemented for demo
            }
            break;
        }
        /*if (type == 0) {
            g.setColor(Color.darkGray);
        } else {
            g.setColor(Color.cyan);
        } */
        tile.setLocation((float) this.x - x, (float) this.y - y);
        g.draw(tile);
        g.fill(tile);
    }

    public void tick(GameContainer gc, StateBasedGame sbg) {
        //nothing to tick
    }

    public Shape getShape() {
        return tile;
    }

    public void changeType(int newType) { //allows for clearing of resources
        type = newType;
    }
}
