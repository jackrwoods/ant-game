import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class GameMenuState implements GameState {
	
	private int width, height, midX, midY;
	private Image background, button1, button2, button3, button4, button1MO, button2MO, button3MO, button4MO;
    private double scale;
    private Input input;
    private GameContainer gc;
    private ArrayList<Image> buttons;

	public GameMenuState(int width, int height, GameContainer gc)
	{
		this.width = width;
		this.height = height;
        buttons = new ArrayList<Image>();
		try {
			background = new Image("misc/images/menu_1920.png");
			button1 = new Image("misc/images/button1_small.png");
            buttons.add(button1);
			button2 = new Image("misc/images/button2_small.png");
            buttons.add(button2);
			button3 = new Image("misc/images/button3_small.png");
            buttons.add(button3);
			button4 = new Image("misc/images/button4_small.png");
            buttons.add(button4);

            button1MO = new Image("misc/images/button1_small_mouse.png");
            buttons.add(button1MO);
            button2MO = new Image("misc/images/button2_small_mouse.png");
            buttons.add(button2MO);
            button3MO = new Image("misc/images/button3_small_mouse.png");
            buttons.add(button3MO);
            button4MO = new Image("misc/images/button4_small_mouse.png");
            buttons.add(button4MO);
		} catch (SlickException e) {
			System.out.println("Menu image file not found.");
			e.printStackTrace();
		}
        scale = width / 1920;
        midX = width/2;
        midY = height/2;
        this.gc = gc;
        input = gc.getInput();
	}
	
	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
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
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		// TODO Auto-generated method stub
        background.draw(0,0);

        int mouseOver = mouseOverButton();
        for (int i = 0; i < buttons.size()/2; i++) {
            if (mouseOver != i) {
                buttons.get(i).draw(midX - buttons.get(i).getWidth()/2, midY + 100 * i);
            } else {
                buttons.get(i+4).draw(midX - buttons.get(i).getWidth()/2, midY + 100 * i);
            }
        }
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
		// TODO Auto-generated method stub
        int button = mouseOverButton();
        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON) && button != -1) {
            switch (button) {
                case 0:
                    sbg.enterState(1);
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    System.exit(0);
                default:
                    break;
            }
        }

	}

    private int mouseOverButton() {
        for (int i = 0; i < buttons.size()/2; i++) {
            Image tempButton = buttons.get(i);
            if (input.getMouseX() > midX - tempButton.getWidth()/2 && input.getMouseY() > midY + 100 * i && input.getMouseX() < midX + tempButton.getWidth()/2 && input.getMouseY() < midY + 100 * i + tempButton.getHeight()) {
                return i;
            }
        }
        return -1;
    }

	//TODO: implement different backgrounds for different menu screens, switch between them with a method

}
