import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameDemoState implements GameState {

    private int width, height;
    private Handler handler;
    private GameContainer gc;

    public GameDemoState(int width, int height, GameContainer gc)
    {
        this.width = width;
        this.height = height;
        this.gc = gc;
        handler = new Handler(width, height, gc);

    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        // TODO implement actual controls. Clicks are used for demo purposes only.
        handler.demoClick(x,y);
        System.exit(0);
    }

    @Override
    public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(int arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(int arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseWheelMoved(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void inputEnded() {
        // TODO Auto-generated method stub

    }

    @Override
    public void inputStarted() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isAcceptingInput() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setInput(Input arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(int arg0, char arg1) {
        // TODO Auto-generated method stub
        System.exit(0);

    }

    @Override
    public void keyReleased(int arg0, char arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void controllerButtonPressed(int arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void controllerButtonReleased(int arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void controllerDownPressed(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void controllerDownReleased(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void controllerLeftPressed(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void controllerLeftReleased(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void controllerRightPressed(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void controllerRightReleased(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void controllerUpPressed(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void controllerUpReleased(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enter(GameContainer arg0, StateBasedGame arg1) throws SlickException {
        // TODO Auto-generated method stub

    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
        // TODO Auto-generated method stub

    }

    @Override
    public void leave(GameContainer arg0, StateBasedGame arg1) throws SlickException {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        // TODO Auto-generated method stub
        handler.render(gc, sbg, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int d) throws SlickException {
        // TODO Auto-generated method stub
        handler.tick(gc, sbg);
    }

}
