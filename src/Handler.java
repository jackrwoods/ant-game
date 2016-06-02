import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class Handler {
	private int width, height, x, y; //x and y are the location of the top left corner of the player's screen
	private GameContainer gc;
    private ArrayList<GameObject> objects;
	private Pathfinder path;
    private Map map;
	
	public Handler(int width, int height, GameContainer gc) {
		this.width = width;
		this.height = height;
        x = 0;
        y = 0;
		this.gc = gc;
        objects = new ArrayList<GameObject>();
        map = new Map(width, height, "demo");
        path = new Pathfinder(map);
        Ant ant = new DemoAnt(width, height, 100, 100, 2.0, 0.0, path);
        objects.add(ant);
	}

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        //Render objects on top of background
        Rectangle background = new Rectangle(0,0,width,height);
        g.setColor(Color.darkGray);
        g.draw(background);
        g.fill(background);

        for (int i = 0; i < objects.size(); i ++) {
            objects.get(i).render(gc, sbg, g, x, y);
        }
        map.render(gc, sbg, g, x, y);
    }

    public void tick(GameContainer gc, StateBasedGame sbg) {
        for (int i = 0; i < objects.size(); i ++) {
            objects.get(i).tick(gc, sbg);
        }
    }

    public void demoClick(int x, int y) {

    }

    //TODO: implement later (for panning player's view);
    public void setX() {

    }

    public void setY() {

    }
}
