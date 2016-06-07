import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

/**
 * Created by jack on 6/7/16.
 *
 * //Controls which ants are moveable by the user, and records which ones are selected currently. The former will be implemented later.
 *
 */
public class AntController {

    private Shape sRect; //the unit selection rectangle.
    private ArrayList<Ant> antList;
    private int width, height, x, y;
    private int[] sRectCoords; //0 and 1 are the initial coordinates, and 2 and 3 are the width and height
    private Pathfinder path;
    private GameContainer gc;
    private Input userInput;

    public AntController(int width, int height, GameContainer gc, Pathfinder path) {
        this.gc = gc;
        userInput = gc.getInput();
        userInput.enableKeyRepeat();
        antList = new ArrayList<Ant>();
        this.width = width;
        this.height = height;
        this.path = path;
        sRectCoords = new int[4];
    }

    public void spawnAnt(int x, int y, int type) { //type will be used when more than one ant is implemented
        antList.add(new DemoAnt(width, height, x, y, 2.0, 0.0, path));
    }

    public void tick (GameContainer gc, StateBasedGame sbg, int x, int y) {
        this.x = x;
        this.y = y;
        for (int i = 0; i < antList.size(); i ++) {
            antList.get(i).tick(gc, sbg);
        }

        //create the selection rectangle
        if (userInput.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            if (sRectCoords[0] == -1 && sRectCoords[1] == -1) {
                sRectCoords[0] = userInput.getMouseX();
                sRectCoords[1] = userInput.getMouseY();
            } else if (userInput.getMouseX() != sRectCoords[0] || userInput.getMouseY() != sRectCoords[1]) {
                sRectCoords[2] = userInput.getMouseX() - sRectCoords[0];
                sRectCoords[3] = userInput.getMouseY() - sRectCoords[1];
            }
        } else {
            sRect = null;
            sRectCoords = new int[4];
            sRectCoords[0] = -1;
            sRectCoords[1] = -1;
            sRectCoords[2] = 1;
            sRectCoords[3] = 1;
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, int x, int y) {
        for (int i = 0; i < antList.size(); i ++) {
            antList.get(i).render(gc, sbg, g, x, y);
        }

        //draw sRect, if it is not null
        if (sRectCoords[0] != -1 && sRectCoords[1] != -1) {
            sRect = new Rectangle(sRectCoords[0], sRectCoords[1], sRectCoords[2], sRectCoords[3]);
            g.setColor(Color.green);
            g.draw(sRect);
            g.setColor(Color.white); //default color
        }
    }
    public void click(int x, int y) {
        //ant.move(this.x + x,this.y + y);
    }
}
