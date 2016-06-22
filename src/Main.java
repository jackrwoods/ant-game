import org.newdawn.slick.*;
import org.newdawn.slick.Game;

/**
 * Created by jack on 6/9/16.
 */
public class Main extends ScalableGame {

    public Main(org.newdawn.slick.Game held, int normalWidth, int normalHeight) {
        super(held, normalWidth, normalHeight);
    }

    public Main(Game held, int normalWidth, int normalHeight, boolean maintainAspect) {
        super(held, normalWidth, normalHeight, maintainAspect);
    }

    public static void main(String[] args) throws SlickException {

    }
}
