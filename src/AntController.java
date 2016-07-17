import org.lwjgl.Sys;
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
 * TODO: ADD ANT RENDERLIST (so only ants on screen are rendered, ADD ANT SELECTION
 *
 */
public class AntController {

    private Shape sRect; //the unit selection rectangle.
    private ArrayList<Ant> antList, renderList, selectList;
    private int width, height, x, y;
    private int[] sRectCoords; //0 and 1 are the initial coordinates, and 2 and 3 are the width and height
    private Pathfinder path;
    private GameContainer gc;
    private Input userInput;
    private boolean select;

    public AntController(int width, int height, GameContainer gc, Pathfinder path) {
        this.gc = gc;
        userInput = gc.getInput();
        userInput.enableKeyRepeat();
        antList = new ArrayList<Ant>();
        this.width = width;
        this.height = height;
        this.path = path;
        sRectCoords = new int[4];
        sRectCoords[0] = -1;
        sRectCoords[1] = -1;
        sRectCoords[2] = 1;
        sRectCoords[3] = 1;
        select = false;
        renderList = new ArrayList<Ant>();
        selectList = new ArrayList<Ant>();
        antList = new ArrayList<Ant>();
        spawnAnt(100, 100, 0);
    }

    public void spawnAnt(int x, int y, int type) { //type will be used when more than one ant is implemented
        antList.add(new AntLeader(x, y, 2.0, 0.0, path, 200));
        //renderList.add(antList.get(antList.size() - 1));
    }

    public void tick (GameContainer gc, StateBasedGame sbg, int x, int y) {
        this.x = x;
        this.y = y;
        for (int i = 0; i < antList.size(); i ++) {
            antList.get(i).tick(gc, sbg);
        }
        //create the selection rectangle
        if (userInput.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            select = true;
            if (sRectCoords[0] == -1 && sRectCoords[1] == -1) {
                sRectCoords[0] = userInput.getMouseX();
                sRectCoords[1] = userInput.getMouseY();
            } else if (userInput.getMouseX() != sRectCoords[0] || userInput.getMouseY() != sRectCoords[1]) {
                sRectCoords[2] = userInput.getMouseX() - sRectCoords[0];
                sRectCoords[3] = userInput.getMouseY() - sRectCoords[1];
            }
        } else  if (select == true) {
            //if the user releases the mouse, select all ants underneath
            sRect = new Rectangle(sRect.getMinX(), sRect.getMinY(), sRect.getMaxX() - sRect.getMinX(), sRect.getMaxY() - sRect.getMinY());
            checkSelect();
            select = false;
            sRectCoords = new int[4];
            sRectCoords[0] = -1;
            sRectCoords[1] = -1;
            sRectCoords[2] = 1;
            sRectCoords[3] = 1;
            sRect = new Rectangle(sRectCoords[0], sRectCoords[1], sRectCoords[2], sRectCoords[3]);
        }

        //Command only selected ants
        if (userInput.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) {
            for (int i = 0; i < selectList.size(); i++) {
                selectList.get(i).move(userInput.getMouseX() + x, userInput.getMouseY() + y);
            }
        }

        //DEMO SPAWN ANT
        if (userInput.isKeyPressed(Input.KEY_SPACE)) {
            spawnAnt(userInput.getMouseX() + x, userInput.getMouseY() + y, 1);
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g, int x, int y) {
        createRenderList(x, y);
        for (int i = 0; i < renderList.size(); i ++) {
            renderList.get(i).render(gc, sbg, g, x, y);
        }

        //draw sRect, if it is not null
        if (sRectCoords[0] != -1 && sRectCoords[1] != -1) {
            sRect = new Rectangle(sRectCoords[0], sRectCoords[1], sRectCoords[2], sRectCoords[3]);
            g.setColor(Color.green);
            g.draw(sRect);
            g.setColor(Color.white); //default color
        }
    }
    public void checkSelect() {
        selectList = new ArrayList<Ant>();
        for (int i = 0; i < renderList.size(); i++) {
            if (sRect.contains(renderList.get(i).getShape()) && renderList.get(i).controllable()) {
                selectList.add(renderList.get(i));
            }
        }
    }

    //To save resources, only tiles that are currently on-screen are rendered
    public void createRenderList (int x, int y) {
        renderList = new ArrayList<Ant>();
        for (int i = 0; i < antList.size(); i++) {
            Ant temp = antList.get(i);
            renderList.addAll(temp.getList(x, y, width, height));
        }
    }
}
