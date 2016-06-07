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
    private int x,y;

    public MapController (GameContainer gc) {
        this.gc = gc;
        userInput = gc.getInput();
        userInput.enableKeyRepeat();
        x = 0;
        y = 0;
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

        return new int[] {x,y};
    }

}
