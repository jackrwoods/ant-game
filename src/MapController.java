import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

/**
 * Created by jack on 6/7/16.
 *
 * Adjusts the player's view to allow the player to "pan" around the map.
 *
 */
public class MapController {

    private GameContainer gc;
    private Input userInput;
    private int x,y,width,height,oldX,oldY;
    private Map map;
    private AntController antController;

    public MapController (int width, int height, GameContainer gc, Map map, AntController antController) {
        this.gc = gc;
        userInput = gc.getInput();
        userInput.enableKeyRepeat();
        x = 0;
        y = 0;
        oldX = 0;
        oldY = 0;
        this.map = map;
        this.width = width;
        this.height = height;
        this.antController = antController;
    }

    //returns the new x,y of the top left of the player's screen.
    public int[] tick() {
        if(userInput.isKeyDown(Input.KEY_W)) {
            y -= 15;
        }
        if(userInput.isKeyDown(Input.KEY_A)) {
            x -= 15;
        }
        if(userInput.isKeyDown(Input.KEY_S)) {
            y += 15;
        }
        if(userInput.isKeyDown(Input.KEY_D)) {
            x += 15;
        }
        if (x != oldX || y != oldY) {
            map.createRenderList(x,y);
            antController.createRenderList(x,y);
            oldX = x;
            oldY = y;
        }
        return new int[] {x,y};
    }

}
